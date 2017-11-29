package br.com.fabercanetas.utils;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

/**
 * Classe que possui métodos utilitarios para manipular datas no banco de dados, para criar prazo e
 * para criar frete.
 * @author TDS Tecnologia
 *
 */
public class Utils {

	public LocalDate getLocalDateFromString() {
		
		LocalDateTime agora = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String data = agora.format(formatter);
		return LocalDate.parse(data, formatter);
		
	}
	
	public int createDeadline() {
		return new Random().nextInt(30);
	}
	
	public double createFreight() {
		double numero = new Random().nextDouble() * 10 + 5;
		DecimalFormat f = new DecimalFormat("#,00");
		return Double.parseDouble(f.format(numero));
	}
	
}
