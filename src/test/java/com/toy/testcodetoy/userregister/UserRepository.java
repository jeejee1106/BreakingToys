package com.toy.testcodetoy.userregister;

public interface UserRepository {

    void save(User user);
    User findById(String id);

}
