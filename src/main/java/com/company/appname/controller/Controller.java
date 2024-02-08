package com.company.appname.controller;

import com.company.appname.dto.Employee;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class Controller {

    private Map<Integer, Employee> employeeMap = new HashMap<>();

    //CRUD
    //Read
    @GetMapping("/") // API URL: Localhost:8090/
    public String getGreetings() { // handler method
        return "Welcome to spring boot !";
    }

    @PostMapping("/") // Creating new resource
    public String createData() {
        return "POstMapping";
    }

    @PutMapping("/") // replace a existing resource
    public String replaceData() {
        return "PutMapping";
    }

    @PatchMapping("/") // updating partial data
    public String updatePartialData() {
        return "PatchMapping";
    }

    @DeleteMapping("/") // delete a data/resource
    public String deleteData() {
        return "deleteMapping";
    }

    // URL best practice : 1. is to give a noun for resources
    @GetMapping("employee/{id}") // URL localhost:8090/employee/1
    public Employee getEmployee(@PathVariable("id") Integer id) {
        return this.employeeMap.get(id);
    }

    @PostMapping("employee")
    public Employee addEmployee(@RequestBody Employee newEmployee) {
        this.employeeMap.put(newEmployee.getId(), newEmployee);
        return this.employeeMap.get(newEmployee.getId());
    }

    @PutMapping("employee")
    public Employee replaceEmployee(@RequestBody Employee replaceEmployee) {
        this.employeeMap.replace(replaceEmployee.getId(), replaceEmployee);
        return this.employeeMap.get(replaceEmployee.getId());
    }

    //@PatchMapping("employee/{id}/salary/{amount}")
    @PatchMapping("employee/salary") // http://localhost:8090/employee/salary
    public Employee updateEmployeeSalary(@RequestBody Employee employee) {
        // this.employeeMap.get(updateEmployee.getId()).setSalary(updateEmployee.getSalary());
        Employee foundEmployee = this.employeeMap.get(employee.getId());
        foundEmployee.setSalary(employee.getSalary());
        return this.employeeMap.get(employee.getId());
    }

    @DeleteMapping("employee/{id}")
    public Employee deleteEmployeeById(@PathVariable("id") Integer id) {

        return this.employeeMap.remove(id);
    }
    @GetMapping("employees") //
    public Collection<Employee> getAllEmployees() {
    return this.employeeMap.values();
    }
    @GetMapping("employee/names")
    public List<String> getAllEmployeeNames(){
        return this.employeeMap.values()
                .stream().map((e)->e.getName())
                .collect(Collectors.toList());
    }

}
