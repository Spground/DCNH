package dcnh.globalInfo;

import java.util.HashSet;
import java.util.Set;

public class SchoolCache {
	private HashSet<String> schoolNameSet = new HashSet<String>();
	
	public Set<String> getAllSchool(){
		return schoolNameSet;
	}
	
	public boolean containsSchool(String schoolName){
		return schoolNameSet.contains(schoolName);
	}
}
