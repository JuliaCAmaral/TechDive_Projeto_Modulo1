package repositories;

import entities.CompanyEntity;

import java.util.HashMap;
import java.util.Map;

public class CompanyDAO {

    private int count = 0;

    private Map<Integer, CompanyEntity> companies = new HashMap<>();

    public void insert(CompanyEntity companyEntity) {
        companyEntity.id = ++count;
        this.companies.put(companyEntity.id, companyEntity);
    }

    public CompanyEntity get(int id) {
        return companies.get(id);
    }
}
