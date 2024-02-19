package com.tmc.bugzillaclone.controllers.masters;

import com.tmc.bugzillaclone.entity.masters.Component;
import com.tmc.bugzillaclone.repository.masters.ComponentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http:localhost:8081")
@RestController
@RequestMapping("/api/components")
public class ComponentController {
    @Autowired
    ComponentRepository componentRepository;

    @GetMapping("")
    @PreAuthorize("hasRole('ADMIN')")

    public ResponseEntity<List<Component>> getAllComponent(@RequestParam(required = false) String componentTitle) {
        try{
            List<Component> componentList;
            if (componentTitle != null && !componentTitle.isEmpty()) {
                componentList = componentRepository.findByComponentTitleContaining(componentTitle);

            }else {
                componentList = componentRepository.findAll();
            }
            if (componentList.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(componentList, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")

    public ResponseEntity<Component> getComponentById(@PathVariable("id") String id) {
        Optional<Component> componentData = componentRepository.findById(id);
        return  componentData.map(component -> new ResponseEntity<>(component, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("")
    @PreAuthorize("hasRole('ADMIN')")

    public ResponseEntity<Component> createComponent(@RequestBody Component component) {
        try {
            Component _component = componentRepository.save(new Component(component.getComponentTitle(), component.isStatus()));
            return new ResponseEntity<>(_component, HttpStatus.CREATED);

        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")

    public ResponseEntity<Component> updateStatus(@PathVariable("id") String id, @RequestBody Component component){
        Optional<Component> componentData = componentRepository.findById(id);
        if (componentData.isPresent()){
            Component _component = componentData.get();
            _component.setComponentTitle(component.getComponentTitle());
            _component.setStatus(component.isStatus());
            return new ResponseEntity<>(componentRepository.save(_component), HttpStatus.OK);

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")

    public ResponseEntity<HttpStatus> deleteComponent(@PathVariable("id") String id) {
        try {
            componentRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("")
    @PreAuthorize("hasRole('ADMIN')")

    public ResponseEntity<HttpStatus> deleteAllComponent(){
        try {
            componentRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/active")
    @PreAuthorize("hasRole('ADMIN')")

    public ResponseEntity<List<Component>> findActiveComponent() {
        try{
            List<Component> componentList = componentRepository.findByStatus(true);
            if (componentList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(componentList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
