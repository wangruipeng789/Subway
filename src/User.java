

import java.util.ArrayList;
import java.util.Collections;

public class User implements Comparable{
    long  cardID;
    public User(long cardID) {
		super();
		this.cardID = cardID;
	}
	ArrayList<UserActivity> acts=new ArrayList<UserActivity>();
	public ArrayList<UserActivity> getSortedActs(){
		Collections.sort(acts);
		return  acts;
	}
	public void addAct(UserActivity u){
		this.acts.add(u);
	}
	public void clear(){
		this.acts.clear();
	}
    @Override
	public int compareTo(Object o) {
    	if(this.cardID>((User)o).cardID){
    		return 1;
    	}else if(this.cardID<((User)o).cardID){
    		return -1;
    	}else{
    		return 0;
    	}
	}
}
