package com.cognizant.ptaedgeservice.service;

import com.cognizant.ptaedgeservice.model.Comment;
import com.cognizant.ptaedgeservice.model.Resource;
import com.cognizant.ptaedgeservice.util.feign.CommentClient;
import com.cognizant.ptaedgeservice.util.feign.ResourceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RefreshScope
public class ptaMemberService {
    @Autowired
    private ResourceClient resourceClient;
    @Autowired
    private CommentClient commentClient;

    public List<Resource> getAllResources() {
        System.out.println("Service Layer. Getting all the resources!");
        return resourceClient.getAllResources();
    }
    public Resource postAResource(Resource resourceToBeAdded) {
        System.out.println("Service Layer. Posting a Resource!");

        return resourceClient.createResource(resourceToBeAdded);
    }
    public List<Comment> getAllComments() {
        System.out.println("Service Layer. Getting all the comments!");
        return commentClient.getAllComments();
    }
    public Comment postAComment(Comment commentToBeAdded) {
        System.out.println("Service Layer. Posting a Resource!");

        return commentClient.createComment(commentToBeAdded);
    }
}
