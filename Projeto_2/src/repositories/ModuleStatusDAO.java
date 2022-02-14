package repositories;

import entities.ModuleStatusEntity;

import java.util.HashMap;
import java.util.Map;

public class ModuleStatusDAO {

    private int count = 0;

    private Map<Integer, ModuleStatusEntity> modulesStatus = new HashMap<>();

    public void insert(ModuleStatusEntity moduleStatusEntity) {
        moduleStatusEntity.id = ++count;
        this.modulesStatus.put(moduleStatusEntity.id, moduleStatusEntity);
    }

    public ModuleStatusEntity get(int id) {
        return modulesStatus.get(id);
    }
}
