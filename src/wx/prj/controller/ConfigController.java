package wx.prj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import wx.prj.funcs.msg.WXRecMsgUtil;

@Controller
public class ConfigController extends WXRecMsgUtil{
	
	/**
	 * ��ת��΢������ҳ��
	 */
	@RequestMapping(value = "/config.do")
	public String toConfig(){
		System.out.println("�����ˣ�");
		return "index";
	}
	
}
