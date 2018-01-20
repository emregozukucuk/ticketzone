package models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Reservation {
	int reservNo;
	int busId;
	int seatNo;
	String customer;
	
	public Reservation(int reservNo, int busId, int seatNo, String customer) {
		this.reservNo = reservNo;
		this.busId = busId;
		this.seatNo = seatNo;
		this.customer = customer;
	}
	
	public Reservation(ResultSet result) throws SQLException {
		this.reservNo = result.getInt(1);
		this.busId = result.getInt(2);
		this.seatNo = result.getInt(3);
		this.customer = result.getString(4);
	}

	@Override
	public String toString() {
		return "Reservation [reservNo=" + reservNo + ", busId=" + busId + ", seatNo=" + seatNo + ", customer="
				+ customer + "]";
	}
}
