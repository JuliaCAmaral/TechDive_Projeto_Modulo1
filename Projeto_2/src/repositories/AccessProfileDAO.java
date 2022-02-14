package repositories;

import entities.AccessProfileEntity;

import java.util.HashMap;
import java.util.Map;

public class AccessProfileDAO {

    private int count = 0;

    private Map<Integer, AccessProfileEntity> accessProfiles = new HashMap<>();

    public void insert(AccessProfileEntity accessProfileEntity) {
        accessProfileEntity.id = ++count;
        this.accessProfiles.put(accessProfileEntity.id, accessProfileEntity);
    }

    public void update(AccessProfileEntity accessProfileEntity) {
        this.accessProfiles.put(accessProfileEntity.id, accessProfileEntity);
    }

    public AccessProfileEntity get(int id) {
        return accessProfiles.get(id);
    }

}
