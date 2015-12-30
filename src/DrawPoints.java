import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.sun.jmx.snmp.Enumerated;


public class DrawPoints extends JFrame{
    MyIpanel myPanel;	
	public DrawPoints() {
	    setTitle("地铁点");
	    setVisible(true);
	    setSize(1600,1080);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    myPanel=new MyIpanel();
        getContentPane().add(myPanel);
	}
    public static void main(String[] args) {
    	DrawPoints dp=new DrawPoints();
	} 
}
class MyIpanel extends JPanel{
	Hashtable<String, String> stationTable=new Hashtable<>();//
	public void paint(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)(g);
		//super.paint(g);
		//g.drawLine(0, 0, 100, 100);
		g2.fillOval(30, 30, 5, 5);
		TimeSpace2 ts2=new TimeSpace2();
		ts2.putHash();
		stationTable=ts2.stations;
		Hashtable<String,Integer>  stationValue=new Hashtable<String,Integer>();//在每个站点处有多少人 
		try {
			BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream("D:\\数据汇总\\一卡通乘客刷卡数据1\\SPTCC-20150401\\sorted.csv"),"utf-8"));
			String type;
			String station;
			String loc;
			String[] recValues;
			int sum=0;
			for(String line=br.readLine();line!=null;line=br.readLine()){
				recValues=line.split(",");
				//System.out.println(recValues.length);
				type=recValues[3];
				if(type.equals("地铁")){
					station=recValues[2];
					Integer count=stationValue.get(station);
					if(count==null){
						count=0;
					}
					stationValue.put(station, ++count);
					sum++;
				}
			}
			br.close();
			System.out.println(sum);
			Enumeration<String> stations=stationValue.keys();
			while(stations.hasMoreElements()){
				String  stationTemp=stations.nextElement();
				int  count=stationValue.get(stationTemp);
				loc=stationTable.get(stationTemp);
				try{
					if(!loc.isEmpty()&&!loc.equals("")&&loc!=null){
						int x=Integer.valueOf(loc.split(",")[0]);
						int y=Integer.valueOf(loc.split(",")[1]);
						g2.setColor(Color.blue);
						g2.fillOval(x, y,count/2000,count/2000);//每个站点数量缩小2000倍
						//System.out.println("x:"+x+"y:"+y);
					}else{
						continue;
					}
				}catch (Exception e) {
					System.out.println(stationTemp);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}