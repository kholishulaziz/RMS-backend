package com.bootcamp.rms.repo;

import com.bootcamp.rms.domain.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by Kholishul_A on 02/05/2017.
 */
@Repository
public interface UserRepository extends PagingAndSortingRepository<User, String> {
    User findByUsername(@Param("username")String username);
}
