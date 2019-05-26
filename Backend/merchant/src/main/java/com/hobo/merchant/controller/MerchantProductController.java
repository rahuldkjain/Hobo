package com.hobo.merchant.controller;

import com.hobo.merchant.exceptions.merchantexceptions.MerchantNotFound;
import com.hobo.merchant.exceptions.merchantproductexceptions.MerchantProductAlreadyExists;
import com.hobo.merchant.exceptions.merchantproductexceptions.MerchantProductNotFound;
import com.hobo.merchant.entity.MerchantProduct;
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
    public JSONObject create(@RequestBody MerchantProductDTO merchantProductDTO) throws MerchantProductAlreadyExists,MerchantNotFound {
        try {
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
    public JSONObject readMerchantProduct(@RequestParam Integer index)throws MerchantProductNotFound {
        try {
            MerchantProductDTO merchantProductDTO=merchantProductService.readMerchantProduct(index);
            JSONObject response=getJSONResponse(merchantProductDTO);
            return response;
        }
        catch (RuntimeException e){
            e.printStackTrace();
        }
        return null;
    }



    @PutMapping("")
    public JSONObject update(@RequestBody MerchantProductDTO merchantProductDTO)throws MerchantProductNotFound,MerchantNotFound{
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
    public JSONObject delete(@RequestParam Integer index) throws MerchantProductNotFound{
        try {
            MerchantProductDTO merchantProductDTO= merchantProductService.deleteMerchantProductById(index);
            JSONObject response=getJSONResponse(merchantProductDTO);
            return response;
        }
        catch (RuntimeException e){
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/gettopproductmerchant")
    public JSONObject getTopMerchant(@RequestParam Integer productId) throws MerchantProductNotFound{
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
    public JSONObject getAllMerchants(@RequestParam Integer productId) throws MerchantProductNotFound{
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
    public JSONObject updateProductRating(@RequestParam Integer index, @RequestParam float productRating) throws MerchantProductNotFound, MerchantNotFound{
        try {
            MerchantProductDTO merchantProductDTO=merchantProductService.updateProductRating(index,productRating);
            JSONObject response=getJSONResponse(merchantProductDTO);
            return response;
        }
        catch (RuntimeException e){
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/getallproduct")
    public JSONObject read(@RequestParam Integer merchantId) throws MerchantProductNotFound{
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
    public JSONObject getProductRating(@RequestParam Integer productId) throws MerchantProductNotFound{
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

    @GetMapping("/getbyids")
    public JSONObject findByMerchantProductId(@RequestParam Integer merchantId, @RequestParam Integer productId) throws MerchantProductNotFound{
        try {
            MerchantProductDTO merchantProductDTO=merchantProductService.findByMerchantProductId(merchantId,productId);
            JSONObject respone=getJSONResponse(merchantProductDTO);
            return respone;
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

