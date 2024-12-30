package Laboratorio3BdaGrupo5.BackendLab3.service;

import Laboratorio3BdaGrupo5.BackendLab3.models.Report;
import Laboratorio3BdaGrupo5.BackendLab3.repository.ReportRepositoryImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {
    @Autowired
    private ReportRepositoryImp reportRepository;

    public List<Report> getReport() {
        try {
            System.out.println("2");
            return reportRepository.getReport();
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener la lista de categorias", e);
        }
    }
}
