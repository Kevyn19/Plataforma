package br.com.fabricadeprogramador;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class TestMaps {


		
		
		 public static void main(String[] args) throws Exception {

		        URL url = new URL("https://maps.googleapis.com/maps/api/geocode/json?address=1600+Amphitheatre+Parkway,+Mountain+View,+CA");

		        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

		        connection.setRequestMethod("GET");

		        InputStream is = connection.getInputStream();
		        BufferedReader rd = new BufferedReader(new InputStreamReader(is));
		        String line;
		        while((line = rd.readLine()) != null) {
		            System.out.println(line);
		        }
		        rd.close();
		    }


}
