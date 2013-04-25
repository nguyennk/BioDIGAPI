/**
 * 
 */
package BioDIGAPI;

import java.net.URI;
import java.util.Arrays;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriBuilder;

import org.json.JSONObject;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.core.util.MultivaluedMapImpl;



public class BioDIGAPI {
	private JsonParser jsonParser = null;
	private JsonArray MyDIGarray = null;
	private String URL;
	private String mediatype = "MediaType.APPLICATION_JSON";
	private ClientConfig config = null;
	private Client client = null;
	private WebResource service = null;
	private String requestpath;
	
	public BioDIGAPI ( String URL ) {
		this.URL = URL;
		this.config = new DefaultClientConfig();
		this.client = Client.create(config);
		this.service = client.resource(getBaseURI());
	}
	
	//Convert string to URL to use with WebResource
	public URI getBaseURI() {
		return UriBuilder.fromUri(URL).build();
	}
	
	//Get request for TAG Api
	public Tags getTAG (int id){		
		requestpath = "tags";
		MultivaluedMap params = new MultivaluedMapImpl();
		params.add("id",Integer.toString(id));
		
		String info = (service.path(requestpath).queryParams(params).accept(mediatype).get(String.class));
		System.out.println(info.toString());
		
		Gson myGson = new Gson();
		JsonParser jsonParser = new JsonParser();
		JsonElement tagSelement= jsonParser.parse(info);
		Tags newTag = myGson.fromJson(tagSelement, Tags.class);
		return newTag;
	}
	
	//Insert for TAG API
	public void insertTAG (int tagGroupID,Point[] postPoint,Color Color,String name) {
		requestpath = "tags";
			MultivaluedMap params = new MultivaluedMapImpl();
			params.add("tagGroupId", Integer.toString(tagGroupID));
			params.add("points", Arrays.toString(postPoint));
			params.add("color", Color.toString());
			params.add("name", name);
			
			ClientResponse response = service.path(requestpath).accept(mediatype).post(ClientResponse.class,params);
			System.out.println(response.toString());
		}
	
	//Edit Request for TAG API
	public void editTAG (int tagID, int tagGroupID,Point[] postPoint,Color Color,String name) {
		requestpath = "tags";
			MultivaluedMap params = new MultivaluedMapImpl();
			params.add("id", Integer.toString(tagID));
			params.add("tagGroupId", Integer.toString(tagGroupID));
			params.add("points", Arrays.toString(postPoint));
			params.add("color", Color.toString());
			params.add("name", name);
			
			ClientResponse response = service.path(requestpath).accept(mediatype).put(ClientResponse.class,params);
			System.out.println(response.toString());
		}
	
	//Delete request for TAG API
	public void deleteTAG(int id) {
		requestpath = "tags";
		MultivaluedMap params = new MultivaluedMapImpl();
		params.add("id", Integer.toString(id));
		ClientResponse response = service.path(requestpath).queryParams(params).delete(ClientResponse.class);
		System.out.println(response.toString());
	}
	
	

}
