package br.edu.utfpr.escola.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotEmpty;

@Entity
public class Curso {

	@Id
	@SequenceGenerator(name="curso_seq",sequenceName="curso_seq")
	@GeneratedValue(generator="curso_seq",
			strategy = GenerationType.SEQUENCE)
	private Long codigo;
	
	@NotEmpty(message="O campo nome não pode ser vazio")
	@Column(length = 150, nullable = false)
	private String nome;
	
	@ManyToMany(mappedBy= "curso", targetEntity = Disciplina.class)
	private List<Disciplina> disciplinas;
	
	@ManyToOne(fetch = FetchType.LAZY, targetEntity= Aluno.class )
	private Aluno aluno;
	
	public Curso(){
		
	}
	
	public Curso(String nome) {
		super();
		this.nome = nome;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
}
