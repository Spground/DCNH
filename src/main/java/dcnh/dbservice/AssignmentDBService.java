package dcnh.dbservice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dcnh.mapper.ProjectDBMapper;
import dcnh.mapper.ProjectExpertAssignmentDBMapper;
import dcnh.model.Assignment;
import dcnh.model.Expert;
import dcnh.model.Project;

@Service
public class AssignmentDBService {

	@Autowired
	private ProjectExpertAssignmentDBMapper assignmentDBMapper;

	@Autowired
	private ProjectDBMapper projectDBMapper;
	/**
	 * 增加一个项目分配
	 * @param assignment
	 */
	@Transactional
	public boolean addNewAssignment(Assignment assignment) {
		int r = assignmentDBMapper.addAssignment(assignment);
		return r == 1;
	}
	
	/**
	 * @param assignment
	 * @return
	 */
	public boolean updateAssignment(Assignment assignment) {
		int r = assignmentDBMapper.updateAssignment(assignment);
		if(r == 1) {
			int avgScore = new Double(assignmentDBMapper.getInnovationProjectAvgScore(assignment.getProject().getProjectId()))
				.intValue();
			projectDBMapper.updateProjectScore(assignment.getProject().getProjectId(), avgScore);
			return true;
		}
		return false;
	}
	
	/*
	 * 获取给某位专家分配的所有项目
	 */
	public List<Project> getAllAssignedProjectsOfExpert(Expert expert) {
		List<Assignment> assignments = assignmentDBMapper.getAssignmentsByExpertId(expert.getUserId());
		List<Project> projects = new ArrayList<>();
		for(Assignment assignment : assignments)
			projects.add(assignment.getProject());
		return projects;
	}
	
	/*
	 * 获取给某位专家分配的所有打分完毕的项目
	 */
	public List<Project> getAllFinishedAssignedProjectsOfExpert(Expert expert) {
		List<Assignment> assignments = assignmentDBMapper.getFinishedAssignmentsByExpertId(expert.getUserId());
		List<Project> projects = new ArrayList<>();
		for(Assignment assignment : assignments)
			projects.add(assignment.getProject());
		return projects;
	}
	
	/*
	 * 获取给某位专家分配的所有未打分的项目
	 */
	public List<Project> getAllUnFinishedAssignedProjectsOfExpert(Expert expert) {
		List<Assignment> assignments = assignmentDBMapper.getUnFinishedAssignmentsByExpertId(expert.getUserId());
		List<Project> projects = new ArrayList<>();
		for(Assignment assignment : assignments)
			projects.add(assignment.getProject());
		return projects;
	}
}
