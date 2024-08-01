package com.example.demo.repository;

import com.example.demo.domain.PossibleTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PossibleTimeRepository extends JpaRepository<PossibleTime, Long> {
}
