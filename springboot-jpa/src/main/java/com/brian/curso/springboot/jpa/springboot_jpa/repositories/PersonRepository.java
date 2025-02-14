package com.brian.curso.springboot.jpa.springboot_jpa.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.brian.curso.springboot.jpa.springboot_jpa.dto.PersonDto;
import com.brian.curso.springboot.jpa.springboot_jpa.entities.Person;
import java.util.List;
import java.util.Optional;



public interface PersonRepository extends CrudRepository<Person, Long> {

    //Todo es son consultas personas ya que como tal la mayoria de veces el CrudRepository ya trae los campos necesarios pero estan Querys personalizadas
    
    //Operador Where in JPQL
    @Query("select p from Person p where p.id in (1, 2, 5)")
    public List<Person> getPersonByIds();

    @Query("select p from Person p where p.id in ?1")
    public List<Person> getPersonByIdsByParameter(List<Long> ids);

    @Query("select p from Person p where p.id not in ?1")
    public List<Person> getPersonByIdsByParameterNotIn(List<Long> ids);

    // Son subConsultas
    @Query("select p.name, length(p.name) from Person p where length(p.name)=(select min(length(p.name)) from Person p)")
    public List<Object[]> getShorterName();

    @Query("select p.name, length(p.name) from Person p where length(p.name)=(select max(length(p.name)) from Person p)")
    public List<Object[]> getLongerName();

    @Query("select p from Person p where p.id = (select max(p.id) from Person p)")
    public Optional<Person> getLastRegistration();

    //Alternativas
    @Query("select min(p.id), max(p.id), sum(p.id), avg(length(p.name)), count(p.id) from Person p")
    public Object getResumeAggregationFuction();

    @Query("select min(length(p.name)) from Person p")
    public Integer getMinLengthName();

    @Query("select max(length(p.name)) from Person p")
    public Integer getMaxLengthName();

    @Query("select p.name, length(p.name) from Person p ")
    public List<Object[]> getPersonNameLenght();

    @Query("select count(p) from Person p")
    Long getTotalPerson();

    @Query("select min(p.id) from Person p")
    Long getMinId();

    @Query("select max(p.id) from Person p")
    Long getMaxId();
    
    List<Person> findByIdBetween(Long id1, Long id2);
    List<Person> findByNameBetween(String name1, String name2);

    @Query("select p from Person p where p.name between ?1 and ?2 order by p.name ")
    List<Person> findAllBetweenNameOrderBy(String c1, String c2);

    @Query("select p from Person p where p.id between ?1 and ?2 order by p.name ")
    List<Person> findAllBetweeIdOrderBy(Long id1, Long id2);

    @Query("select p from Person p where p.name between ?1 and ?2 order by p.name asc")
    List<Person> findAllBetweenNameOrderByAsc(String c1, String c2);

    @Query("select p from Person p where p.id between ?1 and ?2 order by p.name asc")
    List<Person> findAllBetweeIdOrderByAsc(Long id1, Long id2);

    @Query("select p from Person p where p.name between ?1 and ?2 order by p.name desc")
    List<Person> findAllBetweenNameOrderByDesc(String c1, String c2);

    @Query("select p from Person p where p.id between ?1 and ?2 order by p.name desc")
    List<Person> findAllBetweeIdOrderByDesc(Long id1, Long id2);

    //@Query("select p from Person p where p.name between 'J' and 'Q' ")
    @Query("select p from Person p where p.name between ?1 and ?2 ")
    List<Person> findAllBetweenName(String c1, String c2);

    @Query("select p from Person p where p.id between ?1 and ?2")
    List<Person> findAllBetweeId(Long id1, Long id2);
    //Alternativas de sin usar el concat
    @Query("select p.name || ' ' || p.lastname as FullName from Person p")
    List<String> findAllFullNameConcatDiferent();

    @Query("select p.id, upper(p.name), lower(p.lastname), upper(p.programmingLanguage) from Person p")
    List<Object[]> findAllPersonDataListCase();

    @Query("select upper(p.name || ' ' || p.lastname) as FullName from Person p")
    List<String> findAllFullNameConcatUpper();

    @Query("select lower(CONCAT(p.name, ' ', p.lastname)) as FullName from Person p")
    List<String> findAllFullNameConcatLower();
    
    @Query("select CONCAT(p.name, ' ', p.lastname) as FullName from Person p")
    List<String> findAllFullNameConcat();

    @Query("select p.programmingLanguage from Person p")
    List<String> findAllProgrammingLanguage();

    @Query("select distinct(p.programmingLanguage) from Person p")
    List<String> findAllProgrammingLanguageDistinct();

    @Query("select count(distinct(p.programmingLanguage)) from Person p")
    Long findAllProgrammingLanguageDistinctCount();
  
  
    @Query("select p.name from Person p")
    List<String> findAllNames();

    @Query("select distinct(p.name) from Person p")
    List<String> findAllNamesDistinct();

    @Query("select new com.brian.curso.springboot.jpa.springboot_jpa.dto.PersonDto(p.name, p.lastname) from Person p ")
    List<PersonDto> findAllPersonDTO();


    @Query("select new Person(p.name, p.lastname) from Person p ")
    List<Person> findAllObjectPersonPersonalized();


    @Query("select p.name from Person p where p.id=?1")
    String getNameById(Long id);

    @Query("select p.id from Person p where p.id=?1")
    Long getIdById(Long id);

    @Query("select concat(p.name, ' ', p.lastname) as FullName from Person p where p.id=?1")
    String getFullNameById(Long id);

    @Query("select p from Person p where p.id=?1")
    Optional<Person> findOne(Long id);
    @Query("select p from Person p where p.name=?1")
    Optional<Person> findOneName(String name);
    @Query("select p from Person p where p.name like %?1%")
    Optional<Person> findOneLikeName(String name);

    Optional<Person> findByNameContaining(String name);
    
    

    List<Person> findByProgrammingLanguage(String programmingLanguage);


    // @Query("select p from Person p")
    // List<Person> buscarByProgrammingLanguage(String programmingLanguage);

    @Query("select p from Person p where p.programmingLanguage= ?1 and p.name=?2")
    List<Person> buscarByProgrammingLanguage(String programmingLanguage, String name);
    
    // @Query("select p from Person p where p.programmingLanguage= ?1")
    // List<Person> buscarByProgrammingLanguage(String programmingLanguage);

    List<Person> findByProgrammingLanguageAndName(String programmingLanguage, String name);

    @Query("select p.name, p.programmingLanguage from Person p")
    List<Object[]> obtenerPersonData();


    @Query("select p, p.programmingLanguage from Person p")
    List<Object[]> finAllMixPerson();
    

    @Query("select p.id, p.name, p.lastname, p.programmingLanguage from Person p")
    List<Object[]> obtenerPersonDataList();

    @Query("select p.id, p.name, p.lastname, p.programmingLanguage from Person p where p.id=?1")
    Optional<Object> obtenerPersonDataFullById(Long id);



    @Query("select p.name, p.programmingLanguage from Person p where p.name=?1")
    List<Object[]> obtenerPersonData(String name);

    @Query("select p.name, p.programmingLanguage from Person p where p.programmingLanguage= ?1 and p.name=?2")
    List<Object[]> obtenerPersonData(String programmingLanguage, String name);

    @Query("select p.name, p.programmingLanguage from Person p where p.programmingLanguage= ?1")
    List<Object[]> obtenerPersonDatabyProgramminLanguage(String programmingLanguage);



}
