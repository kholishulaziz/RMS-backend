package com.bootcamp.rms.controller;

import com.bootcamp.rms.domain.Dependant;
import com.bootcamp.rms.repo.DependantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by Kholishul_A on 28/04/2017.
 */

@RestController
@RequestMapping(path = "/api/dependant")
public class DependantController {

    @Autowired
    private DependantRepository dependantRepository;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Dependant createDependant(@RequestBody Dependant dependant){
        return dependantRepository.save(dependant);
    }

    @RequestMapping(path = "/all", method = RequestMethod.GET)
    public Iterable<Dependant> findDependantAll() {
        return dependantRepository.findAll();
    }

    @RequestMapping(path = "/{Id}", method = RequestMethod.GET)
    public Dependant findDependant(@PathVariable String Id){
        Dependant dependant = dependantRepository.findOne(Id);
        if (dependant == null){
            throw new NoSuchElementException("Dependant does not exist :" + Id);
        }
        return dependant;
    }

    @RequestMapping(path = "/search/employeeId/{id}", method = RequestMethod.GET)
    public List<Dependant> findDependantByEmployeeId(@PathVariable String employeeId){
        return dependantRepository.findByEmployeeId(employeeId);
    }

    @RequestMapping(path= "/{Id}", method = RequestMethod.PUT)
    public Dependant editDependant(@PathVariable String Id, @RequestBody Dependant editDependant){
        findDependant(Id);
        return dependantRepository.save(editDependant);
    }

    @RequestMapping(path= "/{Id}", method = RequestMethod.DELETE)
    public void deleteDependant(@PathVariable String Id) {
        Dependant dependant = findDependant(Id);
        dependantRepository.delete(dependant);
    }
}


