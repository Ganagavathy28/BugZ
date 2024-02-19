package com.tmc.bugzillaclone.repository.masters;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.tmc.bugzillaclone.entity.masters.Status;

public interface StatusRepository extends MongoRepository<Status, String> {
    List<Status> findByStatusTitleContaining(String statusTitle);
    List<Status> findByStatus(boolean status);
}
