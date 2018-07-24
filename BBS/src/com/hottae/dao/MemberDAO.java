package com.hottae.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.hottae.dto.MemberVO;

import util.DBManager;

public class MemberDAO {
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private MemberDAO() {}
	private static MemberDAO instance = new MemberDAO();
	public static MemberDAO getInstance() {
		return instance;
	}
	
	public int update(MemberVO data) {
		String sql = "UPDATE MEMBER SET MEMPW = ?, MEMNAME = ?, MEMGENDER = ?, MEMEMAIL = ?, NICKNAME = ? WHERE MEMID = ? ";
		String b_sql = "UPDATE BOARD SET NICKNAME = ? WHERE MEMID = ?";
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, data.getMemPW().hashCode());
			pstmt.setString(2, data.getMemName());
			pstmt.setString(3, data.getMemGender());
			pstmt.setString(4, data.getMemEmail());
			pstmt.setString(5, data.getNickName());
			pstmt.setString(6, data.getMemID());
			pstmt.executeUpdate();
			
			pstmt = conn.prepareStatement(b_sql);
			pstmt.setString(1, data.getNickName());
			pstmt.setString(2, data.getMemID());
			pstmt.executeQuery();
			
			return 1; // Join Success
		} catch (Exception e) {
			System.out.println("join sql Error : " + sql);
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		return -2;
	}
	
	public int join(MemberVO data) {
		String sql = "INSERT INTO MEMBER VALUES(?, ?, ?, ?, ?, ?, 1)";
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, data.getMemID());
			pstmt.setLong(2, data.getMemPW().hashCode());
			pstmt.setString(3, data.getMemName());
			pstmt.setString(4, data.getMemGender());
			pstmt.setString(5, data.getMemEmail());
			pstmt.setString(6, data.getNickName());
			pstmt.executeUpdate();
			return 1; // Join Success
		} catch (Exception e) {
			System.out.println("join sql Error");
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		return -2; // DB - Error
	}
	
	 public int login(String memID, int memPW) {
	      String sql = "SELECT MEMPW FROM MEMBER WHERE MEMID = ? AND MEMPW = ? AND MEMUSED = 1 ";
	      
	      try {
	    	conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			System.out.println(memID +"\n" + memPW );
			pstmt.setString(1, memID);
			pstmt.setLong(2, memPW);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				System.out.println("result = 1");
				return 1;	
			} else {
				System.out.println("result = 0");
				return 0;
			}
		} catch (Exception e) {
			System.out.println("Login sql Error");
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return -2;
	 }
	 public int withdrawal(String memID, int memPW ) {
		 String sql = "UPDATE MEMBER SET MEMUSED = 0 WHERE MEMID = ? AND MEMPW = ?";
		 try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memID);
			pstmt.setInt(2, memPW);
			pstmt.executeQuery();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		 return -2;
	 }
	 	
	 public MemberVO info(String memID) {
		 String sql = "SELECT * FROM MEMBER WHERE MEMID = '" + memID +"' ";
		 try {
			 conn = DBManager.getConnection();
			 pstmt = conn.prepareStatement(sql);
			 rs = pstmt.executeQuery();
			 if (rs.next()) {
				 MemberVO m = new MemberVO();
				 m.setMemID(rs.getString("MEMID"));
				 m.setMemName(rs.getString("MEMNAME"));
				 m.setMemGender(rs.getString("MEMGENDER"));
				 m.setMemEmail(rs.getString("MEMEMAIL"));
				 m.setNickName(rs.getString("NICKNAME"));
				return m;
			}else {
				System.out.println("info - ERROR");
				return null;
			}
		 } catch (Exception e) {
			System.out.println("INFO() - Error - sql : " + sql);
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		 return null;
	 }
}
