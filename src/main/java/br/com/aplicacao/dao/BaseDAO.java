package br.com.aplicacao.dao;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import oracle.jdbc.driver.OracleTypes;
import oracle.sql.ARRAY;

import org.apache.log4j.Logger;

import br.com.aplicacao.type.WalmartRotas;
import br.com.aplicacao.util.JNDILocator;
import br.com.aplicacao.util.LogUtil;
import br.com.aplicacao.util.db.CommonsDbcpNativeJdbcExtractor;

/**
 * Rotina de utilitarios basicos do JDBC
 * 
 * @author Ricardo Braga
 */
public class BaseDAO {

	private final JNDILocator jndiLocator = new JNDILocator();

	private static Logger logger = LogUtil.getLogger(br.com.aplicacao.dao.BaseDAO.class.getName());

	public BaseDAO() {}

	protected Connection getNativeConnection() throws ClassNotFoundException,SQLException {

		Connection connectionRaw = null;
		try {

			connectionRaw = getConnection();

			if (connectionRaw != null) {

				CommonsDbcpNativeJdbcExtractor extrator = new CommonsDbcpNativeJdbcExtractor();

				connectionRaw = extrator.doGetNativeConnection(connectionRaw);

			} else {

				throw new SQLException("DataSource nulo");
			}

		} catch (SQLException sqle) {
			throw sqle;
		}
		return connectionRaw;
	}

	public Connection getConnection() throws ClassNotFoundException,SQLException {

		Connection connection = jndiLocator.getConnectionFromDataSource(WalmartRotas.dataSource.value());
		return connection;
	}

	protected void releaseConnection(Connection connection,Statement statement) {
		try {
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException sqle) {
			logger.error("Erro ao liberar conexao de banco");
		}
	}

	protected void releaseConnection(Connection connection,Statement statement1,Statement statement2) {
		try {
			if (statement2 != null) {
				statement2.close();
			}
			if (statement1 != null) {
				statement1.close();
			}
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException sqle) {
			logger.error("Erro ao liberar conexao de banco");
		}
	}

	protected void releaseConnection(Connection connection,Statement statement,ResultSet rs) {
		try {
			if (statement != null) {
				statement.close();
			}
			if (rs != null) {
				rs.close();
			}
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException sqle) {
			logger.error("Erro ao liberar conexao de banco");
		}
	}

	protected void setPSParameter(PreparedStatement ps,int i,Calendar calendario) throws SQLException {
		if (calendario == null) {
			ps.setNull(i,Types.DATE);
		} else {
			Date date = new Date(calendario.getTimeInMillis());
			ps.setDate(i,date);
		}
	}

	protected void setPSParameter(PreparedStatement ps,int i,Timestamp val) throws SQLException {
		if (val == null) {
			ps.setNull(i,Types.TIMESTAMP);
		} else {
			ps.setTimestamp(i,val);
		}
	}

	protected void setPSParameter(PreparedStatement ps,int i,Long longo) throws SQLException {
		if (longo == null) {
			ps.setNull(i,Types.INTEGER);
		} else {
			ps.setLong(i,longo.longValue());
		}
	}

	protected void setPSParameter(PreparedStatement ps,int i,String string) throws SQLException {
		if (string == null) {
			ps.setNull(i,Types.VARCHAR);
		} else if ("".equals(string)) {
			ps.setNull(i,Types.VARCHAR);
		} else {
			ps.setString(i,string);
		}
	}

	protected void setPSParameter(PreparedStatement ps,int i,Integer inteiro) throws SQLException {
		if (inteiro == null) {
			ps.setNull(i,Types.INTEGER);
		} else {
			ps.setInt(i,inteiro.intValue());
		}
	}

	protected void setPSParameter(PreparedStatement ps,int i,Double duplo) throws SQLException {
		if (duplo == null) {
			ps.setNull(i,Types.DOUBLE);
		} else {
			ps.setDouble(i,duplo.doubleValue());
		}
	}

	protected void setPSParameter(PreparedStatement ps,int i,Float numFloat) throws SQLException {
		if (numFloat == null) {
			ps.setNull(i,Types.FLOAT);
		} else {
			ps.setFloat(i,numFloat.floatValue());
		}
	}

