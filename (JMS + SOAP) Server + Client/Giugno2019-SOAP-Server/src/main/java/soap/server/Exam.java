package soap.server;

import javax.jws.WebService;

@WebService
public interface Exam {
    public Professor getDetails(String id);
}
