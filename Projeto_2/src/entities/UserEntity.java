package entities;

import java.util.HashSet;

public class UserEntity {
    public int id;
    public String name;
    public String cpf;
    public String email;
    public String password;
    public HashSet<Integer> accessProfiles;
}
