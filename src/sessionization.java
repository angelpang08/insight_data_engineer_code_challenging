import java.io.File;
import java.io.IOException;


public class sessionization {
	
	public static void main(String[] args){
		
		if(args.length!=3){
			System.out.println("arguments of file paths have problem");
			return;
		}
		
		
		String curren_path = System.getProperty("user.dir");
		String log_path= curren_path+"/"+args[0];
		String inactive_per_path=curren_path+"/"+args[1];
		String output_path=curren_path+"/"+args[2];
		
		System.out.println("log: "+log_path);
		System.out.println("inactive period: "+inactive_per_path);
		System.out.println("output: "+output_path);
		
		
		
		
		
		File outputFile=new File(output_path);
		try{
			//if(outputFile.exists()==false){
				outputFile.createNewFile();
		//	}
		} catch (IOException e) {
			System.out.println("note: output path has a problem");
		}

		
	}

}
