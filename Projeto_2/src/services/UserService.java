package services;

import entities.UserEntity;
import repositories.AccessProfileDAO;
import repositories.UserDAO;

import java.security.InvalidParameterException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserService {

    private static final Pattern CPF_REGEX = Pattern.compile("^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$", Pattern.CASE_INSENSITIVE);
    private static final Pattern EMAIL_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,10}$", Pattern.CASE_INSENSITIVE);
    private static final Pattern NUMBER_REGEX = Pattern.compile("[0-9]+", Pattern.CASE_INSENSITIVE);
    private static final Pattern LETTER_REGEX = Pattern.compile("[A-Z]+", Pattern.CASE_INSENSITIVE);

    private final UserDAO userDAO;
    private final AccessProfileDAO accessProfileDAO;

    public UserService(UserDAO userDAO, AccessProfileDAO accessProfileDAO) {
        this.userDAO = userDAO;
        this.accessProfileDAO = accessProfileDAO;
    }

    public void insert(UserEntity userEntity) {

        if (userEntity.name == null || userEntity.name.isEmpty()) {
            throw new InvalidParameterException("Nome inválido.");
        }

        if (userEntity.cpf == null || userEntity.cpf.isEmpty()) {
            throw new InvalidParameterException("CPF inválido.");
        }

        Matcher cpfMatcher = CPF_REGEX.matcher(userEntity.cpf);
        if(!cpfMatcher.find()) {
            throw new InvalidParameterException("CPF inválido.");
        }

        if (userEntity.email == null || userEntity.email.isEmpty()) {
            throw new InvalidParameterException("Email inválido.");
        }

        Matcher emailMatcher = EMAIL_REGEX.matcher(userEntity.email);
        if(!emailMatcher.find()) {
            throw new InvalidParameterException("Email inválido.");
        }

        if (userEntity.password == null || userEntity.password.isEmpty()) {
            throw new InvalidParameterException("Senha inválida.");
        }

        if (userEntity.password.length() < 8) {
            throw new InvalidParameterException("A senha deve conter ao menos 8 caracteres.");
        }

        Matcher numberMatcher = NUMBER_REGEX.matcher(userEntity.password);
        if(!numberMatcher.find()) {
            throw new InvalidParameterException("A senha deve conter ao menos um caractere numérico.");
        }

        Matcher letterMatcher = LETTER_REGEX.matcher(userEntity.password);
        if(!letterMatcher.find()) {
            throw new InvalidParameterException("A senha deve conter ao menos uma letra.");
        }

        if (userEntity.accessProfiles == null || userEntity.accessProfiles.size() == 0) {
            throw new InvalidParameterException("O usuário deve ter pelo menos um perfil vinculado.");
        }

        for (int accessProfileId : userEntity.accessProfiles) {
            if (accessProfileDAO.get(accessProfileId) == null) {
                throw new InvalidParameterException("Perfil de acesso inválido.");
            }
        }

        userDAO.insert(userEntity);
    }

    public UserEntity get(int id) {
        return userDAO.get(id);
    }
}
