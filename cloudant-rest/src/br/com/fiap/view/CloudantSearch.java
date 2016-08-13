package br.com.fiap.view;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.gson.Gson;

import br.com.fiap.to.CapturaTO;
import br.com.fiap.util.HttpCloudant;


public class CloudantSearch {
	
	public static void main(String[] args){
		HttpCloudant http = new HttpCloudant();
		http.setRequest("https://742f319e-2d45-4d5b-959a-be51c2d0228f-bluemix.cloudant.com/banco/_find");
		http.setRequestmethod("POST");
		http.setUsername("therseradhellympoliferst");
		http.setPassword("070da3ad42379cc01932dbc07c8be101fc6d2eee");
		http.setJson("{\"selector\": {\"devCode\": \"1\" }, \"limit\":10}");
		
		try {
			HttpURLConnection client = http.getClient();
			StringBuilder sb = new StringBuilder();
			int StatusCodeHttp = client.getResponseCode();
						
			if (StatusCodeHttp == HttpURLConnection.HTTP_OK) {
				BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream(),"utf-8"));
				String line = null;
			
			while((line = br.readLine())!= null){
				sb.append(line + "\n");
			} 
			br.close();
			JSONParser parser  = new JSONParser(); 
			JSONObject jsonSaida = (JSONObject) parser.parse(sb.toString());
			JSONArray jsonArray = (JSONArray) jsonSaida.get("docs");
			
			Gson gson = new Gson();
			
			List<CapturaTO> capturas = new ArrayList<>();
			
			for(Object object : jsonArray){
				JSONObject jsonObj = (JSONObject) object;
				capturas.add(gson.fromJson(jsonObj.toString(),CapturaTO.class));
				
				
			}
			
			for(CapturaTO cap : capturas){
				System.out.println("Captura: " +capturas.indexOf(cap) + " : " + cap.toString());;
			}
			}else{
				System.out.println("Codigo:"+StatusCodeHttp+" Erro: "+client.getResponseMessage());
			}
			
		} catch (Exception e) {
			e.printStackTrace();	
		}
	}

}
