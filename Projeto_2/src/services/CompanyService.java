package services;

import entities.CompanyEntity;
import repositories.CompanyDAO;
import repositories.SegmentDAO;

import java.security.InvalidParameterException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CompanyService {

    private static final Pattern CNPJ_REGEX = Pattern.compile("^\\d{2}\\.\\d{3}\\.\\d{3}\\/\\d{4}\\-\\d{2}$", Pattern.CASE_INSENSITIVE);

    private final CompanyDAO companyDAO;
    private final SegmentDAO segmentDAO;

    public CompanyService(CompanyDAO companyDAO, SegmentDAO segmentDAO) {
        this.companyDAO = companyDAO;
        this.segmentDAO = segmentDAO;
    }

    public void insert(CompanyEntity companyEntity) {
        Matcher cnpjMatcher = CNPJ_REGEX.matcher(companyEntity.cnpj);
        if(!cnpjMatcher.find()) {
            throw new InvalidParameterException("CNPJ inválido.");
        }

        if (segmentDAO.get(companyEntity.segmentId) == null) {
            throw new InvalidParameterException("Segmento inválido.");
        }

        if (!companyEntity.headOffice && (companyEntity.subsidiaryName == null || companyEntity.subsidiaryName.isEmpty())) {
            throw new InvalidParameterException("Nome de filial inválido.");
        }

        companyDAO.insert(companyEntity);
    }

    public CompanyEntity get(int id) {
        return companyDAO.get(id);
    }
}
