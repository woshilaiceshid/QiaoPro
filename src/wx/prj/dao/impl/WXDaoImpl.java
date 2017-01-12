package wx.prj.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import wx.prj.dao.WXDao;

public class WXDaoImpl extends JdbcBaseDao implements WXDao{
	
	public List<Object> test(){
		String sql = "select * from wx_accountinfo";
		List<Object> list = this.query(sql);
		System.out.println("11111111111111");
		return null;
	}
}
