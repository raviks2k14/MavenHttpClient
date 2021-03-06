package com.qa.test;

import java.io.IOException;

import junit.framework.Assert;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.test.base.TestBase;
import com.qa.test.client.HttpClient;

public class RestClientTest extends TestBase {

	HttpClient httpClient;
	String serviceUrl;
	String apiUrl;
	String url;
	CloseableHttpResponse closeableHttpResponse;

	public RestClientTest() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		httpClient = new HttpClient();
		serviceUrl = prop.getProperty("serviceUrl");
		apiUrl = prop.getProperty("apiUrl");
		url = serviceUrl + apiUrl;
	}

	@Test
	public void getRequestWithoutHeaders() throws ClientProtocolException,
			IOException {
		httpClient = new HttpClient();
		closeableHttpResponse = httpClient.get(url);

		// a. Status Code:
		int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
		Assert.assertEquals("Status code is not 200", RESPONSE_STATUS_CODE_200,
				statusCode);

		// b. Json String:
		String responseString = EntityUtils.toString(
				closeableHttpResponse.getEntity(), "UTF-8");

		JSONArray jsonArray = new JSONArray(responseString);
		System.out.println("JSON length ---> " + jsonArray.length());
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject responseJson = new JSONObject(jsonArray.get(0)
					.toString());
			System.out.println("Response JSON from API---> " + responseJson);
		}

	}
}
