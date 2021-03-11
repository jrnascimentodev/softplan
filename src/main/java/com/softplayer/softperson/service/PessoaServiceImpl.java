package com.softplayer.softperson.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.softplayer.softperson.domain.Pessoa;
import com.softplayer.softperson.repository.PessoaRepository;

@Service
public class PessoaServiceImpl implements PessoaService{

	@Autowired
	private PessoaRepository repository;

	public List<Pessoa> getAllPessoas() {
		return repository.findAll();
	}

	@Override
	public void save(Pessoa pessoa) {
		this.repository.save(pessoa);
	}
	
	@Override
	public Pessoa getPessoaById(Integer id) {
		Optional<Pessoa> optional = repository.findById(id);
		Pessoa pessoa = null;
		
		if (optional.isPresent()) {
			pessoa = optional.get();
		} else {
			throw new RuntimeException(" Pessoa not found for id :: " + id);
		}
		return pessoa;
	}
	
	public boolean existsByCpf(String cpf) {
        return repository.existsByCpf(cpf);
    }

	@Override
	public void delete(Integer id) {
		this.repository.deleteById(id);
	}

	@Override
	public Page<Pessoa> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.repository.findAll(pageable);
	}
}
