package com.erikender.webapp.repository.notimplemented;

import com.erikender.webapp.model.notimplemented.NYIEnemy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/** Not currently implemented **/
@Repository
public interface EnemyRepository extends JpaRepository<NYIEnemy, Integer> {

}
