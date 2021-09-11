package it.sapienza.softeng.soapws;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.jws.WebService;

@WebService(endpointInterface = "it.sapienza.softeng.soapws.WSInterface")
public class WSImpl implements WSInterface {

    private Map<Integer, Book> books = new LinkedHashMap<Integer, Book>();


    public WSImpl() {
        Book b1 = new Book();
        Map<String, String> sellers = new HashMap<>();
        sellers.put("Nome 1", "Data 1");
        sellers.put("Nome 2", "Data 2");
        sellers.put("Nome 3", "Data 3");
        b1.setId(1);
        b1.setPrice((float) 10.99);
        b1.setSellers(sellers);

        Book b2 = new Book();
        sellers = new HashMap<>();
        sellers.put("Nome 4", "Data 4");
        sellers.put("Nome 5", "Data 5");
        sellers.put("Nome 6", "Data 6");
        b2.setId(2);
        b2.setPrice((float) 19.99);
        b2.setSellers(sellers);

        Book b3 = new Book();
        sellers = new HashMap<>();
        sellers.put("Nome 7", "Data 7");
        sellers.put("Nome 8", "Data 8");
        sellers.put("Nome 9", "Data 9");
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
    public String getBookDetails(int i) {
        if (!books.containsKey(i)) {
            return "Book not found";
        }
        return books.get(i).toString();
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