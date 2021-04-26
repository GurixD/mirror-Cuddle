package ch.hearc.cuddle.auth.service;

import ch.hearc.cuddle.auth.model.User;
import ch.hearc.cuddle.auth.repository.RoleRepository;
import ch.hearc.cuddle.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserService_I{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User save(User user) {
        return save(user, true);
    }

    public User save(User user, boolean encrypt) {
        if(encrypt) user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public long count() {
        return userRepository.count();
    }

    public List<User> findAll(int pageNumber, int rowPerPage) {
        List<User> users = new ArrayList<>();
        Pageable sortedByIdAsc = PageRequest.of(pageNumber - 1, rowPerPage,
                Sort.by("id").ascending());
        userRepository.findAll(sortedByIdAsc).forEach(users::add);
        return users;
    }

    public User findById(long id) {
        return userRepository.findById(id).orElse(null);
    }

    private boolean existsById(Long id) {
        return userRepository.existsById(id);
    }

    public boolean deleteById(Long id) {
        try {
            if (existsById(id)) {
                userRepository.deleteById(id);
                return true;
            }
        } catch (Exception e) {
            return false;
        }

        return false;
    }
}
