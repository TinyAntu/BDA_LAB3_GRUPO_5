package com.grupo5.Lab3.Repository;

import com.grupo5.Lab3.Model.DetalleOrden;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleOrdenRepository extends MongoRepository<DetalleOrden, Integer> {
}
