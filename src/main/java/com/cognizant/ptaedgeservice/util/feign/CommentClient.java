package com.cognizant.ptaedgeservice.util.feign;


import com.cognizant.ptaedgeservice.model.Comment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "pta-resource-commenting-service")
public interface CommentClient {

        @GetMapping("/comment")
        @ResponseStatus(HttpStatus.OK)
        public List<Comment> getAllComments();

        @PostMapping("/comment")
        public Comment createComment(@RequestBody Comment comment);

//        @GetMapping("/comment/{id}")
//        @ResponseStatus(HttpStatus.OK)
//        public Comment getById(@PathVariable Integer id);

        @GetMapping("/comment/{id}")
        @ResponseStatus(HttpStatus.OK)
        public List< Comment> getCommentByResourceId(@PathVariable Integer id);

        @PutMapping("/comment/{id}")
        @ResponseStatus(HttpStatus.NO_CONTENT)
        public void editComment(@RequestBody Comment comment, @PathVariable Integer id);

        @DeleteMapping("/comment/{id}")
        @ResponseStatus(HttpStatus.NO_CONTENT)
        public void deleteComment( @PathVariable Integer id);
}
