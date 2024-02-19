package com.tmc.bugzillaclone.repository.masters;

import com.tmc.bugzillaclone.entity.masters.Menu;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MenuRepository extends MongoRepository<Menu, String> {
   
}
