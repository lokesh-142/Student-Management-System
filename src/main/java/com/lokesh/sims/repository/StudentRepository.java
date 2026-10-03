package com.lokesh.sims.repository;
import com.lokesh.sims.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface StudentRepository extends JpaRepository<Student,Integer>{
    Optional<Student> findByUsernameAndPassword(String username,String password);
    boolean existsByUsername(String username);
}