package it.uniroma1.soapclient;

import it.uniroma1.generatedsource.Interface;
import it.uniroma1.generatedsource.InterfaceImplService;
import it.uniroma1.generatedsource.Professor;

public class SoapService {

    public static Professor getDetails(String id) {
        InterfaceImplService iis = new InterfaceImplService();
       Interface port = iis.getInterfaceImplPort();
       return port.getDetails(id);

    }
}
