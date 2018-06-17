import java.util.LinkedList;

public class Time_Link {
	static LinkedList<Ip_Time> lst;
	
	public Time_Link(){
		lst= new LinkedList<Ip_Time>();
	}
	
	public void add(String ip, long dt){
		lst.addLast(new Ip_Time(ip, dt));
	}
	
	public String remove(){
		return lst.removeFirst().getIp();
	}
	
	public boolean isEmpty(){
		return lst.isEmpty();
	}
	
	public String getFirst(){
		return lst.getFirst().getIp();
	}
	
	public static long getFirstDateTime(){
		return lst.getFirst().getDateTime();
	}

}
class Ip_Time{
	private String ip;
	private long dt;
	
	public Ip_Time(String ip, long dt){
		this.ip=ip;
		this.dt =dt;
	}
	
	public String getIp(){
		return ip;
	}
	public long getDateTime(){
		return dt;	
	}
	
}
