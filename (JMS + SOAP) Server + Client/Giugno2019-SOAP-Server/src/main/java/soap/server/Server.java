/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soap.server;

import javax.xml.ws.Endpoint;

/**
 *
 * @author biar
 */
public class Server {
    public static void main(String[] args)  {
        Professor.insertProfessor(new Professor("Camil", "Demetrescu"));
        Professor.insertProfessor(new Professor("Fabrizio", "D'Amore"));
        Professor.insertProfessor(new Professor("Maurizio", "Lenzerini"));
        Professor.insertProfessor(new Professor("Riccardo", "Rosati"));
        Professor.insertProfessor(new Professor("Angelo", "Spognardi"));
        Professor.insertProfessor(new Professor("Massimo", "Mecella"));
        Professor.insertProfessor(new Professor("Luigi Vincenzo", "Mancini"));
        
        ExamImpl implementor = new ExamImpl();
        String address = "http://localhost:8080/ProfessorManagement";
        Endpoint.publish(address, implementor);
        System.out.println("Server ready...");
    }
}
