package com.bitzen.Bitzen.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bitzen.Bitzen.Models.Contato;
import com.bitzen.Bitzen.Models.Pessoa;
import com.bitzen.Bitzen.Repository.PessoaRepository;

@Service
public class PessoaService {
	
	@Autowired
    private PessoaRepository pessoaRepository;
	
	@Autowired
    private ContatoService contatoService;

    public Pessoa getPessoaById(Long id) {
        Optional<Pessoa> pessoa = pessoaRepository.findById(id);
        return pessoa.orElse(null);
    }

    public List<Pessoa> getAllPessoas() {
        return pessoaRepository.findAll();
    }

    public Page<Pessoa> getPessoasPaginadas(Pageable pageable) {
        return pessoaRepository.findAll(pageable);
    }

    public Pessoa createPessoa(Pessoa pessoa) {
        if (!pessoa.getContatos().isEmpty()) {
        	if(this.checkCPF(pessoa.getCpf())) {
        		if(!this.checkDate(pessoa.getDateNascimento())) {
        			pessoa = pessoaRepository.save(pessoa);

                    for (Contato contato : pessoa.getContatos()) {
                        contato.setPessoa(pessoa);
                        contatoService.createContato(contato);
                    }

                    return pessoa;
        		}else {
                    throw new IllegalArgumentException("Data precisa ser menor que a atual.");  
        		}
        		
        	}else {
                throw new IllegalArgumentException("CPF Inválido.");        		
        	}
            
        } else {
            throw new IllegalArgumentException("Uma pessoa deve possuir ao menos um contato.");
        }
    }

    public Pessoa updatePessoa(Long id, Pessoa pessoa) {
        if (!pessoaRepository.existsById(id)) {
            return null;
        }
        pessoa.setId(id);
        return pessoaRepository.save(pessoa);
    }

    public boolean deletePessoa(Long id) {
        if (!pessoaRepository.existsById(id)) {
            return false;
        }
        pessoaRepository.deleteById(id);
        return true;
    }
    
    public boolean checkCPF(String cpf) {
        cpf = cpf.replaceAll("[^0-9]", "");

        if (cpf.length() != 11) {
            return false;
        }

        int soma = 0;
        for (int i = 0; i < 9; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
        }
        int primeiroDigitoVerificador = 11 - (soma % 11);
        if (primeiroDigitoVerificador > 9) {
            primeiroDigitoVerificador = 0;
        }

        soma = 0;
        for (int i = 0; i < 10; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
        }
        int segundoDigitoVerificador = 11 - (soma % 11);
        if (segundoDigitoVerificador > 9) {
            segundoDigitoVerificador = 0;
        }

        return (Character.getNumericValue(cpf.charAt(9)) == primeiroDigitoVerificador) &&
               (Character.getNumericValue(cpf.charAt(10)) == segundoDigitoVerificador);
    }
    
    public boolean checkDate(LocalDate date) {
    	 LocalDate currentDate = LocalDate.now();


         return date.isAfter(currentDate);
    }
}
