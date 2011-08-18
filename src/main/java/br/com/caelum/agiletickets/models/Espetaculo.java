package br.com.caelum.agiletickets.models;

import static com.google.common.collect.Lists.newArrayList;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

@Entity
public class Espetaculo {

	@Id
	@GeneratedValue
	private Long id;

	private String nome;

	private String descricao;

	@Enumerated(EnumType.STRING)
	private TipoDeEspetaculo tipo;

	@OneToMany(mappedBy = "espetaculo")
	private List<Sessao> sessoes = newArrayList();

	@ManyToOne
	private Estabelecimento estabelecimento;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public TipoDeEspetaculo getTipo() {
		return tipo;
	}

	public void setTipo(TipoDeEspetaculo tipo) {
		this.tipo = tipo;
	}

	public List<Sessao> getSessoes() {
		return sessoes;
	}

	public void setEstabelecimento(Estabelecimento estabelecimento) {
		this.estabelecimento = estabelecimento;
	}

	public Estabelecimento getEstabelecimento() {
		return estabelecimento;
	}

	public List<Sessao> criaSessoes(LocalDate inicio, LocalDate fim,
			LocalTime horario, Periodicidade periodicidade) {
		
		int x;
		int pulo;
		
		if(periodicidade.equals(Periodicidade.DIARIA)){
			pulo = 1;
		} else if (periodicidade.equals(Periodicidade.SEMANAL)){
			pulo = 7;
		} else{
			pulo = 30;
		}
		x = (Days.daysBetween(inicio, fim).getDays() + 1)/pulo;
		
		criaSessoes(x, pulo, inicio, horario);

		return sessoes;
	}

	private void criaSessoes(int nDias, int pulaDias, LocalDate inicio,
			LocalTime horario) {
		Sessao snova;
		LocalDate dataDoDia = inicio;

		for (int i = 0; i < nDias; i++) {
			snova = new Sessao();
			snova.setInicio(dataDoDia.toDateTime(horario));
			dataDoDia = dataDoDia.plusDays(pulaDias);
			sessoes.add(snova);
		}
	}
}
