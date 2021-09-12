package it.uniroma1.restserver;

import com.fasterxml.jackson.jaxrs.json.*;

import java.util.*;

import org.apache.cxf.binding.BindingFactoryManager;
import org.apache.cxf.jaxrs.*;
import org.apache.cxf.jaxrs.lifecycle.*;

public class Server {
    public static void main(String[] args) {
        JAXRSServerFactoryBean factoryBean = new JAXRSServerFactoryBean();
        factoryBean.setResourceClasses(BookRepository.class);
        BookRepository fr = new BookRepository();
        fr.setConnection(args[0]);
        factoryBean.setResourceProvider(new SingletonResourceProvider(fr));
        factoryBean.setAddress("http://localhost:8080/");

        List<Object> providers = new ArrayList<Object>();
        providers.add(new JacksonJaxbJsonProvider());
        factoryBean.setProviders(providers);

        BindingFactoryManager manager = factoryBean.getBus().getExtension(BindingFactoryManager.class);
        JAXRSBindingFactory restFactory = new JAXRSBindingFactory();
        restFactory.setBus(factoryBean.getBus());
        manager.registerBindingFactory(JAXRSBindingFactory.JAXRS_BINDING_ID, restFactory);

        org.apache.cxf.endpoint.Server server = factoryBean.create();

        System.out.println("Server ready...");

        while (true) {
        }
    }
}
