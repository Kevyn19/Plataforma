package br.com.plataforma.ws.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.plataforma.ws.model.DadosPessoais;

@Repository
public interface DadosPessoaisRepository extends JpaRepository<DadosPessoais, Integer> {
	
	@Query(value="Select u from DadosPessoais u where u.usuario.IdUsuario=:pid")
	public DadosPessoais buscarPorUsuario(@Param("pid") Integer pid);

	@Query(value="SELECT nome, "+
				 " (6371 * acos( "+
				 " cos( radians(?2) ) "+
				 " * cos( radians( latitude ) ) "+
				 " * cos( radians( logitude ) - radians(?3) ) "+
				 " + sin( radians(?2) ) "+
				 " * sin( radians(latitude) ) "+ 
				 " ) "+
				" ) AS distancia, dados_pessoais.usuario_id_usuario "+
				 " FROM dados_pessoais  "+
				 " INNER JOIN usuario_plataforma ON dados_pessoais.usuario_id_usuario = usuario_plataforma.id_usuario  "+
				 " INNER JOIN usuario_servico ON dados_pessoais.usuario_id_usuario = usuario_servico.usuario_id_usuario "+
			     " AND usuario_plataforma.perfil_id_perfil = 1 AND usuario_servico.valor < ?4"+
				 " HAVING distancia < ?1 "+
				 " ORDER BY distancia ASC LIMIT 25",nativeQuery = true)
	public List<Object> buscarPrestadoresPorDistancia(@Param("dis") int distancia,@Param("lat") double latitude,@Param("lng") double logitude,@Param("valor") int valor);


}
