package com.cognizant.ptaedgeservice.util.feign;

import com.cognizant.ptaedgeservice.model.Resource;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "pta-resource-sharing-service")
public interface ResourceClient {


    @PostMapping("/resource")
    @ResponseStatus(HttpStatus.CREATED)
    public Resource createResource(@RequestBody Resource resource);

    @GetMapping("/resource")
    @ResponseStatus(HttpStatus.OK)
    public List<Resource> getAllResources();


    @GetMapping("/resource/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Resource getById(@PathVariable Integer id);

    @PutMapping("/resource/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void editResources(@RequestBody Resource resource, @PathVariable Integer id);

    @DeleteMapping("/resource/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteResources( @PathVariable Integer id);

}
