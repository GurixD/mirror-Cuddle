package ch.hearc.cuddle.auth.service;

import ch.hearc.cuddle.auth.model.Role;
import ch.hearc.cuddle.auth.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepo;

    public List<Role> findAll() {

        return roleRepo.findAll();
    }
}
