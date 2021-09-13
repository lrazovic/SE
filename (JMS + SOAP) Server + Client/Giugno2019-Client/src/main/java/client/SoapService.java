package client;


import client.generatedsource.Exam;
import client.generatedsource.ExamImplService;
import client.generatedsource.Professor;

public class SoapService {

    private static ExamImplService is = new ExamImplService();
    private static Exam port = is.getExamImplPort();

    public static Professor getDetails(String ID){
        return port.getDetails(ID);
    }
}