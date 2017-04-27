package com.aziz.rms.controller;

import com.aziz.rms.domain.Lookup;
import com.aziz.rms.repo.LookupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Kholishul_A on 20/04/2017.
 */

@RestController
@RequestMapping(path = "/api/lookup")
public class LookupController {

    @Autowired
    private LookupRepository lookupRepository;

    @RequestMapping(path = "/all", method = RequestMethod.GET)
    public Iterable<Lookup> findLookupAll(){
        return lookupRepository.findAll();
    }

    @RequestMapping(path = "/search/{dataType}", method = RequestMethod.GET)
    public List<Lookup> findLookupByDataType(@PathVariable String dataType){
        return lookupRepository.findByDataType(dataType);
    }
}
