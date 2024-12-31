package Laboratorio1BdaGrupo5.BackendLab1.repository;

import Laboratorio1BdaGrupo5.BackendLab1.models.Opinion;
import Laboratorio1BdaGrupo5.BackendLab1.models.Producto;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OpinionRepository extends MongoRepository<Opinion, ObjectId> {
    List<Opinion> findByProductId(Producto productId);
    List<Opinion> findByProductIdOrderByFechaDesc(ObjectId productId, Sort sort);
}
