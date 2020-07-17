package com.controller.counselor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bean.counselor.CounselorBean;
import com.dao.counselor.CounselorDao;

@RestController
@RequestMapping("/counselor")
@CrossOrigin
public class CounselorController 
{
	@Autowired
	CounselorDao counselorDao;
	
	@PostMapping(value = "/addcounselor")
	public ResponseEntity addCounselor(@RequestBody CounselorBean counselorBean) 
	{	
		int status = counselorDao.addCounselor(counselorBean);
		if (status > 0) {
			
			return new ResponseEntity(counselorBean,HttpStatus.CREATED);
		}
		
		return new ResponseEntity(HttpStatus.SERVICE_UNAVAILABLE);
	}
	
	@GetMapping(value = "/viewcounselors")
	public List<CounselorBean> viewCounselors() 
	{
		return counselorDao.counselorList();
	}
	
	@DeleteMapping("/deletecounselor/{id}")
	public String deleteCounselor(@PathVariable String id) 
	{
		int status = counselorDao.deleteCounselor(id);
		if (status > 0) {
			return "Counselor Deleted !!";
		}

		return "Counselor not Deleted !!";
	}
	
	@PostMapping(value = "/updatecounselor/{id}")
	public CounselorBean updateCounselor(CounselorBean counselorBean, @PathVariable String id) 
	{
		int status = counselorDao.updateCounselor(counselorBean, id);
		if (status > 0) {
			return counselorBean;
		}
		
		return null;
	}
}
