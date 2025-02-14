package com.brian.curso.springboot.jpa.springboot_jpa;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import com.brian.curso.springboot.jpa.springboot_jpa.dto.PersonDto;
import com.brian.curso.springboot.jpa.springboot_jpa.entities.Person;
import com.brian.curso.springboot.jpa.springboot_jpa.repositories.PersonRepository;

@SpringBootApplication
public class SpringbootJpaApplication implements CommandLineRunner {

	@Autowired
	private PersonRepository personRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// System.out.println("Este es de una List de personas");
		// list();
		// System.out.println("Este es de FindById");
		// findOne();

		// System.out.println("/n");
		// System.out.println("Este es para crear un nuevo registro ");
		// create();

		// System.out.println("/n");
		// System.out.println("Este es para actualizar un datos ya registrado");
		update();

		// este solo elimina los registro que esta no tiene alguna exception o metodo
		// para que vea que existe o no
		// System.out.println("Este es para eliminar el usuario por medio del id");
		// delete();

		// System.out.println("Este es para eliminar el usuario existes o no por medio
		// del id");
		// delete2();

		// System.out.println("Este es el metodo personalizado por busqueda del campo id
		// ");
		// personalizeQueries();

		// System.out.println("Es un nuevo metodo de personalizacion 2");
		// personalizeQueries2();

		// System.out.println("Es un nuevo metodo de personalizacion Distinct");
		// personalizeQueriesDistinct()

		// System.out.println("Este es nuevo metodo personalizado ConcaUpperLowerCase");
		// personalizeQueriesConcatUpperLowerCase();

		// System.out.println("Este es un nuevo metodo Between qye busca por rango");
		// personalizeQueriesBetween();

		// System.out.println("Este es un nuevo metodo de QueriesFuctionAggregation");
		// queriesFuctionAggregation();

