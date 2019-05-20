package br.com.senaigo.locadora.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DataUtils {

	private static final DateTimeFormatter FORMATADOR = DateTimeFormatter.ofPattern("d/MM/yyyy");

	public static LocalDate convertaStringParaLocalDate(String dataFormatada) {
		return LocalDate.parse(dataFormatada, FORMATADOR);
	}

	public static String convertaLocalDateParaStringFormatada(LocalDate data) {
		return data.format(FORMATADOR);
	}
}
