package Laboratorio1BdaGrupo5.BackendLab1.repository;

import Laboratorio1BdaGrupo5.BackendLab1.models.Historial;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HistorialRepository extends MongoRepository<Historial, ObjectId> {
    Historial findByIdUsuario(Integer idUsuario);
}
