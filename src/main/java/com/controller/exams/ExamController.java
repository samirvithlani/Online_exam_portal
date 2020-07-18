package com.controller.exams;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bean.exams.ExamBean;
import com.dao.exams.ExamDao;

@RestController
@CrossOrigin
@RequestMapping("exam")
public class ExamController 
{
	@Autowired
	ExamDao examDao;
	
	@PostMapping(value = "/createnewexam")
	public ResponseEntity createExam(ExamBean examBean)
	{
		int status = examDao.createExam(examBean);
		if (status > 0) {
			
			return new ResponseEntity(examBean,HttpStatus.CREATED);
		}
		
		return new ResponseEntity(HttpStatus.SERVICE_UNAVAILABLE);
	}
	
	@GetMapping(value = "/viewexams")
	public List<ExamBean> viewExams() 
	{
		return examDao.examList();
	}
	
	@DeleteMapping("/deleteexam/{id}")
	public String deleteExam(@PathVariable String id) 
	{
		int status = examDao.deleteExam(id);
		if (status > 0) {
			return "Exam Deleted !!";
		}

		return "Exam not Deleted !!";
	}
	
	@PostMapping(value = "/updateexam/{id}")
	public ExamBean updateFaculty(ExamBean examBean, @PathVariable String id) 
	{
		int status = examDao.updateExam(examBean, id);
		if (status > 0) {
			return examBean;
		}
		
		return null;
	}
}
