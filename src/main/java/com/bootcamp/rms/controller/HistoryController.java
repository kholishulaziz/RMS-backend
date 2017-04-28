package com.bootcamp.rms.controller;

import com.bootcamp.rms.domain.History;
import com.bootcamp.rms.repo.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by Kholishul_A on 27/04/2017.
 */

@RestController
@RequestMapping(path = "/api/history")
public class HistoryController {

    @Autowired
    private HistoryRepository historyRepository;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public History createHistory(@RequestBody History history){
        return historyRepository.save(history);
    }

    @RequestMapping(path = "/all", method = RequestMethod.GET)
    public Iterable<History> findHistoryAll() {
        return historyRepository.findAll();
    }

    @RequestMapping(path = "/{Id}", method = RequestMethod.GET)
    public History findHistory(@PathVariable String Id){
        History history = historyRepository.findOne(Id);
        if (history == null){
            throw new NoSuchElementException("History does not exist :" + Id);
        }
        return history;
    }

    @RequestMapping(path = "/search/employeeId/{employeeId}", method = RequestMethod.GET)
    public List<History> findHistoryByEmployeeId(@PathVariable String employeeId){
        return historyRepository.findByEmployeeId(employeeId);
    }

    @RequestMapping(path= "/{Id}", method = RequestMethod.PUT)
    public History editHistory(@PathVariable String Id, @RequestBody History editHistory){
        findHistory(Id);
        return historyRepository.save(editHistory);
    }

    @RequestMapping(path= "/{Id}", method = RequestMethod.DELETE)
    public void deleteHistory(@PathVariable String Id) {
        History history = findHistory(Id);
        historyRepository.delete(history);
    }

}
