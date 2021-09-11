package it.uniroma1.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import javax.xml.bind.JAXB;
import it.uniroma1.generatedsource.*;

public class Client {
    private static final String BASE_URL = "http://localhost:8080/books/";

    public static void main(String[] args) throws IOException {

        // GET all books
        Books books = getBookList();
        System.out.println(books);

        // SOAP a book
        String book = getBook(1);
        System.out.println(book);

        // SOAP a delivery
        book = getDelivery(1, "Nome 2");
        System.out.println(book);

    }

    private static String getBook(int i) {
        WSImplService service = new WSImplService();
        WSInterface port = service.getWSImplPort();
        return port.getBookDetails(i);

    }

    private static String getDelivery(int i, String seller) {
        WSImplService service = new WSImplService();
        WSInterface port = service.getWSImplPort();
        return port.getDeliveryDate(seller, i);

    }

    private static Books getBookList() throws IOException {
        final URL url = new URL(BASE_URL + "list");
        final InputStream input = url.openStream();
        return JAXB.unmarshal(new InputStreamReader(input), Books.class);
    }
}
