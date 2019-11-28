package com.example.demo.Controler;


import com.example.demo.Model.ContactsGroup;
import com.example.demo.Service.GroupsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/Group")
@RestController
public class ContactGroupsController {
    @Autowired
    GroupsService groupsService;

    @RequestMapping(value = "/getGroups", method = RequestMethod.GET)
    public ModelAndView getDepartments() {
        return new ModelAndView("Employee", "groups", groupsService.getGroups());
    }

    //STEP2
    @RequestMapping(value = "get/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String show(@PathVariable Long id) {
        groupsService.getId(id);
        return "Here we will display employee departments" + id;
    }

    //STEP3
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String employeeForm(Model model) {

        model.addAttribute("Group", new ContactsGroup());
        return "Dform";
    }

    //STEP4
    @PostMapping("/contacts")
    @ResponseBody
    public String greetingSubmit(@ModelAttribute ContactsGroup contactsGroup) {
        groupsService.add(contactsGroup);
        return "subission successful of no." + contactsGroup.getId();
    }

    @RequestMapping(value = "/del/{id}", method = RequestMethod.GET)
    public String deleteDepartment(@PathVariable(value = "id") Long id) {

        groupsService.remove(id);


        return "delete successful of no." + id;

    }

}
