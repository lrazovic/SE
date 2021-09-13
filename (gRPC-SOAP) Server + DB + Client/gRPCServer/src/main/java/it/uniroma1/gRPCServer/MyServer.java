package it.uniroma1.gRPCServer;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.System.exit;

public class MyServer {
    private static final int PORT = 8080;

    public static void main(String[] args) throws IOException, InterruptedException {
        // Create the Logger
        Logger logger = Logger.getLogger(MyServer.class.getName());
        // Check for database path name
        if (args.length == 0) {
            logger.log(Level.SEVERE, "Please specify the SQLite database path");
            exit(-1);
        }
        // Build and start the gRPC Server
        Server server = ServerBuilder.forPort(PORT).addService(new BookListImpl(args[0])).build();
        server.start();
        logger.log(Level.INFO, "gRPC Server started @ localhost:{0,number,#}", PORT);
        server.awaitTermination();
    }
}