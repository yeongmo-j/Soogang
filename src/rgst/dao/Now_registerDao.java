package rgst.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.DataSource;

import rgst.vo.Now_register;

public class Now_registerDao {
	DataSource ds;

	public void setDataSource(DataSource ds) {
		this.ds = ds;
	}

	public void insert(Now_register vo) throws Exception  {
		Connection connection = null;
		PreparedStatement stmt = null;
		try {
			connection = ds.getConnection();
			stmt = connection.prepareStatement(
					"INSERT INTO NOW_REGISTER(STU_CODE, SUB_CODE, YEAR, SEMESTER)"
							+ " VALUES (?,?,?,?)");
			stmt.setString(1, vo.getStu_code());
			stmt.setString(2, vo.getSub_code());
			stmt.setString(3, "2019");
			stmt.setString(4, "1");
			stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		} finally {
			try {if (stmt != null) stmt.close();} catch(Exception e) {}
			try {if (connection != null) connection.close();} catch(Exception e) {}
		}
	}

	public int deleteOne(Now_register subject) throws Exception {  
		//과목 하나 지우기
		Connection connection = null;
		Statement stmt = null;
		try {
			connection = ds.getConnection();
			stmt = connection.createStatement();
			return stmt.executeUpdate(
					"DELETE FROM NOW_REGISTER WHERE STU_CODE='" + subject.getStu_code() + "' AND SUB_CODE='" + subject.getSub_code() + "'");
		} catch (Exception e) {
			throw e;
		} finally {
			try {if (stmt != null) stmt.close();} catch(Exception e) {}
			try {if (connection != null) connection.close();} catch(Exception e) {}
		}
	}

	public int delete(Now_register student) throws Exception {  
		//학생의 모든 정보 지우기
		Connection connection = null;
		Statement stmt = null;
		try {
			connection = ds.getConnection();
			stmt = connection.createStatement();
			return stmt.executeUpdate(
					"DELETE FROM NOW_REGISTER WHERE STU_CODE='" + student.getStu_code() + "'");
		} catch (Exception e) {
			throw e;
		} finally {
			try {if (stmt != null) stmt.close();} catch(Exception e) {}
			try {if (connection != null) connection.close();} catch(Exception e) {}
		}
	}

	public ArrayList<Now_register> selectList(Now_register nowregister) throws Exception {
		//수강신청 저장해놓은 목록 다 저장해서 반환
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			connection = ds.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery(
					"SELECT SUB_CODE" + 
							" FROM NOW_REGISTER " +
							"WHERE STU_CODE='" + nowregister.getStu_code() +
					"' ORDER BY SUB_CODE ASC");

			ArrayList<Now_register> registerList = new ArrayList<Now_register>();

			while(rs.next()) {
				registerList.add(new Now_register().setSub_code(rs.getString("SUB_CODE")));
			}
			return registerList;
		} catch (Exception e) {
			throw e;
		} finally {
			try {if (rs != null) rs.close();} catch(Exception e) {}
			try {if (stmt != null) stmt.close();} catch(Exception e) {}
			try {if (connection != null) connection.close();} catch(Exception e) {}
		}
	}

}
