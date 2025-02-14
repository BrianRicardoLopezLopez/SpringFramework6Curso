package com.brian.curso.springboot.jpa.springboot_jpa_relationship;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import com.brian.curso.springboot.jpa.springboot_jpa_relationship.entities.Address;
import com.brian.curso.springboot.jpa.springboot_jpa_relationship.entities.Client;
import com.brian.curso.springboot.jpa.springboot_jpa_relationship.entities.Invoice;
import com.brian.curso.springboot.jpa.springboot_jpa_relationship.repositories.ClientRepository;
import com.brian.curso.springboot.jpa.springboot_jpa_relationship.repositories.InvoiceRepository;

@SpringBootApplication
public class SpringbootJpaRelationshipApplication implements CommandLineRunner {

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private InvoiceRepository invoiceRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJpaRelationshipApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// System.out.println("Este es el metode save para client y invoice para un
		// ManyToOne(muchos a uno)");
		// manyToOne();

		// System.out.println("Este es el metodo finbById donde busca el cliente y si
		// encuentra a uno asignado un nuevo registro en el invoice ");
		// manyToOneFindByIdClient();

		// System.out.println("Este es un nuevo metodo de OneToMany");
		// oneToMany();

		System.out.println("Estes es un nuevo metodo de OneToManyFindByID");
		oneToManyFindById();
	}

	@Transactional
	public void oneToManyFindById() {

		System.out.println(
				"Este Metodo vamos a buscar un cliente existente y asignar las direcciones o creamos las nuevas direcciones");

		Optional<Client> optionalClient = clientRepository.findById(2L);
		optionalClient.ifPresent(client -> {
			Address address1 = new Address("El verjel", 1234);
			Address address2 = new Address("Vasco de Gama", 9875);
	
			client.setAddresses(Arrays.asList(address1,address2));
	
			clientRepository.save(client);
	
			System.out.println(client);
		});

	
	}

	@Transactional
	public void oneToMany() {

		System.out.println(
				"Este es un nuevo metodo de OneToMany(uno a muchos) donde vamos a crear el cliente y direccion y los crea cliente ");
		Client client = new Client("Fran", "Moras");

		Address address1 = new Address("El verjel", 1234);
		Address address2 = new Address("Vasco de Gama", 9875);

		client.getAddresses().add(address1);
		client.getAddresses().add(address2);

		clientRepository.save(client);

		System.out.println(client);
	}

	@Transactional
	public void manyToOne() {

		System.out.println(
				"Guardamos los datos Client y Invoice para hacer el ManyToOne(Muchos a uno) y le asigna la factura al que vaya buscado");

		Client client = new Client("John", "Doe");
		clientRepository.save(client);

		Invoice invoice = new Invoice("Compras de oficinas", 2000L);
		invoice.setClient(client);
		Invoice invoiceDB = invoiceRepository.save(invoice);
		System.out.println(invoiceDB);
		//hola

	}

	@Transactional
	public void manyToOneFindByIdClient() {

		System.out.println(
				"Aqui hacemos la busqueda por findById para buscar el cliente y si encuentea uno crea un Invoice nuevo registro");

		Optional<Client> optionalClient = clientRepository.findById(1L);

		if (optionalClient.isPresent()) {

			Client client = optionalClient.orElseThrow();

			Invoice invoice = new Invoice("Compras de oficinas", 2000L);
			invoice.setClient(client);
			Invoice invoiceDB = invoiceRepository.save(invoice);
			System.out.println(invoiceDB);

		}

	}

}
