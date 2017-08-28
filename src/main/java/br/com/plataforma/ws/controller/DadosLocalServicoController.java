package br.com.plataforma.ws.controller;

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

import br.com.plataforma.ws.model.DadosLocalServico;
import br.com.plataforma.ws.model.UsuarioPlataforma;
import br.com.plataforma.ws.service.DadosLocalServicoService;
import br.com.plataforma.ws.service.UsuarioPlataformaService;
import br.com.plataforma.ws.utils.KeyToken;
import br.com.plataforma.ws.utils.Validador;

@RestController
@RequestMapping("/restrito/dadosLocalServico")
public class DadosLocalServicoController {
	
	@Autowired
	DadosLocalServicoService dadosLocalServicoService;
	
	@Autowired
	private UsuarioPlataformaService usuarioService;
	
	/**
	 * Cadastra os dados do local onde o servico será prestado
	 * @param dadosLocalServico
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<DadosLocalServico> cadastrarDadosLocalServico(@RequestBody DadosLocalServico dadosLocalServico, @RequestHeader(value="Authorization") String token) throws Exception {
		
		String login = KeyToken.pegarUsuarioLogado(token);
		UsuarioPlataforma user = new UsuarioPlataforma();
		
		if(!KeyToken.isUsuarioTokenValido(login)){
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}else{
			user = usuarioService.buscarPorLogin(login);
		}

		if (Validador.isUsuarioValido(user)) {
			dadosLocalServico.setUsuario(user);
			
			if(dadosLocalServico.getnComodos() > 0 && dadosLocalServico.getnComodos() > 0){
				dadosLocalServicoService.cadastrar(dadosLocalServico);
			}else{
				throw new Exception("Dados invalidos!");
			}
			
		}else {
			throw new Exception("Erro! Usuario inexistente ou parametros indevidos");
		}

		
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	/**
	 * Buscar o dados do local onde o servico será prestado
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<DadosLocalServico> buscarDadosLocalServicoPorId(@RequestHeader(value="Authorization") String token) throws Exception {
		
		String login = KeyToken.pegarUsuarioLogado(token);
		UsuarioPlataforma user = new UsuarioPlataforma();
		
		if(!KeyToken.isUsuarioTokenValido(login)){
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}else{
			user = usuarioService.buscarPorLogin(login);
		}
		

		DadosLocalServico dadosLocalServicoBuscados = new DadosLocalServico();

		if (Validador.isUsuarioSolicitanteValido(user)) {
			dadosLocalServicoBuscados = dadosLocalServicoService.buscarPorUsuario(user.getIdUsuario());
		}else{
			throw new Exception("Erro! Usuario inexistente ou parametros indevidos");
		}


		return new ResponseEntity<>(dadosLocalServicoBuscados, HttpStatus.OK);
	}
	
	

	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<DadosLocalServico> alterarDadosLocalServico(@RequestBody DadosLocalServico dadosLocalServico) {

		DadosLocalServico dadosLocalServicoAlterado = dadosLocalServicoService.alterar(dadosLocalServico);

		return new ResponseEntity<>(dadosLocalServicoAlterado, HttpStatus.OK);
	}
	

}
