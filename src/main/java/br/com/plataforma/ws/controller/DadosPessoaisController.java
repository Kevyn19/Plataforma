package br.com.plataforma.ws.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.plataforma.ws.model.DadosPessoais;
import br.com.plataforma.ws.model.UsuarioPlataforma;
import br.com.plataforma.ws.service.DadosPessoaisService;
import br.com.plataforma.ws.service.UsuarioPlataformaService;
import br.com.plataforma.ws.utils.Geolocalizacao;
import br.com.plataforma.ws.utils.KeyToken;
import br.com.plataforma.ws.utils.Validador;

@RestController
@RequestMapping("/restrito/dadosPessoais")
public class DadosPessoaisController {

	@Autowired
	DadosPessoaisService dadosPessoaisService;

	@Autowired
	private UsuarioPlataformaService usuarioService;

	/**
	 * Cadastrar dados pessoais do usuario
	 * @param dadosPessoais
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<DadosPessoais> cadastrarCliente(@RequestBody DadosPessoais dadosPessoais,@RequestHeader(value="Authorization") String token) throws Exception {

		String login = KeyToken.pegarUsuarioLogado(token);
		UsuarioPlataforma user = new UsuarioPlataforma();
		
		if(!KeyToken.isUsuarioTokenValido(login)){
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}else{
			user = usuarioService.buscarPorLogin(login);
		}

		if (Validador.isUsuarioValido(user)) {

			dadosPessoais.setUsuario(user);

			if (Validador.isDadosPessoaisValidos(dadosPessoais)) {

				List<String> listaCoordenadas = Geolocalizacao.buscaCoordenadas(dadosPessoais.getRuaOuAv(), dadosPessoais.getBairro(),
						dadosPessoais.getNumero(), dadosPessoais.getUf(), dadosPessoais.getCidade());
				
				String lat = Geolocalizacao.validaRetornoCoordGoogleLat(listaCoordenadas.get(0));
				String lng = Geolocalizacao.validaRetornoCoordGoogleLng(listaCoordenadas.get(1));

				dadosPessoais.setLatitude(Double.parseDouble(lat));
				dadosPessoais.setLogitude(Double.parseDouble(lng));

			}else{
				throw new Exception("Dados de endereço invalidos!");
			}

			dadosPessoaisService.cadastrar(dadosPessoais);

		} else {
			throw new Exception("Erro! Usuario inexistente ou parametros indevidos");
		}
		return new ResponseEntity<>(HttpStatus.CREATED);

	}

	/**
	 * Buscar dados pessoais do usuario
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<DadosPessoais> buscarDadosPessoaisPorToken(@RequestHeader(value="Authorization") String token) throws Exception {

		String login = KeyToken.pegarUsuarioLogado(token);
		UsuarioPlataforma user = new UsuarioPlataforma();
		
		if(!KeyToken.isUsuarioTokenValido(login)){
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}else{
			user = usuarioService.buscarPorLogin(login);
		}

		DadosPessoais dadosPessoaisBuscados = new DadosPessoais();

		if (Validador.isUsuarioValido(user)) {

			dadosPessoaisBuscados = dadosPessoaisService.buscarPorUsuario(user.getIdUsuario());
		}else{
			throw new Exception("Erro! Usuario inexistente ou parametros indevidos");
		}

		return new ResponseEntity<>(dadosPessoaisBuscados, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<DadosPessoais> buscarDadosPessoaisPorId(@PathVariable Integer id) throws Exception {

		UsuarioPlataforma user = usuarioService.buscarPorId(id);

		DadosPessoais dadosPessoaisBuscados = new DadosPessoais();

		if (Validador.isUsuarioValido(user)) {

			dadosPessoaisBuscados = dadosPessoaisService.buscarPorUsuario(user.getIdUsuario());
		}else{
			throw new Exception("Erro! Usuario inexistente ou parametros indevidos");
		}

		return new ResponseEntity<>(dadosPessoaisBuscados, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<DadosPessoais> alterarDadosPessoais(@RequestBody DadosPessoais dadosPessoais) {

		DadosPessoais dadosPessoaisAlterado = dadosPessoaisService.alterar(dadosPessoais);

		return new ResponseEntity<>(dadosPessoaisAlterado, HttpStatus.OK);
	}
	
	
	@RequestMapping(method = RequestMethod.POST, value = "/uploadFile")
	@ResponseBody
	private ResponseEntity<?> uploadFile(@RequestHeader(value="Authorization") String token,@RequestParam("uploadfile") MultipartFile uploadfile) throws Exception {
		
		String login = KeyToken.pegarUsuarioLogado(token);
		UsuarioPlataforma user = new UsuarioPlataforma();
		
		if(!KeyToken.isUsuarioTokenValido(login)){
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}else{
			user = usuarioService.buscarPorLogin(login);
		}
		
		DadosPessoais dadosPessoaisBuscados = new DadosPessoais();

		if (Validador.isUsuarioValido(user)) {
			dadosPessoaisBuscados = dadosPessoaisService.buscarPorUsuario(user.getIdUsuario());
		}else{
			throw new Exception("Erro! Usuario inexistente ou parametros indevidos");
		}
		 
		String path = new File("").getAbsolutePath();
		
		if(Validador.isDadosPessoaisValidos(dadosPessoaisBuscados)){
			 try {
				   
				    String filename = uploadfile.getOriginalFilename();
				    String directory = path + "/src/main/resources/static/imagens/";
				    String filepath = Paths.get(directory, filename).toString();
				    
				    // Save the file locally
				    BufferedOutputStream stream =
				        new BufferedOutputStream(new FileOutputStream(new File(filepath)));
				    stream.write(uploadfile.getBytes());
				    stream.close();
				    
				    dadosPessoaisBuscados.setUrl("./imagens/"+filename);
				    dadosPessoaisService.cadastrar(dadosPessoaisBuscados);
				  }
				  catch (Exception e) {
					  throw new Exception("Erro! Falha ao subir imagem.");
				  }
			
			
		}

		return new ResponseEntity<>(HttpStatus.CREATED);
	}


}
