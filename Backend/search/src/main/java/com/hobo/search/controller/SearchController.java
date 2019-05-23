package com.hobo.search.controller;

import com.hobo.search.model.ProductDTO;
import com.hobo.search.service.ProductServiceImpl;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    ProductServiceImpl productService;

    public JSONObject getJSONResponse(Object data){
        JSONObject response = new JSONObject();
        response.put("code", "200");
        response.put("data", data);
        response.put("error","");
        response.put("message", "success");

        response.remove(data,"password");

        return response;
    }

    @PostMapping(consumes = {"application/json"})
    public JSONObject saveProduct(@RequestBody ProductDTO productDTO) {
        ProductDTO result = new ProductDTO();
        result = productService.saveProduct(productDTO);
        JSONObject response = getJSONResponse(result);
        response.replace("message", "success", "saving successful");
        return response;
    }

    @GetMapping
    public JSONObject getProduct(@RequestParam String id) {
        JSONObject response = getJSONResponse(productService.getProduct(id));
        response.replace("message", "success", "fetching successful");
        return response;
    }

    @DeleteMapping
    public JSONObject deleteProduct(@RequestParam String id) {
        JSONObject response = getJSONResponse(productService.deleteProduct(id));
        response.replace("message", "success", "fetching successful");
        return response;
    }

    @PutMapping(consumes = {"application/json"})
    public JSONObject putProduct(@RequestBody ProductDTO productDTO) {
        JSONObject response = getJSONResponse(productService.putProduct(productDTO));
        response.replace("message", "success", "updating successful");
        return response;
    }

    @GetMapping(value = "/getall")
    public JSONObject getAllProduct() {
        JSONObject response = getJSONResponse(productService.getAllProduct());
        response.replace("message", "success", "fetching successful");
        return response;

    }

    @GetMapping(value = "/suggestion")
    public JSONObject getQuery(@RequestParam String query) {
        JSONObject response = getJSONResponse(productService.query(query));
        response.replace("message", "success", "fetching successful");
        return response;
    }

}
