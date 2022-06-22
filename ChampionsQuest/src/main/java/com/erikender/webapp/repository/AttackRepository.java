package com.erikender.webapp.repository;

import com.erikender.webapp.model.Attack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/** Not currently implemented **/
@Repository
public interface AttackRepository extends JpaRepository<Attack, Integer> {

}
