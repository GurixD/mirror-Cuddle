package ch.hearc.cuddle.auth.service;

import ch.hearc.cuddle.auth.model.Role;
import ch.hearc.cuddle.auth.model.User;
import ch.hearc.cuddle.auth.repository.RoleRepository;
import ch.hearc.cuddle.auth.repository.UserRepository;
import ch.hearc.cuddle.models.Animal;
import ch.hearc.cuddle.repository.AnimalRepository;
import ch.hearc.cuddle.repository.SpeciesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepo;

    public List<Role> findAll() {

        return roleRepo.findAll();
    }
}
