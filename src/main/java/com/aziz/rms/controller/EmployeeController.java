package com.aziz.rms.controller;

import com.aziz.rms.domain.Employee;
import com.aziz.rms.repo.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by Kholishul_A on 20/04/2017.
 */

@RestController
@RequestMapping(path = "/api/employee")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Employee createEmployee(@RequestBody Employee employee){
        return employeeRepository.save(employee);
    }

    @RequestMapping(path = "/all", method = RequestMethod.GET)
    public Iterable<Employee> findEmployeeAll() {
        return employeeRepository.findAll();
    }

    @RequestMapping(path = "/{Id}", method = RequestMethod.GET)
    public Employee findEmployee(@PathVariable String Id){
        Employee employee = employeeRepository.findOne(Id);
        if (employee == null){
            throw new NoSuchElementException("Employee does not exist :" + Id);
        }
        return employee;
    }

    @RequestMapping(path = "/search/name/{name}", method = RequestMethod.GET)
    public List<Employee> findEmployeeByName(@PathVariable String name){
        return employeeRepository.findByFirstNameContainsOrLastNameContains(name, name);
    }

    @RequestMapping(path= "/{Id}", method = RequestMethod.PUT)
    public Employee editEmployee(@PathVariable String Id, @RequestBody Employee editEmployee){
        findEmployee(Id);
        return employeeRepository.save(editEmployee);
    }

    @RequestMapping(path= "/{Id}", method = RequestMethod.DELETE)
    public void deleteEmployee(@PathVariable String Id) {
        Employee employee = findEmployee(Id);
        employeeRepository.delete(employee);
    }
}
