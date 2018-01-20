package dblayer;

import models.*;
import java.sql.*;
import java.util.ArrayList;

// DBHandler interface defines functions handled by Database Handlers
public interface DBHandler {
	public ArrayList<Bus> getBusList(String departure, String arrival, Date deptDate) throws SQLException;
	public ArrayList<Seat> getSeats(int busId) throws SQLException;
	public Bus getBus(int busId) throws SQLException;
	public void updateSeatStatus(int busId, int seatNo, String status) throws SQLException;
	public void makeReservation(int busId, int seatNo, String customer) throws SQLException;
	public void makeReservation(int reservNo, int busId, int seatNo, String customer) throws SQLException;
	public ArrayList<Reservation> getReservation(int reservNo) throws SQLException;
}
