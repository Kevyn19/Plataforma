package br.com.plataforma.ws.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder.In;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.plataforma.ws.dto.GeolocalizacaoDTO;
import br.com.plataforma.ws.model.DadosPessoais;
import br.com.plataforma.ws.model.UsuarioPlataforma;
import br.com.plataforma.ws.service.DadosPessoaisService;
import br.com.plataforma.ws.service.PerfilService;
import br.com.plataforma.ws.service.UsuarioPlataformaService;
import br.com.plataforma.ws.utils.KeyToken;
import br.com.plataforma.ws.utils.Validador;

@RestController
public class UsuarioPlataformaController {
	
	@Autowired
	private UsuarioPlataformaService usuarioService;
	
	@Autowired
	private PerfilService perfilService;
	
	@Autowired
	DadosPessoaisService dadosPessoaisService;
	
	/**
	 * Cadastrar usuario na plataforma
	 * @param usuario
	 * @param idPerfil
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/usuarios/{idPerfil}",  consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<UsuarioPlataforma> cadastrarUsuario(@RequestBody UsuarioPlataforma usuario,@PathVariable Integer idPerfil) throws Exception {
		UsuarioPlataforma usuarioCadastrado = new UsuarioPlataforma();
		
		if(Validador.isUsuarioValido(usuario)){
			
			if(usuarioService.existeUsuarioNaBase(usuario.getLogin())){
				throw new Exception("Já existe usuario com este login!");
			}else{
				 usuario.setPerfil(perfilService.buscarPorId(idPerfil));
				 usuarioCadastrado = usuarioService.cadastrar(usuario);
			}
			
			
		}
		
		return new ResponseEntity<>(usuarioCadastrado, HttpStatus.CREATED);
	}
	
	/**
	 * Busca usuario prestador por geolocalização
	 * @param request
	 * @param distancia
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/usuarios/geo/{distancia}/{valor}", produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<List<GeolocalizacaoDTO>> buscarTodosDadosLocalServico(@RequestHeader(value="Authorization") String token,@PathVariable int distancia,@PathVariable int valor) throws Exception {
		
		
		String login = KeyToken.pegarUsuarioLogado(token);
		UsuarioPlataforma user = new UsuarioPlataforma();
		
		if(!KeyToken.isUsuarioTokenValido(login)){
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}else{
			user = usuarioService.buscarPorLogin(login);
		}
		
		List<GeolocalizacaoDTO> prestadores = new ArrayList<>();
		
		if (Validador.isUsuarioSolicitanteValido(user)) {
			
			DadosPessoais dadosParam = dadosPessoaisService.buscarPorUsuario(user.getIdUsuario());
			
			if (Validador.isDadosPessoaisValidos(dadosParam)) {
				 prestadores = dadosPessoaisService.buscarPrestadoresPorDistancia(distancia,dadosParam.getLatitude(), dadosParam.getLogitude(),valor);
			}else{
				//throw new Exception("Endereço ainda não cadastrado!");
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			
		}else {
			throw new Exception("Erro! Usuario inexistente ou parametros indevidos");
		}

		return new ResponseEntity<>(prestadores,HttpStatus.OK);
	}
	
	/**
	 * Retorna o perfil do usuario
	 * @param perfil
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/usuario/perfil", produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<Integer> buscarPerfil(@RequestHeader(value="Authorization") String token) throws Exception {
		
		Integer result = 0;

		String login = KeyToken.pegarUsuarioLogado(token);
		UsuarioPlataforma user = new UsuarioPlataforma();
		
		if(!KeyToken.isUsuarioTokenValido(login)){
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}else{
			user = usuarioService.buscarPorLogin(login);
		}
		
		
		if(Validador.isUsuarioValido(user)){
			result = user.getPerfil().getIdPerfil();
		}
		
		return new ResponseEntity<>(result,HttpStatus.OK);
		
	}
	
	/**
	 * Verifica se o usuario está logado
	 * @param token
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/usuario/logado", produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<Integer> varificaLogado(@RequestHeader(value="Authorization") String token) throws Exception {
		

		String login = KeyToken.pegarUsuarioLogado(token);
		UsuarioPlataforma user = new UsuarioPlataforma();
		
		if(!KeyToken.isUsuarioTokenValido(login)){
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}else{
			user = usuarioService.buscarPorLogin(login);
		}
		
		return new ResponseEntity<>(0,HttpStatus.OK);
		
	}
		

}
