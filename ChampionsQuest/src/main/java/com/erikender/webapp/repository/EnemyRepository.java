package com.erikender.webapp.repository;

import com.erikender.webapp.model.Enemy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/** Not currently implemented **/
@Repository
public interface EnemyRepository extends JpaRepository<Enemy, Integer> {

}
