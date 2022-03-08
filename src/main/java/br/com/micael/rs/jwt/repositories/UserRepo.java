package br.com.micael.rs.jwt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.micael.rs.jwt.entities.User;


public interface UserRepo extends JpaRepository<User,Long>{
    public User findByEmail(String email);
    public User findByNome(String nome);
}
