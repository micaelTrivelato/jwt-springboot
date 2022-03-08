package br.com.micael.rs.jwt.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.micael.rs.jwt.entities.Permissao;
import br.com.micael.rs.jwt.repositories.PermissaoRepo;

@Service
public class PermissaoServices {
    @Autowired
    private PermissaoRepo permissaoRepo;

    public ResponseEntity<List<Permissao>>findAll(){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(permissaoRepo.findAll());
        } catch (InvalidDataAccessResourceUsageException e) {
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    public ResponseEntity<Permissao>saveUsuario(Permissao permissao){
        try {
            Permissao permissaoSave = permissaoRepo.save(permissao);
            return ResponseEntity.status(HttpStatus.OK).body(permissaoSave);
        } catch (InvalidDataAccessResourceUsageException e) {
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
