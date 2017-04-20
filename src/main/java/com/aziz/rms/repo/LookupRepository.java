package com.aziz.rms.repo;

import com.aziz.rms.domain.Lookup;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Kholishul_A on 20/04/2017.
 */
public interface LookupRepository extends CrudRepository<Lookup, Long> {
    List<Lookup> findByDataTypeAndDataCode(@Param("dataType")String dataType, @Param("dataCode")String dataCode);
    List<Lookup> findByDataType(@Param("dataType")String dataType);
}
