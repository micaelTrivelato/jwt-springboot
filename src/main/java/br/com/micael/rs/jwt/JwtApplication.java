package br.com.micael.rs.jwt;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.micael.rs.jwt.entities.Permissao;
import br.com.micael.rs.jwt.entities.User;
import br.com.micael.rs.jwt.repositories.PermissaoRepo;
import br.com.micael.rs.jwt.repositories.UserRepo;

@SpringBootApplication
public class JwtApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner init(PermissaoRepo repo,UserRepo repo2){
		
		return (args)->{
			List<Permissao> permissaoSupremo = new ArrayList<>();
			permissaoSupremo.add(repo.save(new Permissao(0L,"Admin")));

			List<Permissao> permissaoGerente = new ArrayList<>();
			permissaoGerente.add(repo.save(new Permissao(0L,"Gerente")));

			repo2.save(new User(0L,"admin@admin.com",passwordEncoder().encode("3J.5.Q#fZ$9&*O"),"Admin", permissaoSupremo));
			repo2.save(new User(0L,"gerente@gerente.com",passwordEncoder().encode("3J.5.Q#fZ$9&*O"),"Gerente", permissaoGerente));
			};
	}

}
