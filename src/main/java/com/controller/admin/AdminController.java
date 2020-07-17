package com.controller.admin;

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

import com.bean.admin.AdminBean;
import com.dao.admin.AdminDao;

@RestController
@CrossOrigin
@RequestMapping("/administrator")
public class AdminController 
{
	@Autowired
	AdminDao adminDao;
	
	@PostMapping(value = "/addadmin")
	public ResponseEntity addAdmin(AdminBean adminBean)
	{
		int status = adminDao.addAdmin(adminBean);
		if (status > 0) {
			
			return new ResponseEntity(adminBean,HttpStatus.CREATED);
		}
		
		return new ResponseEntity(HttpStatus.SERVICE_UNAVAILABLE);
	}
	
	@GetMapping(value = "/viewadmins")
	public List<AdminBean> viewAdmins() 
	{
		return adminDao.adminList();
	}
	
	
	@DeleteMapping("/deleteadmin/{id}")
	public String deleteAdmin(@PathVariable String id) 
	{
		int status = adminDao.deleteAdmin(id);
		if (status > 0) {
			return "Admin Deleted !!";
		}

		return "Admin not Deleted !!";
	}
	
	@PostMapping(value = "/updateadmin/{id}")
	public AdminBean updateAdmin(AdminBean adminBean, @PathVariable String id) 
	{
		int status = adminDao.updateAdmin(adminBean, id);
		if (status > 0) {
			return adminBean;
		}
		
		return null;
	}
	
}
