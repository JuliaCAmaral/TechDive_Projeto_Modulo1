package repositories;

import entities.EmployeeEntity;

import java.util.HashMap;
import java.util.Map;

public class EmployeeDAO {

    private int count = 0;

    private Map<Integer, EmployeeEntity> employees = new HashMap<>();

    public void insert(EmployeeEntity employee) {
        employee.id = ++count;
        this.employees.put(employee.id, employee);
    }

    public void update(EmployeeEntity employee) {
        this.employees.put(employee.id, employee);
    }

    public EmployeeEntity get(int id) {
        return employees.get(id);
    }
}
