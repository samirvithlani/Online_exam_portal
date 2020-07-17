package com.dao.admin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.bean.admin.AdminBean;

@Repository
public class AdminDao 
{
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public int addAdmin(AdminBean adminBean) 
	{
		return jdbcTemplate.update("insert into administrator(aid,fname,lname,amail,acontact,uname,upassword,iid "
				+ "values(?,?,?,?,?,?,?,?)",adminBean.getaId(),adminBean.getfName(),adminBean.getlName(),adminBean.getaMail(),
				adminBean.getaContact(),adminBean.getuName(),adminBean.getuPassword(),adminBean.getiId());
	}
	
	public final static class AdminMapper implements RowMapper<AdminBean> 
	{
		@Override
		public AdminBean mapRow(ResultSet rs, int rowNum) throws SQLException 
		{
			AdminBean adminBean = new AdminBean();
			adminBean.setaId(rs.getString("aid"));
			adminBean.setfName(rs.getString("fname"));
			adminBean.setlName(rs.getString("lname"));
			adminBean.setaContact(rs.getLong("acontact"));
			adminBean.setaMail(rs.getString("amail"));
			adminBean.setuName(rs.getString("uname"));
			adminBean.setuPassword(rs.getString("upassword"));
			adminBean.setiId(rs.getString("iid"));
			return adminBean;
		}
	}
	
	public List<AdminBean> adminList() 
	{
		return jdbcTemplate.query("select * from administrator", new AdminMapper());
	}
	
	public int deleteAdmin(String id) 
	{
		return jdbcTemplate.update("delete from administrator where aid =?", id);
	}
	
	public int updateAdmin(AdminBean adminBean, String id)
	{
		return jdbcTemplate.update("update administrator set fname=?, lname=?, amail=?, acontact=?, uname=?, upassword=?, iid=?"
				+ " where aid =?",adminBean.getfName(),adminBean.getlName(),adminBean.getaMail(),adminBean.getaContact(),
				adminBean.getuName(),adminBean.getuPassword(),adminBean.getiId(),id);
	}
}
