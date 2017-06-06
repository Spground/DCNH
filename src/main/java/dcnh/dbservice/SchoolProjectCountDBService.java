package dcnh.dbservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dcnh.mapper.SchoolProjectCountDBMapper;

/**
 * @author WuJie
 * @date 2017年5月10日下午9:19:07
 * @version 1.0
 **/
@Service
public class SchoolProjectCountDBService {

	@Autowired
	private SchoolProjectCountDBMapper schoolProjectCountDBMapper;

	/*
	 * 保存某个类别下分配了多少项目名额给学校
	 */
	public boolean saveSchoolProjectCountInfo(int schoolId, int maincategoryId, int projectCount) {
		int result = schoolProjectCountDBMapper.insertSchoolProjectCountItem(schoolId, maincategoryId, projectCount);
		return result > 0 ? true : false;
	}

	/*
	 * 获取学校已经上传的项目的个数
	 */
	public int getUploadedProjectCount(int schoolId, int maincategoryId) {
		try {
			return schoolProjectCountDBMapper.getUploadedProjectCount(schoolId, maincategoryId);
		} catch (Exception e) {
			return 0;
		}
	}

	/*
	 * 获取该类别给学校分配的项目的个数
	 */
	public int getLimitProjectCount(int schoolId, int mainCategoryId) {
		try {
			return schoolProjectCountDBMapper.getLimitProjectCount(schoolId, mainCategoryId);
		} catch (Exception e) {
			return 0;
		}
	}

	/*
	 * 更新用户上传项目数
	 */
	public int updateUploadedCount(int schoolId, int maincategoryId, int newUploadedCount) {
		try {
			return schoolProjectCountDBMapper.updateUploadCount(schoolId, maincategoryId, newUploadedCount);
		} catch (Exception e) {
			return 0;
		}
	}
}
