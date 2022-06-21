package com.erikender.webapp.repository;

import com.erikender.webapp.model.Item;
import com.erikender.webapp.model.MyCharacter;
import com.erikender.webapp.model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Integer> {

}
