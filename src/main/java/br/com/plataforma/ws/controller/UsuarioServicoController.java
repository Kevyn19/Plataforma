package br.com.plataforma.ws.controller;

import java.util.List;

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

import br.com.plataforma.ws.dto.DiasDTO;
import br.com.plataforma.ws.model.UsuarioPlataforma;
import br.com.plataforma.ws.model.UsuarioServico;
import br.com.plataforma.ws.service.UsuarioPlataformaService;
import br.com.plataforma.ws.service.UsuarioServicoService;
import br.com.plataforma.ws.utils.Conversor;
import br.com.plataforma.ws.utils.KeyToken;
import br.com.plataforma.ws.utils.Validador;

@RestController
@RequestMapping("/restrito/usuarioServico")
public class UsuarioServicoController {
	
	@Autowired
	UsuarioServicoService usuarioServicoService;
	
	@Autowired
	private UsuarioPlataformaService usuarioService;
	
	
	/**
	 * Cadastrar a lista de servicos prestados pelo usuario prestador
	 * @param usuarioServico
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<UsuarioServico> cadastrarUsuarioServico(@RequestBody UsuarioServico usuarioServico, @RequestHeader(value="Authorization") String token) throws Exception {
		
		String login = KeyToken.pegarUsuarioLogado(token);
		UsuarioPlataforma user = new UsuarioPlataforma();
		
		if(!KeyToken.isUsuarioTokenValido(login)){
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}else{
			user = usuarioService.buscarPorLogin(login);
		}
		

		if (Validador.isUsuarioPrestadorValido(user)) {
			
			usuarioServico.setUsuario(user);
			
			if((usuarioServico.getDiariaOuMeia() != 0) && (usuarioServico.getValor() != 0 && usuarioServico.getValor() > 0) && (usuarioServico.getServicos() != null && !usuarioServico.getServicos().equals(""))){
				
				usuarioServico.setServicosCompac(Conversor.geraString(usuarioServico.getServicos()));
				usuarioServicoService.cadastrar(usuarioServico);
			}else{
				throw new Exception("Dados Invalidos!");
			}
			
		}else {
			throw new Exception("Erro! Usuario inexistente ou parametros indevidos");
		}

		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	/**
	 * Buscar a lista de servicos prestados pelo usuario prestador
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<UsuarioServico> buscarUsuarioServicoPorId(@RequestHeader(value="Authorization") String token) throws Exception {
		
		String login = KeyToken.pegarUsuarioLogado(token);
		UsuarioPlataforma user = new UsuarioPlataforma();
		
		if(!KeyToken.isUsuarioTokenValido(login)){
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}else{
			user = usuarioService.buscarPorLogin(login);
		}
		
		
		UsuarioServico usuarioServicoBuscados = new UsuarioServico();
		
		if (Validador.isUsuarioPrestadorValido(user)) {

			usuarioServicoBuscados = usuarioServicoService.buscarPorUsuario(user.getIdUsuario());
		}else{
			throw new Exception("Erro! Usuario inexistente ou parametros indevidos");
		}


		return new ResponseEntity<>(usuarioServicoBuscados, HttpStatus.OK);
	}
	
	/**
	 * Retorna a lista de servicos prestados por prestador
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method = RequestMethod.GET,  value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<List<String>> buscarServicoPorId(@PathVariable Integer id) throws Exception {
		
		UsuarioServico usuarioServico = usuarioServicoService.buscarPorUsuario(id);
		
		List<String> result = Conversor.geraLista(usuarioServico.getServicosCompac());
	
		return new ResponseEntity<>(result, HttpStatus.OK);
		
	}
	
	/**
	 * Retorna os dias do mes vigente
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method = RequestMethod.GET,  value = "/diasDoMes", produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<List<DiasDTO>> buscarDiasMes() throws Exception {
		
		
		List<DiasDTO> retorno = Conversor.diasDoMes();
	
		return new ResponseEntity<>(retorno, HttpStatus.OK);
		
	}
	
	/**
	 * Retorna o valor da diaria ou meia
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method = RequestMethod.GET,  value = "/valor/{id}/{diariaOuMeia}", produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<Double> buscarValor(@PathVariable Integer id, @PathVariable Integer diariaOuMeia) throws Exception {
		
		Double valor = 0.0;
		UsuarioServico usuarioServico = usuarioServicoService.buscarPorDiariaOuMeia(id,diariaOuMeia);
		if(usuarioServico != null && usuarioServico.getValor() != 0.0){
			valor = usuarioServico.getValor();
		}
	
		return new ResponseEntity<>(valor, HttpStatus.OK);
		
	}
	

	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<UsuarioServico> alterarUsuarioServico(@RequestBody UsuarioServico usuarioServico) {

		UsuarioServico usuarioServicoAlterado = usuarioServicoService.alterar(usuarioServico);

		return new ResponseEntity<>(usuarioServicoAlterado, HttpStatus.OK);
	}
	

}
