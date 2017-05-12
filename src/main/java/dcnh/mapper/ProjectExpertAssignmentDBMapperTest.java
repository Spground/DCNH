package dcnh.mapper;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import dcnh.ApplicationContext;
import dcnh.model.Assignment;
import dcnh.model.Expert;
import dcnh.model.Project;

/**
 * @author WuJie
 * @date 2017年5月10日下午5:32:30
 * @version 1.0
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ApplicationContext.class)
public class ProjectExpertAssignmentDBMapperTest {
	@Autowired
	ProjectExpertAssignmentDBMapper mapper;
	@Autowired
	UserDBMapper userMapper;
	@Autowired
	ProjectDBMapper projectMapper;

	@Test
	public void testGetAssignmentByExpertIdAndProjectId() {
		Assignment assignment = mapper.getAssignmentByExpertIdAndProjectId(4, 13);
		System.out.println(assignment);
	}

	@Test
	public void testAddAssignment() {
		Assignment assignment = new Assignment();
		Expert expert = userMapper.getExpertByExpertId(4);
		Project project = projectMapper.getInnovationProjectByProjectId(14);
		System.out.println(expert);
		System.out.println(project);
		assignment.setExpert(expert);
		assignment.setProject(project);
		int r = mapper.addAssignment(assignment);
		assert (r == 1);
		System.out.println(assignment);
	}

	@Test
	public void testGetInnovationProjectAvgScore() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateAssignment() {
		Assignment assignment = mapper.getAssignmentByExpertIdAndProjectId(4, 13);
		System.out.println(assignment);
		assignment.setScore(95);
		assignment.setFinished(1);
		int r = mapper.updateAssignment(assignment);
		assert (r == 1);
		System.out.println(mapper.getAssignmentByExpertIdAndProjectId(4, 13));
	}

	@Test
	public void testTruncateProjectExpertAssignmentTable() {
		fail("Not yet implemented");
	}
}
