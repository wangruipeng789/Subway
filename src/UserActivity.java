

public class UserActivity implements Comparable{
    long     cardID;
    long     dateTime;
    String   station;
    String   type;
    double   price;
	public UserActivity(long cardID, long dateTime, String station,String type, double price) {
		super();
		this.cardID = cardID;
		this.dateTime = dateTime;
		this.station = station;
		this.type = type;
		this.price = price;
	}
	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		if(this.dateTime>((UserActivity)o).dateTime){
			return 1;
		}else if(this.dateTime<((UserActivity)o).dateTime){
			return -1;
		}else{
			return 0;
		}
	}
	
}
