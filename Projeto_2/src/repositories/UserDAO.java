package repositories;

import entities.UserEntity;

import java.util.HashMap;
import java.util.Map;

public class UserDAO {
    private int count = 0;

    private Map<Integer, UserEntity> users = new HashMap<>();

    public void insert(UserEntity userEntity) {
        userEntity.id = ++count;
        this.users.put(userEntity.id, userEntity);
    }

    public void update(UserEntity userEntity) {
        this.users.put(userEntity.id, userEntity);
    }

    public UserEntity get(int id) {
        return users.get(id);
    }
}
