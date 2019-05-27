package com.hobo.search.service.impl;

import com.hobo.search.entity.Product;
import com.hobo.search.model.ProductDTO;
import com.hobo.search.repository.ProductRepositoryImpl;
import com.hobo.search.service.ProductServiceImpl;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilders;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
public class ProductServiceImplementation implements ProductServiceImpl {

    @Autowired
    private ProductRepositoryImpl productRepository;
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;
    @Autowired
    private TransportClient client;

    @PostConstruct
    public void after() {
        try {
            Settings settings = Settings.settingsBuilder().put("cluster.name", "hobo").build();
            client = TransportClient.builder().settings(settings).build()
                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    @PreDestroy
    public void before() {
        client.close();
    }

    @Override
    public ProductDTO saveProduct(ProductDTO productDTO) {
        Product product = new Product();
        BeanUtils.copyProperties(productDTO, product);
        if( productRepository.exists(product.getProductId())) {
            throw new RuntimeException();
        }
        Product result = productRepository.save(product);
        ProductDTO resultDTO = new ProductDTO();
        BeanUtils.copyProperties(result,resultDTO);
        return resultDTO;
    }

    @Override
    public ProductDTO getProduct(String id) {
        Product result = productRepository.findOne(id);
        ProductDTO resultDTO = new ProductDTO();
        BeanUtils.copyProperties(result, resultDTO);
        return resultDTO;
    }

    @Override
    public ProductDTO deleteProduct(String id) {
        Product product = productRepository.findOne(id);
        productRepository.delete(product);
        ProductDTO result = new ProductDTO();
        BeanUtils.copyProperties(product,result);
        return result;
    }

    @Override
    public ProductDTO putProduct(ProductDTO productDTO) {
        Product product = new Product();
        BeanUtils.copyProperties(productDTO, product);
        Product result = productRepository.save(product);
        ProductDTO resultDTO= new ProductDTO();
        BeanUtils.copyProperties(result,resultDTO);
        return resultDTO;
    }

    @Override
    public List<ProductDTO> getAllProduct() {
        Iterable<Product> productList = productRepository.findAll();
        List<ProductDTO> resultList = new ArrayList<>();

        for ( Product e: productList) {
            ProductDTO productDTO =new ProductDTO();
            BeanUtils.copyProperties(e,productDTO);
            resultList.add(productDTO);
        }
        return resultList;
    }

    @Override
    public JSONArray query(String recievedQuery) throws ParseException {
        String searchQuery="";
        String[] words = recievedQuery.split("\\s+");
        for (int i = 0; i < words.length; i++) {
            words[i] = words[i].replaceAll("[^\\w]", "");
            searchQuery+="*"+words[i]+"* ";
        }
        SearchResponse response = client.prepareSearch().setQuery(QueryBuilders.queryStringQuery(searchQuery)).execute().actionGet();
        JSONParser parser = new JSONParser();
        JSONArray result = (JSONArray)((JSONObject)((JSONObject)parser.parse(response.toString())).get("hits")).get("hits");
        JSONArray resultAray = new JSONArray();
        for (Object object: result) {
            resultAray.add(((JSONObject)object).get("_source"));
        }
        return resultAray;
    }

    @Override
    public List<List<ProductDTO>> result(String query) {

        return null;
    }
}
