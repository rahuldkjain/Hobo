package com.hobo.merchant.service.implementation;


import com.hobo.merchant.entity.JoinedTable;
import com.hobo.merchant.entity.Merchant;
import com.hobo.merchant.model.MerchantDTO;
import com.hobo.merchant.repository.MerchantRepositoryImpl;
import com.hobo.merchant.service.MerchantService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public  class MerchantServiceImpl implements MerchantService {

    @Autowired
    private MerchantRepositoryImpl merchantRepository;

    @Override
    public MerchantDTO createMerchant(MerchantDTO merchantDTO) {
        //checking primary key
        MerchantDTO checkAlreadyExists=readMerchantById(merchantDTO.getMerchantId());
        MerchantDTO merchantDTO1=null;

        if(checkAlreadyExists!=null)
            return merchantDTO1;

        Merchant merchant=new Merchant();
        BeanUtils.copyProperties(merchantDTO,merchant);
        Merchant merchant1=merchantRepository.save(merchant);

        merchantDTO1=new MerchantDTO();
        BeanUtils.copyProperties(merchant1,merchantDTO1);
        return merchantDTO1;
    }

    @Override
    public MerchantDTO readMerchantById(Integer merchantId) {
        MerchantDTO merchantDTO=null;
        Merchant merchant=merchantRepository.findOne(merchantId);
        if(merchant!=null)
        {
            merchantDTO=new MerchantDTO();
            BeanUtils.copyProperties(merchant,merchantDTO);
        }
        System.out.println(merchantDTO);
        return merchantDTO;
    }

    @Override
    public MerchantDTO updateMerchant(MerchantDTO merchantDTO) {
        MerchantDTO checkAlreadyExists=readMerchantById(merchantDTO.getMerchantId());

        MerchantDTO merchantDTO1=null;

        if(checkAlreadyExists==null)
            return merchantDTO1;

        Merchant merchant=new Merchant();
        BeanUtils.copyProperties(merchantDTO,merchant);
        Merchant merchant1=merchantRepository.save(merchant);

        merchantDTO1=new MerchantDTO();
        BeanUtils.copyProperties(merchant1,merchantDTO1);
        return merchantDTO1;
    }

    @Override
    public MerchantDTO deleteMerchantById(Integer merchantId) {
        MerchantDTO merchantDTO=readMerchantById(merchantId);
        if(merchantDTO!=null){
            Merchant merchant=new Merchant();
            BeanUtils.copyProperties(merchantDTO,merchant);
            merchantRepository.delete(merchant);
        }
        return merchantDTO;
    }

    @Override
    public List<JoinedTable> getTopMerchant() {
        List<JoinedTable> topMerchant=merchantRepository.findTopMerchant();
        return topMerchant;
    }
}
