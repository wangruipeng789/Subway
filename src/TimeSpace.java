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
    	 //ts.process();//����ÿСʱ�ļ�¼������
    	 //ts.process1();//������������
    	 //ts.process2();//����ÿСʱ�ж�������
    	 
    	 
     }
	private void process2() {
		int[] hours=new int[24];//ͳ��ÿСʱ�ж�����
		try {
			BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream("D:\\���ݻ���\\һ��ͨ�˿�ˢ������1\\SPTCC-20150401\\sorted.csv"),"utf-8"));
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
					continue;//ͬһ��������ͬʱ�β����е��Ӽ���
				}
				currHour=hour;
				currID  =cardID;
				hours[hour]++;
			}
			br.close();
			for(int i=0;i<24;i++){
				System.out.println("��"+i+"Сʱ,"+hours[i]+",��");
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
			BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream("D:\\���ݻ���\\һ��ͨ�˿�ˢ������1\\SPTCC-20150401\\sorted.csv"),"utf-8"));
			String[] recValues;
			for(String line=br.readLine();line!=null;line=br.readLine()){
				recValues=line.split(",");
				hs.add(recValues[0]);
				//System.out.println(recValues[2]);
			}
			br.close();
			System.out.println("��һ���ܹ��У�"+hs.size()+"��");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void process() {
		int[] hours=new int[24];//ͳ��ÿСʱ�ж�������
		try {
			BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream("D:\\���ݻ���\\һ��ͨ�˿�ˢ������1\\SPTCC-20150401\\sorted.csv"),"utf-8"));
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
				System.out.println("��"+i+"Сʱ,"+hours[i]);
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

