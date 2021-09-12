package it.uniroma1.soapclient;

import it.uniroma1.generatedsource.Professor;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProfessorListener implements MessageListener {
    private TopicConnection topicConnection;

    public ProfessorListener() {
        Context jndiContext;
        ConnectionFactory topicConnectionFactory;
        String destinationName = "dynamicTopics/professors";
        try {
            Properties props = new Properties();
            props.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
            props.setProperty(Context.PROVIDER_URL, "tcp://localhost:61616");
            jndiContext = new InitialContext(props);
            topicConnectionFactory = (ConnectionFactory) jndiContext.lookup("ConnectionFactory");
            Destination destination = (Destination) jndiContext.lookup(destinationName);
            topicConnection = (TopicConnection) topicConnectionFactory.createConnection();
            TopicSession topicSession = topicConnection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
            TopicSubscriber topicSubscriber = topicSession.createSubscriber((Topic) destination);
            topicSubscriber.setMessageListener(this);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(-1);
        }
    }

    public void start() {
        try {
            topicConnection.start();
        } catch (JMSException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void onMessage(Message m) {
        try {
            TextMessage msg = (TextMessage) m;
            String text = msg.getText();
            String id = m.getStringProperty("id");
            Professor p = SoapService.getDetails(id);
            System.out.println(text);
            String printable = "Professor with id " + id + " Has details " + p.getName() + " " + p.getSurname() + ", " + p.getCourse();
            System.out.println(printable);
        } catch (JMSException ex) {
            Logger.getLogger(ProfessorListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
