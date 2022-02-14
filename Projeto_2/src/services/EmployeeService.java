package services;

import entities.EmployeeEntity;
import entities.TrailEntity;
import models.EmployeeEditDTO;
import repositories.CompanyDAO;
import repositories.EmployeeDAO;
import repositories.TrailDAO;

import java.security.InvalidParameterException;
import java.time.OffsetDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmployeeService {

    private static final Pattern CPF_REGEX = Pattern.compile("^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$", Pattern.CASE_INSENSITIVE);

    private final TrailDAO trailDAO;
    private final EmployeeDAO employeeDAO;
    private final CompanyDAO companyDAO;

    public EmployeeService(TrailDAO trailDAO, EmployeeDAO employeeDAO, CompanyDAO companyDAO) {
        this.trailDAO = trailDAO;
        this.employeeDAO = employeeDAO;
        this.companyDAO = companyDAO;
    }

    public void insert(EmployeeEntity employee) {
        if (employee.name == null || employee.name.isEmpty()) {
            throw new InvalidParameterException("Nome inválido.");
        }

        if (employee.cpf == null || employee.cpf.isEmpty()) {
            throw new InvalidParameterException("CPF inválido.");
        }

        Matcher cpfMatcher = CPF_REGEX.matcher(employee.cpf);
        if(!cpfMatcher.find()) {
            throw new InvalidParameterException("CPF inválido.");
        }

        if (companyDAO.get(employee.companyId) == null) {
            throw new InvalidParameterException("Empresa inválida.");
        }

        if (employee.department == null || employee.department.isEmpty()) {
            throw new InvalidParameterException("Setor/Área inválida.");
        }

        if (employee.occupation == null || employee.occupation.isEmpty()) {
            throw new InvalidParameterException("Função inválida.");
        }

        if (employee.occupationDate == null || employee.occupationDate.isAfter(OffsetDateTime.now())) {
            throw new InvalidParameterException("Data de alteração de função inválida.");
        }

        if (employee.trails == null || employee.trails.size() == 0) {
            throw new InvalidParameterException("O trabalhador deve ter pelo menos uma trilha vinculada.");
        }

        for (int trailId : employee.trails) {
            TrailEntity trail = trailDAO.get(trailId);

            if (trail == null) {
                throw new InvalidParameterException("Trilha inválida.");
            }

            if (!(trail.companyId == employee.companyId)) {
                throw new InvalidParameterException("A trilha não possui vínculo com a empresa.");
            }
        }
        employeeDAO.insert(employee);
    }

    public void update(int id, EmployeeEditDTO employeeDTO) {
        EmployeeEntity employee = employeeDAO.get(id);

        if (companyDAO.get(employeeDTO.companyId) == null) {
            throw new InvalidParameterException("Empresa inválida.");
        }

        if (employeeDTO.department == null || employeeDTO.department.isEmpty()) {
            throw new InvalidParameterException("Setor/Área inválida.");
        }

        if (employeeDTO.occupation == null || employeeDTO.occupation.isEmpty()) {
            throw new InvalidParameterException("Função inválida.");
        }

        if (employeeDTO.occupationDate == null || employeeDTO.occupationDate.isAfter(OffsetDateTime.now())) {
            throw new InvalidParameterException("Data de alteração de função inválida.");
        }

        for (int trailId : employeeDTO.trails) {
            TrailEntity trail = trailDAO.get(trailId);

            if (trail == null) {
                throw new InvalidParameterException("Trilha inválida.");
            }

            if (trail.companyId != employeeDTO.companyId) {
                throw new InvalidParameterException("A trilha não possui vínculo com a empresa.");
            }
        }

        employee.companyId = employeeDTO.companyId;
        employee.department = employeeDTO.department;
        employee.occupation = employeeDTO.occupation;
        employee.occupationDate = employeeDTO.occupationDate;
        employee.trails = employeeDTO.trails;

        employeeDAO.update(employee);
    }

    public EmployeeEntity get(int id) {
        return employeeDAO.get(id);
    }
}
