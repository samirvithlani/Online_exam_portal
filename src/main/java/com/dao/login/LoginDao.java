package com.dao.login;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import com.dao.student.StudentDao.StudentMapper;
import com.dao.counselor.CounselorDao.CounselorMapper;
import com.dao.admin.AdminDao.AdminMapper;
import com.dao.faculty.FacultyDao.FacultyMapper;


@Service
public class LoginDao 
{
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public Map<String, Object> login(String email, String password)
	{
		Map<String, Object> loginmap = new HashMap<String, Object>();
		
		List res = jdbcTemplate.query("select * from student where smail='"+email+"' and upassword='"+password+"'", new StudentMapper());
		
		int rsize = res.size();
		if (rsize > 0) 
		{
			loginmap.put("student", res);
			return loginmap;
		}
		
		res = jdbcTemplate.query("select * from counselor where cmail='"+email+"' and upassword='"+password+"'", new CounselorMapper());
		rsize = res.size();
		if (rsize > 0) 
		{
			loginmap.put("counselor", res);
			return loginmap;	
		}
		
		res = jdbcTemplate.query("select * from administrator where amail='"+email+"' and upassword='"+password+"'", new AdminMapper());
		rsize = res.size();
		if (rsize > 0) 
		{
			loginmap.put("admin", res);
			return loginmap;	
		}
		
		res = jdbcTemplate.query("select * from faculty where fmail='"+email+"' and upassword='"+password+"'", new FacultyMapper());
		rsize = res.size();
		if (rsize > 0) 
		{
			loginmap.put("faculty", res);
			return loginmap;	
		}
		
		loginmap.put("404", "not found....");
		return loginmap;
	}

}
