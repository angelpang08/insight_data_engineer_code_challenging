import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class sessionization {
	final private static int log_pos=0;
	final private static int inactive_pos=1;
	final private static int output_pos=2;
	final private static int args_num=3;
	
	private static int ip_pos;
	private static int date_pos;
	private static int time_pos;
	private static int cik_pos;
	private static int accession_pos;
	private static int extention_pos;
	private static int fields_num=6;
	
	private static long period=0;

	private static BufferedWriter writer;
	
	public static void main(String[] args) throws IOException{

		// args parse, get files path
		if(args.length!=args_num){
			System.out.println("arguments of file paths have a problem");
			return;
		}
		String curren_path = System.getProperty("user.dir");
		String log_path= curren_path+"/"+args[log_pos];
		String inactive_per_path=curren_path+"/"+args[inactive_pos];
		String output_path=curren_path+"/"+args[output_pos];
			
		/*
		System.out.println("log: "+log_path);
		System.out.println("inactive period: "+inactive_per_path);
		System.out.println("output: "+output_path);
		*/
		
		// initial output file
		File outputFile=new File(output_path);
		if(outputFile.exists()==false){
			outputFile.createNewFile();
		}else {
			System.out.println("note: output path exists");
		}
		writer = new BufferedWriter(new FileWriter(outputFile));
		
	    
		String st;
		// get inactive period (which is seconds)
		File input_file = new File(inactive_per_path);
		BufferedReader reader = new BufferedReader(new FileReader(input_file));
		if ((st = reader.readLine()) != null){
			period = Integer.parseInt(st)*1000L;
			//System.out.println(st);
		}
		if ((st = reader.readLine()) != null){
			System.out.println("note more than one line in inactivity_period.txt");
			reader.close();
			return;
		}
		reader.close();
		//get log.csv
		input_file = new File(log_path);
		reader = new BufferedReader(new FileReader(input_file));
		if ((st = reader.readLine()) != null){
			init_pos(st);
		}else{
			System.out.println("empty in log.txt");
			reader.close();
			return;
		}
		
		Time_Link time_link= new Time_Link();
		Access_Record access_record = new Access_Record();
		long current_dt = -1;
		
		//read and write synchronize
		while ((st = reader.readLine()) != null){
			Access_Data new_access=parse(st);
            
            String ip= new_access.getIp();
            
			if(current_dt != new_access.getDateTime()){
				current_dt = new_access.getDateTime();
				update(current_dt, time_link, access_record);
			}
            if(access_record.containsKey(ip) ){
					Access_Data exist_data = access_record.remove(ip);
					exist_data.active(new_access);
                    access_record.put(ip, exist_data);
            }else{
                access_record.put(ip, new_access);
            }
            time_link.add(ip, current_dt);

		}
		reader.close();
		//log_end(time_link,access_record);
        log_end(access_record);
        writer.close();
		
	}
	

	
	
	private static void init_pos( String st ){
		String[] fields= st.split(",");
		int get=0;
		for(int i=0; i<fields.length && get<fields_num;i++){
           
			switch (fields[i].toLowerCase()){
	            case "ip":  
	            	ip_pos=i;
	            	get++;
	            	break;    	
	            case "date":  
	            	date_pos = i;
	            	get++;
	            	break;
	            case "time":  
	            	time_pos=i;
	            	get++;
	            	break;
	            case "cik":  
	            	cik_pos=i;
	            	get++;
	            	break;
	            case "accession":  
	            	accession_pos=i;
	            	get++;
	            	break;
	            case "extention":  
	            	extention_pos=i;
	            	get++;
	            	break;
	            default: 
	            	break;
			}
		}	
		if (get!=fields_num){
			System.out.println("unvaild log.csv information");
			System.exit(-1);
		}
        
	}
	
	
	private static Access_Data parse( String st ){
		String[] fields= st.split(",");
		return new Access_Data(
				fields[ip_pos],
				fields[date_pos],
				fields[time_pos],
				fields[cik_pos],
				fields[accession_pos],
				fields[extention_pos] );	
	}
	
	private static void writeoutput(Access_Data exist_data) {
			try {
				writer.write(exist_data.printSession());
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	private static void update(long current_dt, Time_Link time_link, Access_Record access_record){
		while( !time_link.isEmpty() && isExpire(Time_Link.getFirstDateTime(), period, current_dt)){
			String ip = time_link.remove();
            
			if(access_record.containsKey(ip) ){
				Access_Data exist_data = access_record.get(ip);
				if(exist_data.isExpire(period, current_dt)){
					access_record.remove(ip);
					writeoutput(exist_data);
				}
			}
		}
		
	}

	private static void log_end(Time_Link time_link, Access_Record access_record){		
		while(!time_link.isEmpty()){
			long add_time = time_link.getFirstDateTime();
			String ip = time_link.remove();
			
			if(access_record.containsKey(ip) ){
				Access_Data exist_data = access_record.get(ip);
                System.out.println(ip);
                System.out.println(add_time);
                System.out.println(exist_data.getDateTime());
                System.out.println();
				if(exist_data.getDateTime() == add_time ){
                   
					access_record.remove(ip);
					writeoutput(exist_data);
				}
			}
		}	
	}
    private static void log_end(Access_Record access_record){
        Access_Data[] left_access= aceess_record.sort();
        for(int i=0; i<left_access.length;i++){
            writeoutput(left_access[i]);
        }
        
        
        
    }



	/**
	 * Identiry if the access session has expired;
	 * the session is over when the end of the file is reached 
	 * or after a period of inactivity has elapsed with no requests from that user
	 * 
	 * For example, 
	 * if the inactivity period is 2 seconds, 
	 * and the session start is 00:00:01 
	 * and there are no further requests from that user by 00:00:04, 
	 * then the session is considered over at 00:00:01.
	 * 
	 * 
	 * @param  dt the date and time to be identify whether expired
	 * @param  period is the period how long inactive session expire 
	 * @param  current_dt the current date and time of current reading line 
	 * @return true if expired;
	 *         false if not expired.
	 * 
	 */
	public static boolean isExpire(long dt, long prd,long current_dt){
		return dt+prd<current_dt;
	}


}
