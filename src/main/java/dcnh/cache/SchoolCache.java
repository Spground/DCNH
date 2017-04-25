package dcnh.cache;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dcnh.mapper.SchoolDBMapper;

@Component
public class SchoolCache implements InitializingBean {

	@Autowired
	private SchoolDBMapper schoolMapper;

	private HashSet<String> schoolNameSet = new HashSet<String>();

	public Set<String> getAllSchool() {
		return schoolNameSet;
	}

	public boolean containsSchool(String schoolName) {
		return schoolNameSet.contains(schoolName);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		List<String> list = schoolMapper.getAllSchool();
		schoolNameSet.addAll(list);
	}
}
