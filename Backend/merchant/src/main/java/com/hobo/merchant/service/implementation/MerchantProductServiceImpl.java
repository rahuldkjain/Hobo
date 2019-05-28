package com.hobo.merchant.service.implementation;

import com.hobo.merchant.exceptions.merchantexceptions.MerchantNotFound;
import com.hobo.merchant.exceptions.merchantproductexceptions.MerchantProductAlreadyExists;
import com.hobo.merchant.exceptions.merchantproductexceptions.MerchantProductNotFound;
import com.hobo.merchant.entity.Merchant;
import com.hobo.merchant.entity.MerchantProduct;
import com.hobo.merchant.model.MerchantProductDTO;
import com.hobo.merchant.repository.MerchantProductRepository;
import com.hobo.merchant.repository.MerchantRepository;
import com.hobo.merchant.service.MerchantProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
public class MerchantProductServiceImpl  implements MerchantProductService {
    @Autowired
    private MerchantProductRepository merchantProductRepository;

    @Autowired
    private MerchantRepository merchantRepository;

    @Override
    @Transactional(readOnly = false)
    public MerchantProductDTO createMerchantProduct(MerchantProductDTO merchantProductDTO) throws MerchantProductAlreadyExists, MerchantNotFound {

        if(merchantProductRepository.exists(merchantProductDTO.getIndexx())){
            throw new MerchantProductAlreadyExists("Data already exists");
        }

        if (!merchantRepository.exists(merchantProductDTO.getMerchantId())){
            throw new MerchantNotFound("Merchant not found");
        }
        MerchantProductDTO merchantProductDTO1=null;
        MerchantProduct merchantProduct=new MerchantProduct();
        BeanUtils.copyProperties(merchantProductDTO,merchantProduct);
        merchantProduct.setMerchantScore(5);
        merchantProduct.setProductRating(5);
        merchantProduct.setProductsSold(0);
        MerchantProduct merchantProduct1=merchantProductRepository.save(merchantProduct);

        merchantProductDTO1=new MerchantProductDTO();
        BeanUtils.copyProperties(merchantProduct1,merchantProductDTO1);
        return  merchantProductDTO1;
    }

    @Override
    public MerchantProductDTO readMerchantProduct(Integer index) throws MerchantProductNotFound{
        if(!merchantProductRepository.exists(index)){
            throw new MerchantProductNotFound("Data not found");
        }
        MerchantProduct merchantProduct= merchantProductRepository.findOne(index);
        MerchantProductDTO merchantProductDTO=new MerchantProductDTO();
        BeanUtils.copyProperties(merchantProduct,merchantProductDTO);
        return merchantProductDTO;
    }

    @Override
    @Transactional(readOnly = false)
    public MerchantProductDTO updateMerchantProduct(MerchantProductDTO merchantProductDTO) throws MerchantProductNotFound, MerchantNotFound{
        if(!merchantProductRepository.exists(merchantProductDTO.getIndexx()))
        {
            throw new MerchantProductNotFound("Data not found");
        }
        if (!merchantRepository.exists(merchantProductDTO.getMerchantId())){
            throw new MerchantNotFound("Merchant not found");
        }
        MerchantProduct merchantProduct =merchantProductRepository.findOne(merchantProductDTO.getIndexx());
        MerchantProduct merchantProduct1=new MerchantProduct();
        BeanUtils.copyProperties(merchantProductDTO,merchantProduct1);
        merchantProductRepository.save(merchantProduct1);
        //UpdateScore
        calculateScore(merchantProduct1.getIndexx());
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

        double merchantScore=0;
        for (MerchantProduct merchantProduct:merchantProducts) {
            if(merchantProduct.getMerchantScore()>=merchantScore){
                merchantScore=merchantProduct.getMerchantScore();
                BeanUtils.copyProperties(merchantProduct,merchantProductDTO);
            }
        }
        if(merchantProductDTO.getIndexx()==0)
        {
            throw new MerchantProductNotFound("Data not found");
        }
        return merchantProductDTO;
    }

    @Override
    public List<MerchantProduct> getAllMerchants(Integer productId) throws MerchantProductNotFound{
    //public Object getAllMerchants(Integer productId) throws MerchantProductNotFound{
        List<MerchantProduct> merchantProducts=merchantProductRepository.findByProductIdOrderByMerchantScoreDesc(productId);
        //Object merchantProducts = merchantProductRepository.test(productId);
        if(merchantProducts==null)
        {
            throw new MerchantProductNotFound("Data not found");
        }

        return merchantProducts;
    }

    @Override
    @Transactional(readOnly = false )
    public MerchantProductDTO updateProductRating(Integer index, float productRating) throws MerchantProductNotFound,MerchantNotFound{
        if(!merchantProductRepository.exists(index))
        {
            throw new MerchantProductNotFound("Data not found");
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
            throw new MerchantProductNotFound("Data not found");
        }
        return merchantProduct;
    }

    @Override
    public float getProductRating(Integer productId) throws MerchantProductNotFound{
        List<MerchantProduct> merchantProducts=merchantProductRepository.findByProductId(productId);
        if(merchantProducts==null){
            throw new MerchantProductNotFound("Data not found");
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

    @Override
    public MerchantProductDTO findByMerchantProductId(Integer merchantId, Integer productId) throws MerchantProductNotFound {
        MerchantProductDTO merchantProductDTO=null;
        List<MerchantProduct> merchantProducts=readMerchantProductById(merchantId);
        for (MerchantProduct merchantProduct:merchantProducts) {
            if (merchantProduct.getProductId()==productId)
            {
                merchantProductDTO =new MerchantProductDTO();
                BeanUtils.copyProperties(merchantProduct,merchantProductDTO);
            }
        }

        return merchantProductDTO;
    }

    @Override
    @Transactional(readOnly = true)
    public void calculateScore(int indexx) {
        MerchantProduct merchantProduct = merchantProductRepository.findOne(indexx);
        double updateScore;
        Integer productSold = merchantProduct.getProductsSold();
        Integer productStock  = merchantProduct.getStock();
        Merchant merchant = merchantRepository.findOne(merchantProduct.getMerchantId());
        float merchantRating = merchant.getMerchantRating();
        float price = merchantProduct.getPrice();
        float productRating = merchantProduct.getProductRating();

        updateScore = 0.25*productSold+0.1*productStock+0.2*merchantRating+0.2*productRating-0.25*price;
        merchantProduct.setMerchantScore(updateScore);
        merchantProductRepository.save(merchantProduct);
    }

    @Override
    public String checkQuantity(int id, int qty, int prodid) {
        MerchantProduct merchantProduct = merchantProductRepository.findByProductIdAndMerchantId(prodid,id);
        if(qty > (merchantProduct.getStock()*0.8)) {
            return null;
        }
        else {
            Merchant merchant = merchantRepository.findOne(id);
            return merchant.getMerchantName();
        }
    }
}
