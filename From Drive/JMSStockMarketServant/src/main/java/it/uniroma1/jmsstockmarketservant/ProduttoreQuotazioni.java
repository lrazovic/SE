package it.uniroma1.jmsstockmarketservant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;
import java.util.Random;

public class ProduttoreQuotazioni {
    final String[] titoli = {"Telecom", "Finmeccanica", "Banca_Intesa", "Oracle", "Parmalat", "Mondadori", "Vodafone", "Barilla"};

    private String scegliTitolo() {
        int whichMsg;
        Random randomGen = new Random();

        whichMsg = randomGen.nextInt(this.titoli.length);
        return this.titoli[whichMsg];
    }

    private float valore() {
        Random randomGen = new Random();
        return randomGen.nextFloat() * this.titoli.length * 10;
    }

    private static final Logger LOG = LoggerFactory.getLogger(ProduttoreQuotazioni.class);

    public void start() {
        Context jndiContext = null;
        ConnectionFactory connectionFactory = null;
        Connection connection = null;
        Session session;
        Destination destination = null;
        MessageProducer producer;
        String destinationName = "dynamicTopics/Quotazioni";

        /*
         * Create a JNDI API InitialContext object
         */

        try {

            Properties props = new Properties();
            props.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
            props.setProperty(Context.PROVIDER_URL, "tcp://localhost:61616");
            jndiContext = new InitialContext(props);

        } catch (NamingException e) {
            LOG.info("ERROR in JNDI: " + e);
            System.exit(1);
        }

        /*
         * Look up connection factory and destination.
         */
        try {
            connectionFactory = (ConnectionFactory) jndiContext.lookup("ConnectionFactory");
            destination = (Destination) jndiContext.lookup(destinationName);
        } catch (NamingException e) {
            LOG.info("JNDI API lookup failed: " + e);
            System.exit(1);
        }

        /*
         * Create connection. Create session from connection; false means
         * session is not transacted. Create sender and text message. Send
         * messages, varying text slightly. Send end-of-messages message.
         * Finally, close connection.
         */
        try {
            connection = connectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            producer = session.createProducer(destination);
            TextMessage message;
            String messageType;
            message = session.createTextMessage();
            float quotazione;
            int i = 0;
            while (true) {
                i++;
                messageType = scegliTitolo();
                quotazione = valore();
                message.setStringProperty("Nome", messageType);
                message.setFloatProperty("Valore", quotazione);
                message.setText(
                        "Item " + i + ": " + messageType + ", Valore: "
                                + quotazione);

                LOG.info(
                        this.getClass().getName() +
                                "Invio quotazione: " + message.getText());

                producer.send(message);

                try {
                    Thread.sleep(5000);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        } catch (JMSException e) {
            LOG.info("Exception occurred: " + e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException e) {
                    LOG.info("Exception occurred: " + e);
                }
            }
        }
    }
}
