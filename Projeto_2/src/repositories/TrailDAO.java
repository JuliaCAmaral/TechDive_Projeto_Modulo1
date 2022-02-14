package repositories;

import entities.TrailEntity;

import java.util.HashMap;
import java.util.Map;

public class TrailDAO {
    private int count = 0;

    private Map<Integer, TrailEntity> trails = new HashMap<>();

    public void insert(TrailEntity trailEntity) {
        trailEntity.companyTrailNumber = 1;

        for (TrailEntity item : trails.values()) {
            if (item.companyId == trailEntity.companyId && item.occupation.equals(trailEntity.occupation)) {
                trailEntity.companyTrailNumber = Math.max(trailEntity.companyTrailNumber, item.companyTrailNumber + 1);
            }
        }
        trailEntity.id = ++count;
        this.trails.put(trailEntity.id, trailEntity);
    }

    public void update(TrailEntity trailEntity) {
        this.trails.put(trailEntity.id, trailEntity);
    }

    public TrailEntity get(int id) {
        return trails.get(id);
    }
}
