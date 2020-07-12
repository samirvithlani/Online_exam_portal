package com.dao.institute;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bean.institute.InstituteBean;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

@Repository
public class InstituteDao 
{
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public int addInstitute(InstituteBean instituteBean) 
	{	
		return jdbcTemplate.update("insert into institute(iid,iname,icontact,imail,icity,istate)"
				+ " values(?,?,?,?,?,?)",instituteBean.getiId(),
				instituteBean.getiName(),instituteBean.getiContact(),instituteBean.getiMail(),
				instituteBean.getiCity(),instituteBean.getiState());
	}
	
	private final static class InstituteMapper implements RowMapper<InstituteBean> 
	{
		@Override
		public InstituteBean mapRow(ResultSet rs, int rowNum) throws SQLException 
		{
			InstituteBean instituteBean = new InstituteBean();
			instituteBean.setiId(rs.getString("iid"));
			instituteBean.setiName(rs.getString("iname"));
			instituteBean.setiContact(rs.getLong("icontact"));
			instituteBean.setiMail(rs.getString("imail"));
			instituteBean.setiCity(rs.getString("icity"));
			instituteBean.setiState(rs.getString("istate"));
			return instituteBean;
		}
	}
	
	public List<InstituteBean> instituteList() 
	{
		return jdbcTemplate.query("select * from institute", new InstituteMapper());
	}

	public int deleteInstitute(String id) 
	{
		return jdbcTemplate.update("delete from institute where iid =?", id);
	}
	
	public int updateInstitute(InstituteBean instituteBean, String id)
	{
		return jdbcTemplate.update("update institute set iname=?, icontact=?, imail=?, icity=?, istate=? where iid =?",
				instituteBean.getiName(),instituteBean.getiContact(),instituteBean.getiMail(),
				instituteBean.getiCity(),instituteBean.getiState(),id);
	}
}
