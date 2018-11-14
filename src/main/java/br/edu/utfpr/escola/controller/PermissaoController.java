package br.edu.utfpr.escola.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.utfpr.escola.model.Permissao;
import br.edu.utfpr.escola.repositorio.PermissaoRepositorio;

@Controller
@RequestMapping("/permissao") //todas as urls irão iniciar com /curso
public class PermissaoController {

	@Autowired
	private PermissaoRepositorio repositorio;
	
	@GetMapping("/")
	public String lista(Model model){
		//${dados} será a variável disponível no template thymeleaf
		model.addAttribute("dados",repositorio.findAll());
		return "permissao/lista"; //arquivo .html dentro da pasta resources/templates
	}
	
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@GetMapping("/novo")
	public String novo(Model model){
		model.addAttribute("permissao", new Permissao());
		return "permissao/formulario";
	}
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@PostMapping("/salvar")
	public String salvar(Permissao permissao){
		permissao.setAuthority("ROLE_"+permissao.getAuthority().toUpperCase());
		repositorio.save(permissao);
		return "redirect:/permissao/";
	}
	
	@GetMapping("/visualizar/{codigo}")
	public String visualizar(@PathVariable Long codigo, Model model){
		model.addAttribute("aluno", repositorio
							.findById(codigo).orElse(new Permissao()));
		return "permissao/formulario";
	}
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@GetMapping("/remover/{codigo}")
	public String remover(@PathVariable Long codigo, Model model){
		repositorio.deleteById(codigo);
		return "redirect:/permissao/";
	}
	
	
	
	
	
}
