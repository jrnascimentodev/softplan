package com.softplayer.softperson.controller;

import java.io.IOException;
import java.time.Instant;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.softplayer.softperson.domain.Pessoa;
import com.softplayer.softperson.service.PessoaService;

@Controller
public class PessoaController {
	
	@Autowired
	private PessoaService service;
	
	// Login form
	  @RequestMapping("/login.html")
	  public String login() {
	    return "login.html";
	  }	  
	
	@GetMapping("/")
	public String viewHomePage(Model model) {
		return findPaginated(1, "nome", "asc", model);		
	}
	
	@GetMapping("/novoCadastro")
	public String novoCadastro(Model model) {
		Pessoa pessoa = new Pessoa();
		model.addAttribute("pessoa", pessoa);
		return "new_pessoa";
	}
	
	@PostMapping("/save")
	public String save(@ModelAttribute("pessoa") @Valid Pessoa pessoa, BindingResult result, RedirectAttributes attributes) {		
		
		if(pessoa.getId() == null && service.existsByCpf(pessoa.getCpf()))
		{
			attributes.addFlashAttribute("mensagem", "CPF  já existe ");
			return "redirect:/novoCadastro";
		}
		
		if(result.hasErrors())
		{
			return "new_pessoa";
		}
		
		service.save(pessoa);
		return "redirect:/";
	}
	
	@GetMapping("/update/{id}")
	public String update(@PathVariable ( value = "id") Integer id, Model model) {
		Pessoa pessoa = service.getPessoaById(id);
		
		pessoa.setDataAlteracao(Instant.now());
		
		model.addAttribute("pessoa", pessoa);
		return "update_pessoa";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable (value = "id") Integer id) { 
		this.service.delete(id);
		return "redirect:/";
	}
	
	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable (value = "pageNo") int pageNo, 
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
		
		int pageSize = 5;
		
		Page<Pessoa> page = service.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Pessoa> listPessoa = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("listPessoas", listPessoa);
		return "index";
	}
	
	@GetMapping("/source")
	public void viewHomePageGitHub(HttpServletResponse httpServletResponse) throws IOException {
	    httpServletResponse.sendRedirect("https://github.com/jrnascimentodev/softplan");	
	}

}
