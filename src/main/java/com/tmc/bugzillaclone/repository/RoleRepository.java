package com.tmc.bugzillaclone.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.tmc.bugzillaclone.entity.ERole;
import com.tmc.bugzillaclone.entity.Role;

public interface RoleRepository extends MongoRepository<Role, String>{

    Optional<Role> findByName(ERole name);
}
