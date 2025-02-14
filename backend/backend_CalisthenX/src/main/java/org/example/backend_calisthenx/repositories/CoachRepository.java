package org.example.backend_calisthenx.repositories;

import org.example.backend_calisthenx.models.Coach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CoachRepository extends JpaRepository<Coach, Long> {

    Optional<Coach> findByEmail(String email);

    Optional<Coach> findCoachByUsername(String username);
}
