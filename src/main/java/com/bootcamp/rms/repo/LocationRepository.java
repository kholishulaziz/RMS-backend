package com.bootcamp.rms.repo;

import com.bootcamp.rms.domain.Location;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Kholishul_A on 28/04/2017.
 */
@Repository
@RepositoryRestResource(exported = false)
public interface LocationRepository extends PagingAndSortingRepository<Location, String> {
    List<Location> findByEmployeeId(@Param("employeeId")String employeeId);
}
