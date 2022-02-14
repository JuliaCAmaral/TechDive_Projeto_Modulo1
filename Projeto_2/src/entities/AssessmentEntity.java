package entities;

import java.time.OffsetDateTime;
import java.util.Objects;

public class AssessmentEntity {
    public int employeeId;
    public int moduleId;
    public OffsetDateTime completionDate;
    public int score;
    public String notes;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AssessmentEntity that = (AssessmentEntity) o;
        return employeeId == that.employeeId &&
                moduleId == that.moduleId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId, moduleId);
    }
}
