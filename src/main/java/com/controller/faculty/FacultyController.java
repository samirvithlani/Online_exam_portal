package com.controller.faculty;

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

import com.bean.faculty.FacultyBean;
import com.dao.faculty.FacultyDao;

@RestController
@CrossOrigin
@RequestMapping("/faculty")
public class FacultyController
{
	@Autowired
	FacultyDao facultyDao;
	
	@PostMapping(value = "/addfaculty")
	public ResponseEntity addFaculty(FacultyBean facultyBean)
	{
		int status = facultyDao.addFaculty(facultyBean);
		if (status > 0) {
			
			return new ResponseEntity(facultyBean,HttpStatus.CREATED);
		}
		
		return new ResponseEntity(HttpStatus.SERVICE_UNAVAILABLE);
	}
	
	@GetMapping(value = "/viewfaculties")
	public List<FacultyBean> viewFaculties() 
	{
		return facultyDao.facultyList();
	}
	
	@DeleteMapping("/deletefaculty/{id}")
	public String deleteFaculty(@PathVariable String id) 
	{
		int status = facultyDao.deleteFaculty(id);
		if (status > 0) {
			return "Faculty Deleted !!";
		}

		return "Faculty not Deleted !!";
	}
	
	@PostMapping(value = "/updatefaculty/{id}")
	public FacultyBean updateFaculty(FacultyBean facultyBean, @PathVariable String id) 
	{
		int status = facultyDao.updateFaculty(facultyBean, id);
		if (status > 0) {
			return facultyBean;
		}
		
		return null;
	}
	
}
