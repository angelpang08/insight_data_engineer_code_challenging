import java.util.HashMap;

public class Access_Record {
	HashMap<String, Access_Data> obj;

	public Access_Record(){
		obj= new HashMap<String, Access_Data>();
	}
	
	public Access_Data put(String key, Access_Data value){
		return obj.put(key, value);
	}
	
	public Access_Data remove (String key){
		return obj.remove(key);
	}
	
	public boolean containsKey(String key){
		return obj.containsKey(key);
	}
	
	public Access_Data get(String ip){
		return obj.get(ip);
	}

}

