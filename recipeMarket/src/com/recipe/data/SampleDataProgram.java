package com.recipe.data;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.GregorianCalendar;

import com.recipe.jdbc.MyConnection;

public class SampleDataProgram {
//	public static void main(String[] args) {
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//
//		try {
//			conn = MyConnection.getConnection();
//		} catch (ClassNotFoundException | SQLException e1) {
//			e1.printStackTrace();
//		}
//
//		String customerSql = "Insert into CUSTOMER (CUSTOMER_EMAIL,CUSTOMER_PWD,CUSTOMER_NAME,CUSTOMER_GENDER,CUSTOMER_BIRTH_DATE,CUSTOMER_PHONE,BUILDINGNO,CUSTOMER_ADDR,VERIFICATION,CUSTOMER_STATUS) values (?,?,?,?,?,?,?,?,?,?)";
//		for(int i = 0; i < 1000; i++) { //customer add
//			try {
//				pstmt = conn.prepareStatement(customerSql);
//				pstmt.setString(1, i + "@recipe.com");
//				pstmt.setString(2, "1234");
//				pstmt.setString(3, "customer" + (int)(Math.random() * 10));
//				if((int)(Math.random() * 10) % 2 == 0) pstmt.setString(4, "M");
//				else pstmt.setString(4, "F");
//				Date randomDate = new Date((long)(Math.random() * Math.pow(1000000, 2)));
//				pstmt.setDate(5, randomDate);
//				pstmt.setString(6, "000-0000-0000");
//				pstmt.setString(7, "4473025032101680001037793");
//				pstmt.setString(8, "상세주소테스트" + i);
//				pstmt.setString(9, "1");
//				pstmt.setString(10, "y");
//				
//				pstmt.executeUpdate();
//				
//				pstmt.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
		
//		String purchaseSql = "INSERT INTO purchase VALUES (PURCHASE_SEQ.NEXTVAL, ?, ?)";
//		String purchaseDetailSql = "INSERT INTO purchase_detail VALUES (?, ?, ?)";
//		for(int i = 1; i <= 2001; i++) { //date add
//			try {
//				pstmt = conn.prepareStatement(purchaseDetailSql);
//				pstmt.setInt(1, (int)(Math.random() * 1000) % 500);
//				pstmt.setInt(2, i);
//				pstmt.setInt(3, (int)(Math.random() * 10) + 1);
//				pstmt.executeUpdate();
//				
//				pstmt.close();
//				
//			} catch (SQLException e) {
////				e.printStackTrace();
//				if(i > 1) {
//					i--;
//					System.out.println(i);
//				}
//				try {
//					pstmt.close();
//				} catch (SQLException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//			}
//		}
//		
//	}
	public static void main(String[] args) {

        GregorianCalendar gc = new GregorianCalendar();

        String updateSQL = "UPDATE purchase set purchase_date = ? where purchase_code = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
			conn = MyConnection.getConnection();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        for(int i = 1; i <= 2001; i++) {
        	try {
				pstmt = conn.prepareStatement(updateSQL);
				int year = randBetween(2018, 2020);

	            gc.set(gc.YEAR, year);
	            int dayOfYear = 0;
	            if(year != 2020) dayOfYear = randBetween(1, gc.getActualMaximum(gc.DAY_OF_YEAR));
	            else dayOfYear = randBetween(1, 227);
	            gc.set(gc.DAY_OF_YEAR, dayOfYear);

	            pstmt.setDate(1, (new Date(gc.getTimeInMillis())));
	            pstmt.setInt(2, i);
	            pstmt.executeUpdate();
	            pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
        	
        }
        

    }

    public static int randBetween(int start, int end) {
        return start + (int)Math.round(Math.random() * (end - start));
    }
}
