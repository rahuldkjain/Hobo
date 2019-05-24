package com.hobo.merchant.service;

import com.hobo.merchant.entity.JoinedTable;
import com.hobo.merchant.entity.MerchantProduct;
import com.hobo.merchant.model.MerchantDTO;
import com.hobo.merchant.model.MerchantProductDTO;

import java.util.List;

public interface MerchantProductService {
    MerchantProductDTO createMerchantProduct(MerchantProductDTO merchantProductDTO);
    MerchantProductDTO readByMerchangAndProductId(Integer merchantId, Integer productId);
    MerchantProductDTO updateMerchantProduct(MerchantProductDTO merchantProductDTO);
    MerchantProductDTO deleteMerchantProductById(Integer merchantId, Integer productId);


    MerchantProductDTO getTopMerchant(Integer producctId);
    List<MerchantProduct> getAllMerchants(Integer productId);
    MerchantProductDTO updateProductRating(Integer productId, Integer merchantId, float productRating);
    List<MerchantProduct> readMerchantProductById(Integer merchantId);
    float getProductRating(Integer productId);

}
