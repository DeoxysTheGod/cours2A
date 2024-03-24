package net.uzaki.reservation;

import java.util.ArrayList;

public interface UserRepositoryInterface {
    public void close();
    public User getUser(String id);
}
