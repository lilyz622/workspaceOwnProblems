
public class movieSeat {
	public static void main(String[] args){
	
	}
	
	public static double optimalDistance(double distance, double screenSize, double screenFromFloor, double incline){
		double Rincline = Math.toRadians(incline);
		double angle = Math.atan((screenSize - (distance*Math.tan(Rincline)))/distance) + Math.atan((distance*Math.tan(Rincline))/ distance);
		return Math.toDegrees(angle);
	}

}
