package models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Seat {
	int seatNo;
	int busId;
	double price;
	char status;
	
	public Seat(int seatNo, int busId, double price, String status) {
		this.seatNo = seatNo;
		this.busId = busId;
		this.price = price;
		this.status = status.charAt(0);
	}
	
	public Seat(ResultSet result) throws SQLException {
		this.seatNo = result.getInt(1);
		this.busId = result.getInt(2);
		this.price = result.getDouble(3);
		this.status = result.getString(4).charAt(0);;
	}

	@Override
	public String toString() {
		return "Seat [seatNo=" + seatNo + ", busId=" + busId + ", price=" + price + ", status=" + status + "]";
	}
}
