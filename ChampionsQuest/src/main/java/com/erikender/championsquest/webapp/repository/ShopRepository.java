package com.erikender.championsquest.webapp.repository;

import com.erikender.championsquest.webapp.model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/** Shop Repository.  No methods implemented atm. */
@Repository
public interface ShopRepository extends JpaRepository<Shop, Integer> {

}
