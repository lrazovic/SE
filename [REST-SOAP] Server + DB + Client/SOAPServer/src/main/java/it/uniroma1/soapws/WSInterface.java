package it.uniroma1.soapws;
import javax.jws.WebService;

@WebService
public interface WSInterface {
    public Book getBookDetails(int i);
    public String getDeliveryDate(String s, int i);
}
