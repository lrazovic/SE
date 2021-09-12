package it.uniroma1.gRPCClient;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import it.uniroma1.gRPCExam.Book;
import it.uniroma1.gRPCExam.BookId;
import it.uniroma1.gRPCExam.BookListGrpc;
import it.uniroma1.gRPCExam.Input;
import it.uniroma1.generatedsource.WSImplService;
import it.uniroma1.generatedsource.WSInterface;
import it.uniroma1.generatedsource.Book.Sellers.Entry;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import static it.uniroma1.gRPCExam.BookListGrpc.newBlockingStub;

public class Client {
    private static final int PORT = 8080;
    private static WSInterface port;
    private static ManagedChannel channel;
    private static WSImplService service;
    private static Logger logger;

    public static void main(String[] args) {
        // Create a Logger
        logger = Logger.getLogger(Client.class.getName());

        // gRPC Channel
        channel = ManagedChannelBuilder.forAddress("localhost", PORT)
                .usePlaintext()
                .build();

        // Connection to SOAP Service
        service = new WSImplService();
        port = service.getWSImplPort();

        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Choose a command in the following list: [list, details, date, exit]");
            String command = scanner.next();
            switch (command) {
                case "list":
                    System.out.println("Getting all books along with their details");
                    callRPC();
                    break;
                case "details": {
                    System.out.println("Choose a book id");
                    String id = scanner.next();
                    System.out.println("Getting the chosen book along with its details");
                    Book b2 = getBookDetailsRPC(id);
                    int bookId = Integer.parseInt(id);
                    it.uniroma1.generatedsource.Book b1 = getBookDetailsSOAP(bookId);
                    List<Entry> sellers = b1.getSellers().getEntry();
                    System.out.println("Book ID: " + b1.getId());
                    System.out.println("Book Title: " + b2.getTitle());
                    System.out.println("Book Price: " + b1.getPrice());
                    System.out.println("Book Author: " + b2.getAuthor());
                    System.out.println("Book Year of Publication: " + b2.getPublication());
                    for (Entry seller : sellers) {
                        System.out.println("Seller: " + seller.getKey() + " Date: " + seller.getValue());
                    }
                    break;
                }
                case "date": {
                    System.out.println("Choose a book id:");
                    String id = scanner.next();
                    System.out.println("Choose one of its sellers:");
                    String seller = scanner.next();
                    System.out.println("Getting the estimated date for a given book-seller pair");
                    String estimate = getEstimatedDateSOAP(Integer.parseInt(id), seller);
                    System.out.println(estimate);
                    break;
                }
                case "exit":
                    System.out.println("Client shutdown");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Not a valid command");
                    break;
            }
        }
    }

    private static void callRPC() {
        logger.log(Level.INFO, "gRPC Client started @ localhost:{0,number,#}", PORT);
        BookListGrpc.BookListBlockingStub blockingStub = newBlockingStub(channel);
        Input request = Input.newBuilder().build();
        Iterator<Book> booklist = null;
        try {
            booklist = blockingStub.getBooks(request);
        } catch (StatusRuntimeException e) {
            logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
        }
        while (booklist.hasNext()) {
            System.out.println(booklist.next());
        }
    }

    private static Book getBookDetailsRPC(String bookId) {
        logger.log(Level.INFO, "gRPC Client started @ localhost:{0,number,#}", PORT);
        BookListGrpc.BookListBlockingStub blockingStub = newBlockingStub(channel);
        BookId request = BookId.newBuilder().setId(bookId).build();
        Book book = null;
        try {
            book = blockingStub.getBookDetails(request);
        } catch (StatusRuntimeException e) {
            logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
        }
        return book;
    }


    private static it.uniroma1.generatedsource.Book getBookDetailsSOAP(int bookId) {
        return port.getBookDetails(bookId);
    }

    private static String getEstimatedDateSOAP(int bookId, String seller) {
        return port.getDeliveryDate(seller, bookId);
    }


}