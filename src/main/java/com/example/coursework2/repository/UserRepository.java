package com.example.coursework2.repository;

import com.example.coursework2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.username = :username")
    public User getUserByUsername(@Param("username") String username);

    Optional<User> findById(Long user);

    User findByUsername(String username);

    @Query("SELECT propertyType, COUNT(propertyType) FROM User GROUP BY propertyType")
    List<Object[]> countByPropertyType();

    List<User> findByPropertyTypeAndNumberOfBedrooms(String propertyType, int numberOfBedrooms);


    boolean existsByUsername(String username);


}
