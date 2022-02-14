package services;

import entities.ModuleStatusEntity;
import repositories.ModuleStatusDAO;

public class ModuleStatusService {

    private final ModuleStatusDAO moduleStatusDAO;

    public ModuleStatusService(ModuleStatusDAO moduleStatusDAO) {
        this.moduleStatusDAO = moduleStatusDAO;
    }

    public void insert(ModuleStatusEntity moduleStatusEntity) {
        moduleStatusDAO.insert(moduleStatusEntity);
    }

    public ModuleStatusEntity get(int id) {
        return moduleStatusDAO.get(id);
    }
}
