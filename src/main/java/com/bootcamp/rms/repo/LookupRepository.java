package com.bootcamp.rms.repo;

import com.bootcamp.rms.domain.Lookup;
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
public interface LookupRepository extends PagingAndSortingRepository<Lookup, String> {
    List<Lookup> findByDataTypeAndDataCode(@Param("dataType")String dataType, @Param("dataCode")String dataCode);
    List<Lookup> findByDataType(@Param("dataType")String dataType);
    List<Lookup> findByDataCode(@Param("dataCode")String dataType);
}
