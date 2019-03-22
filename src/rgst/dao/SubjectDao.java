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
	  
	  public int[] getTime(String code) {
		  Connection connection = null;
		  Statement stmt = null;
		  ResultSet rs = null;
		  int[] time = new int[2];
		  try {
			  connection = ds.getConnection();
			  stmt = connection.createStatement();
			  rs = stmt.executeQuery(
					  "SELECT TIME1, TIME2 FROM SUBJECT WHERE SUB_CODE='" + code+"'");  
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
	  
	  public String getPre(String subcode) throws Exception { 
		    Connection connection = null;
		    Statement stmt = null;
		    ResultSet rs = null;
		    try {
		      connection = ds.getConnection();
		      stmt = connection.createStatement();
		      rs = stmt.executeQuery(
		          "SELECT PRE FROM SUBJECT" + 
		              " WHERE SUB_CODE='" + subcode+"'");    
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

/*
	  public int delete(int no) throws Exception {  
	    Connection connection = null;
	    Statement stmt = null;
	    try {
	      connection = ds.getConnection();
	      stmt = connection.createStatement();
	      return stmt.executeUpdate(
	          "DELETE FROM MEMBERS WHERE MNO=" + no);

	    } catch (Exception e) {
	      throw e;

	    } finally {
	      try {if (stmt != null) stmt.close();} catch(Exception e) {}
	      try {if (connection != null) connection.close();} catch(Exception e) {}
	    }
	  }

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

	  public int update(Member member) throws Exception { 
	    Connection connection = null;
	    PreparedStatement stmt = null;
	    try {
	      connection = ds.getConnection();
	      stmt = connection.prepareStatement(
	          "UPDATE MEMBERS SET EMAIL=?,MNAME=?,MOD_DATE=now()"
	              + " WHERE MNO=?");
	      stmt.setString(1, member.getEmail());
	      stmt.setString(2, member.getName());
	      stmt.setInt(3, member.getNo());
	      return stmt.executeUpdate();

	    } catch (Exception e) {
	      throw e;

	    } finally {
	      try {if (stmt != null) stmt.close();} catch(Exception e) {}
	      try {if (connection != null) connection.close();} catch(Exception e) {}
	    }
	  }
	  
	  public Member exist(String email, String password) throws Exception {
	    Connection connection = null;
	    PreparedStatement stmt = null;
	    ResultSet rs = null;

	    try {
	      connection = ds.getConnection();
	      stmt = connection.prepareStatement(
	          "SELECT MNAME,EMAIL FROM MEMBERS"
	              + " WHERE EMAIL=? AND PWD=?");
	      stmt.setString(1, email);
	      stmt.setString(2, password);
	      rs = stmt.executeQuery();
	      if (rs.next()) {
	        return new Member()
	          .setName(rs.getString("MNAME"))
	          .setEmail(rs.getString("EMAIL"));
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
	  */
}
