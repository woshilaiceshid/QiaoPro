package wx.prj.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class JdbcBaseDao extends JdbcDaoSupport {
	
	public List<Object> query(String sql){
		return this.getJdbcTemplate().queryForList(sql, Object.class);
	}
}
