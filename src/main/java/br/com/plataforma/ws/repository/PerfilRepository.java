package br.com.plataforma.ws.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.plataforma.ws.model.Perfil;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Integer>{

}
