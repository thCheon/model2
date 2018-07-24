package com.hottae.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.hottae.dto.BoardVO;

import util.DBManager;

public class BoardDAO {
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private static BoardDAO instance = new BoardDAO();
	private BoardDAO() {}
	public static BoardDAO getInstance() {
		return instance;
	}
	
	// ����¡ 10�� ��
	public ArrayList<BoardVO> getList(int pageNum) {
		StringBuffer sql = new StringBuffer();
		ArrayList<BoardVO> list = null;
		sql.append(" SELECT * FROM(SELECT ROWNUM R, IN_R, BDID, BDUSED, FILENAME, BDMGR, BDDATE, MEMID, BDTITLE, BDCONTENT, NICKNAME ");
		sql.append("                FROM(SELECT ROWNUM IN_R, BDID, BDUSED, FILENAME, BDMGR, BDDATE, MEMID, BDTITLE, BDCONTENT, NICKNAME ");
		sql.append("                        FROM (SELECT BDID, BDUSED, BDMGR, FILENAME, BDDATE, MEMID, BDTITLE, BDCONTENT, NICKNAME ");
		sql.append("                                FROM(SELECT ROWNUM PC, BDID, FILENAME, BDUSED, BDMGR, BDDATE, MEMID, BDTITLE, BDCONTENT, NICKNAME ");
		sql.append("                                      FROM BOARD WHERE BDUSED = 1 ");
		sql.append("                                      START WITH BDMGR = 0 ");
		sql.append("                                      CONNECT BY PRIOR BDID = BDMGR ");
		sql.append("                                      ORDER SIBLINGS BY BDID DESC) ");
		sql.append("                                ORDER BY PC DESC) ");
		sql.append("                ORDER BY IN_R DESC) ) ");
		sql.append(" WHERE R BETWEEN ? AND ? ");
		try {
			
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, pageNum * 10 - 9);
			pstmt.setInt(2, pageNum * 10);
			rs = pstmt.executeQuery();
			list = new ArrayList<BoardVO>();
			while (rs.next()) {
				BoardVO b = new BoardVO();
				b.setBdId(rs.getInt("BDID"));
				b.setBdTitle(rs.getString("BDTITLE"));
				b.setBdContent(rs.getString("BDCONTENT"));
				b.setBdUsed(rs.getString("BDUSED"));
				b.setBdDate(rs.getString("BDDATE")); 
				b.setMemId(rs.getString("MEMID"));
				b.setBdMgr(rs.getInt("BDMGR"));
				b.setBdRow(rs.getInt("IN_R"));
				b.setFileName(rs.getString("FILENAME"));
				b.setNickName(rs.getString("NICKNAME"));
				list.add(b);
			}
		} catch (SQLException e) {
			System.out.println("getList-Error");
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return list;
	}
	
