package br.com.plataforma.ws.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.plataforma.ws.dto.GeolocalizacaoDTO;
import br.com.plataforma.ws.model.DadosPessoais;
import br.com.plataforma.ws.repository.DadosPessoaisRepository;

@Service
public class DadosPessoaisService {
	
	@Autowired
	DadosPessoaisRepository dadosPessoaisRepository;
	
	// Negocios
		public DadosPessoais cadastrar(DadosPessoais dadosPessoais) {

			return dadosPessoaisRepository.save(dadosPessoais);
		}

		public Collection<DadosPessoais> buscarTodos() {
			return dadosPessoaisRepository.findAll();
		}
		
		public void excluir(DadosPessoais dadosPessoais) {
			dadosPessoaisRepository.delete(dadosPessoais);

		}
		
		public DadosPessoais alterar(DadosPessoais dadosPessoais){
			return dadosPessoaisRepository.save(dadosPessoais);
		}
		
		public DadosPessoais buscarPorUsuario(Integer id) {
			return dadosPessoaisRepository.buscarPorUsuario(id);

		}

		public List<GeolocalizacaoDTO> buscarPrestadoresPorDistancia(int distancia, double latitude, double logitude, int valor) {
			if(distancia == 0 || distancia < 0){
				distancia = 100000000;
			}
			
			List<Object> list =  dadosPessoaisRepository.buscarPrestadoresPorDistancia(distancia,latitude,logitude,valor);
			List<GeolocalizacaoDTO> listaCoordenadas = new ArrayList<>();
			
			Iterator itr = list.iterator();
			while(itr.hasNext()){
			   GeolocalizacaoDTO temp = new GeolocalizacaoDTO();
			   Object[] obj = (Object[]) itr.next();
			  
			   temp.setNome(String.valueOf(obj[0]));
			   Double d = Double.parseDouble(String.valueOf(obj[1])); 
			   temp.setDistancia(d.intValue());
			   temp.setId(Integer.parseInt(String.valueOf(obj[2]))); 
			   
			   listaCoordenadas.add(temp);
			
			}
			
			return listaCoordenadas;
		}

}
