package repositories;

import entities.ModuleEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ModuleDAO {

    private int count = 0;

    private Map<Integer, ModuleEntity> modules = new HashMap<>();

    public void insert(ModuleEntity moduleEntity) {
        moduleEntity.id = ++count;
        this.modules.put(moduleEntity.id, moduleEntity);
    }

    public void update(int id) {
        this.modules.put(id, get(id));
    }

    public ModuleEntity get(int id) {
        return modules.get(id);
    }

    /**
     * Retorna a lista de módulos de uma trilha.
     */
    public List<ModuleEntity> getByTrailId(int trailId) {
        return modules.values().stream()
                .filter(m -> m.trailId == trailId)
                .collect(Collectors.toList());
    }
}