		// System.out.println("Este es un nuevo metodo para hacer subConsultas(subQueries)");
		// subQueries();
		// System.out.println("Este es un nuevo metodo Operador where in en JPQL");
		// whereIn();

	}
	@Transactional(readOnly = true)
	public void whereIn() {

		System.out.println("============= Consulta where in =============  ");
		List<Person> persons = personRepository.getPersonByIds();
		persons.forEach(System.out::println);

		System.out.println("============= Consulta where in por parametros =============  ");
		List<Person> personsbyParameter = personRepository.getPersonByIdsByParameter(Arrays.asList(1L, 2L, 5L, 7L));
		personsbyParameter.forEach(System.out::println);

		System.out.println("============= Consulta where in por parametros no enlistar lo que te mande =============  ");
		List<Person> pList = personRepository.getPersonByIdsByParameterNotIn(Arrays.asList(1L, 2L, 5L, 7L));
		pList.forEach(System.out::println);
	}

	@Transactional(readOnly = true)
	public void subQueries() {
		System.out.println("============= Consulta por el nombre mas corte y su largo =============  ");
		List<Object[]> registers = personRepository.getShorterName();
		registers.forEach(reg -> {
			String name = (String) reg[0];
			Integer length = (Integer) reg[1];
			System.out.println("Name = " + name + ", Length = " + length);
		});
		System.out.println("============= Consulta por el nombre mas largo y su largo =============  ");
		List<Object[]> registersMax = personRepository.getLongerName();
		registersMax.forEach(reg -> {
			String name = (String) reg[0];
			Integer length = (Integer) reg[1];
			System.out.println("Name = " + name + ", Length = " + length);
		});

		System.out.println("============= Consulta para obtener el ultimo registro de persona =============  ");
		Optional<Person> optionalPerson = personRepository.getLastRegistration();
		optionalPerson.ifPresent(System.out::println);

	}


	@Transactional(readOnly = true)
	public void queriesFuctionAggregation() {

		System.out.println("============= Consulta con el total de registro de la tabla persona =============  ");
		Long count = personRepository.getTotalPerson();
		System.out.println(count);

		System.out.println("============= Consulta con el valor minimo del id =============  ");
		Long min = personRepository.getMinId();
		System.out.println(min);

		System.out.println("============= Consulta con el valor maximo del id =============  ");
		Long max = personRepository.getMaxId();
		System.out.println(max);

		System.out.println("============= Consulta con el nombre y su largo ============= ");
		List<Object[]> regs = personRepository.getPersonNameLenght();
		regs.forEach(reg -> {
			String name = (String) reg[0];
			Integer length = (Integer) reg[1];
			System.out.println("Name = " + name + ", Length = " + length);
		});

		System.out.println("============= Consulta con el nombre mas corto =============  ");
		Integer minNameLength = personRepository.getMinLengthName();
		System.out.println(minNameLength);

		System.out.println("============= Consulta con el nombre mas largo =============  ");
		Integer maxNameLength = personRepository.getMaxLengthName();
		System.out.println(maxNameLength);

		System.out.println(
				"============= Consulta resumen de fuctiones de agregacion min,max,sum,avg y count =============");
		Object[] resumeReg = (Object[]) personRepository.getResumeAggregationFuction();
		System.out.println(
				"min = " + resumeReg[0] +
						", max = " + resumeReg[1] +
						", sum = " + resumeReg[2] +
						", avg = " + resumeReg[3] +
						", count = " + resumeReg[4]);

	}

	@Transactional(readOnly = true)
	public void personalizeQueriesBetween() {
		System.out.println("============= Consulta por rangos de id =============");
		List<Person> persons = personRepository.findAllBetweeId(2L, 5L);
		persons.forEach(System.out::println);

		System.out.println(" ============= Propia del CrudRepository del between id =============");
		persons = personRepository.findByIdBetween(2L, 5L);
		persons.forEach(System.out::println);

		System.out.println("============= Consulta por rangos por letras name =============");
		persons = personRepository.findAllBetweenName("J", "Q");
		persons.forEach(System.out::println);

		System.out.println("============= Propia del CrudRepository del between name =============");
		persons = personRepository.findByNameBetween("J", "Q");
		persons.forEach(System.out::println);

		System.out.println("============= Consulta por rango de id por order by name =============");
		persons = personRepository.findAllBetweeIdOrderBy(2L, 5L);
		persons.forEach(System.out::println);

		System.out.println("============= Consulta por rangos por letras name order by name=============");
		persons = personRepository.findAllBetweenNameOrderBy("J", "Q");
		persons.forEach(System.out::println);

		System.out.println("============= Consulta por rango de id por order by name por asc =============");
		persons = personRepository.findAllBetweeIdOrderByAsc(2L, 5L);
		persons.forEach(System.out::println);

		System.out.println("============= Consulta por rangos por letras name order by name por asc=============");
		persons = personRepository.findAllBetweenNameOrderByAsc("J", "Q");
		persons.forEach(System.out::println);

		System.out.println("============= Consulta por rango de id por order by name por desc =============");
		persons = personRepository.findAllBetweeIdOrderByDesc(2L, 5L);
		persons.forEach(System.out::println);

		System.out.println("============= Consulta por rangos por letras name order by name por desc=============");
		persons = personRepository.findAllBetweenNameOrderByDesc("J", "Q");
		persons.forEach(System.out::println);

	}

	@Transactional(readOnly = true)
	public void personalizeQueriesConcatUpperLowerCase() {

		System.out.println("========== Consultas nombres y apellidos de personas con concat ==========");
		List<String> names = personRepository.findAllFullNameConcat();
		names.forEach(System.out::println);

		System.out.println(
				"========== Consultas nombres y apellidos de personas sin usar el concat solo con (||) ==========");
		names = personRepository.findAllFullNameConcatDiferent();
		names.forEach(System.out::println);

		System.out.println("========== Consulta nombres y apellidos mayuscula ==========");
		names = personRepository.findAllFullNameConcatUpper();
		names.forEach(System.out::println);

		System.out.println("========== Consulta nombres y apellidos minuscula ==========");
		names = personRepository.findAllFullNameConcatLower();
		names.forEach(System.out::println);

		System.out.println("========== Consulta personalizada persona upper y lower case ==========");
		List<Object[]> regs = personRepository.findAllPersonDataListCase();
		regs.forEach(reg -> System.out.println(
				"ID = " + reg[0] + ", Nombre=  " + reg[1] + ", Apellido = " + reg[2] + ", Lenguaje = " + reg[3]));

	}

	@Transactional(readOnly = true)
	public void personalizeQueriesDistinct() {
		System.out.println("====== Consultas con nombres de personas ======");
		List<String> names = personRepository.findAllNames();
		names.forEach(System.out::println);

		// campos que no se repiten con el Distinct
		System.out.println("====== Consultas con nombres unicos de personas ======");
		names = personRepository.findAllNamesDistinct();
		names.forEach(System.out::println);

		System.out.println("====== Consultas con el Lenguaje de programacion ======");
		List<String> programmingLanguages = personRepository.findAllProgrammingLanguage();
		programmingLanguages.forEach(System.out::println);

		System.out.println("====== Consultas con el Lenguaje de programacion unicas ======");
		programmingLanguages = personRepository.findAllProgrammingLanguageDistinct();
		programmingLanguages.forEach(System.out::println);

		System.out.println("====== Consulta con total de lenguaje de programacion unicas ======");
		Long totalLanguage = personRepository.findAllProgrammingLanguageDistinctCount();
		System.out.println("Total de lenguaje de programacion es: " + totalLanguage);
	}

	@Transactional(readOnly = true)
	public void personalizeQueries2() {

		System.out.println("====== consulta por objecto persona y lenguaje de programacion ======");
		List<Object[]> personsRegs = personRepository.finAllMixPerson();

		personsRegs.forEach(reg -> {
			System.out.println("programmingLanguage = " + reg[1] + ", person = " + reg[0]);
		});

		System.out.println("====== Consulta que puebla y devuelve objeto entity de una instancia personalizada ======");
		List<Person> persons = personRepository.findAllObjectPersonPersonalized();
		persons.forEach(System.out::println);

		System.out.println("====== Consulta que puebla y devuelve objeto DTO de una clase personalizada ======");
		List<PersonDto> personDtos = personRepository.findAllPersonDTO();
		personDtos.forEach(System.out::println);
	}

	@Transactional(readOnly = true)
	public void personalizeQueries() {
		Scanner scanner = new Scanner(System.in);

		System.out.println("====== consulta solo el nombre por el id ======");
		System.out.println("Ingrese el id");
		Long id = scanner.nextLong();
		scanner.close();

		System.out.println("====== Mostrando solo el nombre del persona ======");
		String name = personRepository.getNameById(id);
		System.out.println("El nombre es: " + name);

		System.out.println("====== Mostrando solo el id ======");
		Long idDB = personRepository.getIdById(id);
		System.out.println("El id es: " + idDB);

		System.out.println("====== Mostrando nombre completo con concat ======");
		String fullName = personRepository.getFullNameById(id);
		System.out.println("El Nombre y apellidos juntos FullName: " + fullName);

		// System.out.println("====== Consulta por campos personalizados por el id
		// ======");
		// Object[] personReg =(Object[])
		// personRepository.obtenerPersonDataFullById(id);
		// System.out.println("ID = " + personReg[0] + ", Nombre= " + personReg[1] + ",
		// Apellido = " + personReg[2] + ", Lenguaje = " + personReg[3]);

		System.out.println("====== Consulta por campos personalizados por el id ======");
		Optional<Object> optionalReg = personRepository.obtenerPersonDataFullById(id);
		if (optionalReg.isPresent()) {
			Object[] personReg = (Object[]) optionalReg.orElseThrow();
			System.out.println("ID = " + personReg[0] + ", Nombre=  " + personReg[1] + ", Apellido = " + personReg[2]
					+ ", Lenguaje = " + personReg[3]);
		}

		System.out.println("====== Consulta por campos personalizados lista ======");
		List<Object[]> regs = personRepository.obtenerPersonDataList();
		regs.forEach(reg -> System.out.println(
				"ID = " + reg[0] + ", Nombre=  " + reg[1] + ", Apellido = " + reg[2] + ", Lenguaje = " + reg[3]));

	}

	@Transactional
	public void delete2() {

		System.out.println("Antes de Eliminar");
		personRepository.findAll().forEach(System.out::println);

		Scanner scanner = new Scanner(System.in);
		System.out.println("Ingresa el id a eliminar");
		Long id = scanner.nextLong();

		Optional<Person> optionalPerson = personRepository.findById(id);

		optionalPerson.ifPresentOrElse(
				// Alternativas para simpificar todo
				// person -> personRepository.delete(person),

				personRepository::delete,
				() -> System.out.println("Lo sentimos no existe la persona con el id:  " + id));

		System.out.println("Despues de eliminar");
		personRepository.findAll().forEach(System.out::println);
		scanner.close();
	}

	@Transactional
	public void delete() {

		System.out.println("Antes de Eliminar");
		personRepository.findAll().forEach(System.out::println);

		Scanner scanner = new Scanner(System.in);
		System.out.println("Ingresa el id a eliminar");
		Long id = scanner.nextLong();

		personRepository.deleteById(id);
		System.out.println("Despues de eliminar");
		personRepository.findAll().forEach(System.out::println);
		scanner.close();
	}

	@Transactional
	public void update() {

		Scanner scanner = new Scanner(System.in);
		System.out.println("Ingrese el Id de la persona: ");

		Long id = scanner.nextLong();

		Optional<Person> optionalPerson = personRepository.findById(id);
		// optionalPerson.ifPresent(person -> {
		// System.out.println(person);
		// System.out.println("Ingrese el lenguaje de programacion: ");
		// String programmingLanguage = scanner.next();
		// person.setProgrammingLanguage(programmingLanguage);
		// Person personDB = personRepository.save(person);
		// System.out.println("Persona actualizada" + personDB);
		// });

		if (optionalPerson.isPresent()) {
			Person personDB = optionalPerson.orElseThrow();
			System.out.println(personDB);
			System.out.println("Ingrese el lenguaje de programacion: ");
			String programmingLanguage = scanner.next();
			personDB.setProgrammingLanguage(programmingLanguage);
			Person personUpdated = personRepository.save(personDB);
			System.out.println("Persona actualizada" + personUpdated);
		} else {
			System.out.println("El usuario no esta presente! no existe! " + id);
		}
		scanner.close();
	}

	@Transactional
	public void create() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Ingrese el nombre");
		String name = scanner.next();
		System.out.println("Ingrese el apellido");
		String lastname = scanner.next();
		System.out.println("Ingrese el lenguaje de programacion");
		String programmingLanguage = scanner.next();
		scanner.close();

		// Person person = new Person(null, "Lalo", "Thor", "Python");
		Person person = new Person(null, name, lastname, programmingLanguage);

		Person personNew = personRepository.save(person);
		System.out.println(personNew);

		personRepository.findById(personNew.getId()).ifPresent(System.out::println);
	}

	@Transactional(readOnly = true)
	public void findOne() {
		// Person person = null;
		// Optional<Person> optionPerson = personRepository.findById(8L);
		// if (optionPerson.isPresent()) {
		// person = optionPerson.get();
		// }
		// System.out.println(person);
		System.out.println("esto es del metodo findById");
		personRepository.findById(1L).ifPresent(System.out::println);
		System.out.println("esto es del metodo findOne");
		personRepository.findOne(1L).ifPresent(System.out::println);
		System.out.println("esto es del metodo findName");
		personRepository.findOneName("Pepe").ifPresent(System.out::println);
		System.out.println("esto es del metodo findNameLike");
		personRepository.findOneLikeName("an").ifPresent(System.out::println);
		System.out.println("esto es del metodo findNameLike pero sin hacer el query solo por reposity");
		personRepository.findByNameContaining("hn").ifPresent(System.out::println);

	}

	@Transactional(readOnly = true)
	public void list() {

		// List<Person> persons = (List<Person>) personRepository.findAll();
		// List<Person> persons = (List<Person>)
		// personRepository.findByProgrammingLanguage("Java");

		// List<Person> persons = (List<Person>)
		// personRepository.buscarByProgrammingLanguage("Java", "andres");
		List<Person> persons = (List<Person>) personRepository.findByProgrammingLanguageAndName("Java", "Andres");

		persons.stream().forEach(person -> System.out.println(person));

		List<Object[]> personsValue = personRepository.obtenerPersonDatabyProgramminLanguage("Java");

		personsValue.stream().forEach(person -> System.out.println(person[0] + " " + "es experto en " + person[1]));

	}

}
