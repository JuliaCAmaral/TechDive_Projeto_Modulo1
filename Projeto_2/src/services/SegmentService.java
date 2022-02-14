package services;

import entities.SegmentEntity;
import repositories.SegmentDAO;

public class SegmentService {

    private final SegmentDAO segmentDAO;

    public SegmentService(SegmentDAO segmentDAO) {
        this.segmentDAO = segmentDAO;
    }

    public void insert(SegmentEntity segmentEntity) {
        segmentDAO.insert(segmentEntity);
    }

    public SegmentEntity get(int id) {
        return segmentDAO.get(id);
    }
}
