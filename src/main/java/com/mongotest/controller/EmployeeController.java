package com.mongotest.controller;

import com.mongotest.model.EmployeeDTO;
import com.mongotest.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/employees")
    public ResponseEntity<?> getAllEmployees() {
        List<EmployeeDTO> employees = employeeRepository.findAll();
        if (!employees.isEmpty()) {
            return new ResponseEntity<>(employees, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No Employees Found", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/addEmployee")
    public ResponseEntity<?> createEmployee(@RequestBody EmployeeDTO employee) {
        try {
            employee.setCreatedAt(new Date());
            employeeRepository.save(employee);
            return new ResponseEntity<>(employee, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<?> getEmployee(@PathVariable("id") String id){
        Optional<EmployeeDTO> employeeById = employeeRepository.findById(id);
        if(employeeById.isPresent()){
            return new ResponseEntity<>(employeeById.get(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Employee Not Found with id"+id, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable("id") String id, @RequestBody EmployeeDTO employee){
        Optional<EmployeeDTO> employeeById = employeeRepository.findById(id);
        if(employeeById.isPresent()){

            EmployeeDTO currentEmployee = employeeById.get();
            currentEmployee.setEmployeeName(employee.getEmployeeName()!= null ? employee.getEmployeeName() : currentEmployee.getEmployeeName());
            currentEmployee.setEmployeeGender(employee.getEmployeeGender()!= null ? employee.getEmployeeGender() : currentEmployee.getEmployeeGender());
            currentEmployee.setDesignation(employee.getDesignation()!= null ? employee.getDesignation() : currentEmployee.getDesignation());
            currentEmployee.setUpdatedAt(new Date(System.currentTimeMillis()));
            employeeRepository.save(currentEmployee);
            return new ResponseEntity<>(currentEmployee, HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Employee Not Found with id"+id, HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/employee/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") String id){
        Optional<EmployeeDTO> employeeById = employeeRepository.findById(id);
        if(employeeById.isPresent()) {
            try {
                employeeRepository.deleteById(id);
                return new ResponseEntity<>("Employee with user id " + id + " is deleted", HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
            }
        }
        else{
            return new ResponseEntity<>("Employee Not Found with id"+id, HttpStatus.NOT_FOUND);
        }
    }
}