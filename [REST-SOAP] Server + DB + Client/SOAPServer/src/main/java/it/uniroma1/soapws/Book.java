package it.uniroma1.soapws;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Map;

@XmlRootElement
public class Book {
    private int id;
    private float price;
    private Map<String, String> sellers;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Map<String, String> getSellers() {
        return sellers;
    }

    public void setSellers(Map<String, String> sellers) {
        this.sellers = sellers;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", price=" + price +
                ", sellers=" + sellers.toString() +
                '}';
    }
}
