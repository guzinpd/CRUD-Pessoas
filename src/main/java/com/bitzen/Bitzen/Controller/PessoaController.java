package com.bitzen.Bitzen.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bitzen.Bitzen.Models.Pessoa;
import com.bitzen.Bitzen.Service.PessoaService;

@Controller
@RestController
@RequestMapping("/pessoas")
public class PessoaController {

	@Autowired
	private PessoaService pessoaService;

	@GetMapping("")
	public ResponseEntity<List<Pessoa>> getAllPessoas() {
		List<Pessoa> pessoas = pessoaService.getAllPessoas();
		return ResponseEntity.ok(pessoas);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Pessoa> getPessoaById(@PathVariable Long id) {
		Pessoa pessoa = pessoaService.getPessoaById(id);
		return ResponseEntity.ok(pessoa);
	}

	@GetMapping("/page")
	public ResponseEntity<Page<Pessoa>> getPessoasPaginadas(@PageableDefault(size = 10) Pageable pageable) {
		Page<Pessoa> pessoas = pessoaService.getPessoasPaginadas(pageable);
		return ResponseEntity.ok(pessoas);
	}

	@PostMapping("")
	public ResponseEntity<Pessoa> createPessoa(@RequestBody Pessoa pessoa) {
		Pessoa savedPessoa = pessoaService.createPessoa(pessoa);

		return ResponseEntity.ok(savedPessoa);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Pessoa> updatePessoa(@PathVariable Long id, @RequestBody Pessoa pessoa) {
		Pessoa updatedPessoa = pessoaService.updatePessoa(id, pessoa);
		return ResponseEntity.ok(updatedPessoa);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Pessoa> deletePessoa(@PathVariable Long id) {
		Boolean deleted = pessoaService.deletePessoa(id);

		if (deleted) {
			return ResponseEntity.status(HttpStatus.OK).build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
