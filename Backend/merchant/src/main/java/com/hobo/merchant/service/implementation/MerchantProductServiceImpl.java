package com.hobo.merchant.service.implementation;

import com.hobo.merchant.entity.Merchant;
import com.hobo.merchant.entity.MerchantProduct;
import com.hobo.merchant.model.MerchantDTO;
import com.hobo.merchant.model.MerchantProductDTO;
import com.hobo.merchant.repository.MerchantProductRepositoryImpl;
import com.hobo.merchant.repository.MerchantRepositoryImpl;
import com.hobo.merchant.service.MerchantProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MerchantProductServiceImpl  implements MerchantProductService {
    @Autowired
    private MerchantProductRepositoryImpl merchantProductRepository;

    @Autowired
    private MerchantRepositoryImpl merchantRepository;

    @Override
    public MerchantProductDTO createMerchantProduct(MerchantProductDTO merchantProductDTO) {

        //checking foreign key constraint
        /*MerchantServiceImpl merchantService=new MerchantServiceImpl();
        System.out.println(merchantService);
        System.out.println(merchantProductDTO);
        MerchantDTO checkAlreadyExists=merchantService.readMerchantById(merchantProductDTO.getMerchantId());
        System.out.println(checkAlreadyExists);

        //MerchantDTO checkAlreadyExists=merchantRepository.findOne(merchantProductDTO.getMerchantId());

        MerchantProductDTO merchantProductDTO1=null;
        if (checkAlreadyExists==null)
            return merchantProductDTO1;*/

        MerchantProductDTO merchantProductDTO1=null;

        MerchantProduct merchantProduct=new MerchantProduct();
        BeanUtils.copyProperties(merchantProductDTO,merchantProduct);
        MerchantProduct merchantProduct1=merchantProductRepository.save(merchantProduct);

        merchantProductDTO1=new MerchantProductDTO();
        BeanUtils.copyProperties(merchantProduct1,merchantProductDTO1);
        return  merchantProductDTO1;
    }

    @Override
    public List<MerchantProduct> readMerchantProductById(Integer merchantId) {
        MerchantProductDTO merchantProductDTO=null;
        List<MerchantProduct> merchantProduct=merchantProductRepository.findByMerchantId(merchantId);
        /*if(merchantProduct!=null){
            merchantProductDTO=new MerchantProductDTO();
            BeanUtils.copyProperties(merchantProduct,merchantProductDTO);
        }
        System.out.println(merchantProductDTO);*/
        return merchantProduct;
    }

    @Override
    public MerchantProductDTO updateMerchantProduct(MerchantProductDTO merchantProductDTO) {
        //System.out.println(merchantProductDTO.getMerchantId());
        //List<MerchantProduct> checkAlreadyExists=readMerchantProductById(merchantProductDTO.getMerchantId());
        MerchantProduct updatedMerchantProduct = new MerchantProduct();
        List<MerchantProduct> merchantProducts = merchantProductRepository.findByMerchantId(merchantProductDTO.getMerchantId());
        for (MerchantProduct merchantProduct: merchantProducts) {
            if(merchantProduct.getProductId() == merchantProductDTO.getProductId()){
               // System.out.println(merchantProduct);
                //System.out.println(merchantProductDTO);
                //updatedMerchantProduct = merchantProduct;
                Integer indexOfObjectToBeUpdated=merchantProduct.getIndexx();
                //System.out.println(indexOfObjectToBeUpdated);
                merchantProductDTO.setIndexx(indexOfObjectToBeUpdated);
                BeanUtils.copyProperties(merchantProductDTO, updatedMerchantProduct);
            }
        }

        //System.out.println(merchantProductDTO);
        merchantProductRepository.save(updatedMerchantProduct);
        return merchantProductDTO;
//        MerchantProductDTO merchantProductDTO1=null;
//
//        if(checkAlreadyExists==null)
//            return  merchantProductDTO1;
//
//        MerchantProduct merchantProduct=new MerchantProduct();
//        BeanUtils.copyProperties(merchantProductDTO,merchantProduct);
//        MerchantProduct merchantProduct1=merchantProductRepository.save(merchantProduct);
//
//        merchantProductDTO1=new MerchantProductDTO();
//        BeanUtils.copyProperties(merchantProduct1,merchantProductDTO1);
//        return merchantProductDTO1;
    }

    @Override
    public MerchantProductDTO deleteMerchantProductById(Integer merchantId, Integer productId) {
        MerchantProductDTO merchantProductDTO=new MerchantProductDTO();
        List<MerchantProduct> merchantProducts = merchantProductRepository.findByMerchantId(merchantId);
        for (MerchantProduct merchantProduct: merchantProducts) {
            if(merchantProduct.getProductId() == productId){
                // System.out.println(merchantProduct);
                //System.out.println(merchantProductDTO);
                //updatedMerchantProduct = merchantProduct;
                Integer indexOfObjectToBeDeleted=merchantProduct.getIndexx();
                MerchantProduct merchantProduct1=merchantProductRepository.findOne(indexOfObjectToBeDeleted);
                merchantProductRepository.delete(indexOfObjectToBeDeleted);
                BeanUtils.copyProperties(merchantProduct1,merchantProductDTO);
                //System.out.println(indexOfObjectToBeUpdated);
            }
        }
        return merchantProductDTO;
    }
}
