package br.com.fiap.util;

import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

public class HttpCloudant {
	
	private String json, requestmethod, username, password, request;

	public HttpURLConnection getClient()throws Exception{
		URL url = new  URL(getRequest());
		HttpURLConnection client = (HttpURLConnection) url.openConnection();
		String json = getJson();
		client.setRequestProperty("Content-Type", "application/json");
		client.setRequestProperty("charset", "utf-8");
		
		client.setRequestMethod(getRequestmethod());
		client.setDoOutput(true);
		client.setDoInput(true);
		String username = getUsername();
		String pswd = getPassword();
		String encodedPassword = username+pswd;
		String encoded = Base64.getEncoder().encodeToString(encodedPassword.getBytes());
		client.setRequestProperty("Authorization","Basic"+encoded);
		OutputStreamWriter wr;
		wr= new OutputStreamWriter(client.getOutputStream());
		wr.write(json);
		wr.close();
		
		return client;
		
	}
	
	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

	public String getRequestmethod() {
		return requestmethod;
	}

	public void setRequestmethod(String requestmethod) {
		this.requestmethod = requestmethod;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}
}
