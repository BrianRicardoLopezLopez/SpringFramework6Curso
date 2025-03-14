package com.brian.curso.springboot.jpa.springboot_jpa_relationship;

// import java.util.ArrayList;
// import java.util.Arrays;
import java.util.HashSet;
// import java.util.List;
import java.util.Optional;
import java.util.Set;

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

		// System.out.println("Este es el metode save para client y invoice para un " +
		// "ManyToOne(muchos a uno)");
		// manyToOne();

		// System.out.println(
		// "Este es el metodo finbById donde busca el cliente y si encuentra a uno
		// asignado un nuevo registro en el invoice ");
		// manyToOneFindByIdClient();

		// System.out.println("Este es un nuevo metodo de OneToMany");
		// oneToMany();

		// System.out.println("Estes es un nuevo metodo de OneToManyFindByID");
		// oneToManyFindById();

		// System.out.println(
		// "Este es un nuevo metodo, Eliminar objetos dependientes o hijos en la " + "
		// relacion oneToMany parte 1 y parte 2 ");
		// removeAddress();

		// este metodo no funciona no elimina nada y el video no da soluciones a este
		// fallo
		// System.out.println("Este es un nuevo metodo, Eliminar objetos dependientes "
		// +"OneToMany con relacion existente");
		// removeAddressFindById();

		// System.out.println("Este es un nuevo metodo, Relacion oneToMany " + "bidireccional");
		// oneToManyInvoiceBidireccional();

		System.out.println("Este es un nuevo metodo, OneToMany bidireccional con relacion existente");
		oneToManyInvoiceBidireccionalFindById();

	}

	@Transactional
	public void oneToManyInvoiceBidireccionalFindById() {

		Optional<Client> optionalClient = clientRepository.findOne(1L);
		optionalClient.ifPresent(client -> {

			Invoice invoice1 = new Invoice("Compras de la casa", 5000L);
			Invoice invoice2 = new Invoice("Compras de oficina", 8000L);

			client.addInvoice(invoice1).addInvoice(invoice2);
			clientRepository.save(client);

			System.out.println("Clientes: " + client);

		});

	}

	@Transactional
	public void oneToManyInvoiceBidireccional() {

		Client client = new Client("Fran", "Moras");

		Invoice invoice1 = new Invoice("Compras de la casa", 5000L);
		Invoice invoice2 = new Invoice("Compras de oficina", 8000L);

		Set<Invoice> invoices = new HashSet<>();
		//List<Invoice> invoices = new ArrayList<>();
		invoices.add(invoice1);
		invoices.add(invoice2);
		client.setInvoices(invoices);

		invoice1.setClient(client);
		// Optimizacion del codigo con un nuevo metodo desde la clase Client otro codigo
		// que no funciona
		// client.addInvoice(invoice1).addInvoice(invoice2);
		invoice2.setClient(client);
		clientRepository.save(client);

		System.out.println("Clientes: " + client);

	}

	@Transactional
	public void removeAddressFindById() {

		Optional<Client> optionalClient = clientRepository.findById(2L);
		optionalClient.ifPresent(client -> {
			Address address1 = new Address("El verjel", 1234);
			Address address2 = new Address("Vasco de Gama", 9875);

			
			//client.setAddresses(Arrays.asList(address1, address2));
			Set<Address> addresses = new HashSet<>();
			addresses.add(address1);
			addresses.add(address2);

			clientRepository.save(client);

			System.out.println("Antes de modificar la eliminacion: " + client);

			Optional<Client> optionalClient2 = clientRepository.findOneWithAddresses(2L);
			optionalClient2.ifPresent(c -> {
				c.getAddresses().remove(address2);
				clientRepository.save(c);
				System.out.println("Cliente modificado eliminado: " + c);
			});
		});

	}

	@Transactional
	public void removeAddress() {

		Client client = new Client("Fran", "Moras");

		Address address1 = new Address("El verjel", 1234);
		Address address2 = new Address("Vasco de Gama", 9875);

		client.getAddresses().add(address1);
		client.getAddresses().add(address2);

		clientRepository.save(client);

		System.out.println("Antes de modificar: " + client);

		Optional<Client> optionalClient = clientRepository.findById(3L);
		optionalClient.ifPresent(c -> {
			c.getAddresses().remove(address1);
			clientRepository.save(c);
			System.out.println("Cliente modificado: " + c);
		});
	}

	@Transactional
	public void oneToManyFindById() {

		System.out.println(
				"Este Metodo vamos a buscar un cliente existente y asignar las direcciones o creamos las nuevas direcciones");

		Optional<Client> optionalClient = clientRepository.findById(2L);
		optionalClient.ifPresent(client -> {
			Address address1 = new Address("El verjel", 1234);
			Address address2 = new Address("Vasco de Gama", 9875);

			Set<Address> addresses = new HashSet<>();
			addresses.add(address1);
			addresses.add(address2);
			// client.setAddresses(Arrays.asList(address1, address2));

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
		// hola

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
