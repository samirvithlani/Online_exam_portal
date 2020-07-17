package com.dao.faculty;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.bean.faculty.FacultyBean;

@Repository
public class FacultyDao 
{
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public int addFaculty(FacultyBean facultyBean)
	{
		return jdbcTemplate.update("insert into faculty(fid,fname,lname,fmail,fcontact,uname,upassword,fqualification,fdegree,"
				+ "fbio,ytlink,iid) values(?,?,?,?,?,?,?,?,?,?,?,?)",facultyBean.getfId(),facultyBean.getfName(),
				facultyBean.getlName(),facultyBean.getfMail(),facultyBean.getfContact(),facultyBean.getuName(),
				facultyBean.getuPassword(),facultyBean.getfQualification(),facultyBean.getfDegree(),facultyBean.getfBio(),
				facultyBean.getYtLink(),facultyBean.getiId());
	}
	
	public final static class FacultyMapper implements RowMapper<FacultyBean>
	{
		@Override
		public FacultyBean mapRow(ResultSet rs, int rowNum) throws SQLException 
		{
			FacultyBean facultyBean = new FacultyBean();
			facultyBean.setfId(rs.getString("fid"));
			facultyBean.setfName(rs.getString("fname"));
			facultyBean.setlName(rs.getString("lname"));
			facultyBean.setfMail(rs.getString("fmail"));
			facultyBean.setfContact(rs.getLong("fcontact"));
			facultyBean.setuName(rs.getString("uname"));
			facultyBean.setuPassword(rs.getString("upassword"));
			facultyBean.setfQualification(rs.getString("fqualification"));
			facultyBean.setfDegree(rs.getString("fdegree"));
			facultyBean.setfBio(rs.getString("fbio"));
			facultyBean.setYtLink(rs.getString("ytlink"));
			facultyBean.setiId(rs.getString("iid"));
			return facultyBean;
		}
	}
	
	public List<FacultyBean> facultyList() 
	{
		return jdbcTemplate.query("select * from faculty", new FacultyMapper());
	}
	
	public int deleteFaculty(String id) 
	{
		return jdbcTemplate.update("delete from faculty where fid =?", id);
	}
	
	public int updateFaculty(FacultyBean facultyBean, String id)
	{
		return jdbcTemplate.update("update faculty set fname=?, lname=?, fmail=?, fcontact=?, uname=?, upassword=?, "
				+ "fqualification, fdegree, fbio, ytlink, iid=? where fid =?",facultyBean.getfName(),facultyBean.getlName(),
				facultyBean.getfMail(),facultyBean.getfContact(),facultyBean.getuName(),facultyBean.getuPassword(),
				facultyBean.getfQualification(),facultyBean.getfDegree(),facultyBean.getfBio(),facultyBean.getYtLink(),
				facultyBean.getiId(),id);
	}
}
