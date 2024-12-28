package com.grupo5.Lab3.Repository;

import com.grupo5.Lab3.Model.Cliente;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends MongoRepository<Cliente, ObjectId> {

}
