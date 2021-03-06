package br.edu.utfpr.escola.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.SortDefault;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.utfpr.escola.model.Aluno;
import br.edu.utfpr.escola.repositorio.AlunoRepositorio;

@Controller
@RequestMapping("/aluno") //todas as urls irão iniciar com /curso
public class AlunoController {

	@Autowired
	private AlunoRepositorio alunoRepositorio;
	
	
	@GetMapping("/")
	public String lista(Model model, @SortDefault("nome") Pageable pageable){
		//${dados} será a variável disponível no template thymeleaf
		model.addAttribute("dados",alunoRepositorio.findAll(pageable));
		
		return "aluno/lista"; //arquivo .html dentro da pasta resources/templates
	}
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@GetMapping("/novo")
	public String novo(Model model){
		model.addAttribute("aluno", new Aluno());
		
		return "aluno/formulario";
	}
	// Passando uma lista de permissões
	@Secured({"ROLE_ADMIN", "ROLE_ALUNO"})
	@PostMapping("/salvar")
	public String salvar(Aluno aluno){

		alunoRepositorio.save(aluno);
		
		return "redirect:/aluno/";
	}
	
	@GetMapping("/visualizar/{codigo}")
	public String visualizar(@PathVariable Long codigo, Model model){
		model.addAttribute("aluno", alunoRepositorio
							.findById(codigo).orElse(new Aluno()));
	
		return "aluno/formulario";
	}
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@GetMapping("/remover/{codigo}")
	public String remover(@PathVariable Long codigo, Model model){
		alunoRepositorio.deleteById(codigo);
		return "redirect:/aluno/";
	}
	
	
	
	
	
}
