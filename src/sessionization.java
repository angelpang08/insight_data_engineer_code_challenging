import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class sessionization {
	
	public static void main(String[] args) throws IOException{
		
		Access_Record a = new Access_Record();
		
		if(args.length!=3){
			System.out.println("arguments of file paths have a problem");
			return;
		}
		
		
		
		String curren_path = System.getProperty("user.dir");
		String log_path= curren_path+"/"+args[0];
		String inactive_per_path=curren_path+"/"+args[1];
		String output_path=curren_path+"/"+args[2];
		
		int period = 0;
		
		/*
		System.out.println("log: "+log_path);
		System.out.println("inactive period: "+inactive_per_path);
		System.out.println("output: "+output_path);
		*/
		
		
		File outputFile=new File(output_path);
		if(outputFile.exists()==false){
			outputFile.createNewFile();
		}else {
			System.out.println("note: output path exists");
		}
		
		String st;
		
		File input_file = new File(inactive_per_path);
		BufferedReader br = new BufferedReader(new FileReader(input_file));
		if ((st = br.readLine()) != null){
			//period = Integer.parseInt(st);
			System.out.println(st);
		}
		if ((st = br.readLine()) != null){
			System.out.println("note more than one line in inactivity_period.txt");
			br.close();
			return;
		}
		br.close();
		
		input_file = new File(log_path);
		br = new BufferedReader(new FileReader(input_file));
		if ((st = br.readLine()) != null){
			//period = Integer.parseInt(st);
			System.out.println(st);
		}
		while ((st = br.readLine()) != null){
			//System.out.println("more line: "+st);
		
		
		
		}
		br.close();
		
		
		
		
		

		
	}

}
