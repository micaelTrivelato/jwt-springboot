package br.com.micael.rs.jwt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.micael.rs.jwt.entities.Permissao;


public interface PermissaoRepo extends JpaRepository<Permissao,Long> {

    public Permissao findByNomePermissao(String nomePermissao);
    
}
