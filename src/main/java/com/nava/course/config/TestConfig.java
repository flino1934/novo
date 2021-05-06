package com.nava.course.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.nava.course.entities.Category;
import com.nava.course.entities.Order;
import com.nava.course.entities.OrderItem;
import com.nava.course.entities.Payment;
import com.nava.course.entities.Product;
import com.nava.course.entities.User;
import com.nava.course.entities.enums.OrderStatus;
import com.nava.course.repositories.CategoryRepositories;
import com.nava.course.repositories.OrderItemRepositories;
import com.nava.course.repositories.OrderRepository;
import com.nava.course.repositories.ProductRepository;
import com.nava.course.repositories.UserRepository;

@Configuration
@Profile("test") // o nome do perfil deve ser o mesmo do nome do pergil no arquivo
					// application.properties
public class TestConfig implements CommandLineRunner {

	// Vamos fazer o database seeding
	@Autowired // esta fazendo a injeção de dependencia
	private UserRepository userRepository;

	@Autowired // esta fazendo a injeção de dependencia
	private OrderRepository orderRepository;

	@Autowired // esta fazendo a injeção de dependencia
	private CategoryRepositories categoryRepository;

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private OrderItemRepositories orderItemRepositorie;

	// todos os metodos serão iniciado quando a aplicação rodar
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub

		Category cat1 = new Category(null, "Eletronics");
		Category cat2 = new Category(null, "Book");
		Category cat3 = new Category(null, "Computers");

		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));

		Product p1 = new Product(null, "The Lord of the Rings", "Lorem ipsum dolor sit amet, consectetur.", 90.5, "");
		Product p2 = new Product(null, "Smart TV", "Nulla eu imperdiet purus. Maecenas ante.", 2190.0, "");
		Product p3 = new Product(null, "Macbook Pro", "Nam eleifend maximus tortor, at mollis.", 1250.0, "");
		Product p4 = new Product(null, "PC Gamer", "Donec aliquet odio ac rhoncus cursus.", 1200.0, "");
		Product p5 = new Product(null, "Rails for Dummies", "Cras fringilla convallis sem vel faucibus.", 100.99, "");

		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));

		p1.getCategories().add(cat2);// estou acessando a lista de categorias e falando que o p1 é da cat 2
		p2.getCategories().add(cat1);// estou acessando a lista de categorias e falando que o p2 é da cat 1
		p2.getCategories().add(cat3);// estou acessando a lista de categorias e falando que o p1 é da cat 3
		p3.getCategories().add(cat3);// estou acessando a lista de categorias e falando que o p3 é da cat 3
		p4.getCategories().add(cat3);
		p5.getCategories().add(cat2);

		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));

		User u1 = new User(null, "Felipe Lino", "f.lino1934@hotmail.com", "981375682", "Resetar6");
		User u2 = new User(null, "Sarah Lino", "sarah@hotmail.com", "981375682", "Resetar6");

		userRepository.saveAll(Arrays.asList(u1, u2));

		Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), OrderStatus.PAID, u1);
		Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), OrderStatus.WAITING_PAYMENT, u2);
		Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), OrderStatus.WAITING_PAYMENT, u1);

		orderRepository.saveAll(Arrays.asList(o1, o2, o3));

		OrderItem oi1 = new OrderItem(o1, p1, 2, p1.getPrice()); 
		OrderItem oi2 = new OrderItem(o1, p3, 1, p3.getPrice()); 
		OrderItem oi3 = new OrderItem(o2, p3, 2, p3.getPrice()); 
		OrderItem oi4 = new OrderItem(o3, p5, 2, p5.getPrice()); 
		
		orderItemRepositorie.saveAll(Arrays.asList(oi1,oi2,oi3,oi4));

		Payment pay1 = new Payment(null, Instant.parse("2019-06-20T21:53:07Z"), o1);
		o1.setPayment(pay1);//pela associação de mapeamento relacional o pagamento é passado para a classe dependente que é a classe order(pedido) pois pode haver um pedido sem pagamento mas não um pagamento sem pedido
		
		orderRepository.save(o1);// por ser uma classe depente ela não será responsavel por persistir seus dados e sim a classe que ela depende pois pode haver um pedido sem pagamento mas não um pagamento sem pedido
		
	}

}
