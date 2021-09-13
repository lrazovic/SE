package it.uniroma1.client;

import it.uniroma1.generatedsource.WSImplService;
import it.uniroma1.generatedsource.WSInterface;

import javax.xml.bind.JAXB;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

public class Client {
    private static final String BASE_URL = "http://localhost:8080/books/";
    private static final WSImplService service = new WSImplService();
    private static final WSInterface port = service.getWSImplPort();

    public static void main(String[] args) throws IOException {

        // GET all books
        Books books = getBookList();
        for (Book elem : books.getBooks()) {
            System.out.println(elem.toString());
        }

        // SOAP a book
        String book = getBook(1);
        System.out.println(book);

        // SOAP a delivery
        book = getDelivery(1, "Mondadori");
        System.out.println(book);

    }

    private static String getBook(int i) throws IOException {
        final URL url = new URL(BASE_URL + i);
        final InputStream input = url.openStream();
        Book bookrest = JAXB.unmarshal(new InputStreamReader(input), Book.class);

        it.uniroma1.generatedsource.Book booksoap = port.getBookDetails(i);
        List<it.uniroma1.generatedsource.Book.Sellers.Entry> sellers = booksoap.getSellers().getEntry();

        StringBuilder str = new StringBuilder();
        str.append(bookrest.toString());
        str.append(", Year of birth = ").append(bookrest.getYop());
        str.append(", Price: ").append(booksoap.getPrice()).append("\n");
        for (it.uniroma1.generatedsource.Book.Sellers.Entry elem : sellers) {
            str.append("\t Seller: ").append(elem.getKey());
            str.append("\t Delivery date: ").append(elem.getValue()).append("\n");
        }
        return str.toString();

    }

    private static String getDelivery(int i, String seller) {
        return port.getDeliveryDate(seller, i);

    }

    private static Books getBookList() throws IOException {
        final URL url = new URL(BASE_URL + "list");
        final InputStream input = url.openStream();
        return JAXB.unmarshal(new InputStreamReader(input), Books.class);
    }
}
