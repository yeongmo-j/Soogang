package rgst.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.DataSource;

import rgst.vo.Subject;

public class SubjectDao {
	DataSource ds;

	public void setDataSource(DataSource ds) {
		this.ds = ds;
	}

	public int[] getTime(Subject sbj) {
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		int[] time = new int[2];
		try {
			connection = ds.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery(
					"SELECT TIME1, TIME2 FROM SUBJECT WHERE SUB_CODE='" + sbj.getSub_code()+"'");  
			while(rs.next()) {
				time[0] = Integer.parseInt(rs.getString("TIME1"));
				time[1] = Integer.parseInt(rs.getString("TIME2"));
			}
		} catch (Exception e) {
		} finally {
			try {if (rs != null) rs.close();} catch(Exception e) {}
			try {if (stmt != null) stmt.close();} catch(Exception e) {}
			try {if (connection != null) connection.close();} catch(Exception e) {}
		}
		return time;
	}

	public ArrayList<Subject> selectList() throws Exception {
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			connection = ds.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery(
					"SELECT SUB_CODE, SUB_NAME, PROFESSOR, CLASSROOM, PRE, TIME1, TIME2" + 
							" FROM SUBJECT" +
					" ORDER BY SUB_CODE ASC");

			ArrayList<Subject> subjects = new ArrayList<Subject>();

			while(rs.next()) {
				subjects.add(new Subject().setSub_code(rs.getString("SUB_CODE")).setSub_name(rs.getString("SUB_NAME"))
						.setProfessor(rs.getString("PROFESSOR")).setClassroom(rs.getString("CLASSROOM"))
						.setPre(rs.getString("PRE")).setTime1(rs.getString("TIME1")).setTime2(rs.getString("TIME2")));
			}
			return subjects;
		} catch (Exception e) {
			throw e;
		} finally {
			try {if (rs != null) rs.close();} catch(Exception e) {}
			try {if (stmt != null) stmt.close();} catch(Exception e) {}
			try {if (connection != null) connection.close();} catch(Exception e) {}
		}
	}

	public int insert(Subject subject) throws Exception  {
		Connection connection = null;
		PreparedStatement stmt = null;
		try {
			connection = ds.getConnection();
			stmt = connection.prepareStatement(
					"INSERT INTO SUBJECT(SUB_CODE, SUB_NAME, PROFESSOR, CLASSROOM, PRE, TIME1, TIME2)"
							+ " VALUES (?, ?, ?, ?, ?, ?, ?)");
			stmt.setString(1, subject.getSub_code());
			stmt.setString(2, subject.getSub_name());
			stmt.setString(3, subject.getProfessor());
			stmt.setString(4, subject.getClassroom());
			stmt.setString(5, subject.getPre());
			stmt.setString(6, subject.getTime1());
			stmt.setString(7, subject.getTime2());
			return stmt.executeUpdate();

		} catch (Exception e) {
			throw e;

		} finally {
			try {if (stmt != null) stmt.close();} catch(Exception e) {}
			try {if (connection != null) connection.close();} catch(Exception e) {}
		}
	}

	public String getPre(Subject sbj) throws Exception { 
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			connection = ds.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery(
					"SELECT PRE FROM SUBJECT" + 
							" WHERE SUB_CODE='" + sbj.getSub_code()+"'");    
			if (rs.next()) {
				return rs.getString("PRE");
			} else {
				return "x";
			}

		} catch (Exception e) {
			throw e;
		} finally {
			try {if (rs != null) rs.close();} catch(Exception e) {}
			try {if (stmt != null) stmt.close();} catch(Exception e) {}
			try {if (connection != null) connection.close();} catch(Exception e) {}
		}
	}
}
