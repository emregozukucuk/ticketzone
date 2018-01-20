package models;

import java.sql.*;

public class Bus {
	private int id;
	private String departure;
	private String arrival;
	private Time departureTime;
	private Time arrivalTime;
	private Date departureDate;
	private Date arrivalDate;

	
	public Bus(int id, String departure, String arrival, Time departureTime, Time arrivalTime, Date departureDate, Date arrivalDate) {
		this.id = id;
		this.departure = departure;
		this.arrival = arrival;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.departureDate = departureDate;
		this.arrivalDate = arrivalDate;
	}

	public Bus(ResultSet result) throws SQLException {
		this.id = result.getInt(1);
		this.departure = result.getString(2);
		this.arrival = result.getString(3);
		this.departureTime = result.getTime(4);
		this.arrivalTime = result.getTime(5);
		this.departureDate = result.getDate(6);
		this.arrivalDate = result.getDate(7);
	}
	
	@Override
	public String toString() {
		return "Bus [id=" + id + ", departure=" + departure + ", arrival=" + arrival + ", departureTime="
				+ departureTime + ", arrivalTime=" + arrivalTime + ", departureDate=" + departureDate + ", arrivalDate="
				+ arrivalDate + "]";
	}	
 }
