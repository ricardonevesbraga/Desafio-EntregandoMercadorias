package br.com.aplicacao.util.db;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Implementation of the NativeJdbcExtractor interface for the
 * Jakarta Commons DBCP connection pool, version 1.1 or higher.
 * <p>Returns the underlying native Connection, Statement, etc to application
 * code instead of DBCP's wrapper implementations. The returned JDBC classes
 * can then safely be cast, e.g. to <code>oracle.jdbc.OracleConnection</code>.
 * <p>This NativeJdbcExtractor can be set just to <i>allow</i> working with a
 * Commons DBCP DataSource: If a given object is not a Commons DBCP wrapper,
 * it will be returned as-is.
 * <p>Note that this version of CommonsDbcpNativeJdbcExtractor will work
 * against the original Commons DBCP in <code>org.apache.commons.dbcp</code>
 * as well as against Tomcat 5.5's relocated Commons DBCP version in the
 * <code>org.apache.tomcat.dbcp.dbcp</code> package.
 * 
 * @author Juergen Hoeller
 * @since 25.08.2003
 */
public class CommonsDbcpNativeJdbcExtractor {

	private static final String GET_INNERMOST_DELEGATE_METHOD_NAME = "getInnermostDelegate";

	/**
	 * Extracts the innermost delegate from the given Commons DBCP object.
	 * Falls back to the given object if no underlying object found.
	 * 
	 * @param obj the Commons DBCP Connection/Statement/ResultSet
	 * @return the underlying native Connection/Statement/ResultSet
	 */
	@SuppressWarnings({"rawtypes","unchecked"})
	private static Object getInnermostDelegate(Object obj) throws SQLException {
		if (obj == null) { return null; }
		try {
			Class classToAnalyze = obj.getClass();
			while (!Modifier.isPublic(classToAnalyze.getModifiers())) {
				classToAnalyze = classToAnalyze.getSuperclass();
				if (classToAnalyze == null) {
					// No public provider class found -> fall back to given object.
					return obj;
				}
			}
			Method getInnermostDelegate = classToAnalyze.getMethod(GET_INNERMOST_DELEGATE_METHOD_NAME,(Class[]) null);
			Object delegate = ReflectionUtils.invokeJdbcMethod(getInnermostDelegate,obj);
			return delegate != null ? delegate : obj;
		} catch (NoSuchMethodException ex) {
			return obj;
		} catch (SecurityException ex) {
			throw new IllegalStateException("Commons DBCP getInnermostDelegate method is not accessible: " + ex);
		}
	}

	public Connection doGetNativeConnection(Connection con) throws SQLException {
		return (Connection) getInnermostDelegate(con);
	}

}
