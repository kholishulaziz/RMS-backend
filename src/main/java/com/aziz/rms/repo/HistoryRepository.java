package com.aziz.rms.repo;

import com.aziz.rms.domain.History;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Kholishul_A on 20/04/2017.
 */
@Repository
@RepositoryRestResource(exported = false)
public interface HistoryRepository extends CrudRepository<History, Long> {
    List<History> findByEmployeeId(Long employeeId);
}
