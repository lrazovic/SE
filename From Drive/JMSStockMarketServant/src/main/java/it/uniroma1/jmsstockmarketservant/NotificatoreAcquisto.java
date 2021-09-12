package it.uniroma1.jmsstockmarketservant;

import org.slf4j.LoggerFactory;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Logger;

public class NotificatoreAcquisto implements MessageListener {

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(NotificatoreAcquisto.class);
    private final Random randomGen = new Random();
    Properties properties = null;
    Context jndiContext = null;
    private TopicConnection connection = null;
    private TopicSession session = null;
    private Topic destination = null;
    private TopicPublisher publisher = null;

    public void start() throws NamingException, JMSException {
        InitialContext ctx;
        try {
            properties = new Properties();
            properties.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
            properties.setProperty(Context.PROVIDER_URL, "tcp://localhost:61616");
            jndiContext = new InitialContext(properties);

        } catch (NamingException e) {
            LOG.info("ERROR in JNDI: " + e);
            System.exit(1);
        }


        ctx = new InitialContext(properties);
        TopicConnectionFactory connectionFactory = (TopicConnectionFactory) ctx.lookup("ConnectionFactory");
        this.destination = (Topic) ctx.lookup("dynamicTopics/Ordini");

        this.connection = connectionFactory.createTopicConnection();
        this.session = this.connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
        TopicSubscriber subscriber = this.session.createSubscriber(this.destination, null, true);
        this.publisher = this.session.createPublisher(this.destination);
        this.connection.start();

        Logger.getLogger(
                this.getClass().getName()
        ).info("In attesa di richieste di acquisto...");

        subscriber.setMessageListener(this);
    }

    public void onMessage(Message mex) {
        TextMessage message;
        String utente;
        String nome;
        float prezzo;
        int quantita;
        boolean status = randomGen.nextFloat() < 0.5;
        try {
            message = (TextMessage) mex;
            utente = message.getStringProperty("Utente");
            nome = message.getStringProperty("Nome");
            prezzo = message.getFloatProperty("Prezzo");
            quantita = message.getIntProperty("Quantita");
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        try {
            session = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
            publisher = session.createPublisher(destination);
            message = session.createTextMessage();
            message.setStringProperty("Utente", utente);
            message.setStringProperty("Nome", nome);
            message.setBooleanProperty("Status", status);
            message.setIntProperty("Quantita", quantita);
            message.setFloatProperty("Prezzo", prezzo);

            Logger.getLogger(
                    this.getClass().getName()
            ).info(
                    "************************************************" + "\n" +
                            "Notifica richiesta di acquisto" + "\n" +
                            "ID utente: " + utente + "\n" +
                            "Titolo: " + nome + "\n" +
                            "Quantit\u00e0: " + quantita + "\n" +
                            "Prezzo: " + prezzo + "\n" +
                            "Accettato: " + status + "\n" +
                            "************************************************"
            );

            publisher.send(message);
        } catch (Exception err) {
            err.printStackTrace();
        }
    }
}
