package dcnh.cache;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dcnh.mapper.SchoolDBMapper;
import dcnh.model.School;

@Component
public class SchoolCache implements InitializingBean {

	@Autowired
	private SchoolDBMapper schoolMapper;

	private HashSet<String> schoolNameSet = new HashSet<String>();
	
	private Map<Integer, School> schoolIdObjMap = new HashMap<>();
	
	private Map<String, School> schoolNameObjMap = new HashMap<>();
	
	public Set<String> getAllSchool() {
		return schoolNameSet;
	}
	
	public Map<Integer, School> getAllSchoolIdObjMap() {
		return schoolIdObjMap;
	}

	public boolean containsSchool(String schoolName) {
		return schoolNameSet.contains(schoolName);
	}
	
	public String querySchoolNameBySchoolId(int id) {
		School school = schoolIdObjMap.get(id);
		return school != null ? school.getSchoolName() : "null"; 
	}
	
	public int querySchoolIdBySchoolName(String schoolName) {
		School school = schoolNameObjMap.get(schoolName);
		return school != null ? school.getSchoolId() : -1; 
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		List<String> list = schoolMapper.getAllSchool();
		schoolIdObjMap = schoolMapper.getAllSchoolObjectMappedBySchoolId();
		schoolNameObjMap = schoolMapper.getAllSchoolObjectMappedBySchoolName();
		System.out.println(schoolIdObjMap);
		schoolNameSet.addAll(list);
	}
}
