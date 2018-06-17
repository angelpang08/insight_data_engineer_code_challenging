import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Access_Data {
	private String ip;
	
	private long dt_first;
	private long dt_last;
	
	private String cik;
	private String accession;
	private String extention;
	
	private int nums;
	
	public Access_Data(String ip,String date,String time,String cik,String accession,String extention){
		this.ip =ip;
		this.cik=cik;
		this.accession=accession;
		this.extention=accession;
	
		SimpleDateFormat sdf = new SimpleDateFormat ("yyyy-MM-ddhh:mm:ss a zzz");
		try {
			Date dt = sdf.parse(date+time);
			dt_first= dt_last = dt.getTime();
		} catch (ParseException e) {
			System.out.println("wrong date or time format, exit");
			System.exit(-1);
		}
		
		this.nums=1;
		
	}

	public boolean isExpire(long period, long current_dt){
		
		return sessionization.isExpire(this.getDateTime() ,period, current_dt);
	}
	
	public void active (Access_Data new_access){
		this.dt_last = new_access.dt_last;
		nums++;
	}
	
	public 	long duration (){
		return 0;
	}
	
	public String printSession(){
		return "";
	}
	
	public String getIp(){
		return ip;
	}
	
	public long getDateTime(){
		return dt_last;
	}
	
	

}
