package com.project.aTicket.repository;

import com.project.aTicket.model.Seating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatingRepository extends JpaRepository<Seating, Long> {
}
