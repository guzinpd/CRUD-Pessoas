package com.bitzen.Bitzen.Models;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Pessoa {
	
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Long id;

    @NotBlank
	private String nome;

    @NotBlank
	private String cpf;
    
	@JsonFormat(pattern = "dd/MM/yyyy")
    @Past
	private LocalDate dateNascimento;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "pessoa", fetch = FetchType.EAGER)
	private List<Contato> contatos;
	
	

}