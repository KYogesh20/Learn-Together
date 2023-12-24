package com.learntogether.learntogether.Repository;

import com.learntogether.learntogether.Entity.Pool;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PoolRepository extends JpaRepository<Pool,Integer> {
    Pool findByPoolName(String poolName);
}
