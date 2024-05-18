package com.utcn.demo.repository;

import com.utcn.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
   @Query("DELETE FROM User u WHERE u.username = ?1")
    void deleteByUsername(String username);

    User findByUsername(String username);
}
