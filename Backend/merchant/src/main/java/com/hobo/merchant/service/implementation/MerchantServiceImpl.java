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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public  class MerchantServiceImpl implements MerchantService {

    private static final Logger logger
            = LoggerFactory.getLogger(MerchantService.class);

    @Autowired
    private MerchantRepository merchantRepository;
    @Autowired
    private MerchantProductRepository merchantProductRepository;

    @Override
    public MerchantDTO createMerchant(MerchantDTO merchantDTO) throws MerchantAlreadyExists {
        logger.info(merchantDTO.toString());
        if(merchantRepository.exists(merchantDTO.getMerchantId())){
            logger.error("Data already exists error in createMerchant");
            throw new MerchantAlreadyExists("Data already exists");
        }

        Merchant merchant=new Merchant();
        BeanUtils.copyProperties(merchantDTO,merchant);
        merchant.setMerchantRating(5);
        Merchant merchant1=merchantRepository.save(merchant);

        MerchantDTO merchantDTO1=new MerchantDTO();
        BeanUtils.copyProperties(merchant1,merchantDTO1);
        logger.info(merchantDTO1.toString());
        return merchantDTO1;
    }

    @Override
    public MerchantDTO readMerchantById(Integer merchantId) throws MerchantNotFound {
        logger.info("MerchantId : {}",merchantId);
        if(!merchantRepository.exists(merchantId)){
            logger.error("Data not found error in readMerchantId");
            throw new MerchantNotFound("Data not found");
        }
        Merchant merchant=merchantRepository.findOne(merchantId);
        MerchantDTO merchantDTO=new MerchantDTO();
        BeanUtils.copyProperties(merchant,merchantDTO);
        logger.info(merchantDTO.toString());
        return merchantDTO;
    }

    @Override
    public MerchantDTO updateMerchant(MerchantDTO merchantDTO) throws MerchantNotFound {
        logger.info(merchantDTO.toString());
        MerchantDTO merchantDTO1=null;

        if(!merchantRepository.exists(merchantDTO.getMerchantId()))
        {
            logger.error("Data not found error in updateMerchant");
            throw new MerchantNotFound("Data not found");
        }

        Merchant merchant=new Merchant();
        BeanUtils.copyProperties(merchantDTO,merchant);
        Merchant merchant1=merchantRepository.save(merchant);

        merchantDTO1=new MerchantDTO();
        BeanUtils.copyProperties(merchant1,merchantDTO1);
        logger.info(merchantDTO1.toString());
        return merchantDTO1;
    }

    @Override
    public MerchantDTO deleteMerchantById(Integer merchantId) throws MerchantNotFound {
        logger.info("merchantId : {}",merchantId);
        MerchantDTO merchantDTO=readMerchantById(merchantId);
        if(merchantDTO!=null){
            Merchant merchant=new Merchant();
            BeanUtils.copyProperties(merchantDTO,merchant);
            merchantRepository.delete(merchant);
        }
        logger.info(merchantDTO.toString());
        return merchantDTO;
    }

    @Override
    public MerchantDTO updateMerchantRating(Integer merchantId, float merchantRating) throws MerchantNotFound {
        logger.info("MerchantId : {} merchantRating : {}",merchantId,merchantRating);
        Merchant merchant = merchantRepository.findOne(merchantId);
        if(merchant == null)
        {
            logger.error("Data not found error in updateMerchantRating");
            throw new MerchantNotFound("Data not found");
        }
        merchant.setMerchantRating(merchantRating);
        merchant = merchantRepository.save(merchant);
        MerchantDTO result = new MerchantDTO();
        BeanUtils.copyProperties(merchant,result);
        logger.info(result.toString());
        return result;
    }

    public String getName(Integer merchantId) throws MerchantNotFound {
        logger.info("MerchantId : {}",merchantId);
        Merchant merchant = merchantRepository.findOne(merchantId);
        if(merchant == null) {
            logger.error("Data not found error in getName");
            throw new MerchantNotFound("Data not found");
        }
        String result = merchant.getMerchantName();
        logger.info(result);
        return result;
    }


    public NameAndStockDTO getNameAndStock(Integer merchantId, Integer productId) throws MerchantNotFound {
        logger.info("MerchantId : {} productId : {}",merchantId,productId);
        Merchant merchant = merchantRepository.findOne(merchantId);
        if(merchant == null) {
            logger.error("Data not found error in getNameAndStock");
            throw new MerchantNotFound("Data not found");
        }
        MerchantProduct merchantProduct = merchantProductRepository.findByProductIdAndMerchantId(productId,merchantId);
        NameAndStockDTO result = new NameAndStockDTO();
        if(merchant !=null)
            result.setName(merchant.getMerchantName());
        if (merchantProduct!=null)
        result.setStock(merchantProduct.getStock());
        logger.info(result.toString());
        return result;
    }

}
