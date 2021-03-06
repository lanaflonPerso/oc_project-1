package com.lambazon.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.lambazon.domain.Product;

/**

 * JPA managed
 * 
 * @author stanlick
 *
 */

@Repository
public interface ProductRepository extends CrudRepository<Product, Long>{}
