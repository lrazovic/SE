package it.uniroma1.soapclient;

import javax.jms.JMSException;
import javax.naming.NamingException;


    public class Client {
        public static void main(String[] args) throws JMSException, NamingException {
            System.out.println("Client start \n");
            ProfessorListener p = new ProfessorListener();
            p.start();
        }
    }


