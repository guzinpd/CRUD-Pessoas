package com.bitzen.Bitzen.Models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Contato {

	
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Long id;
    @NotBlank
    private String nome;
    
    @NotBlank
    private String telefone;    

    @NotBlank
    @Email
    private String email;
    
    @ManyToOne
    @JsonIgnore
    private Pessoa pessoa;
}
