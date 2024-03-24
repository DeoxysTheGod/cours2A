package net.uzaki.user;

import java.util.*;

public interface UserRepositoryInterface {
    public void close();
    public User getUser(String id);
    public ArrayList<User> getAllUsers();
}