	protected void setPSParameter(PreparedStatement ps,int i,Boolean bo) throws SQLException {
		if (bo == null) {
			ps.setNull(i,Types.VARCHAR);
		} else {
			if (bo) {
				ps.setString(i,"S");
			} else {
				ps.setString(i,"N");
			}
		}
	}

	protected void setPSParameter(PreparedStatement ps,int i,BigDecimal valor) throws SQLException {
		if (valor == null) {
			ps.setNull(i,Types.NUMERIC);
		} else {
			ps.setBigDecimal(i,valor);
		}
	}

	protected void setPSParameter(PreparedStatement ps,int i,Calendar calendario,String nome) throws SQLException {
		new SimpleDateFormat("dd/MM/yyyy");
		if (calendario == null) {

			ps.setNull(i,Types.DATE);
		} else {
			Date date = new Date(calendario.getTimeInMillis());
			ps.setDate(i,date);
		}
	}

	protected void setPSParameter(PreparedStatement ps,int i,Long longo,String nome) throws SQLException {
		if (longo == null) {

			ps.setNull(i,Types.INTEGER);
		} else {
			ps.setLong(i,longo.longValue());
		}
	}

	protected void setPSParameter(PreparedStatement ps,int i,String string,String nome) throws SQLException {
		if (string == null) {

			ps.setNull(i,Types.VARCHAR);
		} else if ("".equals(string)) {
			ps.setNull(i,Types.VARCHAR);
		} else {
			ps.setString(i,string);
		}
	}

	protected void setPSParameter(PreparedStatement ps,int i,Integer inteiro,String nome) throws SQLException {
		if (inteiro == null) {

			ps.setNull(i,Types.INTEGER);
		} else {
			ps.setInt(i,inteiro.intValue());
		}
	}

	protected void setPSParameter(PreparedStatement ps,int i,Double duplo,String nome) throws SQLException {
		if (duplo == null) {

			ps.setNull(i,Types.DOUBLE);
		} else {
			ps.setDouble(i,duplo.doubleValue());
		}
	}

	protected void setPSParameter(PreparedStatement ps,int i,Float numFloat,String nome) throws SQLException {
		if (numFloat == null) {

			ps.setNull(i,Types.FLOAT);
		} else {
			ps.setFloat(i,numFloat.floatValue());
		}
	}

	protected void setPSParameter(PreparedStatement ps,int i,Boolean bo,String nome) throws SQLException {
		if (bo == null) {

			ps.setNull(i,Types.VARCHAR);
		} else {
			if (bo) {
				ps.setString(i,"S");
			} else {
				ps.setString(i,"N");
			}
		}
	}

	protected void setPSParameter(PreparedStatement ps,int i,Timestamp val,String nome) throws SQLException {
		if (val == null) {

			ps.setNull(i,Types.TIMESTAMP);
		} else {
			new Date(val.getTime());
			ps.setTimestamp(i,val);
		}
	}

	protected void setPSParameter(PreparedStatement ps,int i,BigDecimal val,String nome) throws SQLException {
		if (val == null) {

			ps.setNull(i,Types.NUMERIC);
		} else {
			ps.setBigDecimal(i,val);
		}
	}

	protected Integer getIntValue(ResultSet rs,int i) throws SQLException {
		int integer;
		try {
			integer = rs.getInt(i);
		} catch (SQLException e) {
			throw e;
		}
		if (rs.wasNull()) {
			return null;
		} else {
			return Integer.valueOf(integer);
		}
	}

	protected Integer getIntValue(CallableStatement cs,int i) throws SQLException {
		int integer;
		try {
			integer = cs.getInt(i);
		} catch (SQLException e) {
			throw e;
		}
		if (cs.wasNull()) {
			return null;
		} else {
			return Integer.valueOf(integer);
		}
	}

	protected Date getDateValue(ResultSet rs,int i) throws SQLException {
		try {
			return rs.getDate(i,Calendar.getInstance());
		} catch (SQLException e) {
			throw e;
		}
	}

	protected Date getDateValue(CallableStatement cs,int i) throws SQLException {
		try {
			return cs.getDate(i,Calendar.getInstance());
		} catch (SQLException e) {
			throw e;
		}
	}

