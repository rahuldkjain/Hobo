package com.hobo.merchant.service.implementation;

import com.hobo.merchant.entity.MerchantProduct;
import com.hobo.merchant.exceptions.merchantexceptions.MerchantAlreadyExists;
import com.hobo.merchant.exceptions.merchantexceptions.MerchantNotFound;
import com.hobo.merchant.entity.Merchant;
import com.hobo.merchant.model.MerchantDTO;
import com.hobo.merchant.model.NameAndStockDTO;
import com.hobo.merchant.repository.MerchantProductRepository;
import com.hobo.merchant.repository.MerchantRepository;
import com.hobo.merchant.service.MerchantService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public  class MerchantServiceImpl implements MerchantService {

    @Autowired
    private MerchantRepository merchantRepository;
    @Autowired
    private MerchantProductRepository merchantProductRepository;

    @Override
    public MerchantDTO createMerchant(MerchantDTO merchantDTO) throws MerchantAlreadyExists {
        if(merchantRepository.exists(merchantDTO.getMerchantId())){
            throw new MerchantAlreadyExists("Data already exists");
        }

        Merchant merchant=new Merchant();
        BeanUtils.copyProperties(merchantDTO,merchant);
        merchant.setMerchantRating(5);
        Merchant merchant1=merchantRepository.save(merchant);

        MerchantDTO merchantDTO1=new MerchantDTO();
        BeanUtils.copyProperties(merchant1,merchantDTO1);
        return merchantDTO1;
    }

    @Override
    public MerchantDTO readMerchantById(Integer merchantId) throws MerchantNotFound {
        if(!merchantRepository.exists(merchantId)){
            throw new MerchantNotFound("Data not found");
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
            throw new MerchantNotFound("Data not found");
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
        Merchant merchant = merchantRepository.findOne(merchantId);
        merchant.setMerchantRating(merchantRating);
        merchant = merchantRepository.save(merchant);
        MerchantDTO result = new MerchantDTO();
        BeanUtils.copyProperties(merchant,result);
        return result;
    }

    public String getName(Integer merchantId) {
        Merchant merchant = merchantRepository.findOne(merchantId);
        String result = merchant.getMerchantName();
        return result;
    }


    public NameAndStockDTO getNameAndStock(Integer merchantId, Integer productId) {
        Merchant merchant = merchantRepository.findOne(merchantId);
        MerchantProduct merchantProduct = merchantProductRepository.findByProductIdAndMerchantId(productId,merchantId);
        NameAndStockDTO result = new NameAndStockDTO();
        if(merchant !=null)
            result.setName(merchant.getMerchantName());
        if (merchantProduct!=null)
        result.setStock(merchantProduct.getStock());
        return result;
    }

}
