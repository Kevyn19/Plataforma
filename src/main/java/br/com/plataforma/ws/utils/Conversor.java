package br.com.plataforma.ws.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import br.com.plataforma.ws.dto.DiasDTO;

public class Conversor {
	
	//Converte a lista de servicos em um unica String
	public static String geraString(List<String> list){
		String aux = "";
		
		for(int i = 0; i < list.size(); i++ ){
			aux = aux + ", " + list.get(i);
		}
		
		return aux;
	}
	
	//Converte a string em uma lista com os servicos
	public static List<String> geraLista(String compac){
		
		List<String> myList = new ArrayList<String>(Arrays.asList(compac.split(",")));

		List<String> result = new ArrayList<>();
		
		for (String s : myList) {
			String aux = s.trim();
			if(aux.equals(ServicosEnum.VARRER_OU_ASPIRAR.getValor())){
				result.add("VARRER OU ASPIRAR");
			}else if(aux.equals(ServicosEnum.LIMPAR_JANELAS.getValor())){
				result.add("LIMPAR JANELAS");
			}else if(aux.equals(ServicosEnum.ARRUMAR_CAMA.getValor())){
				result.add("ARRUMAR CAMA");
			}
		}
		
		return result;
	}
	
	//Retorna o ultimo dia do mes
	public static List<DiasDTO> diasDoMes(){
		
		Calendar datas = new GregorianCalendar();
		int ultimoDia =  datas.getActualMaximum (Calendar.DAY_OF_MONTH);
		List<DiasDTO> retorno = new ArrayList<>();
		
		
		for(int i = 1; i <= ultimoDia; i++ ){
			DiasDTO dia = new DiasDTO();
			dia.setDia(i);
			retorno.add(dia);
		}
		
		return retorno;
	}
	
	//Retorna o mes vigente
	public static int mesVigente(){
		
		Date data = new Date();
		GregorianCalendar dataCal = new GregorianCalendar();
		dataCal.setTime(data);
		int mes = dataCal.get(Calendar.MONTH) + 1;
		
		return mes;
	}

	//Formata a data
	public static String geraData(Integer dia, Integer mesAtual, Integer anoAtual) throws ParseException {
		String aux = "";
		
		if(dia < 10){
			aux = "0" + dia.toString() + "/";  
		}else{
			aux = dia.toString() + "/";  
		}
		
		if(mesAtual < 10){
			aux = aux +  "0" + mesAtual.toString() + "/";
		}else{
			aux = aux + mesAtual.toString() + "/";
		}
		
		aux = aux + anoAtual.toString();
		
		return aux;
	}

	//Retorna o ano vigente
	public static Integer anoVigente() {
		Date data = new Date();
		GregorianCalendar dataCal = new GregorianCalendar();
		dataCal.setTime(data);
		int ano = dataCal.get(Calendar.YEAR);
		
		return ano;
	}
	
	//Converte String em data
	public static Date converteStringEmData(String aux) throws ParseException{
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date = (Date)formatter.parse(aux);
		
		return date;
	}
	

}
