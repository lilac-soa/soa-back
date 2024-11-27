package com.lilac.soaback.tourist.repository;

import com.lilac.soaback.entity.Brewery;
import com.lilac.soaback.entity.BreweryTourist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface TouristRepository extends JpaRepository<BreweryTourist, UUID> {
    List<BreweryTourist> findBreweryTouristsByBrewery(Brewery brewery);
}
