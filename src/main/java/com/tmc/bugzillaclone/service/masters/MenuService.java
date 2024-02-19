package com.tmc.bugzillaclone.service.masters;

import com.tmc.bugzillaclone.entity.masters.Menu;
import com.tmc.bugzillaclone.entity.masters.Submenu;
import com.tmc.bugzillaclone.repository.masters.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;

    public List<Menu> getAllMenus() {
        return menuRepository.findAll();
    }

    public Optional<Menu> getMenuById(String id) {
        return menuRepository.findById(id);
    }

    public Menu createMenu(Menu menu) {
        return menuRepository.save(menu);
    }

    public Menu updateMenu(String id, Menu updatedMenu) {
        updatedMenu.setId(id);
        return menuRepository.save(updatedMenu);
    }

    public void deleteMenu(String id) {
        menuRepository.deleteById(id);
    }

    public Menu addSubmenu(String menuId, Submenu submenu) {
        Optional<Menu> menuOptional = menuRepository.findById(menuId);
        if (menuOptional.isPresent()) {
            Menu menu = menuOptional.get();
            List<Submenu> submenus = menu.getSubmenus();
            submenus.add(submenu);
            menu.setSubmenus(submenus);
            return menuRepository.save(menu);
        } else {
            throw new RuntimeException("Menu not found");
        }
    }

    public Menu updateSubmenu(String menuId, String submenuId, Submenu updatedSubmenu) {
        Optional<Menu> menuOptional = menuRepository.findById(menuId);
        if (menuOptional.isPresent()) {
            Menu menu = menuOptional.get();
            List<Submenu> submenus = menu.getSubmenus();
            for (Submenu submenu : submenus) {
                if (submenu.getSubId().equals(submenuId)) {
                    int index = submenus.indexOf(submenu);
                    submenus.set(index, updatedSubmenu);
                    menu.setSubmenus(submenus);
                    return menuRepository.save(menu);
                }
            }
            throw new RuntimeException("Submenu not found");
        } else {
            throw new RuntimeException("Menu not found");
        }
    }

    public Menu deleteSubmenu(String menuId, String submenuId) {
        Optional<Menu> menuOptional = menuRepository.findById(menuId);
        if (menuOptional.isPresent()) {
            Menu menu = menuOptional.get();
            List<Submenu> submenus = menu.getSubmenus();
            submenus.removeIf(submenu -> submenu.getSubId().equals(submenuId));
            menu.setSubmenus(submenus);
            return menuRepository.save(menu);
        } else {
            throw new RuntimeException("Menu not found");
        }
    }
}
