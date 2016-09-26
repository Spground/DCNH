package dcnh.globalInfo;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import lombok.Data;

@Data
public class CategoryCache {
	private final ConcurrentHashMap<String,List<String>> categoryCache = new ConcurrentHashMap<String,List<String>>();
	
	public void refersh(){
		
	}
	
 }
