package Laboratorio1BdaGrupo5.BackendLab1.service;

import Laboratorio1BdaGrupo5.BackendLab1.models.Opinion;
import Laboratorio1BdaGrupo5.BackendLab1.models.Producto;
import Laboratorio1BdaGrupo5.BackendLab1.repository.OpinionRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OpinionService {
    @Autowired
    OpinionRepository opinionRepository;



    public Opinion saveReview(Opinion review) {
        return opinionRepository.save(review);
    }

    public List<Opinion> getReviewsByProductId(Producto productId) {
        return opinionRepository.findByProductId(productId);
    }

    //Las opiniones de un producto ordenadas por la fecha de mas nueva a mas vieja
    public List<Opinion> getReviewsByProductWithDate(ObjectId productId) {
        return opinionRepository.findByProductIdOrderByFechaDesc(productId,Sort.by(Sort.Direction.DESC, "fecha"));
    }
}
