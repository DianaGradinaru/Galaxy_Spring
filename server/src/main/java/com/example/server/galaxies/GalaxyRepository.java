package com.example.server.galaxies;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GalaxyRepository extends JpaRepository<GalaxyModel, Long> {
}
