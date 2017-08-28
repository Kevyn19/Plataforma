package br.com.plataforma.ws.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;

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

import br.com.plataforma.ws.dto.SolicitacoesServicoDTO;
import br.com.plataforma.ws.model.Agenda;
import br.com.plataforma.ws.model.DadosLocalServico;
import br.com.plataforma.ws.model.DadosPessoais;
import br.com.plataforma.ws.model.SolicitacaoServico;
import br.com.plataforma.ws.model.UsuarioPlataforma;
import br.com.plataforma.ws.service.AgendaService;
import br.com.plataforma.ws.service.DadosLocalServicoService;
import br.com.plataforma.ws.service.DadosPessoaisService;
import br.com.plataforma.ws.service.SolicitacaoServicoService;
import br.com.plataforma.ws.service.UsuarioPlataformaService;
import br.com.plataforma.ws.utils.Conversor;
import br.com.plataforma.ws.utils.KeyToken;

@RestController
@RequestMapping("/restrito/solicitacaoServico")
public class SolicitacaoServicoController {
	
	@Autowired
	SolicitacaoServicoService solicitacaoServicoService;
	
	@Autowired
	private UsuarioPlataformaService usuarioService;
	
	@Autowired
	AgendaService agendaService;
	
	@Autowired
	DadosPessoaisService dadosPessoaisService;
	
	@Autowired
	DadosLocalServicoService dadosLocalServicoService;

	
	/**
	 * Usuario solicitante cadastrar uma solicitacao de servico passando como param o Id do prestador e o dia
	 * @param token
	 * @param id
	 * @param dia
	 * @return
	 * @throws ServletException
	 * @throws ParseException
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/{id}/{dia}/{periodo}/{distancia}",  produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<SolicitacaoServico> cadastrarSolicitacaoServico(@RequestHeader(value="Authorization") String token,@PathVariable Integer id,@PathVariable Integer dia,
			@PathVariable Integer periodo, @PathVariable Integer distancia) throws ServletException, ParseException {
		
		SolicitacaoServico soliService = new SolicitacaoServico();
		
		String login = KeyToken.pegarUsuarioLogado(token);
		UsuarioPlataforma user = new UsuarioPlataforma();
		
		if(!KeyToken.isUsuarioTokenValido(login)){
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}else{
			user = usuarioService.buscarPorLogin(login);
		}

		UsuarioPlataforma usuarioPrestador = new UsuarioPlataforma();
		
		usuarioPrestador = usuarioService.buscarPorId(id);
		
		Integer mesAtual = Conversor.mesVigente();
		Integer anoAtual = Conversor.anoVigente();
		
		String data = Conversor.geraData(dia,mesAtual,anoAtual);
		
		soliService.setUsuarioContratante(user);
		soliService.setUsuarioPrestador(usuarioPrestador);
		soliService.setData(data);
		soliService.setStatus(1);
		soliService.setManhaOuTardeDiaria(periodo);
		soliService.setDistancia(distancia);
		
		SolicitacaoServico SolicitacaoServicoCadastrado = solicitacaoServicoService.cadastrar(soliService);

		return new ResponseEntity<>(SolicitacaoServicoCadastrado, HttpStatus.CREATED);
	}
	
	/**
	 * Busca a lista de solicitacoes de servico por prestador 
	 * @param token
	 * @param id
	 * @return
	 * @throws ServletException
	 * @throws ParseException 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/prestador", produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<List<SolicitacoesServicoDTO>> buscarSolicitacaoPorPrestador(@RequestHeader(value="Authorization") String token) throws ServletException, ParseException {
		
		List<SolicitacoesServicoDTO> result = new ArrayList<>();

		String login = KeyToken.pegarUsuarioLogado(token);
		UsuarioPlataforma user = new UsuarioPlataforma();
		
		if(!KeyToken.isUsuarioTokenValido(login)){
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}else{
			user = usuarioService.buscarPorLogin(login);
		}
		
		List<SolicitacaoServico> solicitacaoesPorUser = solicitacaoServicoService.buscarPorUsuarioPrestador(user.getIdUsuario());
		for (SolicitacaoServico solicitacaoServico : solicitacaoesPorUser) {
			SolicitacoesServicoDTO aux = new SolicitacoesServicoDTO();
			
			aux.setId(solicitacaoServico.getIdSolicitacaoServico());
			aux.setData(solicitacaoServico.getData());
			aux.setManhaOuTardeDiaria(solicitacaoServico.getManhaOuTardeDiaria());
			aux.setDistancia(solicitacaoServico.getDistancia());
			
			DadosPessoais solicitante = dadosPessoaisService.buscarPorUsuario(solicitacaoServico.getUsuarioContratante().getIdUsuario());
			
			DadosLocalServico local = dadosLocalServicoService.buscarPorUsuario(solicitante.getUsuario().getIdUsuario());
			
			aux.setTamanhoCasa(local.getTamanhoCasa());
			aux.setnComodos(local.getnComodos());
			aux.setNome(solicitante.getNome());
			
			
			result.add(aux);
		}

		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	/**
	 * Retorna count de solicitacoes de servico por prestador 
	 * @param token
	 * @return
	 * @throws ServletException
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/prestador/count", produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<Integer> countSolicitacaoPorPrestador(@RequestHeader(value="Authorization") String token) throws ServletException {

		String login = KeyToken.pegarUsuarioLogado(token);
		UsuarioPlataforma user = new UsuarioPlataforma();
		
		if(!KeyToken.isUsuarioTokenValido(login)){
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}else{
			user = usuarioService.buscarPorLogin(login);
		}
		
		Integer result = 0;
		
		Collection<SolicitacaoServico> solicitacaoesPorUser = solicitacaoServicoService.buscarPorUsuarioPrestador(user.getIdUsuario());
		
		if(!solicitacaoesPorUser.isEmpty()){
			result = solicitacaoesPorUser.size();
		}

		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	

	/**
	 * Prestador altera a solicitacao, confirmando ou não a solicitacao
	 * @param token
	 * @param solicitacaoServico
	 * @param id
	 * @return
	 * @throws ServletException
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/prestador/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<SolicitacaoServico> alterarSolicitacaoServico(@RequestHeader(value="Authorization") String token,@PathVariable Integer id) throws ServletException {
		
		String login = KeyToken.pegarUsuarioLogado(token);
		UsuarioPlataforma user = new UsuarioPlataforma();
		
		if(!KeyToken.isUsuarioTokenValido(login)){
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}else{
			user = usuarioService.buscarPorLogin(login);
		}
		
	     SolicitacaoServico solicitacaoServico = solicitacaoServicoService.buscarPorId(id);
		
		if(solicitacaoServico.getStatus() == 1){
			Agenda agenda = new Agenda();
			
			agenda.setStatus(1);
			agenda.setData(solicitacaoServico.getData());
			agenda.setManhaOuTardeDiaria(solicitacaoServico.getManhaOuTardeDiaria());
			agenda.setUsuarioPrestador(user);
			
			agendaService.cadastrar(agenda);
		}
		
		solicitacaoServico.setStatus(2);
		
		SolicitacaoServico solicitacaoServicoAlterado = solicitacaoServicoService.alterar(solicitacaoServico);

		return new ResponseEntity<>(solicitacaoServicoAlterado, HttpStatus.OK);
	}
	

}
