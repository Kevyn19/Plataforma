package br.com.plataforma.ws.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.plataforma.ws.model.UsuarioServico;

@Repository
public interface UsuarioServicoRepository extends JpaRepository<UsuarioServico, Integer>{
	
	@Query(value="Select u from UsuarioServico u where u.usuario.IdUsuario=:pid")
	public UsuarioServico buscarPorUsuario(@Param("pid") Integer pid);

	@Query(value="Select u from UsuarioServico u where u.usuario.IdUsuario=:pid AND u.diariaOuMeia=:pdiariaOuMeia")
	public UsuarioServico buscarPorDiariaOuMeia(@Param("pid") Integer id,@Param("pdiariaOuMeia") Integer diariaOuMeia);
	


}
