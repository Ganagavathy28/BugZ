package com.tmc.bugzillaclone.controllers.masters;

import com.tmc.bugzillaclone.entity.masters.Status;
import com.tmc.bugzillaclone.repository.masters.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/status")
public class StatusController {
    @Autowired
    StatusRepository statusRepository;

    @GetMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Status>> getAllStatus(@RequestParam(required = false) String statusTitle) {
        try {
            List<Status> statusList;
            if (statusTitle != null && !statusTitle.isEmpty()) {
                statusList = statusRepository.findByStatusTitleContaining(statusTitle);
            } else {
                statusList = statusRepository.findAll();
            }
            if (statusList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(statusList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")

    public ResponseEntity<Status> getStatusById(@PathVariable("id") String id) {
        Optional<Status> statusData = statusRepository.findById(id);
        return statusData.map(status -> new ResponseEntity<>(status, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("")
    @PreAuthorize("hasRole('ADMIN')")

    public ResponseEntity<Status> createStatus(@RequestBody Status status) {
        try {
            Status _status = statusRepository.save(new Status(status.getStatusTitle(), status.isStatus()));
            return new ResponseEntity<>(_status, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")

    public ResponseEntity<Status> updateStatus(@PathVariable("id") String id, @RequestBody Status status) {
        Optional<Status> statusData = statusRepository.findById(id);
        if (statusData.isPresent()) {
            Status _status = statusData.get();
            _status.setStatusTitle(status.getStatusTitle());
            _status.setStatus(status.isStatus());
            return new ResponseEntity<>(statusRepository.save(_status), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")

    public ResponseEntity<HttpStatus> deleteStatus(@PathVariable("id") String id) {
        try {
            statusRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("")
    @PreAuthorize("hasRole('ADMIN')")

    public ResponseEntity<HttpStatus> deleteAllStatus() {
        try {
            statusRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/active")
    @PreAuthorize("hasRole('ADMIN')")

    public ResponseEntity<List<Status>> findActiveStatus() {
        try {
            List<Status> statusList = statusRepository.findByStatus(true);
            if (statusList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(statusList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
