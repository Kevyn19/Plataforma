package br.com.plataforma.ws.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.plataforma.ws.model.SolicitacaoServico;

@Repository
public interface SolicitacaoServicoRepository extends JpaRepository<SolicitacaoServico, Integer>{
	
	@Query(value="Select u from SolicitacaoServico u where u.usuarioPrestador.IdUsuario=:pid")
	public List<SolicitacaoServico> buscarPorUsuarioPrestador(@Param("pid") Integer pid);

}
