package com.example.demo.Controler;

import com.example.demo.Model.Department;
import com.example.demo.Service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;
    @RequestMapping("/getDepartments")
    public ModelAndView getDepartments(){
        return new ModelAndView("Employee","departments", departmentService.getDepartments());
    }


    //STEP2
    @RequestMapping("/{id}")
    @ResponseBody
    public String show(@PathVariable Long id) {
        departmentService.getDepid(id);
        return "Here we will display employee departments" + id;
    }

    //STEP3
    @RequestMapping("/new")
    public String employeeForm(Model model) {

        model.addAttribute("department", new Department());
        return "Dform";
    }

    //STEP4
    @PostMapping("/departments")
    @ResponseBody
    public String greetingSubmit(@ModelAttribute Department department) {
        departmentService.add(department);
        return "subission successful of no." + department.getId();
    }

    @RequestMapping ("/del/{id}")
    public String deleteDepartment(@PathVariable(value = "id") Long id){

        departmentService.remove(id);


        return "delete successful of no." + id;

    }
    /*@RequestMapping("/employees/newr")
    public String rel(Model model) {
        ConED  conED=new ConED();
        DAO.findAll().forEach(dep->conED.dep.add(dep));
        model.addAttribute("ConED", conED);
        model.addAttribute("employee",new Employee());
        return "relation";
    }
*/
}
