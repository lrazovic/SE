package it.uniroma1.soapws;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.jws.WebService;

@WebService(endpointInterface = "it.uniroma1.soapws.WSInterface")
public class WSImpl implements WSInterface {

    private Map<Integer, Book> books = new LinkedHashMap<Integer, Book>();


    public WSImpl() {
        Book b1 = new Book();
        Map<String, String> sellers = new HashMap<>();
        sellers.put("Mondadori", "11/09/2021");
        sellers.put("Einaudi", "19/09/2021");
        sellers.put("Mondolibri", "14/09/2021");
        b1.setId(1);
        b1.setPrice((float) 10.99);
        b1.setSellers(sellers);

        Book b2 = new Book();
        sellers = new HashMap<>();
        sellers.put("Mondadori", "10/12/2021");
        sellers.put("Amazon", "09/06/2021");
        sellers.put("Ebay", "30/05/2021");
        b2.setId(2);
        b2.setPrice((float) 19.99);
        b2.setSellers(sellers);

        Book b3 = new Book();
        sellers = new HashMap<>();
        sellers.put("Feltrinelli", "20/09/2021");
        sellers.put("Ebay", "15/10/2021");
        sellers.put("Ibis", "10/06/2021");
        b3.setId(3);
        b3.setPrice((float) 16.99);
        b3.setSellers(sellers);

        books.put(1, b1);
        books.put(2, b2);
        books.put(3, b3);

    }

    public Book getBook(int i) {
        return books.get(i);
    }

    public Map<Integer, Book> getBooks() {
        return books;
    }

    @Override
    public Book getBookDetails(int i) {
        if (!books.containsKey(i)) {
            return null;
        }
        return books.get(i);
    }

    @Override
    public String getDeliveryDate(String s, int i) {
        if (!books.containsKey(i)) {
            return "Book not found";
        } else {
            Book book = books.get(i);
            if (!book.getSellers().containsKey(s)) {
                return "Seller not found";
            } else {
                return book.getSellers().get(s);
            }
        }
    }
}