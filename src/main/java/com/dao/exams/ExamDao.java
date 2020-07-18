package com.dao.exams;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.bean.exams.ExamBean;

@Repository
public class ExamDao 
{
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public int createExam(ExamBean examBean)
	{
		return jdbcTemplate.update("insert into exams(eid,esubject,etitle,totalmarks,dateofcreation,dateofconduct,duration,"
				+ "estatus,fid) values(?,?,?,?,?,?,?,?,?)",examBean.geteId(),examBean.geteSubject(),examBean.geteTitle(),
				examBean.getTotalMarks(),examBean.getDateOfCreation(),examBean.getDateOfConduct(),examBean.getDuration(),
				examBean.geteStatus(),examBean.getfId());
	}
	
	public final static class ExamMapper implements RowMapper<ExamBean>
	{
		@Override
		public ExamBean mapRow(ResultSet rs, int rowNum) throws SQLException 
		{
			ExamBean examBean = new ExamBean();
			examBean.seteId(rs.getString("eid"));
			examBean.seteSubject(rs.getString("esubject"));
			examBean.seteTitle(rs.getString("etitle"));
			examBean.setTotalMarks(rs.getInt("totalmarks"));
			examBean.setDateOfCreation(rs.getDate("dateofcreatrion"));
			examBean.setDateOfConduct(rs.getDate("dateofconduct"));
			examBean.setDuration(rs.getString("duration"));
			examBean.seteStatus(rs.getString("estatus"));
			examBean.setfId(rs.getString("fid"));
			return examBean;
		}
	}
	
	public List<ExamBean> examList()
	{
		return jdbcTemplate.query("select * from exams", new ExamMapper());
	}
	
	public int deleteExam(String id) 
	{
		return jdbcTemplate.update("delete from exams where eid =?", id);
	}
	
	public int updateExam(ExamBean examBean,String id)
	{
		return jdbcTemplate.update("update exams set esubject=?, etitle=?, totalmarks=?, dateofcreation=?, dateofconduct=?, "
				+ "duration=?, estatus=?, fid=? where eid=?",examBean.geteSubject(),examBean.geteTitle(),
				examBean.getTotalMarks(),examBean.getDateOfCreation(),examBean.getDateOfConduct(),examBean.getDuration(),
				examBean.geteStatus(),examBean.getfId(),id);
	}
}
