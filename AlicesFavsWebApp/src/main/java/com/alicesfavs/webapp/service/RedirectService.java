package com.alicesfavs.webapp.service;

import com.alicesfavs.datamodel.Product;
import com.alicesfavs.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by kjung on 2/2/16.
 */
@Component
public class RedirectService
{

    @Autowired
    private ProductService productService;


    public Product getProduct(long productId)
    {
        try
        {
            return productService.findProduct(productId);
        }
        catch (Exception e)
        {
            return null;
        }
    }

}
