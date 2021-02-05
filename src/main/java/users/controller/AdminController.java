package users.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import users.model.User;
import users.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String getAllUsers(ModelMap modelMap) {
        modelMap.addAttribute("usersList", userService.getListUser());
        return "users";
    }

    @GetMapping(value = "/update/{id}")
    public String updatePage(@PathVariable Long id, ModelMap modelMap) {
        modelMap.addAttribute("user", userService.getUserById(id));
        return "update";
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute User user) {
        userService.update(user);
        return "redirect:/users";
    }

    @GetMapping("/add")
    public String addPage() {
        return "add";
    }

/*    @PostMapping("/add")
    public String addUser(@ModelAttribute User user, ModelMap model) {
        userService.add(user);
        return "redirect:/users";
    }   */
    @PostMapping("/add")
    public String addUser(@ModelAttribute("userForm") User user, ModelMap model) {
        userService.add(user);
        return "redirect:/users";
    }



    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return "redirect:/users";
    }
}




