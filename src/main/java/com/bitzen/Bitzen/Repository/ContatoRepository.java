package com.bitzen.Bitzen.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bitzen.Bitzen.Models.Contato;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long>{

}
