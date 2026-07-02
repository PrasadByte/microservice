package com.college.repository;


import com.college.model.College;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface CollegeRepository extends JpaRepository<College,  Integer> {
    Optional<College> findByCollegeName(String collegeName);
}
