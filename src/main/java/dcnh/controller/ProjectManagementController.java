package dcnh.controller;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import dcnh.global.SessionKey;
import dcnh.global.UserPermission;
import dcnh.handler.AttachementHandler;
import dcnh.handler.ProjectManageHandler;
import dcnh.model.Expert;
import dcnh.model.Project;
import dcnh.model.ResponseMessage;
import dcnh.model.User;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ProjectManagementController {

	@Autowired
	private AttachementHandler attachmentHandler;

	@Autowired
	private ProjectManageHandler projectManageHandler;

	@Autowired
	private AttachementHandler attachementHandler;

	@RequestMapping("/dcnh/getcategory")
	@ResponseBody
	public Map<String, List<String>> getCategory() {
		Map<String, List<String>> result = projectManageHandler.getAllCategory();
		log.debug("/dcnh/getcategory");
		return result;
	}

	@RequestMapping("/dcnh/uploadattachement")
	@ResponseBody
	public ResponseMessage getAttachement(@RequestParam("attachement_file") MultipartFile file) throws IOException {
		ResponseMessage response = new ResponseMessage();
		String fileName = file.getOriginalFilename();
		String id = attachementHandler.uploadFile(file, fileName);
		response.setMessage(id);
		return response;
	}

	/*
	 * 下载附件
	 */
	@RequestMapping(value = "/dcnh/getattachement/{attachementId}")
	public void getAttachement(@PathVariable String attachementId, HttpServletResponse response) {
		attachmentHandler.getFile( response, attachementId);
	}

	/*
	 * 获取该用户可以访问的大创项目
	 */
	@RequestMapping("/dcnh/getprojectlist")
	@ResponseBody
	public List<Project> getProjectList(@RequestParam int kind, HttpSession session) {
		User user = (User) session.getAttribute(SessionKey.USERNAME.name());
		if (user == null) {
			System.out.println(" user is null");
			return new LinkedList<Project>();
		}
		if (user.getPermission().equals(UserPermission.SCHOOLADMIN))
			return projectManageHandler.getAllProjectsBySchoolName(user.getSchoolName());
		if (user.getPermission().equals(UserPermission.EXPERT)) {
			System.out.println(" user is judge ");
			return projectManageHandler.getAllAssignedProjectsOfExpert(new Expert(user), kind);
		}
		if (user.getPermission().equals(UserPermission.SUPERADMIN)) {
			return projectManageHandler.getAllProjects();
		}
		return new LinkedList<Project>();
	}
}
