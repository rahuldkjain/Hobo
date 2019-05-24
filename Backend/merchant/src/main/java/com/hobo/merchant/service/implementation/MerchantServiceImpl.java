package com.hobo.merchant.service.implementation;

import com.hobo.merchant.Exceptions.MerchantExceptions.MerchantAlreadyExists;
import com.hobo.merchant.Exceptions.MerchantExceptions.MerchantNotFound;
import com.hobo.merchant.entity.Merchant;
import com.hobo.merchant.model.MerchantDTO;
import com.hobo.merchant.repository.MerchantRepository;
import com.hobo.merchant.service.MerchantService;
import org.json.simple.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public  class MerchantServiceImpl implements MerchantService {

    @Autowired
    private MerchantRepository merchantRepository;

    @Override
    public MerchantDTO createMerchant(MerchantDTO merchantDTO) throws MerchantAlreadyExists {
        if(merchantRepository.exists(merchantDTO.getMerchantId())){
            JSONObject error = new JSONObject();
            error.put("code", "500");
            error.put("data", "{}");
            error.put("error", "Content Already Exists");
            error.put("message", "Content you are inserting is already present in the database");
            throw new MerchantAlreadyExists(error);
        }

        Merchant merchant=new Merchant();
        BeanUtils.copyProperties(merchantDTO,merchant);
        Merchant merchant1=merchantRepository.save(merchant);

        MerchantDTO merchantDTO1=new MerchantDTO();
        BeanUtils.copyProperties(merchant1,merchantDTO1);
        return merchantDTO1;
    }

    @Override
    public MerchantDTO readMerchantById(Integer merchantId) throws MerchantNotFound {
        if(!merchantRepository.exists(merchantId)){
            JSONObject error = new JSONObject();
            error.put("code", "500");
            error.put("data", "{}");
            error.put("error", "Content not found");
            error.put("message", "Content you are looking for is not present in the database");

            throw new MerchantNotFound(error);
        }
        Merchant merchant=merchantRepository.findOne(merchantId);
        MerchantDTO merchantDTO=new MerchantDTO();
        BeanUtils.copyProperties(merchant,merchantDTO);

        return merchantDTO;
    }

    @Override
    public MerchantDTO updateMerchant(MerchantDTO merchantDTO) throws MerchantNotFound {

        MerchantDTO merchantDTO1=null;

        if(!merchantRepository.exists(merchantDTO.getMerchantId()))
        {
            JSONObject error = new JSONObject();
            error.put("code", "500");
            error.put("data", "{}");
            error.put("error", "Content not found");
            error.put("message", "Content you are looking for is not present in the database");
            throw new MerchantNotFound(error);
        }

        Merchant merchant=new Merchant();
        BeanUtils.copyProperties(merchantDTO,merchant);
        Merchant merchant1=merchantRepository.save(merchant);

        merchantDTO1=new MerchantDTO();
        BeanUtils.copyProperties(merchant1,merchantDTO1);
        return merchantDTO1;
    }

    @Override
    public MerchantDTO deleteMerchantById(Integer merchantId) throws MerchantNotFound {
        MerchantDTO merchantDTO=readMerchantById(merchantId);
        if(merchantDTO!=null){
            Merchant merchant=new Merchant();
            BeanUtils.copyProperties(merchantDTO,merchant);
            merchantRepository.delete(merchant);
        }
        return merchantDTO;
    }

    @Override
    public MerchantDTO updateMerchantRating(Integer merchantId, float merchantRating) throws MerchantNotFound {
        MerchantDTO merchantDTO=readMerchantById(merchantId);
        merchantDTO.setMerchantRating(merchantRating);
        MerchantDTO merchantDTO1=updateMerchant(merchantDTO);
        return merchantDTO1;
    }
}
