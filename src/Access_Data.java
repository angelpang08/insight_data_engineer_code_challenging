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
        
        
		//System.out.println("date: "+date);
		//System.out.println("time: "+time);
		
		SimpleDateFormat sdf = new SimpleDateFormat ("yyyy-MM-ddHH:mm:ss");
		try {
			Date dt = sdf.parse(date+time);
			dt_first= dt_last = dt.getTime();
            System.out.println(dt_last);
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
		String separator = ",";
		StringBuilder sb = new StringBuilder("");
		
		sb.append(getIp());
		sb.append(separator);
		System.out.println(dt_last);
		SimpleDateFormat sdf = new SimpleDateFormat ("yyyy-MM-dd','HH:mm:ss");
		
		sb.append(sdf.format(new Date(dt_first)));
		sb.append(separator);
		sb.append(sdf.format(new Date(dt_last)));
		sb.append(separator);
		
		sb.append(duration());
		sb.append(separator);
		
		sb.append(nums);
		sb.append("\n");
		
		return sb.toString();
	}
	
	public String getIp(){
		return ip;
	}
	
	public long getDateTime(){
		return dt_last;
	}
	
	

}
