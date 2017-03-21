package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.WeakHashMap;


/**
 * 数据库操作基类
 * @author chenjunliang
 *
 */
public abstract class DataBase {
	protected Connection conn = null;
	public static DataBase createConnection() {
		String dbtype = "";
		String dbname = "";
		String user = "";
		String password = "";
		return createConnection(dbtype, dbname, user, password);
	}

	public static DataBase createConnection(String dbtype, String dburl, String username, String password) {
		DataBase dbc;
		if (dbtype.equals("Mysql")){
			dbc = new DBMySql(dburl, username, password);
			}
		else{
			dbc = null;
			}
		if (dbc.conn == null){
			return null;
			}
		return dbc;
	}

	//建立一个预编译好的SQL语句对象
	public PreparedStatement createPrepare(String sql) throws SQLException {
		return conn.prepareStatement(sql);
	}
	//返回常规的SQL语句对象
	public Statement createStatement() throws SQLException {
		return conn.createStatement();
	}

	/**
	 * 设定事务 （来保证事务的完整性）
	 * 
	 * @throws SQLException
	 */
	public void setAutoCommit(boolean value) throws SQLException {
		this.conn.setAutoCommit(value);//
	}

	/**
	 * 提交事务
	 * 
	 * @throws SQLException
	 */
	public void commit() throws SQLException {
		this.conn.commit();// 提交JDBC事务
		// 恢复JDBC事务的默认提交方式
		// this.conn.setAutoCommit(true);
		// this.close();
	}

	//根据传入的预编译SQL进行预处理
	public void submitPrepare(PreparedStatement ps) throws SQLException {
		ps.executeBatch();
		conn.commit();
	}

	public abstract long insertWithAutoId(String sql, String mainColumn) throws Exception;

	public abstract Object getResultSetValue(ResultSet rs, int index) throws SQLException;

	// 为了提高效率，不释放连接，重复使用，需要手动关闭连接
	// 实现为单例模式，以保证不会占用太多连接	
	private static DataBase sharedConnection;

	public static DataBase getSharedConnection() {
		if (sharedConnection == null || sharedConnection.isClosed()) {
			sharedConnection = DataBase.createConnection();
			sharedConnection.shared = true;
		}
		return sharedConnection;
	}

	private boolean shared = false;

	public boolean isShared() {
		return shared;
	}

	public void setShared(boolean value) {
		this.shared = value;
	}

	public void close() {
		if (!shared)
			try {
				for (PreparedStatement pstmt : pstmtPool.values())
					pstmt.close();
				conn.close();
			} catch (Exception e) {
			}
	}

	public boolean isClosed() {
		try {
			return this.conn.isClosed(); // this.conn.isValid(arg0);
		} catch (Exception e) {
			return true;
		}
	}

	// lryh 添加my statement生成	
	public DBStatement createMyStatement() throws SQLException {
		return new DBStatement(this);
	}

	public DBStatement createMyStatement(boolean pooled) throws SQLException {
		return new DBStatement(this, pooled);
	}

	/**
	 * PreparedStatement池，以SQL语句为索引
	 */
	private Map<String, PreparedStatement> pstmtPool = new WeakHashMap<String, PreparedStatement>();

	public PreparedStatement getPooledPrepare(String sql) {
		PreparedStatement ps = null;
		try {
			ps = pstmtPool.get(sql);
			// Oracle JDBC未实现isClosed方法
			// if (ps == null || ps.isClosed()) {
			if (ps == null) {
				ps = createPrepare(sql);
				pstmtPool.put(sql, ps);
				//log.info(sql);
				//log.info("语句池大小：" + pstmtPool.size());
				// Oracle JDBC未实现setPoolable方法
				// ps.setPoolable(true);						
			}
			return ps;
		} catch (SQLException e) {
			
			return null;
		}
	}
}
