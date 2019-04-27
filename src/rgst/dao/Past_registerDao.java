package rgst.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

import rgst.vo.Past_register;

public class Past_registerDao {
	DataSource ds;

	public void setDataSource(DataSource ds) {
		this.ds = ds;
	}

	public boolean exist(Past_register past) throws Exception {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			connection = ds.getConnection();
			stmt = connection.prepareStatement(
					"SELECT STU_CODE FROM PAST_REGISTER"
							+ " WHERE STU_CODE=? AND SUB_CODE=?");
			stmt.setString(1, past.getStu_code());
			stmt.setString(2, past.getSub_code());
			rs = stmt.executeQuery();
			if (rs.next()) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			throw e;

		} finally {
			try {if (rs != null) rs.close();} catch (Exception e) {}
			try {if (stmt != null) stmt.close();} catch (Exception e) {}
			try {if (connection != null) connection.close();} catch(Exception e) {}
		}
	}

}
