package com.grupo5.Lab3.Repository;

import com.grupo5.Lab3.Model.Opinion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OpinionRepository extends CrudRepository<Opinion, Integer> {
}
