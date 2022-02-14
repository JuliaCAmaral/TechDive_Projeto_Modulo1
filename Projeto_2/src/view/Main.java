package view;

import entities.*;
import models.AssessmentDTO;
import models.EmployeeEditDTO;
import models.TrailEvaluationDTO;
import repositories.*;
import services.*;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.HashSet;

public class Main {

    private static CompanyDAO companyDAO = new CompanyDAO();
    private static SegmentDAO segmentDAO = new SegmentDAO();
    private static TrailDAO trailDAO = new TrailDAO();
    private static ModuleStatusDAO moduleStatusDAO = new ModuleStatusDAO();
    private static ModuleDAO moduleDAO = new ModuleDAO();
    private static EmployeeDAO employeeDAO = new EmployeeDAO();
    private static AccessProfileDAO accessProfileDAO = new AccessProfileDAO();
    private static UserDAO userDAO = new UserDAO();
    private static AssessmentDAO assessmentDAO = new AssessmentDAO();

    private static CompanyService companyService = new CompanyService(companyDAO, segmentDAO);
    private static SegmentService segmentService = new SegmentService(segmentDAO);
    private static TrailService trailService = new TrailService(trailDAO, companyDAO, moduleDAO);
    private static ModuleStatusService moduleStatusService = new ModuleStatusService(moduleStatusDAO);
    private static ModuleService moduleService = new ModuleService(moduleDAO, trailDAO);
    private static EmployeeService employeeService = new EmployeeService(trailDAO, employeeDAO, companyDAO);
    private static AccessProfileService accessProfileService = new AccessProfileService(accessProfileDAO);
    private static UserService userService = new UserService(userDAO, accessProfileDAO);
    private static AssessmentService assessmentService = new AssessmentService(moduleDAO,assessmentDAO);

