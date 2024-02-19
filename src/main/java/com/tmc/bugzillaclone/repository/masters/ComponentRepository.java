package com.tmc.bugzillaclone.repository.masters;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.tmc.bugzillaclone.entity.masters.Component;

public interface ComponentRepository extends  MongoRepository<Component, String>{
    List<Component> findByComponentTitleContaining(String componentTitle);

    List<Component> findByStatus(boolean status);
}
