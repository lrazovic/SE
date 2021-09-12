package it.uniroma1.soapws;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.jws.WebService;

@WebService(endpointInterface = "it.uniroma1.soapws.WSInterface")
public class WSImpl implements WSInterface {

    private final Map<Integer, Book> books = new LinkedHashMap<>();


    public WSImpl() {
        Book b1 = new Book();
        Map<String, String> sellers = new HashMap<>();
        sellers.put("Seller1", "Delivery Date 1");
        sellers.put("Seller2", "Delivery Date 2");
        sellers.put("Seller3", "Delivery Date 3");
        b1.setId(1);
        b1.setPrice((float) 10.99);
        b1.setSellers(sellers);

        Book b2 = new Book();
        sellers = new HashMap<>();
        sellers.put("Seller4", "Delivery Date 4");
        sellers.put("Seller5", "Delivery Date 5");
        sellers.put("Seller6", "Delivery Date 6");
        b2.setId(2);
        b2.setPrice((float) 19.99);
        b2.setSellers(sellers);

        Book b3 = new Book();
        sellers = new HashMap<>();
        sellers.put("Seller7", "Delivery Date 7");
        sellers.put("Seller8", "Delivery Date 8");
        sellers.put("Seller9", "Delivery Date 9");
        b3.setId(3);
        b3.setPrice((float) 16.99);
        b3.setSellers(sellers);

        books.put(1, b1);
        books.put(2, b2);
        books.put(3, b3);

    }

    @Override
    public Book getBookDetails(int bookId) {
        if (!books.containsKey(bookId)) {
            System.err.println("Book not found with ID: " + bookId);
            return null;
        }
        return books.get(bookId);
    }

    @Override
    public String getDeliveryDate(String seller, int bookId) {
        if (!books.containsKey(bookId)) {
            return "Book not found";
        } else {
            Book book = books.get(bookId);
            return book.getSellers().getOrDefault(seller, "Seller not found");
        }
    }
}