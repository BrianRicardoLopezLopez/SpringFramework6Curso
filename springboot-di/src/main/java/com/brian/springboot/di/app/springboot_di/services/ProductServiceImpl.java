package com.brian.springboot.di.app.springboot_di.services;

import com.brian.springboot.di.app.springboot_di.models.Product;
import com.brian.springboot.di.app.springboot_di.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {


    //@Autowired
    private Environment environment;

    @Value("${config.price.tax}")
    private Double tax;

    //@Autowired
    //@Qualifier("productList")
    private ProductRepository repository;

    /// por medio del SETTER pero tenemos que pasar el Autowired
    //@Autowired
    //public void setRepository(ProductRepository repository) {
    //    this.repository = repository;
    //}

    // por medio del construtor pero este se pasa sin el autowired no es necesario funciona
    public ProductServiceImpl(@Qualifier("productJson") ProductRepository repository, Environment environment) {
       this.repository = repository;
       this.environment = environment;
    }


    @Override
    public List<Product> findAll(){
        return repository.findAll().stream().map(p -> {
           // System.out.println(environment.getProperty("config.price.tax",Double.class));
            //Double priceTax = p.getPrice() * environment.getProperty("config.price.tax",Double.class);
            System.out.println(tax);
            Double priceTax = p.getPrice() * tax;
           // Double priceTax = p.getPrice() * 1.25d);
            //Product newProduct = new Product(p.getId(), p.getName(), priceImpl.longValue());
            Product newProduct = (Product) p.clone();
            newProduct.setPrice(priceTax.longValue());
            return newProduct;
            //p.setPrice(priceTax.longValue());
            //return p;
        }).collect(Collectors.toList());
    }
    @Override
    public Product findById(Long id){
        return repository.findById(id);
    }
}
