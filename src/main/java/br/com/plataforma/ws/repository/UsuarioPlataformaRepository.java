package br.com.plataforma.ws.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.plataforma.ws.model.UsuarioPlataforma;

@Repository
public interface UsuarioPlataformaRepository extends JpaRepository<UsuarioPlataforma, Integer>{
	
	@Query(value="Select u from UsuarioPlataforma u where u.login=:plogin")
	public UsuarioPlataforma bsucarPorLogin(@Param("plogin") String login);

}