	protected Timestamp getTimestampValue(ResultSet rs,int i) throws SQLException {
		try {
			return rs.getTimestamp(i,Calendar.getInstance());
		} catch (SQLException e) {
			throw e;
		}
	}

	protected Timestamp getTimestampValue(CallableStatement cs,int i) throws SQLException {
		try {
			return cs.getTimestamp(i,Calendar.getInstance());
		} catch (SQLException e) {
			throw e;
		}
	}

	protected BigDecimal getBigdecimalValue(CallableStatement cs,int i) throws SQLException {
		try {
			return cs.getBigDecimal(i);
		} catch (SQLException e) {
			throw e;
		}
	}

	protected Calendar getCalendarValue(ResultSet rs,int i) throws SQLException {
		Date date = null;
		try {
			date = rs.getDate(i);
		} catch (SQLException e) {
			throw e;
		}
		if (date == null) { return null; }
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		return calendar;
	}

	protected Calendar getCalendarValue(CallableStatement cs,int i) throws SQLException {
		Date date = null;
		try {
			date = cs.getDate(i);
		} catch (SQLException e) {
			throw e;
		}
		if (date == null) { return null; }
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		return calendar;
	}

	protected Long getLongValue(ResultSet rs,int i) throws SQLException {
		long longo;
		try {
			longo = rs.getLong(i);
		} catch (SQLException e) {
			throw e;
		}
		if (rs.wasNull()) {
			return null;
		} else {
			return Long.valueOf(longo);
		}
	}

	protected Long getLongValue(CallableStatement cs,int i) throws SQLException {
		long longo;
		try {
			longo = cs.getLong(i);
		} catch (SQLException e) {
			throw e;
		}
		if (cs.wasNull()) {
			return null;
		} else {
			return Long.valueOf(longo);
		}
	}

	protected Double getDoubleValue(ResultSet rs,int i) throws SQLException {
		double duplo;
		try {
			duplo = rs.getDouble(i);
		} catch (SQLException e) {
			throw e;
		}
		if (rs.wasNull()) {
			return null;
		} else {
			return Double.valueOf(duplo);
		}
	}

	protected Double getDoubleValue(CallableStatement cs,int i) throws SQLException {
		double duplo;
		try {
			duplo = cs.getDouble(i);
		} catch (SQLException e) {
			throw e;
		}
		if (cs.wasNull()) {
			return null;
		} else {
			return Double.valueOf(duplo);
		}
	}

	protected String getStringValue(ResultSet rs,int i) throws SQLException {
		try {
			return rs.getString(i);
		} catch (SQLException e) {
			throw e;
		}
	}

	protected String getStringValue(CallableStatement cs,int i) throws SQLException {
		try {
			return cs.getString(i);
		} catch (SQLException e) {
			throw e;
		}
	}

	protected Float getFloatValue(ResultSet rs,int i) throws SQLException {
		float numFloat;
		try {
			numFloat = rs.getFloat(i);
		} catch (SQLException e) {
			throw e;
		}
		if (rs.wasNull()) {
			return null;
		} else {
			return new Float(numFloat);
		}
	}

	protected Float getFloatValue(CallableStatement cs,int i) throws SQLException {
		float numFloat;
		try {
			numFloat = cs.getFloat(i);
		} catch (SQLException e) {
			throw e;
		}
		if (cs.wasNull()) {
			return null;
		} else {
			return new Float(numFloat);
		}
	}

	protected Boolean getBooleanValue(ResultSet rs,int i) throws SQLException {
		boolean bo;
		try {
			if (rs.getString(i) != null && (rs.getString(i).equals("S") || rs.getString(i).equals("SIM"))) {
				bo = true;
			} else {
				bo = false;
			}
		} catch (SQLException e) {
			throw e;
		}
		return Boolean.valueOf(bo);
	}

	protected Boolean getBooleanValue(CallableStatement cs,int i) throws SQLException {
		boolean bo;
		try {
			if (cs.getString(i) != null && (cs.getString(i).equals("S") || cs.getString(i).equals("SIM"))) {
				bo = true;
			} else {
				bo = false;
			}
		} catch (SQLException e) {
			throw e;
		}
		return Boolean.valueOf(bo);
	}

