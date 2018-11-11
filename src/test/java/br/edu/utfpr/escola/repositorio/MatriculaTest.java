package br.edu.utfpr.escola.repositorio;

import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.edu.utfpr.escola.Main;
import br.edu.utfpr.escola.model.Aluno;
import br.edu.utfpr.escola.model.Curso;
import br.edu.utfpr.escola.model.Disciplina;
import br.edu.utfpr.escola.model.Matricula;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=Main.class)
public class MatriculaTest {

	@Autowired
	private MatriculaRepositorio matriculaRepositorio;

	@Autowired
	private AlunoRepositorio alunoRepositorio;
	
	@Autowired
	private DisciplinaRepositorio disciplinaRepositorio;
	
	@Autowired
	private CursoRepositorio cursoRepositorio;
	
	
	@Test
	public void deveMatricular() {
		
		Aluno aluno = new Aluno("Denis", LocalDate.of(1989, 11, 24));
		aluno = alunoRepositorio.save(aluno);
		
		Curso curso = new Curso("Java");
		curso = cursoRepositorio.save(curso);
		
		Disciplina disciplina = new Disciplina("POO", curso);
		disciplina = disciplinaRepositorio.save(disciplina);
		
		Matricula matricula = new Matricula(aluno, disciplina);
		matricula = matriculaRepositorio.save(matricula);
		
		assertNotNull(matricula.getCodigo());
		
		
		
		
	}
	
	
	
}
