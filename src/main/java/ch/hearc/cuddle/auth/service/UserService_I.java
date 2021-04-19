package ch.hearc.cuddle.auth.service;

import ch.hearc.cuddle.auth.model.User;

public interface UserService_I {
    void save(User user);

    User findByEmail(String email);
}
