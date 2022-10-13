package Project;

import java.sql.*;

public class AddRecords {

	public static void main(String iD, String fAT, String sNF, String wEIGHT) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","Password");
			 try( Statement stmt = con.createStatement()){
					float rateGot = AddRecords.rate(fAT,sNF);
					float w = Float.parseFloat(wEIGHT);
					float Total = w * rateGot;
					String total = Float.toString(Total);
					System.out.println("total: " + total);
					String query1 = "INSERT INTO `record` (`Id`, `snf`, `fat`, `rate`, `weigth`, `total`, `date`) "
							+ "VALUES ('"+iD+"', '"+sNF+"', '"+fAT+"', '"+rateGot+"', '"+wEIGHT+"', '"+total+"', current_timestamp())";
					stmt.executeUpdate(query1);
					System.out.println("query run successfully");
			}
			con.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("sql fail");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
	public static float rate(String Fat, String Snf) 
	{
		float rateGot = 0;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","Password");
			 try( Statement stmt = con.createStatement()){
				String query = " SELECT `rate` FROM rateTable WHERE format(fat,2) = format("+Fat+",2) AND  format(snf,2) = format("+Snf+",2)";
					ResultSet rs = stmt.executeQuery(query);	
					if(rs.next()) {
						rateGot = rs.getFloat("rate");
						//Records.rate.setText(rateGot);
					}
			}
			con.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("sql fail");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return rateGot;
	}

}
