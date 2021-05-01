package com.nava.course.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.nava.course.entities.User;
import com.nava.course.repositories.UserRepository;

@Configuration
@Profile("test") // o nome do perfil deve ser o mesmo do nome do pergil no arquivo
					// application.properties
public class TestConfig implements CommandLineRunner {

	// Vamos fazer o database seeding
	@Autowired // esta fazendo a injeção de dependencia
	private UserRepository userRepository;

	// todos os metodos serão iniciado quando a aplicação rodar
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub

		User u1 = new User(null, "Felipe Lino", "f.lino1934@hotmail.com", "981375682", "Resetar6");
		User u2 = new User(null, "Sarah Lino", "sarah@hotmail.com", "981375682", "Resetar6");

		userRepository.saveAll(Arrays.asList(u1, u2));

	}

}
