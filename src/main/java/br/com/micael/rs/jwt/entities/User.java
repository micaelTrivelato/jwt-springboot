package br.com.micael.rs.jwt.entities;

import java.util.ArrayList;
import java.util.Collection;

import javax.net.ssl.ExtendedSSLSession;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String email;
    private String senha;
    private String nome;

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Permissao>permissoes=new ArrayList<>();
}
