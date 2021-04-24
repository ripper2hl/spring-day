package com.perales.spring.day.covid.repository;

import com.perales.spring.day.covid.model.Modelo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModeloRepository extends JpaRepository<Modelo, Integer> {
}
