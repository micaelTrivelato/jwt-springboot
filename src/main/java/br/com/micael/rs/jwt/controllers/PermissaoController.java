package br.com.micael.rs.jwt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.micael.rs.jwt.entities.Permissao;
import br.com.micael.rs.jwt.services.PermissaoServices;


@RestController
@RequestMapping(value = "/permissoes")
public class PermissaoController {
    
    @Autowired
    private PermissaoServices permissaoServices;

    @GetMapping
    private ResponseEntity<?>findAll(){
        return permissaoServices.findAll();
    } 

    @PostMapping()
    @ResponseBody
    private ResponseEntity<?>saveUsuario(@RequestBody Permissao permissao){
        return permissaoServices.saveUsuario(permissao);
    }
}