	public ArrayList<BoardVO> myBoard(String memId, int pageNum) {
		System.out.println("GetList()");
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT * FROM(SELECT ROWNUM R, IN_R, BDID, BDUSED, FILENAME, BDMGR, BDDATE, MEMID, BDTITLE, BDCONTENT, NICKNAME ");
		sql.append("                FROM(SELECT ROWNUM IN_R, BDID, BDUSED, FILENAME, BDMGR, BDDATE, MEMID, BDTITLE, BDCONTENT, NICKNAME ");
		sql.append("                        FROM (SELECT BDID, BDUSED, BDMGR, FILENAME, BDDATE, MEMID, BDTITLE, BDCONTENT, NICKNAME ");
		sql.append("                                FROM(SELECT ROWNUM PC, BDID, FILENAME, BDUSED, BDMGR, BDDATE, MEMID, BDTITLE, BDCONTENT, NICKNAME ");
		sql.append("                                      FROM BOARD WHERE BDUSED = 1 AND MEMID = ? ");
		sql.append("                                      START WITH BDMGR = 0 ");
		sql.append("                                      CONNECT BY PRIOR BDID = BDMGR ");
		sql.append("                                      ORDER SIBLINGS BY BDID DESC) ");
		sql.append("                                ORDER BY PC DESC) ");
		sql.append("                ORDER BY IN_R DESC) ) ");
		sql.append(" WHERE R BETWEEN ? AND ? ");
		
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, memId);
			pstmt.setInt(2, pageNum * 10 - 9);
			System.out.println("pageNum1 : " + (pageNum * 10 - 9));
			pstmt.setInt(3, pageNum * 10);
			System.out.println("pageNum2 : " + (pageNum * 10));
			rs = pstmt.executeQuery();
			while (rs.next()) {
				BoardVO b = new BoardVO();
				b.setBdId(rs.getInt("BDID"));
				b.setBdTitle(rs.getString("BDTITLE"));
				b.setBdContent(rs.getString("BDCONTENT"));
				b.setBdUsed(rs.getString("BDUSED"));
				b.setBdDate(rs.getString("BDDATE")); 
				b.setMemId(rs.getString("MEMID"));
				b.setBdMgr(rs.getInt("BDMGR"));
				b.setBdRow(rs.getInt("IN_R"));
				b.setFileName(rs.getString("FILENAME"));
				b.setNickName(rs.getString("NICKNAME"));
				list.add(b);
			}
		} catch (SQLException e) {
			System.out.println("myBoard Error");
			e.printStackTrace();
			return null;
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return list;
	}
	
