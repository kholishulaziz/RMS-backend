package com.bootcamp.rms.controller;

import com.bootcamp.rms.domain.Grade;
import com.bootcamp.rms.repo.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by Kholishul_A on 28/04/2017.
 */

@RestController
@RequestMapping(path = "/api/grade")
public class GradeController {

    @Autowired
    private GradeRepository gradeRepository;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Grade createGrade(@RequestBody Grade grade){
        return gradeRepository.save(grade);
    }

    @RequestMapping(path = "/all", method = RequestMethod.GET)
    public Iterable<Grade> findGradeAll() {
        return gradeRepository.findAll();
    }

    @RequestMapping(path = "/{Id}", method = RequestMethod.GET)
    public Grade findGrade(@PathVariable String Id){
        Grade grade = gradeRepository.findOne(Id);
        if (grade == null){
            throw new NoSuchElementException("Grade does not exist :" + Id);
        }
        return grade;
    }

    @RequestMapping(path = "/search/employeeId/{id}", method = RequestMethod.GET)
    public List<Grade> findGradeByEmployeeId(@PathVariable String employeeId){
        return gradeRepository.findByEmployeeId(employeeId);
    }

    @RequestMapping(path= "/{Id}", method = RequestMethod.PUT)
    public Grade editGrade(@PathVariable String Id, @RequestBody Grade editGrade){
        findGrade(Id);
        return gradeRepository.save(editGrade);
    }

    @RequestMapping(path= "/{Id}", method = RequestMethod.DELETE)
    public void deleteGrade(@PathVariable String Id) {
        Grade grade = findGrade(Id);
        gradeRepository.delete(grade);
    }

}
