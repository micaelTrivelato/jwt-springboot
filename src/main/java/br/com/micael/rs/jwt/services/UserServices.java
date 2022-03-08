package br.com.micael.rs.jwt.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.micael.rs.jwt.entities.Permissao;
import br.com.micael.rs.jwt.entities.User;
import br.com.micael.rs.jwt.repositories.PermissaoRepo;
import br.com.micael.rs.jwt.repositories.UserRepo;
import lombok.RequiredArgsConstructor;


@Service
@Transactional
@RequiredArgsConstructor
public class UserServices implements UserDetailsService{
    
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PermissaoRepo permissaoRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(username);
        if(user == null){
            throw new UsernameNotFoundException("Usuário não existe");
        } 
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getPermissoes().forEach(permissao -> 
        { 
            authorities.add(new SimpleGrantedAuthority(permissao.getNomePermissao()));
        });
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getSenha(), authorities);
    }

    public ResponseEntity<List<User>>findAll(){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userRepo.findAll());
        } catch (InvalidDataAccessResourceUsageException e) {
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    public ResponseEntity<User>findByEmail(String email){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userRepo.findByEmail(email));
        } catch (InvalidDataAccessResourceUsageException e) {
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    public ResponseEntity<User>saveUsuario(User user){
        try {
            user.setSenha(passwordEncoder.encode(user.getSenha()));
            User userSave = userRepo.save(user);       
            return ResponseEntity.status(HttpStatus.OK).body(userSave);
        } catch (InvalidDataAccessResourceUsageException e) {
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Transactional
    public ResponseEntity<?> addPermissaoToUser(String email,String nomePermissao){
        try{
            User user=userRepo.findByEmail(email);
            if(user==null)
                return ResponseEntity.badRequest().body("Usuário não existe");
            Permissao permissao=permissaoRepo.findByNomePermissao(nomePermissao);
            if(permissao==null)
                return ResponseEntity.badRequest().body("Permissão não existe");
            if(user.getPermissoes().stream().filter(e->e.getNomePermissao().equals(nomePermissao)).count()==0){
                user.getPermissoes().add(permissao);
            }else{
                return ResponseEntity.badRequest().body("Usuário já tem essa permissão "+"(Escolha outra permissão)");
            }
        return ResponseEntity.ok().body("Permissão adicionada para "+user.getNome());
        }catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
        
    }

    @Transactional
    public ResponseEntity<?> removePermissaoToUser(String email,String nomePermissao){
        try{
            User user=userRepo.findByEmail(email);
            if(user==null)
                return ResponseEntity.badRequest().body("Usuário não existe");
            Permissao permissao=permissaoRepo.findByNomePermissao(nomePermissao);
            if(permissao==null)
                return ResponseEntity.badRequest().body("Permissão não existe");
            user.getPermissoes().remove(permissao);
        return ResponseEntity.ok().body("Permissão removida de "+user.getNome());
        }catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
        
    }

    
}
