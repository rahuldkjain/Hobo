package com.hobo.merchant.controller;

import com.hobo.merchant.entity.Merchant;
import com.hobo.merchant.model.MerchantDTO;
import com.hobo.merchant.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/merchant")
public class MerchantController{

    @Autowired
    private MerchantService merchantService;

    @PostMapping(value = "/create", consumes = {"application/json"})
    public MerchantDTO create(@RequestBody MerchantDTO merchantDTO){
        try {
            return merchantService.createMerchant(merchantDTO);
        }
        catch (RuntimeException e){
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/read")
    public MerchantDTO read(@RequestParam Integer id){
        try {
            return merchantService.readMerchantById(id);
        }
        catch (RuntimeException e){
            e.printStackTrace();
        }
        return null;
    }

    @PutMapping("/update")
    public MerchantDTO update(@RequestBody MerchantDTO merchantDTO){
        try {
            return merchantService.updateMerchant(merchantDTO);
        }
        catch (RuntimeException e){
            e.printStackTrace();
        }
        return null;
    }
    @DeleteMapping("/delete")
    public MerchantDTO delete(@RequestParam Integer id){
        try {
            return merchantService.deleteMerchantById(id);
        }
        catch (RuntimeException e){
            e.printStackTrace();
        }
        return null;
    }

}
