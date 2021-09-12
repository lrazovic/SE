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

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import static it.uniroma1.gRPCExam.BookListGrpc.newBlockingStub;

public class Client {
    private static final int PORT = 8080;

    public static void main(String[] args) {
        // Create a Logger
        Logger logger = Logger.getLogger(Client.class.getName());

        while(true){
            Scanner scanner = new Scanner(System.in);
            System.out.println("Choose a command in the following list: [list, details, date, exit]");
            String command = scanner.next();
            if(command.equals("list")){
                System.out.println("Getting all books along with their details");
                callRPC(logger);
            }
            else if(command.equals("details")){
                System.out.println("Choose a book id");
                String id = scanner.next();
                System.out.println("Getting the chosen book along with its details");
                Book b2 = getBookDetailsRPC(logger, id);
                int bookId = Integer.parseInt(id);
                it.uniroma1.generatedsource.Book b1 = getBookDetailsSOAP(bookId);
                List<it.uniroma1.generatedsource.Book.Sellers.Entry> sellers = b1.getSellers().getEntry();
                System.out.println("Book ID: " + b1.getId());
                System.out.println("Book Title: " +b2.getTitle());
                System.out.println("Book Price: " + b1.getPrice());
                System.out.println("Book Author: " + b2.getAuthor());
                System.out.println("Book Year of Publication: " + b2.getPublication());
                for (it.uniroma1.generatedsource.Book.Sellers.Entry seller : sellers) {
                    System.out.println("Seller: " + seller.getKey() + " Date: " + seller.getValue());
                }
            }
            else if(command.equals("date")){
                System.out.println("Choose a book id:");
                String id = scanner.next();
                System.out.println("Choose one of its sellers:");
                String seller = scanner.next();
                System.out.println("Getting the estimated date for a given book-seller pair");
                String estimate = getEstimatedDate(Integer.parseInt(id), seller);
                System.out.println(estimate);
            }
            else if(command.equals("exit")){
                System.out.println("Client shutdown");
                scanner.close();
                System.exit(0);
            }
            else{
                System.out.println("Not a valid command");
            }
        }
    }

    private static void callRPC(Logger logger) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", PORT)
                .usePlaintext()
                .build();
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
        channel.shutdown();
    }

    private static Book getBookDetailsRPC(Logger logger, String bookId) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", PORT)
                .usePlaintext()
                .build();
        logger.log(Level.INFO, "gRPC Client started @ localhost:{0,number,#}", PORT);
        BookListGrpc.BookListBlockingStub blockingStub = newBlockingStub(channel);
        BookId request = BookId.newBuilder().setId(bookId).build();
        Book book = null;
        try {
            book = blockingStub.getBookDetails(request);
        } catch (StatusRuntimeException e) {
            logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
        }
        channel.shutdown();
        return book;
    }


    private static it.uniroma1.generatedsource.Book getBookDetailsSOAP(int bookId){
        WSImplService service = new WSImplService();
        WSInterface port = service.getWSImplPort();
        return port.getBookDetails(bookId);
    }

    private static String getEstimatedDate(int bookId, String seller){
        WSImplService service = new WSImplService();
        WSInterface port = service.getWSImplPort();
        return port.getDeliveryDate(seller, bookId);
    }


}