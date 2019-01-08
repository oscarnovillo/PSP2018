package com.test.ws.client;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import org.glassfish.jersey.jackson.JacksonFeature;
import com.test.ws.view.RequestObj;
import com.test.ws.view.ResponseObj;
public class TestClient 
{
    public static void main( String[] args )
    {
    	RequestObj requestObj = new RequestObj();
    	requestObj.setSinger("Rammstein");
    	requestObj.setTitle("Stripped");
    	Client client = ClientBuilder.newBuilder()
	      .register(JacksonFeature.class)
	      .build();    	
    	//WS text get
    	WebTarget target = client.target("http://localhost:8080/TestJersey2/rest/").path("test/get");
        String res = target.request().get().readEntity(String.class);
        System.out.println(res); 	
    	//WS JSON get
    	WebTarget target2 = client.target("http://localhost:8080/TestJersey2/rest/").path("test/get");
        String res2 = target2.request(MediaType.APPLICATION_JSON).get().readEntity(String.class);
        System.out.println(res2); 	
    	//WS sending and receiving a String by post
    	String req = "{\"title\":\"Stripped\", \"singer\":\"Rammstein\"}";// a valid json
    	WebTarget target3 = client.target("http://localhost:8080/TestJersey2/rest/").path("test/validate");
        String res3 = target3.request().post(Entity.entity(req, MediaType.APPLICATION_JSON), String.class);  	
        System.out.println(res3);
    	//WS text getsending and receiving objects
    	WebTarget target4 = client.target("http://localhost:8080/TestJersey2/rest/").path("test/validate");    	
    	ResponseObj responseObj = target4.request(MediaType.APPLICATION_JSON).post(Entity.entity(requestObj, MediaType.APPLICATION_JSON)).readEntity(ResponseObj.class);  	
    	System.out.println(responseObj.getResult() + "--" + responseObj.getRetorno() + "--" + responseObj.getError());
    }
}
