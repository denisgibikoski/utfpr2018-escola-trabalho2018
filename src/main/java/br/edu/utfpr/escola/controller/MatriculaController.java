package br.edu.utfpr.escola.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.utfpr.escola.model.Matricula;
import br.edu.utfpr.escola.model.Usuario;
import br.edu.utfpr.escola.repositorio.AlunoRepositorio;
import br.edu.utfpr.escola.repositorio.DisciplinaRepositorio;
import br.edu.utfpr.escola.repositorio.MatriculaRepositorio;

@Controller
@RequestMapping("/matricula") // todas as urls irão iniciar com /curso
public class MatriculaController {

	@Autowired
	private MatriculaRepositorio matriculaRepositorio;

	@Autowired
	private AlunoRepositorio alunoRespositorio;

	@Autowired
	private DisciplinaRepositorio disciplinaRepositorio;

	@GetMapping("/")
	@Secured({ "ROLE_ADMIN", "ROLE_ALUNO" })
	public String lista(Model model) {
		// ${dados} será a variável disponível no template thymeleaf
		model.addAttribute("matriculas", matriculaRepositorio.findAll());
		return "matricula/lista"; // arquivo .html dentro da pasta resources/templates
	}

	@GetMapping("/novo")
	public String novo(Model model, @AuthenticationPrincipal Usuario usuario) {
		model.addAttribute("alunos", alunoRespositorio.findAll());
		model.addAttribute("disciplinas", disciplinaRepositorio.findAll());
		model.addAttribute("matricula", new Matricula());
		return "matricula/formulario";
	}
	@ModelAttribute
	public void provideMota(Model model){
	    model.addAttribute("nota", new Double(0));
	}

	
	@PostMapping("/salvar")
	public String salvar(@Valid Matricula matricula, @AuthenticationPrincipal Usuario usuario,
			BindingResult erros, Model model, RedirectAttributes redirect) {
		
		if (erros.hasErrors()) {
			return "matricula/formulario";
		}
		matricula.setUsuario(usuario);
		matricula.setAluno(alunoRespositorio.findByNomeLike(usuario.getNome()));

		redirect.addFlashAttribute("mensagem", "Registro salvo com sucesso");
		matriculaRepositorio.save(matricula);
		return "redirect:/matricula/";
	}

	@PostMapping("/atualizar/{codigo}")
	public String atualizar(@PathVariable Long codigo,@Valid Matricula matricula,
			BindingResult erros, Model model, RedirectAttributes redirect) {
		
		if (erros.hasErrors()) {
			return "matricula/formulario";
		}
		
		matricula.setAluno(alunoRespositorio.findById(codigo).orElse(null));

		redirect.addFlashAttribute("mensagem", "Registro salvo com sucesso");
		matriculaRepositorio.save(matricula);
		return "redirect:/matricula/";
	}

	@GetMapping("/visualizar/{codigo}")
	public String visualizar(@PathVariable Long codigo, Model model) {
		model.addAttribute("matricula", matriculaRepositorio.findById(codigo).orElse(new Matricula()));
		
		return "matricula/formulario";
	}
	
	
	
	

	@GetMapping("/remover/{codigo}")
	public String remover(@PathVariable Long codigo, Model model) {
		matriculaRepositorio.deleteById(codigo);
		return "redirect:/matricula/";
	}

}
