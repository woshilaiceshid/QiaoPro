package wx.prj.funcs.menu;

import org.apache.log4j.Logger;

import wx.constant.WXAccount;
import wx.http.util.HttpUtil;
import wx.prj.funcs.basic.BasicFunc;

public class MenuUtil extends BasicFunc{
	Logger log = Logger.getLogger(MenuUtil.class);
	
	/**
	 * 删除公众号当前所有菜单
	 */
	public void delAllMenu(){
		String access_token = getAccessToken();
		String url = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=" + access_token;
		String response = "";
		try {
			response = HttpUtil.get(url);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (response.equals("{\"errcode\":0,\"errmsg\":\"ok\"}")) {
			log.info("删除菜单成功！");
		}
	}
	
	
}
