package br.com.micael.rs.jwt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.micael.rs.jwt.entities.User;
import br.com.micael.rs.jwt.services.UserServices;


@RestController
@RequestMapping(value = "/users")
public class UserController {
    @Autowired
    private UserServices userServices;

    @GetMapping
    private ResponseEntity<?>findAll(){
        return userServices.findAll();
    } 

    @GetMapping("/{email}")
    private ResponseEntity<?>findByEmail(@PathVariable("email") String email){
        return userServices.findByEmail(email);
    }

    @PostMapping("/save")
    @ResponseBody
    private ResponseEntity<User>saveUsuario(@RequestBody User user){
        return userServices.saveUsuario(user);
    }

    @PutMapping("/add-permissao-to-user/{email}/{nomePermissao}")
    private ResponseEntity<?>addPermissaoToUser(@PathVariable("email") String email,@PathVariable("nomePermissao") String nomePermissao){
        return userServices.addPermissaoToUser(email, nomePermissao);
    }

    @PutMapping("/remove-permissao-to-user/{email}/{nomePermissao}")
    private ResponseEntity<?>removePermissaoToUser(@PathVariable("email") String email,@PathVariable("nomePermissao") String nomePermissao){
        return userServices.removePermissaoToUser(email, nomePermissao);
    }
}
