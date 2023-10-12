package exercise.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import exercise.model.Product;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

}


