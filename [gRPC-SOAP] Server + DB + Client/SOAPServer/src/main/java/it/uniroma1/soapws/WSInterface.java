package it.uniroma1.soapws;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface WSInterface {
    @WebMethod
    Book getBookDetails(int i);
    String getDeliveryDate(String s, int i);
}