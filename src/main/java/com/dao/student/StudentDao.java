package com.dao.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bean.student.StudentBean;

@Repository
public class StudentDao {
	@Autowired 
	JdbcTemplate jdbcTemplate;
	public int addStudent(StudentBean studentBean)
	{
		return jdbcTemplate.update("insert into student(sid,fname,lname,smail,scontact,uname,upassword,iid)"+" values(?,?,?,?,?,?,?,?)",studentBean.getsId(),studentBean.getfName()
				,studentBean.getlName(),studentBean.getsMail(),studentBean.getsContact(),studentBean.getuName(),studentBean.getuPassword(),studentBean.getiId());
	}
}
