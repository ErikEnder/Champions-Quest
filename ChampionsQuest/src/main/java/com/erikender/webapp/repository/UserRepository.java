package com.erikender.webapp.repository;

import com.erikender.webapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/** Repository for accessing Users **/
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    // Returns a User based on the given e-mail (which must be unique)
    @Query("SELECT u FROM User u WHERE u.email = ?1")
    User findByEmail(String email);
}
