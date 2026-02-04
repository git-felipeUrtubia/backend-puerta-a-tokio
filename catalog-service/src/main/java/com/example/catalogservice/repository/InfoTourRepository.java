package com.example.catalogservice.repository;

import com.example.catalogservice.model.InfoTour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InfoTourRepository extends JpaRepository<InfoTour,Long> {
}
