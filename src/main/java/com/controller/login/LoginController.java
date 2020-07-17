package com.controller.login;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bean.admin.AdminBean;
import com.bean.counselor.CounselorBean;
import com.bean.faculty.FacultyBean;
import com.bean.student.StudentBean;
import com.dao.login.LoginDao;

@RestController
@CrossOrigin
public class LoginController 
{
	@Autowired
	LoginDao loginDao;
	
	@PostMapping("/login/{email}/{password}")
	public Object Login(@PathVariable("email") String email, @PathVariable("password") String password) 
	{
		Map<String, Object> loginMap = new HashMap<String, Object>();
		loginMap = loginDao.login(email, password);
		
		for (Map.Entry m : loginMap.entrySet()) 
		{
			String key = (String)m.getKey();

			if (key.equals("student"))
			{
				List<StudentBean> studentBean = (List<StudentBean>) m.getValue();
				return studentBean;
			}
			
			else if (key.equals("counselor"))
			{
				List<CounselorBean> counselorBean = (List<CounselorBean>) m.getValue();
				return counselorBean;
			}
			
			else if (key.equals("admin"))
			{
				List<AdminBean> adminBean = (List<AdminBean>) m.getValue();
				return adminBean;
			}
			
			else if (key.equals("faculty"))
			{
				List<FacultyBean> facultyBean = (List<FacultyBean>) m.getValue();
				return facultyBean;
			}
		}
		
		return "pls check email or password...";
	}
}
