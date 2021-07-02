package com.cognizant.ptaedgeservice.service;

import com.cognizant.ptaedgeservice.model.Comment;
import com.cognizant.ptaedgeservice.model.Resource;
import com.cognizant.ptaedgeservice.util.feign.CommentClient;
import com.cognizant.ptaedgeservice.util.feign.ResourceClient;
import com.cognizant.ptaedgeservice.viewModel.ResourcePlusComments;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RefreshScope
public class ServiceLayer {

    private ResourceClient resourceClient;

    private CommentClient commentClient;

    @Autowired
    public ServiceLayer(ResourceClient rc ,  CommentClient cc ) {
        this.resourceClient = rc;
        this.commentClient = cc;
    }
    public ResourcePlusComments savePost(ResourcePlusComments viewModel) {
        System.out.println("View Model!!!!!!" +viewModel);

        // Persist Resource
        Resource r = new Resource();
        r.setAuthor(viewModel.getAuthor());
        r.setTitle(viewModel.getTitle());
        r.setContent(viewModel.getContent());

        r = resourceClient.createResource(r);
        viewModel.setId(r.getId());

        List<Comment> comments;
        comments = viewModel.getComments();

        comments
                .forEach( (c) ->
                {
                    c.setResourceId((viewModel.getId()));
                    commentClient.createComment(c);
                });

        comments = commentClient.getCommentByResourceId(viewModel.getId());
        viewModel.setComments(comments);

        return viewModel;

    }

    public ResourcePlusComments findResource(Integer id) {
        Resource r = resourceClient.getById(id);
        System.out.println("REsource :"+ r);
        // build a view model out of the album information...
        return buildResourceViewModel(r, id);
    }
    private ResourcePlusComments buildResourceViewModel(Resource r, Integer id) {

        // get all the comments
        List<Comment> comments = commentClient.getCommentByResourceId(id);
        System.out.println("BuildService method    "+comments);
        // instantiate a view model so that I can return it
        ResourcePlusComments resourcePlusComments = new ResourcePlusComments();
        // put all the album information on the album view model
        resourcePlusComments.setId(id);
        resourcePlusComments.setAuthor(r.getAuthor());
        resourcePlusComments.setTitle(r.getTitle());
        resourcePlusComments.setContent(r.getContent());


        // add all the tracks for this album to the view model
        resourcePlusComments.setComments(comments);
        System.out.println(resourcePlusComments);
        return resourcePlusComments;
    }

    public List<ResourcePlusComments> getAllPosts() {
        List <ResourcePlusComments> resourcesPlusCommentsList = new ArrayList<>();

        List<Resource> resources = resourceClient.getAllResources();
        System.out.println("Edge Service Resources got GetAll Posts"+resources);
        for ( Resource r : resources) {
            ResourcePlusComments rp = buildResourceViewModel(r, r.getId());
            resourcesPlusCommentsList.add(rp);
        }
        return resourcesPlusCommentsList;
    }
//    public List<Resource> getAllPosts() {
//        List <ResourcePlusComments> resourcesPlusCommentsList = new ArrayList<>();
//
//        List<Resource> resources = resourceClient.getAllResources();
//        System.out.println("Edge Service Resources got GetAll Posts"+resources);
////        for ( Resource r : resources) {
////            ResourcePlusComments rp = buildResourceViewModel(r, r.getId());
////            resourcesPlusCommentsList.add(rp);
////        }
//        return resources;
//    }

    public void updatePost(ResourcePlusComments viewModel) {
        // Create an Album object from the AlbumViewModel
        //   so that we can update it using the DAO
        Resource r = new Resource();
        r.setId(viewModel.getId());
        r.setTitle(viewModel.getTitle());
        r.setContent(viewModel.getContent());
        r.setAuthor(viewModel.getAuthor());


        // Perform update of resource info
        resourceClient.editResources(r, r.getId());

        // We will not actually update any comments
        // Rather, we will delete all comments associated with the current
        // post(resource) (in database), and then add the comments associated with the
        // new post (in the ResoucePlusComments parameter to this method)

        // Get all tracks from database that are connected to this album
        List<Comment> comments = commentClient.getCommentByResourceId(r.getId());

        // Delete them!
        comments.stream().forEach(comment ->  commentClient.deleteComment(comment.getId()));

        // Now, there are no tracks in the database associated with the album
        // So, we need to add all the tracks from the view model (parameter)
        List<Comment> updatedComments = viewModel.getComments();
        updatedComments.stream()
                .forEach(c ->
                {
                    c.setResourceId(viewModel.getId());
                    commentClient.createComment(c);
                });
    }

    public void removePost(int id) {

        // Remove all associated tracks first
        List<Comment> comments = commentClient.getCommentByResourceId(id);

        comments.stream()
                .forEach(comment -> commentClient.deleteComment (comment.getId()));

        // Remove album
        resourceClient.deleteResources(id);

    }

}
