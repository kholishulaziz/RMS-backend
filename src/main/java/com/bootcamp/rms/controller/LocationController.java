package com.bootcamp.rms.controller;

import com.bootcamp.rms.domain.Location;
import com.bootcamp.rms.repo.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by Kholishul_A on 28/04/2017.
 */

@RestController
@RequestMapping(path = "/api/location")
public class LocationController {

    @Autowired
    private LocationRepository locationRepository;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Location createLocation(@RequestBody Location location){
        return locationRepository.save(location);
    }

    @RequestMapping(path = "/all", method = RequestMethod.GET)
    public Iterable<Location> findLocationAll() {
        return locationRepository.findAll();
    }

    @RequestMapping(path = "/{Id}", method = RequestMethod.GET)
    public Location findLocation(@PathVariable String Id){
        Location location = locationRepository.findOne(Id);
        if (location == null){
            throw new NoSuchElementException("Location does not exist :" + Id);
        }
        return location;
    }

    @RequestMapping(path = "/search/employeeId/{employeeId}", method = RequestMethod.GET)
    public List<Location> findLocationByEmployeeId(@PathVariable String employeeId){
        return locationRepository.findByEmployeeId(employeeId);
    }

    @RequestMapping(path= "/{Id}", method = RequestMethod.PUT)
    public Location editLocation(@PathVariable String Id, @RequestBody Location editLocation){
        findLocation(Id);
        return locationRepository.save(editLocation);
    }

    @RequestMapping(path= "/{Id}", method = RequestMethod.DELETE)
    public void deleteLocation(@PathVariable String Id) {
        Location location = findLocation(Id);
        locationRepository.delete(location);
    }
}
