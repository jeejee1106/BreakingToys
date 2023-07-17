package com.toy.testcodetoy.userregister;

import java.util.HashMap;
import java.util.Map;

/**
 * 가짜(fake)
 * - 제품에는 적합하지 않지만, 실제 동작하는 구현을 제공한다.
 * - '실제 DB 대신에 메모리를 이용해서 구현'하는 것이 가짜 대역에 해당한다!
 */

public class MemoryUserRepository implements UserRepository {

    private Map<String, User> userMap = new HashMap<>();

    @Override
    public void save(User user) {
        userMap.put(user.getId(), user);
    }

    @Override
    public User findById(String id) {
        return userMap.get(id);
    }
}
