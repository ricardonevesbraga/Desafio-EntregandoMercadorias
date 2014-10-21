package br.com.aplicacao.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Classe util para utilização de datas
 * 
 * @author Ricardo Braga
 */
public final class DateUtil {

	private DateUtil() {}

	private static SimpleDateFormat dataSemHora = new SimpleDateFormat("dd/MM/yyyy");

	private static SimpleDateFormat dataCompleta = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

	private static SimpleDateFormat ano = new SimpleDateFormat("yyyy");

	private static SimpleDateFormat mes = new SimpleDateFormat("MM");

	private static SimpleDateFormat diaDoMes = new SimpleDateFormat("dd");

	public static synchronized String formataSemHora(Calendar cal) {

		if (cal == null) { return ""; }

		return dataSemHora.format(cal.getTime());
	}

	public static Long minutosEmMilisegundos(Integer minutos) {

		return 60L * 1000 * minutos;

	}

	public static synchronized String formataComMes(Date date) {

		if (date == null) { return ""; }

		return mes.format(date);
	}

	public static synchronized String formataComAno(Date date) {

		if (date == null) { return ""; }

		return ano.format(date);
	}

	public static synchronized String formataCompleta(Date date) {

		if (date == null) { return ""; }

		return dataCompleta.format(date);
	}

	public static synchronized String dataDeInicioDoServidor() {

		java.lang.management.RuntimeMXBean rb = java.lang.management.ManagementFactory.getRuntimeMXBean();

		return dataCompleta.format(rb.getStartTime());
	}

	public static synchronized String getDiaDoMes() {

		Calendar hoje = new GregorianCalendar();

		return diaDoMes.format(hoje.getTime());

	}

	public static synchronized GregorianCalendar date2calendar(Date data) {

		Calendar cal = new GregorianCalendar();

		cal.setTime(data);

		return (GregorianCalendar) cal;
	}
}
