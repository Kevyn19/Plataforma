package br.com.plataforma.ws.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.plataforma.ws.model.DadosLocalServico;

public interface DadosLocalServicoRepository extends JpaRepository<DadosLocalServico, Integer>{

	@Query(value="Select u from DadosLocalServico u where u.usuario.IdUsuario=:pid")
	public DadosLocalServico buscarPorUsuario(@Param("pid") Integer pid);
	
}
