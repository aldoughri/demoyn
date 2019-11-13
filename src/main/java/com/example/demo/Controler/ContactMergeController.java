package com.example.demo.Controler;

import com.example.demo.Model.ContactMerge;
import com.example.demo.Model.ContactsGroup;
import com.example.demo.Service.MergesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
@RestController
@RequestMapping("/Merge")
public class ContactMergeController {
    @Autowired
    MergesService mergesService;

    @RequestMapping(value = "/getContacts",method = RequestMethod.GET)
    public ModelAndView getDepartments() {
        return new ModelAndView("Employee", "groups", mergesService.getMerges());
    }


    //STEP2
    @RequestMapping(value = "get/{id}",method = RequestMethod.GET)
    @ResponseBody
    public String show(@PathVariable Long id) {
        mergesService.getId(id);
        return "Here we will display employee departments" + id;
    }

    //STEP3
    @RequestMapping(value = "/new",method = RequestMethod.GET)
    public String employeeForm(Model model) {

        model.addAttribute("Group", new ContactsGroup());
        return "Dform";
    }

    //STEP4
    @PostMapping("/contacts")
    @ResponseBody
    public String greetingSubmit(@ModelAttribute ContactMerge contactMerge) {
        mergesService.add(contactMerge);
        return "subission successful of no." + contactMerge.getId();
    }

    @RequestMapping(value = "/del/{id}",method = RequestMethod.GET)
    public String deleteDepartment(@PathVariable(value = "id") Long id) {

        mergesService.remove(id);


        return "delete successful of no." + id;

    }

}
