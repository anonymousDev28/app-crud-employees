package com.techmaster.appcrudemployees.controller;

import com.techmaster.appcrudemployees.model.Employee;
import com.techmaster.appcrudemployees.service.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EmployeeController {
    @Autowired
    EmployeeServiceImpl employeeService;
    // display employees
    @GetMapping("/")
    public String viewHomePage(Model model){
        return findPaginated(1,"firstName","asc",model);
//        model.addAttribute("Employees",employeeService.getAllEmployees());
//        return "index";
    }

    @GetMapping("/newEmployeeForm")
    public String addNewEmployeeForm(Model model){
        Employee employee = new Employee();
        model.addAttribute("employee",employee);
        return "new_employee";
    }
    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute("employee") Employee employee){
        employeeService.saveEmployee(employee);
        return "redirect:/";
    }
    @GetMapping("/editEmployeeForm/{id}")
    public String editEmployeeForm(@PathVariable (value = "id") int id,Model model){
        Employee employee = employeeService.getEmployeeById(id);
        model.addAttribute("employee",employee);
        return "edit_employee";
    }
    @GetMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable (value = "id") int id){
        employeeService.deleteEmployeeById(id);
        return "redirect:/";
    }
    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDirection,
                                Model model) {
        int pageSize = 5;

        Page < Employee > page = employeeService.findPaginated(pageNo, pageSize,sortField,sortDirection);
        List < Employee > listEmployees = page.getContent();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("sortField",sortField);
        model.addAttribute("sortDir",sortDirection);
        model.addAttribute("reverseSortDir",sortDirection.equals("asc")?"desc":"asc");
        model.addAttribute("listEmployees", listEmployees);
        return "index";
    }

}
