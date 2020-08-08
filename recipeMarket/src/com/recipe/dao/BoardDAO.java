/**
 * @author yonghwan
 */
package com.recipe.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.recipe.exception.AddException;
import com.recipe.exception.FindException;
import com.recipe.jdbc.MyConnection;
import com.recipe.pair.Pair;
import com.recipe.vo.Board;

public class BoardDAO {
	/**
	 * 
	 * @param board
	 * @throws AddException
	 */
	public void insert(Board board) throws AddException{
		Connection con=null;
		PreparedStatement pstmt=null;
		
		String insertSQL = "INSERT INTO board ("
				+ "board_no, "
				+ "parent_no, "
				+ "board_title, "
				+ "board_writer, "
				+ "board_dt, "
				+ "board_content, "
				+ "board_views)\r\n" + 
				   "VALUES (board_seq.NEXTVAL, ?, ?, ?, SYSTIMESTAMP, ?, 0)";
		
		try {
			con = MyConnection.getConnection();

			pstmt = con.prepareStatement(insertSQL);
			
			pstmt.setInt(1, board.getParentNo());
			pstmt.setString(2, board.getBoardTitle());
			pstmt.setString(3, board.getBoardWriter());
			pstmt.setString(4, board.getBoardContent());
			
			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new AddException(e.getMessage());
		} finally {
			MyConnection.close(pstmt, con);
		}
	}

	/**
	 * 
	 * @param startRow
	 * @param endRow
	 * @return
	 * @throws FindException
	 */
	public List<Board> selectAll(int startRow, int endRow) throws FindException{
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		List<Board> list = null;
				
		String selectAllSQL = "SELECT *\r\n" + 
				"FROM (SELECT ROWNUM r,  a.*\r\n" + 
				"      FROM (SELECT level, board.*\r\n" + 
				"            FROM board\r\n" + 
				"            START WITH parent_no = 0 \r\n" + 
				"            CONNECT BY PRIOR board_no = parent_no\r\n" + 
				"            ORDER SIBLINGS BY board_no DESC) a\r\n" + 
				")\r\n" + 
				"WHERE r BETWEEN ? AND ?";
				
		try {
			con = MyConnection.getConnection();
			
			pstmt = con.prepareStatement(selectAllSQL, ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);			
			
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rs = pstmt.executeQuery();
		
			if (!rs.next())
				throw new FindException("There is no data corresponding to the condition.");

			list = new ArrayList<>();

			rs.previous();
			
			while(rs.next()) {
				Board b = new Board(rs.getInt("level"), 
						rs.getInt("board_no"), 
						rs.getInt("parent_no"), 
						rs.getString("board_title"), 
						rs.getString("board_writer"), 
						rs.getDate("board_dt"), 
						rs.getString("board_content"), 
						rs.getInt("board_views"));
				
				list.add(b);
			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		} finally {
			MyConnection.close(rs, pstmt, con);
		}
		
		return list;
	}

	/**
	 * 
	 * @return
	 * @throws FindException
	 */
	public int selectCount() throws FindException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String selectCountSQL = "SELECT COUNT(*) FROM board";
		
		try {
			con = MyConnection.getConnection();
			
			pstmt = con.prepareStatement(selectCountSQL);
			rs = pstmt.executeQuery();
			
			rs.next();
			
			return rs.getInt(1);
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		} finally {
			MyConnection.close(rs, pstmt, con);
		}
	}

	/**
	 * 
	 * @param board_no
	 * @return
	 * @throws FindException
	 */
	public List<Board> selectByNo(int board_no) throws FindException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String selectAllSQL = "SELECT * FROM board WHERE board_no=?";
		
		List<Board> list = null;


		try {
			con = MyConnection.getConnection();
			
			pstmt = con.prepareStatement(selectAllSQL, ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			
			pstmt.setInt(1, board_no);
			
			rs = pstmt.executeQuery();
			
			if (!rs.next())
				throw new FindException("There is no data corresponding to the condition.");

			list = new ArrayList<>();

			rs.previous();
			
			while(rs.next()) {
				list.add(new Board(0, 
						board_no, 
						rs.getInt("parent_no"), 
						rs.getString("board_title"), 
						rs.getString("board_writer"), 
						rs.getDate("board_dt"), 
						rs.getString("board_content"), 
						rs.getInt("board_views")));
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		} finally {
			MyConnection.close(rs, pstmt, con);
		}
		
		return list;
	}
}
