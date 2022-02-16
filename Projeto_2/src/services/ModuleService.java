package services;

import entities.ModuleEntity;
import repositories.ModuleDAO;
import repositories.TrailDAO;

import java.security.InvalidParameterException;
import java.time.DayOfWeek;
import java.time.OffsetDateTime;

public class ModuleService {

    private final ModuleDAO moduleDAO;
    private final TrailDAO trailDAO;


    public ModuleService(ModuleDAO moduleDAO, TrailDAO trailDAO) {
        this.moduleDAO = moduleDAO;
        this.trailDAO = trailDAO;
    }

    public void insert(ModuleEntity moduleEntity) {

        if (trailDAO.get(moduleEntity.trailId) == null)  {
            throw new InvalidParameterException("Trilha inválida.");
        }

        if (moduleEntity.limitDate == 0)  {
            throw new InvalidParameterException("Prazo limite não pode ser 0.");
        }

        moduleEntity.statusId = 1;
        moduleDAO.insert(moduleEntity);
    }

    public void startModule(int id) {
        ModuleEntity moduleEntity = moduleDAO.get(id);
        moduleEntity.startDate = OffsetDateTime.now();

        if (moduleEntity.statusId != 1) {
            throw new InvalidParameterException("Curso já iniciado.");
        }

        moduleEntity.statusId = 2;
        moduleDAO.update(id);
    }

    public void startEvaluation(int id) {
        ModuleEntity moduleEntity = moduleDAO.get(id);

        if (moduleEntity.statusId == 3) {
            throw new InvalidParameterException("Curso em fase de avaliação.");
        }

        if (moduleEntity.statusId != 2) {
            throw new InvalidParameterException("Curso não iniciado.");
        }

        moduleEntity.startDateEvaluation = OffsetDateTime.now();
        moduleEntity.deadline = addWorkDays(moduleEntity.startDate, moduleEntity.limitDate);
        moduleEntity.statusId = 3;
        moduleDAO.update(id);
    }

    public ModuleEntity get(int id) {
        ModuleEntity moduleEntity = moduleDAO.get(id);

        if (moduleEntity.deadline.equals(OffsetDateTime.now()) || moduleEntity.deadline.isBefore(OffsetDateTime.now())) {
            moduleEntity.statusId = 4;
            moduleDAO.update(id);
        }
        return moduleEntity;
    }

    private OffsetDateTime addWorkDays(OffsetDateTime startDate, int workdays){
        if (workdays < 1) {
            return startDate;
        }

        OffsetDateTime result = startDate;
        int addedDays = 0;
        while (addedDays < workdays) {
            result = result.plusDays(1);
            if (!(result.getDayOfWeek() == DayOfWeek.SATURDAY || result.getDayOfWeek() == DayOfWeek.SUNDAY)) {
                ++addedDays;
            }
        }
        return result;
    }
}
