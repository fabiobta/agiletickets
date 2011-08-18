package br.com.caelum.agiletickets.models;

import static org.junit.Assert.*;

import java.util.List;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.junit.Test;

public class EspetaculoTest {

	@Test
	public void deveCriarUmaSessaoDiariaIntervaloNoMesmoDia() throws Exception {

		Espetaculo esp = new Espetaculo();
		esp.setNome("Titanic");
		esp.setDescricao("filme");

		LocalDate data = new LocalDate();
		List<Sessao> lista = esp.criaSessoes(data, data,new LocalTime(17, 0, 0), Periodicidade.DIARIA);
		assertEquals(1, lista.size());

	}
	@Test
	public void deveCriarTrintaSessoesDiariasIntervaloDeTrintaDias() throws Exception {

		Espetaculo esp = new Espetaculo();
		esp.setNome("Titanic");
		esp.setDescricao("filme");

		LocalDate data = new LocalDate();
		List<Sessao> lista = esp.criaSessoes(data, data.plusDays(29),new LocalTime(17, 0, 0), Periodicidade.DIARIA);
		assertEquals(30, lista.size());

	}
	@Test
	public void deveCriarUmaSessaoSemanalIntervaloNoMesmoDia() throws Exception {

		Espetaculo esp = new Espetaculo();
		esp.setNome("Titanic");
		esp.setDescricao("filme");

		LocalDate data = new LocalDate();
		List<Sessao> lista = esp.criaSessoes(data, data,new LocalTime(17, 0, 0), Periodicidade.SEMANAL);
		assertEquals(0, lista.size());

	}
	@Test
	public void deveCriarQuatroSessoesSemanaisIntervaloDeTrintaDias() throws Exception {

		Espetaculo esp = new Espetaculo();
		esp.setNome("Titanic");
		esp.setDescricao("filme");

		LocalDate data = new LocalDate();
		List<Sessao> lista = esp.criaSessoes(data, data.plusDays(30),new LocalTime(17, 0, 0), Periodicidade.SEMANAL);
		assertEquals(4, lista.size());

	}
}
