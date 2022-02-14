package services;

import entities.AccessProfileEntity;
import repositories.AccessProfileDAO;

public class AccessProfileService {

    private final AccessProfileDAO accessProfileDAO;

    public AccessProfileService(AccessProfileDAO accessProfileDAO) {
        this.accessProfileDAO = accessProfileDAO;
    }

    public void insert(AccessProfileEntity accessProfileEntity) {
        accessProfileDAO.insert(accessProfileEntity);
    }

    public AccessProfileEntity get(int id) {
        return accessProfileDAO.get(id);
    }
}
