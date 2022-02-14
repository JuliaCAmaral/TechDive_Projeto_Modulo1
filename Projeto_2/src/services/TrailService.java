package services;

import entities.CompanyEntity;
import entities.ModuleEntity;
import entities.TrailEntity;
import models.TrailEvaluationDTO;
import repositories.CompanyDAO;
import repositories.ModuleDAO;
import repositories.TrailDAO;

import java.security.InvalidParameterException;
import java.time.OffsetDateTime;
import java.util.List;

public class TrailService {

    private final TrailDAO trailDAO;
    private final CompanyDAO companyDAO;
    private final ModuleDAO moduleDAO;
    private final OffsetDateTime offsetDateTime = OffsetDateTime.now();

    public TrailService(TrailDAO trailDAO, CompanyDAO companyDAO, ModuleDAO moduleDAO) {
        this.trailDAO = trailDAO;
        this.companyDAO = companyDAO;
        this.moduleDAO = moduleDAO;
    }

    public void insert(TrailEntity trail) {

        CompanyEntity company = companyDAO.get(trail.companyId);

        if (company == null) {
            throw new InvalidParameterException("Empresa inválida.");
        }

        if (trail.occupation == null || trail.occupation.isEmpty()) {
            throw new InvalidParameterException("Ocupação inválida.");
        }

        trailDAO.insert(trail);

        trail.name = trail.occupation + "-" + company.name + "-" + trail.companyTrailNumber + "-" + offsetDateTime.getYear();
        trail.nickname = trail.occupation + "-" + trail.companyTrailNumber;

        trailDAO.update(trail);
    }

    /**
     * Realiza a avaliação de satisfação geral da trilha.
     */
    public void evaluation(int trailId, TrailEvaluationDTO trailEvaluationDTO) {
        TrailEntity trail = get(trailId);

        List<ModuleEntity> modules = moduleDAO.getByTrailId(trailId);

        if (modules == null || modules.size() == 0) {
            throw new InvalidParameterException("A trilha não possui módulos cadastrados.");
        }

        for (ModuleEntity module : modules) {
            if (module.statusId != 4) {
                throw new InvalidParameterException("A trilha possui módulos não finalizados.");
            }
        }

        if (trailEvaluationDTO.score < 1 || trailEvaluationDTO.score > 5) {
            throw new InvalidParameterException("A nota da avaliação precisa estar entre 1 e 5.");
        }

        if (trailEvaluationDTO.notes == null || trailEvaluationDTO.notes.isEmpty()) {
            throw new InvalidParameterException("Anotação inválida.");
        }

        trail.score = trailEvaluationDTO.score;
        trail.notes = trailEvaluationDTO.notes;

        trailDAO.update(trail);
    }

    public TrailEntity get(int id) {
        return trailDAO.get(id);
    }
}
