package com.hobo.merchant.service;

import com.hobo.merchant.Exceptions.MerchantExceptions.MerchantNotFound;
import com.hobo.merchant.Exceptions.MerchantProductExceptions.MerchantProductAlreadyExists;
import com.hobo.merchant.Exceptions.MerchantProductExceptions.MerchantProductNotFound;
import com.hobo.merchant.entity.MerchantProduct;
import com.hobo.merchant.model.MerchantProductDTO;
import java.util.List;

public interface MerchantProductService {
    MerchantProductDTO createMerchantProduct(MerchantProductDTO merchantProductDTO)throws MerchantProductAlreadyExists,MerchantNotFound;
    MerchantProductDTO readMerchantProduct(Integer index) throws MerchantProductNotFound;
    MerchantProductDTO updateMerchantProduct(MerchantProductDTO merchantProductDTO) throws MerchantProductNotFound, MerchantNotFound;
    MerchantProductDTO deleteMerchantProductById(Integer index) throws MerchantProductNotFound;

    MerchantProductDTO getTopMerchant(Integer producctId) throws MerchantProductNotFound;
    List<MerchantProduct> getAllMerchants(Integer productId) throws MerchantProductNotFound;
    MerchantProductDTO updateProductRating(Integer index, float productRating) throws MerchantProductNotFound, MerchantNotFound;
    List<MerchantProduct> readMerchantProductById(Integer merchantId) throws MerchantProductNotFound;
    float getProductRating(Integer productId) throws MerchantProductNotFound;

}
