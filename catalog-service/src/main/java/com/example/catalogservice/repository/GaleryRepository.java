package com.example.catalogservice.repository;

import com.example.catalogservice.model.Galery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GaleryRepository extends JpaRepository<Galery, Long> {

    @Query("SELECT g FROM Galery g WHERE g.id_galery = :id")
    Optional<Galery> findById(@Param("id") Long id);
}
