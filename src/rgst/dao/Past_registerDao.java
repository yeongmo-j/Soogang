package rgst.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

public class Past_registerDao {
	  DataSource ds;

	  public void setDataSource(DataSource ds) {
	    this.ds = ds;
	  }

	  public boolean exist(String studentCode, String pastCode) throws Exception {
		  Connection connection = null;
		  PreparedStatement stmt = null;
		  ResultSet rs = null;
		  try {
			  connection = ds.getConnection();
			  stmt = connection.prepareStatement(
					  "SELECT STU_CODE FROM PAST_REGISTER"
							  + " WHERE STU_CODE=? AND SUB_CODE=?");
			  stmt.setString(1, studentCode);
			  stmt.setString(2, pastCode);
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

	  /*
	  public int insert(Now_register nowregister) throws Exception  {
	    Connection connection = null;
	    PreparedStatement stmt = null;
	    try {
	      connection = ds.getConnection();
	      stmt = connection.prepareStatement(
	          "INSERT INTO NOW_REGISTER(STU_CODE, SUB_CODE)"
	              + " VALUES (?,?)");
	      stmt.setString(1, nowregister.getStu_code());
	      stmt.setString(2, nowregister.getSub_code());
	      return stmt.executeUpdate();
	    } catch (Exception e) {
	      throw e;
	    } finally {
	      try {if (stmt != null) stmt.close();} catch(Exception e) {}
	      try {if (connection != null) connection.close();} catch(Exception e) {}
	    }
	  }

	  public int delete(Now_register nowregister) throws Exception {  
		  //학생의 모든 정보 지우기
	    Connection connection = null;
	    Statement stmt = null;
	    try {
	      connection = ds.getConnection();
	      stmt = connection.createStatement();
	      return stmt.executeUpdate(
	          "DELETE FROM NOW_REGISTER WHERE STU_CODE=" + nowregister.getStu_code());
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
		              " FROM SUBJECT " +
		        		  "WHERE STU_CODE=" + nowregister.getStu_code() +
		          " ORDER BY SUB_CODE ASC");

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

	  */

}
