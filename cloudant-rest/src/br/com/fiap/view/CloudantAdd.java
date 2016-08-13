package br.com.fiap.view;

import java.net.HttpURLConnection;

import com.google.gson.Gson;

import br.com.fiap.to.CapturaTO;
import br.com.fiap.util.HttpCloudant;

public class CloudantAdd {

	public static void main(String[] args) {
		
		HttpCloudant http = new HttpCloudant();
		http.setRequest("https://3fc6f038-6b5d-4e7f-814b-cc4d7e1d6e22-bluemix.cloudant.com/fiap-db/");
		http.setRequestmethod("POST");
		http.setUsername("shatedidenceilsommortion");
		http.setPassword("2afe71b5c6fbe431b6c151b3592bcf1a9b1916b0");		
		
		HttpURLConnection client;
		
		try {
			CapturaTO cap = new CapturaTO();
			cap.setDispositivo("churros");
			cap.setDevCode("3");
			cap.setTemperatura(55);
			cap.setUnidade(55);
			cap.setChuva(12);			
						
			Gson gson = new Gson();
			String json = gson.toJson(cap).toString();
			http.setJson(json);
			client = http.getClient();
			
			int statusCodeHttp = client.getResponseCode();
			
			if(statusCodeHttp == HttpURLConnection.HTTP_CREATED){
				System.out.println("Cadastrado! " + statusCodeHttp + client.getResponseCode());
			}else{
                System.out.println(client.getResponseMessage());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}


	}

}