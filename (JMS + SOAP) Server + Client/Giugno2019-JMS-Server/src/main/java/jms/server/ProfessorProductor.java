/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jms.server;

import java.util.Properties;
import java.util.Random;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;


public class ProfessorProductor {

    Properties properties = null;
    Context jndiContext = null;

    private final Random randomGen = new Random();

    private String getId() {
        return Integer.toString(randomGen.nextInt(6 + 1));  // only 6 professors in the map
    }

    private float getRank() {
        return randomGen.nextFloat() * 100;
    }

    public void start() throws NamingException, JMSException {
        try {
            properties = new Properties();
            properties.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
            properties.setProperty(Context.PROVIDER_URL, "tcp://localhost:61616");
            jndiContext = new InitialContext(properties);
        } catch (NamingException e) {
            System.out.println(e);
            System.exit(1);
        }

        ConnectionFactory connectionFactory = (ConnectionFactory) jndiContext.lookup("ConnectionFactory");
        Destination destination = (Destination) jndiContext.lookup("dynamicTopics/professors");

        Connection connection = connectionFactory.createConnection();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        MessageProducer producer = session.createProducer(destination);

        String id;
        float rank;
        String text;
        TextMessage message = session.createTextMessage();
        while (true) {
            id = getId();
            rank = getRank();
            
            message.setStringProperty("id", id);
            message.setFloatProperty("rank", rank);
            text = "Professor " + id + ": " + rank;
            message.setText(text);

            producer.send(message);
            System.out.println(text);

            try {
                Thread.sleep(5000);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
