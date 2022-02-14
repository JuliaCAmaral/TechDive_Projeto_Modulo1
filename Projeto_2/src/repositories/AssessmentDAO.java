package repositories;

import entities.AssessmentEntity;

import java.util.HashSet;

public class AssessmentDAO {

    private HashSet<AssessmentEntity> assessments = new HashSet<>();

    public void insert(AssessmentEntity entity) {
        this.assessments.add(entity);
    }

    public AssessmentEntity get(int employeeId, int moduleId) {
        return assessments.stream()
                .filter(a -> a.employeeId == employeeId && a.moduleId == moduleId)
                .findAny().orElse(null);
    }
}
