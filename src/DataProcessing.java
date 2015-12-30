

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

public class DataProcessing {
	 static SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	 Hashtable<Long, User>   users=new Hashtable<Long, User>();
     public static void main(String[] args){
    	 DataProcessing dp=new DataProcessing();
    	 dp.process();
     }
	 private void process() {
	 	 try {
			BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream("D:\\数据汇总\\一卡通乘客刷卡数据1\\SPTCC-20150401\\SPTCC-20150401.csv")));
			String[]  recValues;
			long      cardID;
			//long      currID=0;//目前正在整理的ID
			long      dateTime;
			String    station;
			String    type;
			double    price;    
			UserActivity ua;
			User         u;
			for(String line=br.readLine();line!=null;line=br.readLine()){
				recValues=line.split(",");
                cardID=Long.valueOf(recValues[1]);
                dateTime=sdf.parse(recValues[1]+" "+recValues[2]).getTime();
                station =recValues[3];
                type    =recValues[4];
                price   =Double.valueOf(recValues[5]);
                ua=new UserActivity(cardID, dateTime, station, type, price);
                if(users.get(cardID)!=null){//字典中有了
                	users.get(cardID).acts.add(ua);
                }else{//字典中还没有
                	u=new User(cardID);
                	users.put(cardID,u);
                	users.get(cardID).acts.add(ua);
                }
			}
			br.close();
			sortAndExport();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} 
	 }
	 private void sortAndExport() {
         Enumeration<Long> ids=this.users.keys(); 
         long      cardID;
	     long      dateTime;
		 String    station;
		 String    type;
		 double    price;    
		 UserActivity ua;
	     User         u;
	     ArrayList<UserActivity> uas=new ArrayList<UserActivity>();
		 try {
			 while(ids.hasMoreElements()){
				 BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("D:\\数据汇总\\一卡通乘客刷卡数据1\\SPTCC-20150401\\sorted.csv"),true),"utf-8"));
	        	 cardID=ids.nextElement();
	        	 uas=users.get(cardID).getSortedActs();
	        	 for(int i=0,len=uas.size();i<len;i++){
	        		 //System.out.println(len);
	        		 ua=uas.get(i);
	        		 cardID=ua.cardID;
	        		 dateTime=ua.dateTime;
	        		 station =ua.station;
	        		 type    =ua.type;
	        		 price   =ua.price;
	        		 bw.append(cardID+","+sdf.format(dateTime)+","+station+","+type+","+price+"\r\n");
	        		 bw.flush();
	        	 }
	        	 bw.close();
	        }
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	 }
}
