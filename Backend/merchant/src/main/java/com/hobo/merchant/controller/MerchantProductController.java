package com.hobo.merchant.controller;

import com.hobo.merchant.entity.MerchantProduct;
import com.hobo.merchant.model.MerchantDTO;
import com.hobo.merchant.model.MerchantProductDTO;
import com.hobo.merchant.service.MerchantProductService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/merchantproduct")
public class MerchantProductController {
    @Autowired
    MerchantProductService merchantProductService;

    @PostMapping(consumes = {"application/json"})
    public JSONObject create(@RequestBody MerchantProductDTO merchantProductDTO){
        try {
           // System.out.println("in controller" + merchantProductDTO);
            MerchantProductDTO merchantProductDTO1=merchantProductService.createMerchantProduct(merchantProductDTO);
            System.out.println(merchantProductDTO1);
            JSONObject response=getJSONResponse(merchantProductDTO1);
            return response;
        }
        catch (RuntimeException e){
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("")
    public JSONObject read(@RequestParam Integer id){
        try {
            List<MerchantProduct> merchantProducts=merchantProductService.readMerchantProductById(id);
            JSONObject response=getJSONResponse(merchantProducts);
            return response;
        }
        catch (RuntimeException e){
            e.printStackTrace();
        }
        return null;
    }

    @PutMapping("")
    public JSONObject update(@RequestBody MerchantProductDTO merchantProductDTO){
        try {
            MerchantProductDTO merchantProductDTO1= merchantProductService.updateMerchantProduct(merchantProductDTO);
            JSONObject response=getJSONResponse(merchantProductDTO1);
            return response;
        }
        catch (RuntimeException e){
            e.printStackTrace();
        }
        return null;
    }

    @DeleteMapping("")
    public JSONObject delete(@RequestParam Integer merchantId, @RequestParam Integer productId){
        try {
            MerchantProductDTO merchantProductDTO= merchantProductService.deleteMerchantProductById(merchantId,productId);
            JSONObject response=getJSONResponse(merchantProductDTO);
            return response;
        }
        catch (RuntimeException e){
            e.printStackTrace();
        }
        return null;
    }
    public JSONObject getJSONResponse(Object data){
        JSONObject response = new JSONObject();
        response.put("code", "200");
        response.put("data", data);
        response.put("error","");
        response.put("message", "success");
        return response;
    }
}

