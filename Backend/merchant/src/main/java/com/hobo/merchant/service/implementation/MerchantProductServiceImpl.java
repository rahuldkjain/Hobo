package com.hobo.merchant.service.implementation;

import com.hobo.merchant.Exceptions.MerchantExceptions.MerchantAlreadyExists;
import com.hobo.merchant.Exceptions.MerchantExceptions.MerchantNotFound;
import com.hobo.merchant.Exceptions.MerchantProductExceptions.MerchantProductAlreadyExists;
import com.hobo.merchant.Exceptions.MerchantProductExceptions.MerchantProductNotFound;
import com.hobo.merchant.entity.MerchantProduct;
import com.hobo.merchant.model.MerchantProductDTO;
import com.hobo.merchant.repository.MerchantProductRepository;
import com.hobo.merchant.repository.MerchantRepository;
import com.hobo.merchant.service.MerchantProductService;
import org.json.simple.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MerchantProductServiceImpl  implements MerchantProductService {
    @Autowired
    private MerchantProductRepository merchantProductRepository;

    @Autowired MerchantRepository merchantRepository;

    @Override
    public MerchantProductDTO createMerchantProduct(MerchantProductDTO merchantProductDTO) throws MerchantProductAlreadyExists, MerchantNotFound {

        if(merchantProductRepository.exists(merchantProductDTO.getIndexx())){
            JSONObject error = new JSONObject();
            error.put("code", "500");
            error.put("data", "{}");
            error.put("error", "Content Already Exists");
            error.put("message", "Content you are inserting is already present in the database");
            throw new MerchantProductAlreadyExists(error);
        }

        if (!merchantRepository.exists(merchantProductDTO.getMerchantId())){
            JSONObject error = new JSONObject();
            error.put("code", "500");
            error.put("data", "{}");
            error.put("error", "Content not found");
            error.put("message", "Content you are looking for is not present in the database");
            throw new MerchantNotFound(error);
        }
        MerchantProductDTO merchantProductDTO1=null;
        MerchantProduct merchantProduct=new MerchantProduct();
        BeanUtils.copyProperties(merchantProductDTO,merchantProduct);
        MerchantProduct merchantProduct1=merchantProductRepository.save(merchantProduct);

        merchantProductDTO1=new MerchantProductDTO();
        BeanUtils.copyProperties(merchantProduct1,merchantProductDTO1);
        return  merchantProductDTO1;
    }

    @Override
    public MerchantProductDTO readMerchantProduct(Integer index) throws MerchantProductNotFound{
        if(!merchantProductRepository.exists(index)){
            JSONObject error = new JSONObject();
            error.put("code", "500");
            error.put("data", "{}");
            error.put("error", "Content not found");
            error.put("message", "Content you are looking for is not present in the database");
            throw new MerchantProductNotFound(error);
        }
        MerchantProduct merchantProduct= merchantProductRepository.findOne(index);
        MerchantProductDTO merchantProductDTO=new MerchantProductDTO();
        BeanUtils.copyProperties(merchantProduct,merchantProductDTO);
        return merchantProductDTO;
    }

    @Override
    public MerchantProductDTO updateMerchantProduct(MerchantProductDTO merchantProductDTO) throws MerchantProductNotFound, MerchantNotFound{
        if(!merchantProductRepository.exists(merchantProductDTO.getIndexx()))
        {
            JSONObject error = new JSONObject();
            error.put("code", "500");
            error.put("data", "{}");
            error.put("error", "Content not found");
            error.put("message", "Content you are looking for is not present in the database");
            throw new MerchantProductNotFound(error);
        }
        if (!merchantRepository.exists(merchantProductDTO.getMerchantId())){
            JSONObject error = new JSONObject();
            error.put("code", "500");
            error.put("data", "{}");
            error.put("error", "Content not found");
            error.put("message", "Content you are looking for is not present in the database");
            throw new MerchantNotFound(error);
        }
        MerchantProduct merchantProduct =merchantProductRepository.findOne(merchantProductDTO.getIndexx());
        MerchantProduct merchantProduct1=new MerchantProduct();
        BeanUtils.copyProperties(merchantProductDTO,merchantProduct1);
        merchantProductRepository.save(merchantProduct1);
        return merchantProductDTO;
    }

    @Override
    public MerchantProductDTO deleteMerchantProductById(Integer index) throws MerchantProductNotFound{
        MerchantProductDTO merchantProductDTO=readMerchantProduct(index);
        if(merchantProductDTO!=null){
            merchantProductRepository.delete(index);
        }
        return merchantProductDTO;
    }

    @Override
    public MerchantProductDTO getTopMerchant(Integer producctId) throws MerchantProductNotFound{
        MerchantProductDTO merchantProductDTO=new MerchantProductDTO();
        List<MerchantProduct> merchantProducts=merchantProductRepository.findByProductId(producctId);

        float merchantScore=0;
        for (MerchantProduct merchantProduct:merchantProducts) {
            if(merchantProduct.getMerchantScore()>=merchantScore){
                merchantScore=merchantProduct.getMerchantScore();
                BeanUtils.copyProperties(merchantProduct,merchantProductDTO);
            }
        }
        if(merchantProductDTO.getIndexx()==0)
        {
            JSONObject error = new JSONObject();
            error.put("code", "500");
            error.put("data", "{}");
            error.put("error", "Content not found");
            error.put("message", "Content you are looking for is not present in the database");
            throw new MerchantProductNotFound(error);
        }
        return merchantProductDTO;
    }

    @Override
    public List<MerchantProduct> getAllMerchants(Integer productId) throws MerchantProductNotFound{
        List<MerchantProduct> merchantProducts=merchantProductRepository.findByProductId(productId);
        if(merchantProducts==null)
        {
            JSONObject error = new JSONObject();
            error.put("code", "500");
            error.put("data", "{}");
            error.put("error", "Content not found");
            error.put("message", "Content you are looking for is not present in the database");
            throw new MerchantProductNotFound(error);
        }

        return merchantProducts;
    }

    @Override
    public MerchantProductDTO updateProductRating(Integer index, float productRating) throws MerchantProductNotFound,MerchantNotFound{
        if(!merchantProductRepository.exists(index))
        {
            JSONObject error = new JSONObject();
            error.put("code", "500");
            error.put("data", "{}");
            error.put("error", "Content not found");
            error.put("message", "Content you are looking for is not present in the database");
            throw new MerchantProductNotFound(error);
        }
        MerchantProductDTO merchantProductDTO=readMerchantProduct(index);
        merchantProductDTO.setProductRating(productRating);
        MerchantProductDTO merchantProductDTO1=new MerchantProductDTO();
        merchantProductDTO1=updateMerchantProduct(merchantProductDTO);

        return merchantProductDTO1;
    }

    @Override
    public List<MerchantProduct> readMerchantProductById(Integer merchantId) throws MerchantProductNotFound{
        MerchantProductDTO merchantProductDTO=null;
        List<MerchantProduct> merchantProduct=merchantProductRepository.findByMerchantId(merchantId);
        if(merchantProduct==null){
            JSONObject error = new JSONObject();
            error.put("code", "500");
            error.put("data", "{}");
            error.put("error", "Content not found");
            error.put("message", "Content you are looking for is not present in the database");
            throw new MerchantProductNotFound(error);
        }
        return merchantProduct;
    }

    @Override
    public float getProductRating(Integer productId) throws MerchantProductNotFound{
        List<MerchantProduct> merchantProducts=merchantProductRepository.findByProductId(productId);
        if(merchantProducts==null){
            JSONObject error = new JSONObject();
            error.put("code", "500");
            error.put("data", "{}");
            error.put("error", "Content not found");
            error.put("message", "Content you are looking for is not present in the database");
            throw new MerchantProductNotFound(error);
        }
        float totalRating=0;
        float rating=0;
        int noOfItems=0;
        if(merchantProducts!=null) {
            for (MerchantProduct merchantProduct : merchantProducts) {
                rating = merchantProduct.getProductRating() * merchantProduct.getProductsSold() + rating;
                noOfItems = noOfItems + merchantProduct.getProductsSold();
            }
            totalRating = rating / noOfItems;
        }
        return totalRating;
    }
}
