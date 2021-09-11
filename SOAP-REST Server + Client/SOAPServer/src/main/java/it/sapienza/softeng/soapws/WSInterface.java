package it.sapienza.softeng.soapws;
import javax.jws.WebService;

@WebService
public interface WSInterface {
    public String getBookDetails(int i);
    public String getDeliveryDate(String s, int i);
}