	protected ResultSet getResultSetValue(CallableStatement cs,int i) throws SQLException {
		ResultSet retorno = null;
		try {
			retorno = (ResultSet) cs.getObject(i);
		} catch (SQLException e) {
			throw e;
		}
		if (cs.wasNull()) {
			return null;
		} else {
			return retorno;
		}
	}

	protected BigDecimal getBigdecimalValue(ResultSet rs,int i) throws SQLException {
		try {
			return rs.getBigDecimal(i);
		} catch (SQLException e) {
			throw e;
		}
	}

	/**
	 * Retorna a string com as interrogacoes para os DAOS
	 * 
	 * @param quantas interrogacoes
	 * @return string formatado
	 */
	public String montarInterrogacoes(int x) {
		StringBuffer buff = new StringBuffer();
		buff.append("(");
		for (int i = 0 ; i < x - 1 ; i++) {
			buff.append("?, ");
		}
		buff.append("?)");
		return buff.toString();
	}

	/**
	 * Retorna a instrucao sql para execucao de uma procedure
	 * 
	 * @return String com a instrucao sql de chamada da procedure
	 */
	public String geraChamadaProcedure(String packageName,String nomeProcedure,int numeroParametros) {
		StringBuilder sql = new StringBuilder();
		sql.append(" BEGIN ");
		sql.append(packageName + "." + nomeProcedure);

		if (numeroParametros > 0) {
			sql.append(montarInterrogacoes(numeroParametros));
		}

		sql.append(";END;");

		return sql.toString();
	}

	/**
	 * Metodo generico para executar procedures para retornar lista de valores
	 * 
	 * @param nomePackage - Package onde est? implementada a procedure
	 * @param nomeProcedure - nome da procedure a ser invocada
	 * @return um resultset com os dados
	 * @throws Exception, SQLException
	 */
	public ResultSet executaProcedureLov(String nomePackage,String nomeProcedure) throws Exception,SQLException {
		ResultSet rsConsulta = null;
		CallableStatement callable = null;
		Connection connection = null;
		try {
			connection = getConnection();
			callable = connection.prepareCall(geraChamadaProcedure(nomePackage,nomeProcedure,2));
			callable.registerOutParameter(1,OracleTypes.CURSOR);
			callable.registerOutParameter(2,OracleTypes.VARCHAR);
			callable.execute();
			String mensErro = this.getStringValue(callable,2);
			if (mensErro != null) { throw new Exception("PROC: " + nomeProcedure + " erro: " + mensErro); }
			rsConsulta = this.getResultSetValue(callable,1);
		} catch (SQLException sqle) {
			throw sqle;
		} finally {
			releaseConnection(connection,callable);
		}
		return rsConsulta;
	}

	public ResultSet executaView(String nomeView) throws Exception,SQLException {
		ResultSet rsConsulta = null;
		Boolean mensErro = false;
		CallableStatement callable = null;
		Connection connection = null;
		try {
			connection = getConnection();
			callable = connection.prepareCall(nomeView);
			mensErro = callable.execute();

			if (!mensErro) { throw new Exception("PROC: " + nomeView + " erro: " + mensErro); }
			rsConsulta = callable.getResultSet();
		} catch (SQLException sqle) {
			throw sqle;
		} finally {
			releaseConnection(connection,callable);
		}

		return rsConsulta;
	}

	protected Object[] getArrayValue(ARRAY array) {
		Object[] linhas = null;
		try {
			linhas = (Object[]) array.getArray();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return linhas;
	}

	@Deprecated
	/**
	 * Use o releaseConnection
	 * @param rs
	 * @throws SQLException
	 */
	protected void closeResultset(ResultSet rs) throws SQLException {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException sqle) {
			throw sqle;
		}
	}

	@Deprecated
	/**
	 * Use o releaseConnection
	 * @param rs
	 * @throws SQLException
	 */
	protected void closeCallableStatement(CallableStatement cs) throws SQLException {
		try {
			if (cs != null) {
				cs.close();
			}
		} catch (SQLException sqle) {
			throw sqle;
		}
	}
}
