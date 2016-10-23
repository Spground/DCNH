package dcnh.dbservice;

import lombok.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dcnh.mapper.JudgementDBMapper;
import dcnh.mapper.ProjectDBMapper;
import dcnh.mode.Judgement;

@Data
@Service
public class JudgeDBServer {
	
	@Autowired
	private JudgementDBMapper judgementDBMapper;
	
	@Autowired
	private ProjectDBMapper projectDBMapper;
	
	@Transactional
	public void addJudgement(String userName,int projectId,int score){
		Judgement judgement = judgementDBMapper.getJudgement(userName, projectId);
		
		//如果没有记录则添加该条记录
		if(judgement ==null){
			judgement = new Judgement();
			judgement.setJudgeName(userName);
			judgement.setProjectId(projectId);
			judgement.setScore(score);
			judgementDBMapper.addNewJudgement(judgement);
			int newScore = new Double(judgementDBMapper.getAvgScore(projectId)).intValue();
			projectDBMapper.updateScore(projectId, newScore);
		}
	}
}
