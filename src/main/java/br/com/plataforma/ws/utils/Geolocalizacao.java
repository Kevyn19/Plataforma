package br.com.plataforma.ws.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Geolocalizacao {

	//Método que bate na api do google que converte o endereço em um Json contendo todos os dados de localização 
	public static List<String> buscaCoordenadas(String RuaOuAv, String Bairro, int Numero, String UF, String Cidade)
			throws Exception {
		
		List<String> result = new ArrayList<>();
		
		String urlComEspac = "https://maps.googleapis.com/maps/api/geocode/json?address=" + Numero + "+" + RuaOuAv + "+" + Bairro + ",+"
				+ Cidade + "+" + UF;

		URL url = new URL(urlComEspac.replace(" ", "%20"));

		HttpURLConnection connection = (HttpURLConnection) url.openConnection();

		connection.setRequestMethod("GET");

		InputStream is = connection.getInputStream();
		BufferedReader rd = new BufferedReader(new InputStreamReader(is));
		String line;

		while ((line = rd.readLine()) != null) {
			System.out.println(line);
			if (line.contains("location")) {
				result.add(rd.readLine());
				result.add(rd.readLine());
				break;
			}
		}
		rd.close();
		
		if(result.size() == 0){
			throw new Exception("Dados de endereço invalidos!");
		}

		return result;
	
	}
	
	//Retorna apenas a latitude
	public static String validaRetornoCoordGoogleLat(String coord) {

		String format = coord.substring(coord.indexOf(':'),coord.indexOf(','));
		return format.replace(':', ' ').trim();
	}
	
	//Retorna apenas a longitude
	public static String validaRetornoCoordGoogleLng(String coord) {

		String format = coord.substring(coord.indexOf(':'));
		return format.replace(':', ' ').trim();
	}
}
