package dblayer;

import java.util.ArrayList;
import java.sql.*;
import java.io.*;

import models.*;

public class MySQLHandler implements DBHandler {
	private String driver;
	private String url;
	private String username;
	private String password;
	private Connection conn;

	public MySQLHandler(String driver, String url, String username, String password) throws Exception {
		this.driver = driver;
		this.url = url;
		this.username = username;
		this.password = password;

		connectDatabase();
	}

	private void connectDatabase() throws Exception {
		// Register driver
		Class.forName(driver);

		// Connect to MySQL Server
		Connection mysqlConn = DriverManager.getConnection(url, username, password);

		try {
			// Run ScriptRunner on file 'create_db.sql'.
			// It creates and prepares the database.
			// We actually doesn't care the exceptions like can't insert etc.
			// thrown by ScriptRunner.
			// Instead, we execute a final code block to connect to database
			ScriptRunner runner = new ScriptRunner(mysqlConn, false, true);
			runner.runScript(new BufferedReader(new FileReader("src/dblayer/create_db.sql")));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			// Connect to database ticketzone
			String dbname = "ticketzone";
			conn = DriverManager.getConnection(url + dbname, username, password);
		}

		mysqlConn.close();
	}

	/*
	 * Implementing DBHandler
	 */

	@Override
	public ArrayList<Bus> getBusList(String departure, String arrival, Date deptDate) throws SQLException {
		ArrayList<Bus> busList = new ArrayList<Bus>();

		PreparedStatement pstmt = conn.prepareStatement(
				"SELECT * FROM bus WHERE departure = ?" + " AND arrival = ?" + " AND departure_date = ?");
		pstmt.setString(1, departure);
		pstmt.setString(2, arrival);
		pstmt.setDate(3, deptDate);

		ResultSet result = pstmt.executeQuery();

		while (result.next()) {
			Bus bus = new Bus(result);
			busList.add(bus);

			System.out.println(bus);
		}

		return busList;
	}

	@Override
	public ArrayList<Seat> getSeats(int busId) throws SQLException {
		ArrayList<Seat> seats = new ArrayList<Seat>();

		Statement stmt = conn.createStatement();
		ResultSet result = stmt.executeQuery("SELECT * FROM seat WHERE bus_id = " + busId);

		while (result.next()) {
			Seat seat = new Seat(result);
			seats.add(seat);

			System.out.println(seat);
		}

		return seats;
	}

	@Override
	public Bus getBus(int busId) throws SQLException {
		Statement stmt = conn.createStatement();
		ResultSet result = stmt.executeQuery("SELECT * FROM bus WHERE bus_id = " + busId);

		while (result.next()) {
			Bus bus = new Bus(result);
			System.out.println(bus);
			return bus;
		}

		throw new SQLException("Executing query resulted in empty set");
	}

	@Override
	public void updateSeatStatus(int busId, int seatNo, String status) throws SQLException {
		Statement stmt = conn.createStatement();
		int success = stmt.executeUpdate(
				"UPDATE seat SET status = '" + status + "' WHERE seat_number = " + seatNo + " AND bus_id = " + busId);

		System.out.println("Updating seat status: " + success);
	}

	@Override
	public void makeReservation(int busId, int seatNo, String customer) throws SQLException {
		Statement stmt = conn.createStatement();
		int success = stmt.executeUpdate("INSERT INTO reservation" + " (bus_id, seat_number, customer_name) "
				+ "VALUES(" + busId + ", " + seatNo + ", '" + customer + "')");

		System.out.println("Making reservation: " + success);
	}

	@Override
	public void makeReservation(int reservNo, int busId, int seatNo, String customer) throws SQLException {
		Statement stmt = conn.createStatement();
		int success = stmt
				.executeUpdate("INSERT INTO reservation" + " (reservation_no, bus_id, seat_number, customer_name) "
						+ "VALUES(" + reservNo + ", " + busId + ", " + seatNo + ", '" + customer + "')");

		System.out.println("Making reservation: " + success);
	}

	@Override
	public ArrayList<Reservation> getReservation(int reservNo) throws SQLException {
		ArrayList<Reservation> reservations = new ArrayList<Reservation>();

		PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM reservation where reservation_no = ?");
		pstmt.setInt(1, reservNo);

		ResultSet result = pstmt.executeQuery();

		while (result.next()) {
			Reservation reservation = new Reservation(result);
			reservations.add(reservation);

			System.out.println(reservation);
		}

		return reservations;
	}
}
