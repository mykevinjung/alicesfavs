package com.alicesfavs;

import com.alicesfavs.service.CryptoService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class ServiceApp
{

    public static void main(String[] args)
    {
        ApplicationContext context = new ClassPathXmlApplicationContext("service.xml");
        CryptoService cryptoService = context.getBean(CryptoService.class);
        System.out.println(cryptoService.encrypt("kevinjung"));
        System.out.println(cryptoService.encrypt("kevinjung"));
    }

}
