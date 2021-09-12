package it.uniroma1.soapws;

import javax.xml.ws.Endpoint;

public class Server {

    public static void main(String args[]) {
        WSImpl implementor = new WSImpl();
        String address = "http://0.0.0.0:7070/WSInterface";
        Endpoint.publish(address, implementor);
        while(true) {}
    }
}
