package com.hobo.merchant.controller;

import com.hobo.merchant.entity.JoinedTable;
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
    public JSONObject readByMerchangAndProductId(@RequestParam Integer merchantId, @RequestParam Integer productId){
        try {
            MerchantProductDTO merchantProductDTO=merchantProductService.readByMerchangAndProductId(merchantId,productId);
            JSONObject response=getJSONResponse(merchantProductDTO);
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

    @GetMapping("/gettopproductmerchant")
    public JSONObject getTopMerchant(@RequestParam Integer productId){
        try{
            MerchantProductDTO merchantProductDTO=merchantProductService.getTopMerchant(productId);
            JSONObject response=getJSONResponse(merchantProductDTO);
            return response;
        }
        catch (RuntimeException e){
            e.printStackTrace();
        }
        return  null;
    }


    @GetMapping("/productmerchants")
    public JSONObject getAllMerchants(@RequestParam Integer productId){
        try {
            List<MerchantProduct> merchantProducts=merchantProductService.getAllMerchants(productId);
            JSONObject response=getJSONResponse(merchantProducts);
            return response;
        }
        catch (RuntimeException e){
            e.printStackTrace();
        }
        return null;
    }

    @PutMapping("/updateprodcutrating")
    public JSONObject updateProductRating(@RequestParam Integer productId, @RequestParam Integer merchantId, @RequestParam float productRating){
        try {
            MerchantProductDTO merchantProductDTO=merchantProductService.updateProductRating(productId,merchantId,productRating);
            JSONObject response=getJSONResponse(merchantProductDTO);
            return response;
        }
        catch (RuntimeException e){
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/getallproduct")
    public JSONObject read(@RequestParam Integer merchantId){
        try {
            List<MerchantProduct> merchantProducts=merchantProductService.readMerchantProductById(merchantId);
            JSONObject response=getJSONResponse(merchantProducts);
            return response;
        }
        catch (RuntimeException e){
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/getprodcutrating")
    public JSONObject getProductRating(@RequestParam Integer productId){
        try {
            float productRating=merchantProductService.getProductRating(productId);
            JSONObject response=getJSONResponse(productRating);
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

