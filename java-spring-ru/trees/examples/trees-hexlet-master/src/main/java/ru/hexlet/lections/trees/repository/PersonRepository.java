package ru.hexlet.lections.trees.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.hexlet.lections.trees.model.Person;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    Person findPersonById(Long id);

    @Query(value = "SELECT p.path FROM people p WHERE p.id = ?1", nativeQuery = true)
    String findParentPathById(Long id);

    @Query(value = "SELECT * FROM people p WHERE p.id IN ?1 group by p.id", nativeQuery = true)
    List<Person> findPeopleByIds(List<Long> ids);
}
