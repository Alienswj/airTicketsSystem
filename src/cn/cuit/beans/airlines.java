package cn.cuit.beans;

public class airlines {
	private String airId,airLine,departure,destination,depTime,landTime,price;

	public airlines(String airId, String airLine, String departure, String destination, String depTime, String landTime,
			String price) {
		super();
		this.airId = airId;
		this.airLine = airLine;
		this.departure = departure;
		this.destination = destination;
		this.depTime = depTime;
		this.landTime = landTime;
		this.price = price;
	}

	public airlines() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getAirId() {
		return airId;
	}

	public void setAirId(String airId) {
		this.airId = airId;
	}

	public String getAirLine() {
		return airLine;
	}

	public void setAirLine(String airLine) {
		this.airLine = airLine;
	}

	public String getDeparture() {
		return departure;
	}

	public void setDeparture(String departure) {
		this.departure = departure;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getDepTime() {
		return depTime;
	}

	public void setDepTime(String depTime) {
		this.depTime = depTime;
	}

	public String getLandTime() {
		return landTime;
	}

	public void setLandTime(String landTime) {
		this.landTime = landTime;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "airlines [airId=" + airId + ", airLine=" + airLine + ", departure=" + departure + ", destination="
				+ destination + ", depTime=" + depTime + ", landTime=" + landTime + ", price=" + price + "]";
	}
	
}
