package com.aziz.rms.repo;

import com.aziz.rms.domain.Employee;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Kholishul_A on 20/04/2017.
 */

@Repository
@RepositoryRestResource(exported = false)
public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long>{
    List<Employee> findByFirstNameContainsOrLastNameContains(String firstName, String lastName);
}
