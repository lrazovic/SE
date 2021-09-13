package soap.server;

import javax.jws.WebService;

@WebService(endpointInterface = "soap.server.Exam")
public class ExamImpl implements Exam {
    @Override
    public Professor getDetails(String id) {
        return Professor.getProfessor(id);
    }
}