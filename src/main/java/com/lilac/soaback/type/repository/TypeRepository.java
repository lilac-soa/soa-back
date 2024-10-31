package com.lilac.soaback.type.repository;

import com.lilac.soaback.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface TypeRepository extends JpaRepository<Type, Long> {
    Optional<Type> findByName(String name);
}
