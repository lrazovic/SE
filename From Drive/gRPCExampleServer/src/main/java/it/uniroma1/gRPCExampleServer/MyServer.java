package it.uniroma1.gRPCExampleServer;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import java.io.IOException;

public class MyServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        Server server;
        server = ServerBuilder
                .forPort(8080)
                .addService(new HelloServiceImpl()).build();

        server.start();
        server.awaitTermination();
    }
}
