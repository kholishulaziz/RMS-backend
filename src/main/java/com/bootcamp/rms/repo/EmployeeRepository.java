package com.bootcamp.rms.repo;

import com.bootcamp.rms.domain.Employee;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Kholishul_A on 20/04/2017.
 */
@Repository
@RepositoryRestResource(exported = false)
public interface EmployeeRepository extends PagingAndSortingRepository<Employee, String>{
    List<Employee> findByFirstNameContainsOrLastNameContains(@Param("firstName")String firstName, @Param("lastName")String lastName);
}
