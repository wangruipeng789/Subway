import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Hashtable;


public class TimeSpace {
     static SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
	 public static void main(String[] args){
    	 TimeSpace ts=new TimeSpace();
    	 //ts.process();//计算每小时的记录的数量
    	 //ts.process1();//计算所有人数
    	 //ts.process2();//计算每小时有多少人数
    	 
    	 
     }
	private void process2() {
		int[] hours=new int[24];//统计每小时有多少人
		try {
			BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream("D:\\数据汇总\\一卡通乘客刷卡数据1\\SPTCC-20150401\\sorted.csv"),"utf-8"));
			int currHour=0;
			int hour;
			String[] recValues;
			String   currID="";
			String   cardID;
			for(String line=br.readLine();line!=null;line=br.readLine()){
				recValues=line.split(",");
				cardID=recValues[0];
				hour=sdf.parse(recValues[1]).getHours();
				if((hour==currHour)&&(cardID.equals(currID))){
					continue;//同一个人在相同时段不进行叠加计算
				}
				currHour=hour;
				currID  =cardID;
				hours[hour]++;
			}
			br.close();
			for(int i=0;i<24;i++){
				System.out.println("第"+i+"小时,"+hours[i]+",人");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	private void process1() {
		HashSet<String> hs=new HashSet<String>();
		String cardID="";
		try {
			BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream("D:\\数据汇总\\一卡通乘客刷卡数据1\\SPTCC-20150401\\sorted.csv"),"utf-8"));
			String[] recValues;
			for(String line=br.readLine();line!=null;line=br.readLine()){
				recValues=line.split(",");
				hs.add(recValues[0]);
				//System.out.println(recValues[2]);
			}
			br.close();
			System.out.println("第一天总共有："+hs.size()+"人");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void process() {
		int[] hours=new int[24];//统计每小时有多少数据
		try {
			BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream("D:\\数据汇总\\一卡通乘客刷卡数据1\\SPTCC-20150401\\sorted.csv"),"utf-8"));
			int hour;
			String[] recValues;
			for(String line=br.readLine();line!=null;line=br.readLine()){
				recValues=line.split(",");
				//System.out.println(recValues[2]);
				hour=sdf.parse(recValues[1]).getHours();
				hours[hour]++;
			}
			br.close();
			for(int i=0;i<24;i++){
				System.out.println("第"+i+"小时,"+hours[i]);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}

