package com.lilac.soaback.brewery.repository;

import com.lilac.soaback.entity.Brewery;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BreweryRepository extends JpaRepository<Brewery, Long> {
}
