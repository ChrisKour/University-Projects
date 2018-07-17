package pkg;
 
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
 
@Path("Calculations")
public class FeetToInchAndInchToFeetConversionService {
	@GET
	@Path("/remote_add/{num1}/{num2}")
	@Produces(MediaType.TEXT_XML)
	public String remote_add(@PathParam("num1") int num1,@PathParam("num2") int num2) {
		int sum = (num1+num2);
		return (String.valueOf(sum));
	}
}