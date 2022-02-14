package entities;

import java.time.OffsetDateTime;
import java.util.HashSet;

public class EmployeeEntity {
    public int id;
    public int companyId;
    public String name;
    public String cpf;
    public String department;
    public String occupation;
    public OffsetDateTime occupationDate;
    public HashSet<Integer> trails;
}
