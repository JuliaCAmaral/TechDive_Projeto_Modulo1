package repositories;

import entities.SegmentEntity;
import java.util.HashMap;
import java.util.Map;

public class SegmentDAO {

    private int count = 0;

    private Map<Integer, SegmentEntity> segments = new HashMap<>();

    public void insert(SegmentEntity segmentEntity) {
        segmentEntity.id = ++count;
        this.segments.put(segmentEntity.id, segmentEntity);
    }

    public SegmentEntity get(int id) {
        return segments.get(id);
    }
}
