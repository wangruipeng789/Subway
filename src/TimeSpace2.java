import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;


public class TimeSpace2 {
	Hashtable<String, String> stations=new Hashtable<>();
    public static void main(String[] args) {
    	TimeSpace2 ts2=new TimeSpace2();
    	ts2.putHash();//����վ����ӳ��
    	//ts2.processException();
    	ts2.splitBus();
	}
	private void splitBus() {
		try {
			BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream("D:\\���ݻ���\\һ��ͨ�˿�ˢ������1\\SPTCC-20150401\\sorted.csv"),"utf-8"));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(new FileOutputStream("D:\\���ݻ���\\һ��ͨ�˿�ˢ������1\\SPTCC-20150401\\����\\������������.csv"),"utf-8"));
			String[] recValues;
			String type;
			for(String line=br.readLine();line!=null;line=br.readLine()){
				recValues=line.split(",");
				type=recValues[3];
				if(type.equals("����")){
					bw.append(line+"\r\n");
				}
			}
			br.close();
			bw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void processException() {
		HashSet<String>  excepts=new HashSet<String>();
		String   station;
		String[] recValues;
 		try {
			BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream("D:\\���ݻ���\\һ��ͨ�˿�ˢ������1\\SPTCC-20150401\\�쳣վ��.csv")));
			for(String line=br.readLine();line!=null;line=br.readLine()){
				recValues=line.split(",");
				station=recValues[0];
				excepts.add(station);
			}
			br.close();
			Iterator<String> iters=excepts.iterator();
			while(iters.hasNext()){
				String name=iters.next();
				System.out.println(name);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void putHash() {
		try {
			BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream("D:\\���ݻ���\\һ��ͨ�˿�ˢ������1\\SPTCC-20150401\\��վ.csv")));
			//BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(new FileOutputStream("D:\\���ݻ���\\һ��ͨ�˿�ˢ������1\\SPTCC-20150401\\��վ_hash.csv")));
			br.readLine();//ȥ����ͷ
			String name;
			String lineNum;
			String loc;
			String[] recValues;
			for(String line=br.readLine();line!=null;line=br.readLine()){
				recValues=line.split(",");
				name=recValues[1].trim();
				lineNum=recValues[3].trim();
				loc=recValues[4]+","+recValues[5];
				stations.put((lineNum+"����"+name).trim(), loc);
				//bw.append(lineNum+"����"+name+","+loc+"\r\n");
			}
			br.close();
			//bw.close();
		} catch (UnsupportedEncodingException | FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
