package dcnh.mapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import dcnh.ApplicationContext;
import dcnh.model.Project;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ApplicationContext.class)
public class ProjectDBMapperTest {
	@Autowired
	ProjectDBMapper mapper;

	@Test
	public void testGetInnovationProjectByProjectId() {
		Project project = mapper.getInnovationProjectByProjectId(12);
		assert (project != null);
		System.out.println(project);
		Map<String, Object> where = new HashMap<>();
		where.put("schoolName", "大连理工大学");
		where.put("projectStatus", "1");
		List<Project> projects = mapper.getAllInnovationProjects(where);
		assert (projects != null);
		System.err.println(projects);
	}

	@Test
	public void testAddProject() {
		Project project = new Project();
		project.setMainCategory("主分类");
		project.setSubCategory("子分类");
		project.setParticipators(new ArrayList<>(Arrays.asList(new String[] { "A", "B", "C" })));
		project.setProjectStatus(0);
		project.setSchoolName("大连海事大学");
		project.setTitle("机器人巡逻");
		project.setUploader(1);// admin
		project.setUploadTime((long) (System.currentTimeMillis() * 1.0 / 1000));
		project.setYear(2017);
		System.out.println(project.getParticipators());

		int r = mapper.addProject(project);

		assert (r == 1);
		System.out.println(project);
	}

	@Test
	public void testGetAllInnovationProjectsOrderBySchoolName() {
		List<Project> projects = mapper.getAllInnovationProjectsOrderBySchoolName();
		assert (projects != null);
		System.err.println(projects);
	}

	@Test
	public void testUpdateScore() {
		boolean ok = mapper.updateProjectScore(12, 90);
		assert (ok == true);
		System.out.println(mapper.getInnovationProjectByProjectId(12));
	}

	@Test
	public void testGetTopInnovationProjectsByMainCategory() {
		List<Project> projects = mapper.getTopInnovationProjectsByMainCategory("主分类1", 3);
		assert (projects != null);
		System.err.println(projects);
	}

	@Test
	public void testGetInnovationProjectsCountByMainCategory() {
		int count = mapper.getInnovationProjectsCountByMainCategory("主分类1");
		assert (count != 0);
		System.out.println(count);
	}

	@Test
	public void testDeleteInnovationProjectByProjectId() {
		int r = mapper.deleteInnovationProjectByProjectId(12);
		assert (r == 1);
	}

}
