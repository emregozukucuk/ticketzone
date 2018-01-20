package dblayer;

import java.sql.*;


public class TestDatabase {
	// MySQL driver
	// You need to add mysql-connector.jar to your library
	// In order to do that; right click your project, then go Build Path - Configure Build Path
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	// Database url
	private static final String DB_URL = "jdbc:mysql://localhost:3306/";

	// Database credentials
	private static final String USERNAME = "<YOUR USERNAME>";
	private static final String PASSWORD = "<YOUR PASSWORD>";

	public static void main(String[] args) {
		System.out.println("TestDatabase is running.");

		try {						
			DBHandler dbhandler = DatabaseLayer.getDBHandler(DatabaseLayer.DBType.MySQL, JDBC_DRIVER, DB_URL, USERNAME, PASSWORD);
			
			dbhandler.getBusList("Istanbul", "Ankara", Date.valueOf("2017-12-15"));
		
			//dbhandler.getSeats(1);
			
			//dbhandler.getBus(1);
			
			//dbhandler.updateSeatStatus(1, 21, "R");
			
			//dbhandler.makeReservation(1, 9, "Emre De.");
			
			//dbhandler.makeReservation(1, 1, 5, "Emre Demir");
			
			//dbhandler.getReservation(1);

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			//e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			//e.printStackTrace();
		}
	}
}
