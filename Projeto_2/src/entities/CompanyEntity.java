package entities;

import java.util.Objects;

public class CompanyEntity {
    public int id;
    public String name;
    public String cnpj;
    public boolean headOffice;
    public String subsidiaryName;
    public int segmentId;
    public String city;
    public String State;
    public String regionalSenai;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompanyEntity companyEntity = (CompanyEntity) o;
        return id == companyEntity.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
