
package jms.server;

public class Server {
    public static void main(String[] args) throws Exception {
        ProfessorProductor productor = new ProfessorProductor();
        productor.start();      
    }
}
