package com.inti.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inti.model.Salarie;

@Repository
public interface ISalarieRepository extends JpaRepository<Salarie, Integer> {

}
