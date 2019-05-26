package com.hobo.merchant.controller;

import com.hobo.merchant.exceptions.merchantexceptions.MerchantAlreadyExists;
import com.hobo.merchant.exceptions.merchantexceptions.MerchantNotFound;
import com.hobo.merchant.model.MerchantDTO;
import com.hobo.merchant.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.json.simple.JSONObject;

@RestController
@RequestMapping(path = "/merchant")
public class MerchantController{

    @Autowired
    private MerchantService merchantService;

    @PostMapping(consumes = {"application/json"})
    public JSONObject create(@RequestBody MerchantDTO merchantDTO) throws MerchantAlreadyExists {
        try {
            MerchantDTO merchantDTO1=merchantService.createMerchant(merchantDTO);
            JSONObject response=getJSONResponse(merchantDTO1);
            return response;
        }
        catch (RuntimeException e){
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping()
    public JSONObject read(@RequestParam Integer id) throws MerchantNotFound {
        try {
            MerchantDTO merchantDTO = merchantService.readMerchantById(id);
            JSONObject response = getJSONResponse(merchantDTO);
            response.replace("message", "success", "Merchant read successful");
            return response;
        }
        catch (RuntimeException e){
            e.printStackTrace();
        }
        return null;
    }

    @PutMapping()
    public JSONObject update(@RequestBody MerchantDTO merchantDTO) throws MerchantNotFound {
        try {
            MerchantDTO merchantDTO1=merchantService.updateMerchant(merchantDTO);
            JSONObject response=getJSONResponse(merchantDTO1);
            return response;
        }
        catch (RuntimeException e){
            e.printStackTrace();
        }
        return null;
    }
    @DeleteMapping()
    public JSONObject delete(@RequestParam Integer id) throws MerchantNotFound {
        try {
            MerchantDTO merchantDTO=merchantService.deleteMerchantById(id);
            JSONObject response=getJSONResponse(merchantDTO);
            return response;
        }
        catch (RuntimeException e){
            e.printStackTrace();
        }
        return null;
    }

    @PutMapping("/updatemerchantrating")
    public JSONObject updateMerchantRating(@RequestParam Integer merchantId, @RequestParam float merchantRating) throws MerchantNotFound {
        try {
            MerchantDTO merchantDTO=merchantService.updateMerchantRating(merchantId,merchantRating);
            JSONObject response=getJSONResponse(merchantDTO);
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
