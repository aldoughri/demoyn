package com.example.demo.Controler;

import com.example.demo.Model.Department;
import com.example.demo.Service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;

    @RequestMapping(value = "/getDepartments", method = RequestMethod.GET)
    public ModelAndView getDepartments() {
        return new ModelAndView("DepartmentHome", "departments", departmentService.getDepartments());
    }


    //STEP2
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String show(@PathVariable Long id) {
        departmentService.getDepid(id);
        return "Here we will display employee departments" + id;
    }

    //STEP3
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public ModelAndView employeeForm(Model model) {

        model.addAttribute("department", new Department());
  //      model.addAttribute("url","");

        return new ModelAndView("Dform");
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public ModelAndView employeeUpdate(Model model, @PathVariable Long id) {
        Department department = departmentService.getDepid(id);
        if(department==null){
            return new ModelAndView("redirect:/");
        }

        model.addAttribute("url","/edit");
        model.addAttribute("department", department);
        return new ModelAndView("Dform");
    }

    @PostMapping("/employees")
    @ResponseBody
    public String greetingSubmit(@ModelAttribute Department department) {
        departmentService.add(department);
        return "subission successful of no." + department.getId();
    }

    @PostMapping("/employees/edit")
    @ResponseBody
    public String greetingSubmitedit(@ModelAttribute Department department) {
        departmentService.add(department);
        return "subission successful of no." + department.getId();
    }

    @RequestMapping(value = "/del/{id}", method = RequestMethod.GET)
    public String deleteDepartment(@PathVariable(value = "id") Long id) {

        departmentService.remove(id);


        return "delete successful of no." + id;

    }

}
