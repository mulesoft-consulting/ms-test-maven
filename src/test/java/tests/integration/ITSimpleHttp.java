package tests.integration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ITSimpleHttp  {
	 
	@Test
    public void test() throws ClientProtocolException, IOException {   
		HttpClient client = new DefaultHttpClient();
		 HttpGet request = new HttpGet("http://localhost:8085/test");
	        HttpResponse response = client.execute(request);
	        BufferedReader rd = new BufferedReader (new InputStreamReader(response.getEntity().getContent()));
	        String line = "";
	        StringBuffer output = new StringBuffer();
	        while ((line = rd.readLine()) != null) {
	        	output.append(line);
	        }
	        assertEquals(output.toString(), "Hello World");
    }
	
}
