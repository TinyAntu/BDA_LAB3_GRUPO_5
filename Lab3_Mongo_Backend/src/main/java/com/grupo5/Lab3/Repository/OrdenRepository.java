package com.grupo5.Lab3.Repository;

import com.grupo5.Lab3.Model.Orden;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdenRepository extends MongoRepository<Orden, Integer> {
}
