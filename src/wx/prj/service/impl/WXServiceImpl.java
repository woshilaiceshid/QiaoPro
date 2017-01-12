package wx.prj.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wx.prj.dao.WXDao;
import wx.prj.service.WXService;

@Service(value="wxService")
public class WXServiceImpl implements WXService {

	@Resource(name="wxDaoImpl")
	private WXDao wxDao;
	
	@Override
	public List<Object> test() {
		// TODO Auto-generated method stub
		return wxDao.test();
	}

}
