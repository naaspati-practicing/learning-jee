package async.server;

import javax.jws.WebService;
import javax.websocket.server.ServerEndpoint;

@WebService(
		serviceName = "AddNumbersService", 
		targetNamespace = "http://duke.example.org")
@ServerEndpoint("/addnumber")
public class AddNumbersImpl {
	
	public int addNumbers(int number1, int number2) {
		System.out.println ("Received Request!");
        System.out.println("Sleeping for 5 seconds");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return number1 + number2;
	}

}
