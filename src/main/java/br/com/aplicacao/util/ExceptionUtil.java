package br.com.aplicacao.util;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ExceptionUtil {

	private static final int MAX_STACK_TRACE_LENGTH = 4000;

	public static String getStackTrace(Throwable t) {
		StringWriter stringWriter = new StringWriter();
		PrintWriter printWriter = new PrintWriter(stringWriter);
		t.printStackTrace(printWriter);
		String stackTrace = stringWriter.toString();
		if (stackTrace.length() > MAX_STACK_TRACE_LENGTH) {
			stackTrace = stackTrace.substring(0,MAX_STACK_TRACE_LENGTH);
		}
		return stackTrace;
	}

}
