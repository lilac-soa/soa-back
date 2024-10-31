package com.lilac.soaback.tourist.repository;

import com.lilac.soaback.entity.BreweryTourist;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TouristRepository extends JpaRepository<BreweryTourist, Long> {
}
