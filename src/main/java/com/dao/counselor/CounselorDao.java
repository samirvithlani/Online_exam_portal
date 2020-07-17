package com.dao.counselor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.bean.counselor.CounselorBean;

@Repository
public class CounselorDao 
{
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public int addCounselor(CounselorBean counselorBean) 
	{	
		return jdbcTemplate.update("insert into counselor(cid,fname,lname,cmail,ccontact,uname,upassword,cbio,linkedin,iid)"
				+ " values(?,?,?,?,?,?,?,?,?,?)",counselorBean.getcId(),counselorBean.getfName(),counselorBean.getlName(),
				counselorBean.getcMail(),counselorBean.getcContact(),counselorBean.getuName(),counselorBean.getuPassword(),
				counselorBean.getcBio(),counselorBean.getLinkedIn(),counselorBean.getiId());
	}
	
	public final static class CounselorMapper implements RowMapper<CounselorBean>
	{
		@Override
		public CounselorBean mapRow(ResultSet rs, int rowNum) throws SQLException 
		{
			CounselorBean counselorBean = new CounselorBean();
			counselorBean.setcId(rs.getString("cid"));
			counselorBean.setfName(rs.getString("fname"));
			counselorBean.setlName(rs.getString("lname"));
			counselorBean.setcMail(rs.getString("cmail"));
			counselorBean.setcContact(rs.getLong("ccontact"));
			counselorBean.setuName(rs.getString("uname"));
			counselorBean.setuPassword(rs.getString("upassword"));
			counselorBean.setcBio(rs.getString("cbio"));
			counselorBean.setLinkedIn(rs.getString("linkedin"));
			counselorBean.setiId(rs.getString("iid"));
			return counselorBean;
		}
	}
	
	public List<CounselorBean> counselorList() 
	{
		return jdbcTemplate.query("select * from counselor", new CounselorMapper());
	}

	public int deleteCounselor(String id) 
	{
		return jdbcTemplate.update("delete from counselor where cid =?", id);
	}
	
	public int updateCounselor(CounselorBean counselorBean,String id)
	{
		return jdbcTemplate.update("update counselor set fname=?, lname=?, cmail=?,ccontact=?,uname=?,upassword=?,cbio=?,"
				+ "linkedin=?,iid=? where cid=?",counselorBean.getfName(),counselorBean.getlName(),counselorBean.getcMail(),
				counselorBean.getcContact(),counselorBean.getuName(),counselorBean.getuPassword(),counselorBean.getcBio(),
				counselorBean.getLinkedIn(),counselorBean.getiId(),id);
	}
}
