package com.example.demo.repository;

import com.example.demo.domain.MeetingDay;
import org.springframework.data.jpa.repository.JpaRepository;
public interface MeetingDayRepository extends JpaRepository<MeetingDay, Long>{
}
