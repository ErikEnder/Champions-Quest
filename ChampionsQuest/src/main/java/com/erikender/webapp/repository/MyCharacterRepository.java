package com.erikender.webapp.repository;

import com.erikender.webapp.model.MyCharacter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MyCharacterRepository extends JpaRepository<MyCharacter, Integer> {

    @Query("SELECT c FROM MyCharacter c WHERE c.char_id = ?1")
    MyCharacter findById(int char_id);

    @Query("SELECT max(c.char_id) FROM MyCharacter c")
    int getHighestId();

    @Query("SELECT c FROM MyCharacter c WHERE c.name = :name AND c.user_id = :user_id")
    MyCharacter findByName(String name, int user_id);
}
