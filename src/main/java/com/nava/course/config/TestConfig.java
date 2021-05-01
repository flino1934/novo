package com.nava.course.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.nava.course.entities.Order;
import com.nava.course.entities.User;
import com.nava.course.entities.enums.OrderStatus;
import com.nava.course.repositories.OrderRepository;
import com.nava.course.repositories.UserRepository;

@Configuration
@Profile("test") // o nome do perfil deve ser o mesmo do nome do pergil no arquivo application.properties
public class TestConfig implements CommandLineRunner {

	// Vamos fazer o database seeding
	@Autowired // esta fazendo a injeção de dependencia
	private UserRepository userRepository;
	
	@Autowired // esta fazendo a injeção de dependencia
	private OrderRepository orderRepository;

	// todos os metodos serão iniciado quando a aplicação rodar
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub

		User u1 = new User(null, "Felipe Lino", "f.lino1934@hotmail.com", "981375682", "Resetar6");
		User u2 = new User(null, "Sarah Lino", "sarah@hotmail.com", "981375682", "Resetar6");

		userRepository.saveAll(Arrays.asList(u1, u2));
		
		Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"),OrderStatus.PAID, u1); 
		Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"),OrderStatus.WAITING_PAYMENT, u2); 
		Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"),OrderStatus.WAITING_PAYMENT, u1);
		
		orderRepository.saveAll(Arrays.asList(o1,o2,o3));

	}

}
