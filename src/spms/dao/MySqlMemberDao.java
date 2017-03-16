package spms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import spms.annotation.Component;
import spms.vo.Member;
@Component("memberDao")
public class MySqlMemberDao implements MemberDao {
	DataSource ds;
	public void setDataSource(DataSource ds) {
		this.ds = ds;
	}
	public Member selectOne(int no) throws Exception {
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		try{
			stmt = connection.createStatement();
			rs = stmt.executeQuery(
					"SELECT MNO,EMAIL,MNAME,CRE_DATE FROM MEMBERS" + 
					" WHERE MNO=" + no);	
			rs.next();
			Member member = new Member ()
				.setNo(rs.getInt("MNO"))
				.setName(rs.getString("MNAME"))
				.setEmail(rs.getString("EMAIL"))
				.setCreatedDate(rs.getDate("CRE_DATE"));
			return member;
		} catch(Exception e) {
			throw e;
		
		} finally {
			try {if (rs != null) rs.close();} catch(Exception e) {}
			try {if (stmt != null) stmt.close();} catch(Exception e) {}
	}
	}
	public Member exist(String email, String password) throws Exception {
		Connection connection =null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			connection = ds.getConnection();
			stmt = connection.prepareStatement(
				"SELECT MNAME,EMAIL FROM MEMBERS"
				+ " WHERE EMAIL=? AND PWD=?");
			stmt.setString(1, email);
			stmt.setString(2, password);
			rs = stmt.executeQuery();
			if (rs.next()) {
				return new Member()
						.setEmail(rs.getString("EMAIL"))
						.setName(rs.getString("MNAME"));
			} else 
				return null;
		} catch(Exception e) {
			throw e;
		
		} finally {
			try {if (rs != null) rs.close();} catch(Exception e) {}
			try {if (stmt != null) stmt.close();} catch(Exception e) {}
			try {if (connection != null) connection.close();} catch(Exception e) {}
		}
	}
	public int update(Member member) throws Exception {
		Connection connection =null;
		PreparedStatement stmt = null;
		try{	
			connection = ds.getConnection();
			stmt = connection.prepareStatement(
					"UPDATE MEMBERS SET EMAIL=?,MNAME=?,MOD_DATE=now()"
						+ " WHERE MNO=?");
			stmt.setString(1, member.getEmail());
			stmt.setString(2, member.getName());
			stmt.setInt(3, member.getNo());
			return stmt.executeUpdate();
		} catch(Exception e) {
			throw e;
		
		} finally {
			try {if (stmt != null) stmt.close();} catch(Exception e) {}
			try {if (connection != null) connection.close();} catch(Exception e) {}
		}
	}
	public void  addMember(Member member) throws Exception {
		PreparedStatement stmt = null;
		Connection connection = null;
		try {
			connection = ds.getConnection();
			stmt = connection.prepareStatement(
					"INSERT INTO MEMBERS(EMAIL,PWD,MNAME,CRE_DATE,MOD_DATE)"
							+ " VALUES (?,?,?,NOW(),NOW())");
			stmt.setString(1, member.getEmail());
			stmt.setString(2, member.getPassword());
			stmt.setString(3, member.getName());
			stmt.executeUpdate();
			}
		catch(Exception e) {
			throw e;
		} 	finally {
			try {if (stmt != null) stmt.close();} catch(Exception e) {}
			try {if (connection != null) connection.close();} catch(Exception e) {}
		}
	}
	public List<Member> selectlList() throws Exception{
		Statement stmt = null;
		ResultSet rs = null;
		Connection connection = null;
		try{
			connection = ds.getConnection();
			stmt = connection.createStatement();
			rs=stmt.executeQuery("SELECT MNO, MNAME,EMAIL, CRE_DATE" +
					" FROM MEMBERS" +
					" ORDER BY MNO ASC");
			
			ArrayList<Member> members = new ArrayList<Member>();
			
			while(rs.next()){
				members.add(new Member()
						.setNo(rs.getInt("MNO"))
						.setName(rs.getString("MNAME"))
						.setEmail(rs.getString("EMAIL"))
						.setCreatedDate(rs.getDate("CRE_DATE")) );
				}
				return members;

		} catch(Exception e) {
			throw e;
		
		} finally {
			try {if (rs != null) rs.close();} catch(Exception e) {}
			try {if (stmt != null) stmt.close();} catch(Exception e) {}
			try {if (connection != null) connection.close();} catch(Exception e) {}
	}
	}
}
