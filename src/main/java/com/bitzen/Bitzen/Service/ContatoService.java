package com.bitzen.Bitzen.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bitzen.Bitzen.Models.Contato;
import com.bitzen.Bitzen.Repository.ContatoRepository;

@Service
public class ContatoService {
	
	@Autowired
    private ContatoRepository ContatoRepository;

    public Contato getContatoById(Long id) {
        Optional<Contato> Contato = ContatoRepository.findById(id);
        return Contato.orElse(null);
    }

    public List<Contato> getAllContatos() {
        return ContatoRepository.findAll();
    }

    public Page<Contato> getContatosPaginadas(Pageable pageable) {
        return ContatoRepository.findAll(pageable);
    }

    public Contato createContato(Contato Contato) {
        return ContatoRepository.save(Contato);
    }

    public Contato updateContato(Long id, Contato Contato) {
        if (!ContatoRepository.existsById(id)) {
            return null;
        }
        Contato.setId(id);
        return ContatoRepository.save(Contato);
    }

    public boolean deleteContato(Long id) {
        if (!ContatoRepository.existsById(id)) {
            return false;
        }
        ContatoRepository.deleteById(id);
        return true;
    }
}
