package br.edu.utfpr.escola.repositorio.test;

import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.edu.utfpr.escola.MainEscola;
import br.edu.utfpr.escola.model.Aluno;
import br.edu.utfpr.escola.repositorio.AlunoRepositorio;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=MainEscola.class)
public class AlunoRepositorioTest {

	@Autowired
	private AlunoRepositorio alunoRepositorio;
	
	@Test
	public void deveInserir(){
		Aluno aluno = new Aluno();
		aluno.setNome("André");
		aluno.setDataNascimento(LocalDate.of(1980, 9, 27));
		alunoRepositorio.save(aluno);
	}
	
	@Test
	public void deveRemover(){
		Aluno aluno = new Aluno();
		aluno.setNome("André");
		aluno.setDataNascimento(LocalDate.of(1980, 9, 27));
		aluno = alunoRepositorio.save(aluno);
		alunoRepositorio.delete(aluno);
	}
	
	@Test
	public void deveRetornarAlunosNome(){
		alunoRepositorio.deleteAll();
		
		Aluno aluno = new Aluno();
		aluno.setNome("André");
		aluno.setDataNascimento(LocalDate.of(1980, 9, 27));
		aluno = alunoRepositorio.save(aluno);
		
		Aluno aluno2 = new Aluno();
		aluno2.setNome("Joaquim");
		aluno2.setDataNascimento(LocalDate.of(1990, 9, 27));
		aluno2 = alunoRepositorio.save(aluno2);
		
	
		
		
	}
	
	
	
	
	
	
	
	
	
}
