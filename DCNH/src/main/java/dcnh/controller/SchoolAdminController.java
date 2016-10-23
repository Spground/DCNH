package dcnh.controller;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import dcnh.handler.ProjectManageHandler;

@Controller
@Slf4j
public class SchoolAdminController {
	@Autowired
	private ProjectManageHandler projectManageHandler;
	
	
	
}
