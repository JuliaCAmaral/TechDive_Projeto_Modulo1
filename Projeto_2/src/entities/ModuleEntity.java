package entities;

import java.time.OffsetDateTime;

public class ModuleEntity {
    public int id;
    public int trailId;
    public String name;
    public String skills;
    public String assessment;
    public int statusId;
    public int limitDate;
    public OffsetDateTime startDate;
    public OffsetDateTime startDateEvaluation;
    public OffsetDateTime deadline;
}
