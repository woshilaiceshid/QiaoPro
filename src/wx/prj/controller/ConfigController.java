package wx.prj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import wx.prj.funcs.msg.WXRecMsgUtil;

@Controller
public class ConfigController extends WXRecMsgUtil{
	
	/**
	 * 跳转到微信配置页面
	 */
	@RequestMapping(value = "/config.do")
	public String toConfig(){
		System.out.println("进入了！");
		return "index";
	}
	
}
