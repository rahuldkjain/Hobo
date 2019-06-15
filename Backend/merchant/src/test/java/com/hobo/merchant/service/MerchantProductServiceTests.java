package com.hobo.merchant.service;

import com.hobo.merchant.entity.MerchantProduct;
import com.hobo.merchant.exceptions.merchantexceptions.MerchantNotFound;
import com.hobo.merchant.exceptions.merchantproductexceptions.MerchantProductAlreadyExists;
import com.hobo.merchant.exceptions.merchantproductexceptions.MerchantProductNotFound;
import com.hobo.merchant.model.MerchantProductDTO;
import com.hobo.merchant.repository.MerchantProductRepository;
import com.hobo.merchant.repository.MerchantRepository;
import com.hobo.merchant.service.implementation.MerchantProductServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.matchers.JUnitMatchers.containsString;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class MerchantProductServiceTests {

    @InjectMocks
    private MerchantProductServiceImpl merchantProductService;

    @Mock
    private MerchantProductRepository merchantProductRepository;

    @Mock
    private MerchantRepository merchantRepository;

    @Before
    public void init() {
        //Use this or @RunWith
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateMerchantProduct() throws MerchantProductAlreadyExists, MerchantNotFound {
        MerchantProductDTO merchantProductDTO= new MerchantProductDTO(1,1,213,10,200,6,10,5);
        MerchantProduct merchantProduct = new MerchantProduct(1,1,213,10,200,6,10,5);
        when(merchantRepository.exists(1)).thenReturn(true);
        when(merchantProductRepository.exists(1)).thenReturn(false);
        when(merchantProductRepository.save(any(MerchantProduct.class))).thenReturn(merchantProduct);
        MerchantProductDTO merchantProductDTO1 = merchantProductService.createMerchantProduct(merchantProductDTO);
        verify(merchantProductRepository,times(1)).save(any(MerchantProduct.class));
        verify(merchantRepository,times(1)).exists(1);
        verify(merchantProductRepository,times(1)).exists(1);
        Assert.assertEquals(merchantProductDTO.toString(),merchantProductDTO1.toString());
    }

    @Test
    public void testCreateMerchantFail1() {
        MerchantProductDTO merchantProductDTO= new MerchantProductDTO(1,1,213,10,200,6,10,8);
        MerchantProduct merchantProduct = new MerchantProduct(1,1,213,10,200,6,10,8);
        when(merchantRepository.exists(1)).thenReturn(false);
        when(merchantProductRepository.exists(1)).thenReturn(false);
        when(merchantProductRepository.save(any(MerchantProduct.class))).thenReturn(merchantProduct);
        try {
            merchantProductService.createMerchantProduct(merchantProductDTO);
        } catch (MerchantProductAlreadyExists merchantProductAlreadyExists) {
            Assert.assertThat(merchantProductAlreadyExists.getMessage(),containsString("Data already exists"));
        } catch (MerchantNotFound merchantNotFound) {
            Assert.assertThat(merchantNotFound.getMessage(),containsString("Merchant not found"));
        }
        verify(merchantProductRepository,times(0)).save(any(MerchantProduct.class));
        verify(merchantRepository,times(1)).exists(1);
        verify(merchantProductRepository,times(1)).exists(1);
    }

    @Test
    public void testCreateMerchantFail2() {
        MerchantProductDTO merchantProductDTO= new MerchantProductDTO(1,1,213,10,200,6,10,8);
        MerchantProduct merchantProduct = new MerchantProduct(1,1,213,10,200,6,10,8);
        when(merchantRepository.exists(1)).thenReturn(true);
        when(merchantProductRepository.exists(1)).thenReturn(true);
        when(merchantProductRepository.save(any(MerchantProduct.class))).thenReturn(merchantProduct);
        try {
            merchantProductService.createMerchantProduct(merchantProductDTO);
        } catch (MerchantProductAlreadyExists merchantProductAlreadyExists) {
            Assert.assertThat(merchantProductAlreadyExists.getMessage(),containsString("Data already exists"));
        } catch (MerchantNotFound merchantNotFound) {
            Assert.assertThat(merchantNotFound.getMessage(),containsString("Merchant not found"));
        }
        verify(merchantProductRepository,times(0)).save(any(MerchantProduct.class));
        verify(merchantRepository,times(0)).exists(1);
        verify(merchantProductRepository,times(1)).exists(1);
    }

    @Test
    public void testReadMerchantProductSuccess() throws MerchantProductNotFound {
        MerchantProductDTO merchantProductDTO= new MerchantProductDTO(1,1,213,10,200,6,10,8);
        MerchantProduct merchantProduct = new MerchantProduct(1,1,213,10,200,6,10,8);
        when(merchantProductRepository.exists(1)).thenReturn(true);
        when(merchantProductRepository.findOne(1)).thenReturn(merchantProduct);
        MerchantProductDTO merchantProductDTO1 = merchantProductService.readMerchantProduct(1);
        verify(merchantProductRepository,times(1)).exists(1);
        verify(merchantProductRepository,times(1)).findOne(1);
        Assert.assertEquals(merchantProductDTO.toString(),merchantProductDTO1.toString());
    }

    @Test
    public void testReadMerchantProductFail() {
        MerchantProduct merchantProduct = new MerchantProduct(1,1,213,10,200,6,10,8);
        when(merchantProductRepository.exists(1)).thenReturn(true);
        when(merchantProductRepository.findOne(1)).thenReturn(merchantProduct);
        try {
            merchantProductService.readMerchantProduct(1);
        } catch (MerchantProductNotFound merchantProductNotFound) {
            Assert.assertThat(merchantProductNotFound.getMessage(),containsString("Data not found"));
        }
        verify(merchantProductRepository,times(1)).exists(1);
        verify(merchantProductRepository,times(1)).findOne(1);
    }

    @Test
    public void testUpdateMerchantProduct() throws MerchantProductAlreadyExists, MerchantNotFound, MerchantProductNotFound {
        MerchantProductDTO merchantProductDTO= new MerchantProductDTO(1,1,213,10,200,6,10,8);
        MerchantProduct merchantProduct = new MerchantProduct(1,1,213,10,200,6,10,8);
        when(merchantRepository.exists(1)).thenReturn(true);
        when(merchantProductRepository.exists(1)).thenReturn(true);
        when(merchantProductRepository.findOne(1)).thenReturn(merchantProduct);
        when(merchantProductRepository.save(any(MerchantProduct.class))).thenReturn(merchantProduct);
        MerchantProductDTO merchantProductDTO1 = merchantProductService.updateMerchantProduct(merchantProductDTO);
        verify(merchantProductRepository,times(1)).save(any(MerchantProduct.class));
        verify(merchantRepository,times(1)).exists(1);
        verify(merchantProductRepository,times(1)).exists(1);
        verify(merchantProductRepository,times(1)).findOne(1);
        Assert.assertEquals(merchantProductDTO.toString(),merchantProductDTO1.toString());
    }

    @Test
    public void testUpdateMerchantFail1() {
        MerchantProductDTO merchantProductDTO= new MerchantProductDTO(1,1,213,10,200,6,10,8);
        MerchantProduct merchantProduct = new MerchantProduct(1,1,213,10,200,6,10,8);
        when(merchantRepository.exists(1)).thenReturn(false);
        when(merchantProductRepository.exists(1)).thenReturn(true);
        when(merchantProductRepository.findOne(1)).thenReturn(merchantProduct);
        when(merchantProductRepository.save(any(MerchantProduct.class))).thenReturn(merchantProduct);
        try {
            merchantProductService.updateMerchantProduct(merchantProductDTO);
        } catch (MerchantNotFound merchantNotFound) {
            Assert.assertThat(merchantNotFound.getMessage(),containsString("Merchant not found"));
        } catch (MerchantProductNotFound merchantProductNotFound) {
            Assert.assertThat(merchantProductNotFound.getMessage(),containsString("Data not found"));
        }
        verify(merchantProductRepository,times(0)).save(any(MerchantProduct.class));
        verify(merchantRepository,times(1)).exists(1);
        verify(merchantProductRepository,times(1)).exists(1);
        verify(merchantProductRepository,times(0)).findOne(1);
    }

    @Test
    public void testUpdateMerchantFail2() {
        MerchantProductDTO merchantProductDTO= new MerchantProductDTO(1,1,213,10,200,6,10,8);
        MerchantProduct merchantProduct = new MerchantProduct(1,1,213,10,200,6,10,8);
        when(merchantRepository.exists(1)).thenReturn(true);
        when(merchantProductRepository.exists(1)).thenReturn(false);
        when(merchantProductRepository.findOne(1)).thenReturn(merchantProduct);
        when(merchantProductRepository.save(any(MerchantProduct.class))).thenReturn(merchantProduct);
        try {
            merchantProductService.updateMerchantProduct(merchantProductDTO);
        } catch (MerchantNotFound merchantNotFound) {
            Assert.assertThat(merchantNotFound.getMessage(),containsString("Merchant not found"));
        } catch (MerchantProductNotFound merchantProductNotFound) {
            Assert.assertThat(merchantProductNotFound.getMessage(),containsString("Data not found"));
        }
        verify(merchantProductRepository,times(0)).save(any(MerchantProduct.class));
        verify(merchantRepository,times(0)).exists(1);
        verify(merchantProductRepository,times(1)).exists(1);
        verify(merchantProductRepository,times(0)).findOne(1);
    }

    @Test
    public void testDeleteMerchantProductByIdSuccess() throws MerchantProductNotFound {
        MerchantProductServiceImpl merchantProductService1 = Mockito.spy(merchantProductService);
        MerchantProductDTO merchantProductDTO= new MerchantProductDTO(1,1,213,10,200,6,10,8);
        //when(merchantProductService1.readMerchantProduct(1)).thenReturn(merchantProductDTO);
        Mockito.doReturn(merchantProductDTO).when(merchantProductService1).readMerchantProduct(1);
        MerchantProductDTO merchantProductDTO1 = merchantProductService1.deleteMerchantProductById(1);
        verify(merchantProductService1,times(1)).readMerchantProduct(1);
        Assert.assertEquals(merchantProductDTO,merchantProductDTO1);
    }

    @Test
    public void testDeleteMerchantProductByIdFail(){
        MerchantProductServiceImpl merchantProductService1 = Mockito.spy(merchantProductService);
        MerchantProductDTO merchantProductDTO= new MerchantProductDTO(1,1,213,10,200,6,10,8);
        try {
            Mockito.doThrow(new MerchantProductNotFound("Data not found")).when(merchantProductService1).readMerchantProduct(1);
            merchantProductService1.deleteMerchantProductById(1);
            verify(merchantProductService1,times(1)).readMerchantProduct(1);
        } catch (MerchantProductNotFound merchantProductNotFound) {
            Assert.assertThat(merchantProductNotFound.getMessage(),containsString("Data not found"));
        }
    }

    @Test
    public void testGetTopMerchantSuccess() throws MerchantProductNotFound {
        MerchantProductDTO merchantProductDTO= new MerchantProductDTO(1,1,213,8,200,6,10,8);
        MerchantProduct merchantProduct = new MerchantProduct(1,1,213,10,200,6,10,8);
        List<MerchantProduct> merchantProducts = new ArrayList<>();
        merchantProducts.add(merchantProduct);
        merchantProduct = new MerchantProduct(1,1,213,10,200,6,10,5);
        merchantProducts.add(merchantProduct);
        when(merchantProductRepository.findByProductId(213)).thenReturn(merchantProducts);
        MerchantProductDTO merchantProductDTO1 = merchantProductService.getTopMerchant(213);
        verify(merchantProductRepository,times(1)).findByProductId(213);
        Assert.assertEquals(merchantProductDTO.toString(),merchantProductDTO1.toString());
    }

    @Test
    public void testGetTopMerchantFail() {
        List<MerchantProduct> merchantProducts = new ArrayList<>();when(merchantProductRepository.findByProductId(213)).thenReturn(merchantProducts);
        try {
                merchantProductService.getTopMerchant(213);
        } catch (MerchantProductNotFound merchantProductNotFound) {
            Assert.assertThat(merchantProductNotFound.getMessage(),containsString("Data not found"));
        }
        verify(merchantProductRepository,times(1)).findByProductId(213);
    }

    @Test
    public void testGetAllMerchantsSuccess() throws MerchantProductNotFound {
        List<MerchantProduct> merchantProducts = new ArrayList<>();
        List<MerchantProductDTO> merchantProductDTOS = new ArrayList<>();
        MerchantProductDTO merchantProductDTO= new MerchantProductDTO(1,1,213,8,200,6,10,8);
        merchantProductDTOS.add(merchantProductDTO);
        merchantProductDTO= new MerchantProductDTO(1,1,213,8,200,6,10,7);
        merchantProductDTOS.add(merchantProductDTO);

        MerchantProduct merchantProduct = new MerchantProduct(1,1,213,10,200,6,10,8);
        merchantProducts.add(merchantProduct);
        merchantProduct = new MerchantProduct(1,1,213,10,200,6,10,7);
        merchantProducts.add(merchantProduct);

        when(merchantProductRepository.findByProductIdOrderByMerchantScoreDesc(213)).thenReturn(merchantProducts);
        List<MerchantProductDTO> merchantProductDTOS1= merchantProductService.getAllMerchants(213);
        verify(merchantProductRepository,times(1)).findByProductIdOrderByMerchantScoreDesc(213);
        Assert.assertEquals(merchantProductDTOS.toString(),merchantProductDTOS1.toString());
    }

    @Test
    public void testGetAllMerchantsFail() {
        when(merchantProductRepository.findByProductIdOrderByMerchantScoreDesc(213)).thenReturn(null);
        try {
            merchantProductService.getAllMerchants(213);
        } catch (MerchantProductNotFound merchantProductNotFound) {
            Assert.assertThat(merchantProductNotFound.getMessage(),containsString("Data not found"));
        }
        verify(merchantProductRepository,times(1)).findByProductIdOrderByMerchantScoreDesc(213);
    }

    @Test
    public void testUpdateProductRatingSuccess() throws MerchantProductNotFound, MerchantNotFound {
        MerchantProductServiceImpl merchantProductService1 = Mockito.spy(merchantProductService);
        MerchantProductDTO merchantProductDTO= new MerchantProductDTO(1,1,213,10,200,6,10,8);
        MerchantProductDTO merchantProductDTO1= new MerchantProductDTO(1,1,213,10,200,8,10,8);
        Mockito.doReturn(merchantProductDTO).when(merchantProductService1).readMerchantProduct(1);
        Mockito.doReturn(merchantProductDTO1).when(merchantProductService1).updateMerchantProduct(merchantProductDTO);
        when(merchantProductRepository.exists(1)).thenReturn(true);
        MerchantProductDTO merchantProductDTO2 = merchantProductService1.updateProductRating(1,8);
        verify(merchantProductRepository,times(1)).exists(1);
        verify(merchantProductService1,times(1)).readMerchantProduct(1);
        verify(merchantProductService1,times(1)).updateMerchantProduct(any(MerchantProductDTO.class));
        Assert.assertEquals(merchantProductDTO1,merchantProductDTO2);
    }

    @Test
    public void testUpdateProductRatingFail() {
        when(merchantProductRepository.exists(1)).thenReturn(false);
        try {
            merchantProductService.updateProductRating(1,8);
        } catch (MerchantProductNotFound merchantProductNotFound) {
            Assert.assertThat(merchantProductNotFound.getMessage(),containsString("Data not found"));
        } catch (MerchantNotFound merchantNotFound) {
            Assert.assertThat(merchantNotFound.getMessage(),containsString("Merchant not found"));
        }
        verify(merchantProductRepository,times(1)).exists(1);
    }

}
