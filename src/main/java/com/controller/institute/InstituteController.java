package com.controller.institute;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bean.institute.InstituteBean;
import com.dao.institute.InstituteDao;

@RestController
@RequestMapping("/institute")
public class InstituteController 
{
	@Autowired
	InstituteDao instituteDao;
	
	@PostMapping(value = "/adduinstitute")
	public ResponseEntity addInstitute(InstituteBean instituteBean) 
	{	
		int status = instituteDao.addInstitute(instituteBean);
		if (status > 0) {
			
			return new ResponseEntity(instituteBean,HttpStatus.CREATED);
		}
		
		return new ResponseEntity(HttpStatus.SERVICE_UNAVAILABLE);
	}
	
	@GetMapping(value = "/viewinstitutes")
	public List<InstituteBean> viewInstitutes() 
	{

		return instituteDao.instituteList();
	}
}
