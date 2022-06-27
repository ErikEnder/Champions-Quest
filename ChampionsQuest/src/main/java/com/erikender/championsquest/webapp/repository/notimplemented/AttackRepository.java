package com.erikender.championsquest.webapp.repository.notimplemented;

import com.erikender.championsquest.webapp.model.notimplemented.NYIAttack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/** Not currently implemented **/
@Repository
public interface AttackRepository extends JpaRepository<NYIAttack, Integer> {

}