	public ArrayList<BoardVO> serchList(String option ,String word, int pageNum) {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT * FROM(SELECT ROWNUM R, IN_R, BDID, BDUSED, FILENAME, BDMGR, BDDATE, MEMID, BDTITLE, BDCONTENT, NICKNAME ");
		sql.append("                FROM(SELECT ROWNUM IN_R, BDID, BDUSED, FILENAME, BDMGR, BDDATE, MEMID, BDTITLE, BDCONTENT, NICKNAME ");
		sql.append("                        FROM (SELECT BDID, BDUSED, BDMGR, FILENAME, BDDATE, MEMID, BDTITLE, BDCONTENT, NICKNAME ");
		sql.append("                                FROM(SELECT ROWNUM PC, BDID, FILENAME, BDUSED, BDMGR, BDDATE, MEMID, BDTITLE, BDCONTENT, NICKNAME ");
		sql.append("                                      FROM BOARD WHERE BDUSED = 1 AND (" + option + " LIKE (?)) " );
		sql.append("                                      START WITH BDMGR = 0 ");
		sql.append("                                      CONNECT BY PRIOR BDID = BDMGR ");
		sql.append("                                      ORDER SIBLINGS BY BDID DESC) ");
		sql.append("                                ORDER BY PC DESC) ");
		sql.append("                ORDER BY IN_R DESC) ) ");
		sql.append(" WHERE R BETWEEN ? AND ? ");
		
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, "%" + word + "%");
			pstmt.setInt(2, pageNum * 10 - 9);
			pstmt.setInt(3, pageNum * 10);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				BoardVO b = new BoardVO();
				b.setBdId(rs.getInt("BDID"));
				b.setBdTitle(rs.getString("BDTITLE"));
				b.setBdContent(rs.getString("BDCONTENT"));
				b.setBdUsed(rs.getString("BDUSED"));
				b.setBdDate(rs.getString("BDDATE")); 
				b.setMemId(rs.getString("MEMID"));
				b.setBdMgr(rs.getInt("BDMGR"));
				b.setBdRow(rs.getInt("IN_R"));
				b.setFileName(rs.getString("FILENAME"));
				b.setNickName(rs.getString("NICKNAME"));
				list.add(b);
			}
		} catch (SQLException e) {
			System.out.println("serchList-Error");
			e.printStackTrace();
			return null;
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return list;
	}
	
	public int write(BoardVO b) {
		String sql = "INSERT INTO BOARD VALUES(BOARD_SEQ.NEXTVAL, ?, ?, SYSDATE, ?, 1, 0, ?, ?, 0, ?)";
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, b.getBdTitle());
			pstmt.setString(2, b.getMemId());
			pstmt.setString(3, b.getBdContent());
			pstmt.setString(4, b.getBdIp());
			pstmt.setString(5, b.getFileName());
			pstmt.setString(6, b.getNickName());
			pstmt.executeUpdate();
			return 1;
		} catch (SQLException e) {
			System.out.println("write sql error : " + sql);
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		return -2; // DB-Error
	}
	
	public int reply(BoardVO b) {
		String sql = "INSERT INTO BOARD VALUES(BOARD_SEQ.NEXTVAL, ?, ?, SYSDATE, ?, 1, ?, ?, ?, 0, ?)";
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "└[re]" + b.getBdTitle());
			pstmt.setString(2, b.getMemId());
			pstmt.setString(3, b.getBdContent());
			pstmt.setInt(4, b.getBdMgr());
			pstmt.setString(5, b.getBdIp());
			pstmt.setString(6, b.getFileName());
			pstmt.setString(7, b.getNickName());
			pstmt.executeUpdate();
			return 1;
		} catch (SQLException e) {
			System.out.println("REPLY sql error : " + sql);
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		return -2; // DB-Error
	}
	
	public int delete(String bdId) {
		String sql = "UPDATE BOARD SET BDUSED = 0 WHERE BDID = ?";
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bdId);
			pstmt.executeUpdate();
			return 1;
		}catch(Exception e) {
			System.out.println("ERROR update : " + sql);
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		return -2;
	}
	
	public int update(BoardVO b) {
		String sql = "UPDATE BOARD SET BDTITLE = ?, BDCONTENT = ?, FILENAME = ? WHERE BDID = ?";
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, b.getBdTitle());
			pstmt.setString(2, b.getBdContent());
			pstmt.setString(3, b.getFileName());
			pstmt.setInt(4, b.getBdId());
			pstmt.executeUpdate();
			return 1;
		}catch(Exception e){
			System.out.println("ERROR update : " + sql);
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		return -2; // DB-Error
	}
	
	public  ArrayList<BoardVO> topView() {
		String sql = "SELECT BDTITLE, BDID, MEMID, BDDATE, READCOUNT, NICKNAME, RANK " +
				"FROM(SELECT BDTITLE, BDID, MEMID, BDDATE, READCOUNT, NICKNAME, " +
				"				RANK()OVER(ORDER BY READCOUNT DESC) RANK " +
				"		FROM BOARD WHERE BDUSED = 1) " +
				"WHERE ROWNUM < 4";
		
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				BoardVO b = new BoardVO();
				b.setBdId(rs.getInt("BDID"));
				b.setBdTitle(rs.getString("BDTITLE"));
				b.setMemId(rs.getString("MEMID"));
				b.setReadCount(rs.getInt("READCOUNT"));
				b.setBdDate(rs.getString("BDDATE"));
				b.setNickName(rs.getString("NICKNAME"));
				list.add(b);
			}
			return list;
		} catch (Exception e) {
			System.out.println("TopView Error - sql : " + sql);
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		return null;
	}
	
	public Boolean countPlus(int bdId) {
		String sql = "UPDATE BOARD SET READCOUNT = READCOUNT + 1 WHERE BDID = ?";
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bdId);
			pstmt.executeUpdate();
			return true;
		} catch (Exception e) {
			DBManager.close(conn, pstmt);
		}
		return true;
	}
	public BoardVO view(String id) {
		String sql = "SELECT NICKNAME, BDID, BDTITLE, BDCONTENT, MEMID, FILENAME, READCOUNT FROM BOARD WHERE BDID = ?";
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				BoardVO b = new BoardVO();
				b.setBdTitle(rs.getString("BDTITLE"));
				b.setBdContent(rs.getString("BDCONTENT"));
				b.setMemId(rs.getString("MEMID"));
				b.setBdId(rs.getInt("BDID"));
				b.setFileName(rs.getString("FILENAME"));
				b.setNickName(rs.getString("NICKNAME"));
				b.setReadCount(rs.getInt("READCOUNT"));
				return b;
			} else {
				System.out.println("sql Error : " + sql);
				return null;
			}
		} catch (Exception e) {
			System.out.println("DB-ERROR");
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		return null;
	}
	
	
}
