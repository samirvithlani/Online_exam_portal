package com.controller.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bean.student.StudentBean;
import com.dao.student.StudentDao;

@RestController
@RequestMapping("/student")
public class StudentController {
	@Autowired
	StudentDao studentDao;
	@PostMapping(value="/addstudent")
	public ResponseEntity addStudent(StudentBean studentBean)
	{
		int status=studentDao.addStudent(studentBean);
		if(status>0)
		{
			return new ResponseEntity(studentBean,HttpStatus.CREATED);
		}
		return new ResponseEntity(HttpStatus.SERVICE_UNAVAILABLE);
	}
	
	

}
