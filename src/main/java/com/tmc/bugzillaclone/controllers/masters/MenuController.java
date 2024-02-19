package com.tmc.bugzillaclone.controllers.masters;

import com.tmc.bugzillaclone.entity.masters.Menu;
import com.tmc.bugzillaclone.entity.masters.Submenu;
import com.tmc.bugzillaclone.service.masters.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/menus")
public class MenuController {

    @Autowired

    private MenuService menuService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")

    public ResponseEntity<List<Menu>> getAllMenus() {
        List<Menu> menus = menuService.getAllMenus();
        return new ResponseEntity<>(menus, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")

    public ResponseEntity<Menu> getMenuById(@PathVariable String id) {
        Optional<Menu> menu = menuService.getMenuById(id);
        return menu.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")

    public ResponseEntity<Menu> createMenu(@RequestBody Menu menu) {
        Menu createdMenu = menuService.createMenu(menu);
        return new ResponseEntity<>(createdMenu, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")

    public ResponseEntity<Menu> updateMenu(@PathVariable String id, @RequestBody Menu updatedMenu) {
        Menu menu = menuService.updateMenu(id, updatedMenu);
        return new ResponseEntity<>(menu, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")

    public ResponseEntity<Void> deleteMenu(@PathVariable String id) {
        menuService.deleteMenu(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{menuId}/submenus")
    @PreAuthorize("hasRole('ADMIN')")

    public ResponseEntity<Menu> addSubmenu(@PathVariable String menuId, @RequestBody Submenu submenu) {
        Menu menu = menuService.addSubmenu(menuId, submenu);
        return new ResponseEntity<>(menu, HttpStatus.OK);
    }

    @PutMapping("/{menuId}/submenus/{submenuId}")
    @PreAuthorize("hasRole('ADMIN')")

    public ResponseEntity<Menu> updateSubmenu(@PathVariable String menuId,
            @PathVariable String submenuId,
            @RequestBody Submenu updatedSubmenu) {
        Menu menu = menuService.updateSubmenu(menuId, submenuId, updatedSubmenu);
        return new ResponseEntity<>(menu, HttpStatus.OK);
    }

    @DeleteMapping("/{menuId}/submenus/{submenuId}")
    @PreAuthorize("hasRole('ADMIN')")

    public ResponseEntity<Menu> deleteSubmenu(@PathVariable String menuId,
            @PathVariable String submenuId) {
        Menu menu = menuService.deleteSubmenu(menuId, submenuId);
        return new ResponseEntity<>(menu, HttpStatus.OK);
    }
}
