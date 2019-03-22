package rgst.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.sql.DataSource;

import rgst.vo.Student;

public class StudentDao {
	DataSource ds;

	public void setDataSource(DataSource ds) {
		this.ds = ds;
	}

	public int insert(Student student) throws Exception  {
		Connection connection = null;
		PreparedStatement stmt = null;
		try {
			connection = ds.getConnection();
			stmt = connection.prepareStatement(
					"INSERT INTO STUDENT(STU_CODE, STU_NAME, CREDIT, PASSWORD)"
							+ " VALUES (?,?,?,?)");
			stmt.setString(1, student.getStu_code());
			stmt.setString(2, student.getStu_name());
			stmt.setInt(3, student.getCredit());
			stmt.setString(4, student.getPassword());
			return stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		} finally {
			try {if (stmt != null) stmt.close();} catch(Exception e) {}
			try {if (connection != null) connection.close();} catch(Exception e) {}
		}
	}

	public int delete(String stu_code) throws Exception {  
		Connection connection = null;
		Statement stmt = null;

		try {
			connection = ds.getConnection();
			stmt = connection.createStatement();
			return stmt.executeUpdate(
					"DELETE FROM STUDENT WHERE MNO='" + stu_code+"'");
		} catch (Exception e) {
			throw e;
		} finally {
			try {if (stmt != null) stmt.close();} catch(Exception e) {}
			try {if (connection != null) connection.close();} catch(Exception e) {}
		}
	}

	public Student exist(String stu_code, String password) throws Exception {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			connection = ds.getConnection();
			stmt = connection.prepareStatement(
					"SELECT STU_CODE,STU_NAME,CREDIT FROM STUDENT"
							+ " WHERE STU_CODE=? AND PASSWORD=?");
			stmt.setString(1, stu_code);
			stmt.setString(2, password);
			rs = stmt.executeQuery();
			if (rs.next()) {
				return new Student()
						.setStu_code(rs.getString("STU_CODE"))
						.setStu_name(rs.getString("STU_NAME"))
						.setCredit(rs.getInt("CREDIT"));
			} else {
				return null;
			}
		} catch (Exception e) {
			throw e;

		} finally {
			try {if (rs != null) rs.close();} catch (Exception e) {}
			try {if (stmt != null) stmt.close();} catch (Exception e) {}
			try {if (connection != null) connection.close();} catch(Exception e) {}
		}
	}
	
	public int updateCredit(String stu_code, int credit) throws Exception { 
	    Connection connection = null;
	    PreparedStatement stmt = null;
	    try {
	      connection = ds.getConnection();
	      stmt = connection.prepareStatement(
	          "UPDATE STUDENT SET CREDIT=?"
	              + " WHERE STU_CODE='"+stu_code+"'");
	      stmt.setInt(1, credit);
	      return stmt.executeUpdate();
	    } catch (Exception e) {
	      throw e;
	    } finally {
	      try {if (stmt != null) stmt.close();} catch(Exception e) {}
	      try {if (connection != null) connection.close();} catch(Exception e) {}
	    }
	  }

	/*
	  public Member selectOne(int no) throws Exception { 
	    Connection connection = null;
	    Statement stmt = null;
	    ResultSet rs = null;
	    try {
	      connection = ds.getConnection();
	      stmt = connection.createStatement();
	      rs = stmt.executeQuery(
	          "SELECT MNO,EMAIL,MNAME,CRE_DATE FROM MEMBERS" + 
	              " WHERE MNO=" + no);    
	      if (rs.next()) {
	        return new Member()
	        .setNo(rs.getInt("MNO"))
	        .setEmail(rs.getString("EMAIL"))
	        .setName(rs.getString("MNAME"))
	        .setCreatedDate(rs.getDate("CRE_DATE"));

	      } else {
	        throw new Exception("해당 번호의 회원을 찾을 수 없습니다.");
	      }

	    } catch (Exception e) {
	      throw e;
	    } finally {
	      try {if (rs != null) rs.close();} catch(Exception e) {}
	      try {if (stmt != null) stmt.close();} catch(Exception e) {}
	      try {if (connection != null) connection.close();} catch(Exception e) {}
	    }
	  }


	 */

}
