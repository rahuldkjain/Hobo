package com.hobo.merchant.service;

import com.hobo.merchant.entity.Merchant;
import com.hobo.merchant.entity.MerchantProduct;
import com.hobo.merchant.exceptions.merchantexceptions.MerchantAlreadyExists;
import com.hobo.merchant.exceptions.merchantexceptions.MerchantNotFound;
import com.hobo.merchant.model.MerchantDTO;
import com.hobo.merchant.model.NameAndStockDTO;
import com.hobo.merchant.repository.MerchantProductRepository;
import com.hobo.merchant.repository.MerchantRepository;
import com.hobo.merchant.service.implementation.MerchantServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import static org.junit.matchers.JUnitMatchers.containsString;
import static org.mockito.Mockito.*;

//@RunWith(MockitoJUnitRunner.class)
public class MerchantServiceTest {

    @InjectMocks
    private MerchantServiceImpl merchantService;

    @Mock
    private MerchantRepository merchantRepository;

    @Mock
    private MerchantProductRepository merchantProductRepository;

    @Before
    public void init() {
        //Use this or @RunWith
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateMerchantSuccess() throws MerchantAlreadyExists {
        MerchantDTO merchantDTO = new MerchantDTO(1,"Mudit","hobo","hobo@hobo.com",123456789);
        Merchant merchant = new Merchant(1,"Mudit",5,"hobo","hobo@hobo.com",123456789);
        when(merchantRepository.exists(1)).thenReturn(false);
        when(merchantRepository.save(any(Merchant.class))).thenReturn(merchant);
        MerchantDTO merchantDTO1 = merchantService.createMerchant(merchantDTO);
        verify(merchantRepository,times(1)).save(any(Merchant.class));
        verify(merchantRepository,times(1)).exists(1);
        Assert.assertEquals(merchantDTO.toString(),merchantDTO1.toString());
    }

    @Test
    public void testCreateMerchantFail() {
        MerchantDTO merchantDTO = new MerchantDTO(1,"Mudit","hobo","hobo@hobo.com",123456789);
        Merchant merchant = new Merchant(1,"Mudit",5,"hobo","hobo@hobo.com",123456789);
        when(merchantRepository.exists(1)).thenReturn(true);
        when(merchantRepository.save(any(Merchant.class))).thenReturn(merchant);
        try {
            MerchantDTO merchantDTO1 = merchantService.createMerchant(merchantDTO);
        } catch (MerchantAlreadyExists merchantAlreadyExists) {
            Assert.assertThat(merchantAlreadyExists.getMessage(),containsString("Data already exists"));
        }
        verify(merchantRepository,times(1)).exists(1);
    }

    @Test
    public void testReadMerchantByIdSuccess() throws MerchantNotFound {
        MerchantDTO merchantDTO1 = new MerchantDTO(1,"Mudit","hobo","hobo@hobo.com",123456789);
        Merchant merchant = new Merchant(1,"Mudit",5,"hobo","hobo@hobo.com",123456789);
        when(merchantRepository.exists(1)).thenReturn(true);
        when(merchantRepository.findOne(1)).thenReturn(merchant);
        MerchantDTO merchantDTO = merchantService.readMerchantById(1);
        verify(merchantRepository,times(1)).findOne(1);
        verify(merchantRepository,times(1)).exists(1);
        Assert.assertEquals(merchantDTO1.toString(),merchantDTO.toString());
    }

    @Test
    public void testReadMerchantByIdFail(){
        MerchantDTO merchantDTO1 = new MerchantDTO(1,"Mudit","hobo","hobo@hobo.com",123456789);
        Merchant merchant = new Merchant(1,"Mudit",5,"hobo","hobo@hobo.com",123456789);
        when(merchantRepository.exists(1)).thenReturn(false);
        when(merchantRepository.findOne(1)).thenReturn(merchant);
        try {
            MerchantDTO merchantDTO = merchantService.readMerchantById(1);
        } catch (MerchantNotFound merchantNotFound) {
            Assert.assertThat(merchantNotFound.getMessage(),containsString("Data not found"));
        }
        verify(merchantRepository,times(1)).exists(1);
    }

    @Test
    public void testUpdateMerchantSuccess() throws MerchantNotFound {
        MerchantDTO merchantDTO = new MerchantDTO(1,"Mudit","hobo","hobo@hobo.com",123456789);
        Merchant merchant = new Merchant(1,"Mudit",5,"hobo","hobo@hobo.com",123456789);
        when(merchantRepository.exists(1)).thenReturn(true);
        when(merchantRepository.save(any(Merchant.class))).thenReturn(merchant);
        MerchantDTO merchantDTO1 = merchantService.updateMerchant(merchantDTO);
        verify(merchantRepository,times(1)).save(any(Merchant.class));
        verify(merchantRepository,times(1)).exists(1);
        Assert.assertEquals(merchantDTO.toString(),merchantDTO1.toString());
    }

    @Test
    public void testUpdateMerchantFail() {
        MerchantDTO merchantDTO = new MerchantDTO(1,"Mudit","hobo","hobo@hobo.com",123456789);
        Merchant merchant = new Merchant(1,"Mudit",5,"hobo","hobo@hobo.com",123456789);
        when(merchantRepository.exists(1)).thenReturn(false);
        when(merchantRepository.save(any(Merchant.class))).thenReturn(merchant);
        try {
            MerchantDTO merchantDTO1 = merchantService.createMerchant(merchantDTO);
        } catch (MerchantAlreadyExists merchantAlreadyExists) {
            Assert.assertThat(merchantAlreadyExists.getMessage(),containsString("Data not found"));
        }
        verify(merchantRepository,times(1)).exists(1);
    }

    @Test
    public void testDeleteMerchantSuccess() throws MerchantNotFound {
        MerchantDTO merchantDTO = new MerchantDTO(1,"Mudit","hobo","hobo@hobo.com",123456789);
        Merchant merchant = new Merchant(1,"Mudit",5,"hobo","hobo@hobo.com",123456789);
        when(merchantRepository.exists(1)).thenReturn(true);
        when(merchantRepository.findOne(1)).thenReturn(merchant);
        MerchantDTO merchantDTO1 = merchantService.deleteMerchantById(1);
        verify(merchantRepository,times(1)).delete(any(Merchant.class));
        Assert.assertEquals(merchantDTO1.toString(),merchantDTO.toString());
    }

    @Test
    public void testDeleteMerchantFail() {
        when(merchantRepository.exists(1)).thenReturn(false);
        try {
            merchantService.deleteMerchantById(1);
        } catch (MerchantNotFound merchantNotFound) {
            Assert.assertThat(merchantNotFound.getMessage(),containsString("Data not found"));
        }
        verify(merchantRepository,times(0)).delete(any(Merchant.class));
    }

    @Test
    public void testUpdateMerchantRatingSuccess() throws MerchantNotFound {
        MerchantDTO merchantDTO = new MerchantDTO(1,"Mudit","hobo","hobo@hobo.com",123456789);
        Merchant merchant = new Merchant(1,"Mudit",6,"hobo","hobo@hobo.com",123456789);
        when(merchantRepository.findOne(1)).thenReturn(merchant);
        when(merchantRepository.save(any(Merchant.class))).thenReturn(merchant);
        MerchantDTO merchantDTO1 = merchantService.updateMerchantRating(1,6);
        verify(merchantRepository,times(1)).findOne(1);
        verify(merchantRepository,times(1)).save(any(Merchant.class));
        Assert.assertEquals(merchantDTO.toString(),merchantDTO1.toString());
    }

    @Test
    public void testUpdateMerchantRatingFail() {
        MerchantDTO merchantDTO = new MerchantDTO(1,"Mudit","hobo","hobo@hobo.com",123456789);
        Merchant merchant = new Merchant(1,"Mudit",6,"hobo","hobo@hobo.com",123456789);
        when(merchantRepository.findOne(1)).thenReturn(null);
        when(merchantRepository.save(any(Merchant.class))).thenReturn(merchant);
        try {
            MerchantDTO merchantDTO1 = merchantService.updateMerchantRating(1,6);
        } catch (MerchantNotFound merchantNotFound) {
            Assert.assertThat(merchantNotFound.getMessage(),containsString("Data not found"));
        }
        verify(merchantRepository,times(1)).findOne(1);
        verify(merchantRepository,times(0)).save(any(Merchant.class));
    }

    @Test
    public void testGetNameSuccess() throws MerchantNotFound {
        Merchant merchant = new Merchant(1,"Mudit",6,"hobo","hobo@hobo.com",123456789);
        when(merchantRepository.findOne(1)).thenReturn(merchant);
        String name = merchantService.getName(1);
        verify(merchantRepository,times(1)).findOne(1);
        Assert.assertEquals(name,merchant.getMerchantName());
    }

    @Test
    public void testGetNameFail() {
        when(merchantRepository.findOne(1)).thenReturn(null);
        try {
            merchantService.getName(1);
        } catch (MerchantNotFound merchantNotFound) {
            Assert.assertThat(merchantNotFound.getMessage(),containsString("Data not found"));
        }
        verify(merchantRepository,times(1)).findOne(1);
    }

    @Test
    public void testGetNameAndStockSuccess() throws MerchantNotFound {
        Merchant merchant = new Merchant(1,"Mudit",6,"hobo","hobo@hobo.com",123456789);
        NameAndStockDTO nameAndStockDTO = new NameAndStockDTO("Mudit",10);
        MerchantProduct merchantProduct = new MerchantProduct(1,1,10,10,200,7,10,8.0);
        when(merchantRepository.findOne(1)).thenReturn(merchant);
        when(merchantProductRepository.findByProductIdAndMerchantId(10,1)).thenReturn(merchantProduct);
        NameAndStockDTO nameAndStockDTO1 = merchantService.getNameAndStock(1,10);
        verify(merchantRepository,times(1)).findOne(1);
        verify(merchantProductRepository,times(1)).findByProductIdAndMerchantId(10,1);
        Assert.assertEquals(nameAndStockDTO.toString(),nameAndStockDTO1.toString());
    }

    @Test
    public void testGetNameAndStockFail() {
        Merchant merchant = new Merchant(1,"Mudit",6,"hobo","hobo@hobo.com",123456789);
        MerchantProduct merchantProduct = new MerchantProduct(1,1,10,10,200,7,10,8.0);
        when(merchantRepository.findOne(1)).thenReturn(null);
        when(merchantProductRepository.findByProductIdAndMerchantId(10,1)).thenReturn(merchantProduct);
        try {
            merchantService.getNameAndStock(1,10);
        } catch (MerchantNotFound merchantNotFound) {
            Assert.assertThat(merchantNotFound.getMessage(),containsString("Data not found"));
        }
        verify(merchantRepository,times(1)).findOne(1);
        verify(merchantProductRepository,times(0)).findByProductIdAndMerchantId(10,1);
    }
}
