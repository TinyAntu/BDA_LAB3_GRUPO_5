package com.grupo5.Lab3.Repository;

import com.grupo5.Lab3.Model.Producto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends MongoRepository<Producto, Integer> {
}
