package com.softplayer.softperson.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.softplayer.softperson.domain.Pessoa;
import com.softplayer.softperson.repository.PessoaRepository;


public interface PessoaService {
	
	public List<Pessoa> getAllPessoas();
	Pessoa getPessoaById(Integer id);
	void save(Pessoa pessoa); 
	void delete(Integer id);
	Page<Pessoa> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
	
	public boolean existsByCpf(String cpf);
}
