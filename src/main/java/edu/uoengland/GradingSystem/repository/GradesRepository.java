package edu.uoengland.GradingSystem.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.uoengland.GradingSystem.entity.Grade;

@Repository
public interface GradesRepository extends JpaRepository<Grade, UUID> {

}
