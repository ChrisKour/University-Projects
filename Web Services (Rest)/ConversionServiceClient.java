package pkg;
 
import javax.ws.rs.core.MediaType;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import java.util.Scanner;
 
public class ConversionServiceClient {
    static final String REST_URI = "http://localhost:8080/REST";
    static final String REMOTE_ADD = "/Calculations/remote_add/";
 
    public static void main(String[] args) {
    	Scanner in = new Scanner(System.in);
    	System.out.println("Enter the two numbers that you wish to add:");
    	String line = in.nextLine();
    	String parts[] = line.split(" ");
    	int num1 = Integer.parseInt(parts[0]);
    	int num2 = Integer.parseInt(parts[1]);
 
        ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);
        WebResource service = client.resource(REST_URI);
 
        WebResource addService = service.path("rest").path(REMOTE_ADD+num1+"/"+num2);
        System.out.println("Result: " + addService.accept(MediaType.TEXT_XML).get(String.class));
    }
}