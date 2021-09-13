package it.uniroma1.soapws;

import javax.jws.WebService;

@WebService
public interface WSInterface {
    Book getBookDetails(int i);
    String getDeliveryDate(String s, int i);
}