    public static void main(String[] args) {

        // ---------------------------- Inserindo os perfis de acesso ---------------------------------

        AccessProfileEntity accessProfileEntity1 = new AccessProfileEntity();
        AccessProfileEntity accessProfileEntity2 = new AccessProfileEntity();
        AccessProfileEntity accessProfileEntity3 = new AccessProfileEntity();

        accessProfileEntity1.name = "Administrativo";
        accessProfileEntity2.name = "Operacional";
        accessProfileEntity3.name = "RH";

        accessProfileService.insert(accessProfileEntity1);
        accessProfileService.insert(accessProfileEntity2);
        accessProfileService.insert(accessProfileEntity3);

        // -------------------------------- Inserindo os status dos módulos ----------------------------

        ModuleStatusEntity moduleStatusEntity1 = new ModuleStatusEntity();
        ModuleStatusEntity moduleStatusEntity2 = new ModuleStatusEntity();
        ModuleStatusEntity moduleStatusEntity3 = new ModuleStatusEntity();
        ModuleStatusEntity moduleStatusEntity4 = new ModuleStatusEntity();

        moduleStatusEntity1.name = "Curso não iniciado.";
        moduleStatusEntity2.name = "Curso em andamento.";
        moduleStatusEntity3.name = "Curso em fase de avaliação.";
        moduleStatusEntity4.name = "Curso com fase de avaliação finalizada.";

        moduleStatusService.insert(moduleStatusEntity1);
        moduleStatusService.insert(moduleStatusEntity2);
        moduleStatusService.insert(moduleStatusEntity3);
        moduleStatusService.insert(moduleStatusEntity4);

        // -------------------------- Criando os usuários e inserindo perfis --------------------------

        UserEntity userEntity1 = new UserEntity();
        UserEntity userEntity2 = new UserEntity();
        UserEntity userEntity3 = new UserEntity();

        userEntity1.name = "Kiara";
        userEntity1.cpf = "111.111.111-22";
        userEntity1.accessProfiles = new HashSet<>();
        userEntity1.accessProfiles.add(1);
        userEntity1.accessProfiles.add(2);
        userEntity1.accessProfiles.add(3);
        userEntity1.email = "gui.fred.der@sc.senai.br";
        userEntity1.password = "12345o78";

        userEntity2.name = "José";
        userEntity2.cpf = "111.111.111-22";
        userEntity2.accessProfiles = new HashSet<>();
        userEntity2.accessProfiles.add(2);
        userEntity2.email = "exemple@domine.terminacao";
        userEntity2.password = "12er56ty78";

        userEntity3.name = "Lucas";
        userEntity3.cpf = "111.111.111-22";
        userEntity3.accessProfiles = new HashSet<>();
        userEntity3.accessProfiles.add(3);
        userEntity3.email = "l@hjuftdys675.br";
        userEntity3.password = "_ed33.!0qdes";

        userService.insert(userEntity1);
        userService.insert(userEntity2);
        userService.insert(userEntity3);

        //----------------------------------- Inserindo os segmentos -----------------------------------

        SegmentEntity segmentEntity1 = new SegmentEntity();
        SegmentEntity segmentEntity2 = new SegmentEntity();
        SegmentEntity segmentEntity3 = new SegmentEntity();
        SegmentEntity segmentEntity4 = new SegmentEntity();
        SegmentEntity segmentEntity5 = new SegmentEntity();
        SegmentEntity segmentEntity6 = new SegmentEntity();

        segmentEntity1.name = "Alimentos e Bebidas";
        segmentEntity2.name = "Celulose e Papel";
        segmentEntity3.name = "Construção";
        segmentEntity4.name = "Equipamento Elétricos";
        segmentEntity5.name = "Óleo, Gás e Eletricidade";
        segmentEntity6.name = "Produtos Químicos e Plástico";

        segmentService.insert(segmentEntity1);
        segmentService.insert(segmentEntity2);
        segmentService.insert(segmentEntity3);
        segmentService.insert(segmentEntity4);
        segmentService.insert(segmentEntity5);
        segmentService.insert(segmentEntity6);

        // -------------------------------- Cadastrando as empresas ------------------------------------

        CompanyEntity companyEntity1 = new CompanyEntity();
        CompanyEntity companyEntity2 = new CompanyEntity();

        companyEntity1.name = "Lacticínios Tirol";
        companyEntity1.cnpj = "81.911.337/0001-58";
        companyEntity1.city = "Chapecó";
        companyEntity1.State = "SC";
        companyEntity1.segmentId = 1;
        companyEntity1.regionalSenai = "Oeste";
        companyEntity1.headOffice = false;
        companyEntity1.subsidiaryName = "Unidade de Chapecó";

        companyEntity2.name = "Plasc";
        companyEntity2.cnpj = "00.977.760/0001-53";
        companyEntity2.city = "Biguaçu";
        companyEntity2.State = "SC";
        companyEntity2.segmentId = 6;
        companyEntity2.regionalSenai = "Litoral Sul";
        companyEntity2.headOffice = true;

        companyService.insert(companyEntity1);
        companyService.insert(companyEntity2);

        // --------------------------------- Cadastro de Trilhas --------------------------------------

        TrailEntity trailEntity1 = new TrailEntity();
        TrailEntity trailEntity2 = new TrailEntity();
        TrailEntity trailEntity3 = new TrailEntity();

        trailEntity1.companyId = 1;
        trailEntity1.occupation = "Laboratorista";
        trailEntity1.description = "Realizar análises químicas, físicas, microbiológicas e instrumentais seguindo procedimentos técnicos.";

        trailEntity2.companyId = 1;
        trailEntity2.occupation = "Laboratorista";
        trailEntity2.description = "Realizar análises químicas, físicas, microbiológicas e instrumentais seguindo procedimentos técnicos.";

        trailEntity3.companyId = 2;
        trailEntity3.occupation = "Laboratorista";
        trailEntity3.description = "Realizar análises químicas, físicas, microbiológicas e instrumentais seguindo procedimentos técnicos.";

        trailService.insert(trailEntity1);
        trailService.insert(trailEntity2);
        trailService.insert(trailEntity3);

        System.out.printf("Nome: %s\nApelido: %s.\n\n", trailEntity1.name, trailEntity1.nickname);
        System.out.printf("Nome: %s\nApelido: %s.\n\n", trailEntity2.name, trailEntity2.nickname);
        System.out.printf("Nome: %s\nApelido: %s.\n\n", trailEntity3.name, trailEntity3.nickname);

        // -------------------------------- Cadastro de um trabalhador -----------------------------------

        EmployeeEntity employee1 = new EmployeeEntity();

        employee1.name = "Maria";
        employee1.cpf = "831.536.320-49";
        employee1.companyId = 1;
        employee1.department = "Laboratório";
        employee1.occupation = "Laboratorista";
        employee1.occupationDate = OffsetDateTime.of(2020, 1, 12, 8, 0, 0, 0, ZoneOffset.of("-03:00"));
        employee1.trails = new HashSet<>();
        employee1.trails.add(1);

        employeeService.insert(employee1);

        // ------------------------------ Edição do cadastro do trabalhador ------------------------------

        EmployeeEditDTO employeeDTO = new EmployeeEditDTO();

        EmployeeEntity employeeEdit = employeeService.get(1);

        employeeDTO.companyId = employeeEdit.companyId;
        employeeDTO.department = employeeEdit.department;
        employeeDTO.occupation = employeeEdit.occupation;
        employeeDTO.occupationDate = employeeEdit.occupationDate;
        employeeDTO.trails = employeeEdit.trails;

        employeeDTO.trails.add(2);
        employeeService.update(1, employeeDTO);

        // ------------------------------------- Cadastro dos módulos --------------------------------------

        ModuleEntity moduleEntity1 = new ModuleEntity();
        ModuleEntity moduleEntity2 = new ModuleEntity();
        ModuleEntity moduleEntity3 = new ModuleEntity();
        ModuleEntity moduleEntity4 = new ModuleEntity();

        moduleEntity1.trailId = 1;
        moduleEntity1.name = "Carta de Controle e Diagrama de Dispersão";
        moduleEntity1.skills = "Elaboração da Carta de controle";
        moduleEntity1.assessment = "Elaborar e preencher uma carta controle.";
        moduleEntity1.limitDate = 10;

        moduleEntity2.trailId = 1;
        moduleEntity2.name = "Fundamentos de Técnicas Laboratoriais";
        moduleEntity2.skills = "Vidrarias de Calibração e micropipetas;";
        moduleEntity2.limitDate = 10;

        moduleEntity3.trailId = 1;
        moduleEntity3.name = "Calibração de equipamentos de pesagem";
        moduleEntity3.skills = "Balanças de precisão e Semi analiticas";
        moduleEntity3.limitDate = 10;

        moduleEntity4.trailId = 3;
        moduleEntity4.name = "Carta de Controle e Diagrama de Dispersão";
        moduleEntity4.skills = "Elaboração da Carta de controle";
        moduleEntity4.limitDate = 10;

        moduleService.insert(moduleEntity1);
        moduleService.insert(moduleEntity2);
        moduleService.insert(moduleEntity3);
        moduleService.insert(moduleEntity4);

        moduleService.startModule(1);
        moduleService.startModule(2);

        moduleService.startEvaluation(1);

        //--------------------------------------- Avaliação do Módulo ---------------------------------

        AssessmentDTO assessmentDTO = new AssessmentDTO();

        assessmentDTO.score = 3;
        assessmentDTO.notes = "Avaliação do módulo feita.";

        assessmentService.evaluate(1,1, assessmentDTO);

        // -------------------------------------- Avaliação da Trilha ----------------------------------

        moduleEntity4.statusId = 4;

        TrailEvaluationDTO trailEvaluationDTO = new TrailEvaluationDTO();

        trailEvaluationDTO.score = 3;
        trailEvaluationDTO.notes = "Avaliação da trilha feita.";

        trailService.evaluation(3,trailEvaluationDTO);
    }
}
