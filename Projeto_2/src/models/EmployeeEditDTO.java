package models;

import java.time.OffsetDateTime;
import java.util.HashSet;

public class EmployeeEditDTO {
    public int companyId;
    public String department;
    public String occupation;
    public OffsetDateTime occupationDate;
    public HashSet<Integer> trails;
}
