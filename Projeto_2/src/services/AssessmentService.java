package services;

import entities.AssessmentEntity;
import entities.ModuleEntity;
import models.AssessmentDTO;
import repositories.AssessmentDAO;
import repositories.ModuleDAO;

import java.security.InvalidParameterException;
import java.time.OffsetDateTime;

public class AssessmentService {

    private final ModuleDAO moduleDAO;
    private final AssessmentDAO assessmentDAO;

    public AssessmentService(ModuleDAO moduleDAO, AssessmentDAO assessmentDAO) {
        this.moduleDAO = moduleDAO;
        this.assessmentDAO = assessmentDAO;
    }

    public void evaluate(int employeeId, int moduleId, AssessmentDTO assessmentDTO){
        ModuleEntity module = moduleDAO.get(moduleId);

        if (module.statusId != 3) {
            throw new InvalidParameterException("O módulo não está em fase de avaliação.");
        }

        if (module.assessment == null || module.assessment.isEmpty()) {
            throw new InvalidParameterException("O módulo não tem tarefa de avaliação cadastrada.");
        }

        if (assessmentDAO.get(employeeId, moduleId) != null) {
            throw new InvalidParameterException("Já existe uma avaliação deste trabalhador para este módulo.");
        }

        if (assessmentDTO.score < 1 || assessmentDTO.score > 6) {
            throw new InvalidParameterException("A nota da avaliação precisa estar entre 1 e 5.");
        }

        if (assessmentDTO.notes == null || assessmentDTO.notes.isEmpty()) {
            throw new InvalidParameterException("Anotação Inválida.");
        }

        AssessmentEntity assessment = new AssessmentEntity();

        assessment.employeeId = employeeId;
        assessment.moduleId = moduleId;
        assessment.completionDate = OffsetDateTime.now();
        assessment.score = assessmentDTO.score;
        assessment.notes = assessmentDTO.notes;

        assessmentDAO.insert(assessment);
    }
}
