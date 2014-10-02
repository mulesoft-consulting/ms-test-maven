package tests.integration;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ITSimpleHttp {

	private String getPort() throws IOException {
		Properties prop = new Properties();
		InputStream is = getClass().getClassLoader().getResourceAsStream(
				"mule-app.properties");
		prop.load(is);
		if (is==null){
			throw new FileNotFoundException("mule-app.properties not found on classpath");
		}
		return prop.getProperty("http.port");
	}

	@Test
	public void test() throws ClientProtocolException, IOException {
		String port = getPort();
		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet("http://localhost:"+port+"/test");
		HttpResponse response = client.execute(request);
		BufferedReader rd = new BufferedReader(new InputStreamReader(response
				.getEntity().getContent()));
		String line = "";
		StringBuffer output = new StringBuffer();
		while ((line = rd.readLine()) != null) {
			output.append(line);
		}
		assertEquals(output.toString(), "Hello World");
	}

}
