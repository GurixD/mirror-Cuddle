package ch.hearc.cuddle.auth.service;

public interface SecurityService_I {
    String findLoggedInUsername();

    void autoLogin(String username, String password);
}
