package wx.prj.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import wx.prj.service.WXService;

@Controller
@RequestMapping(value="/weixin")
public class WeixinController {

	@Resource(name="wxService")
	private WXService wxService;
	
	/**
	 * ���ù��ں���Ϣ
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/save.do")
	public void saveConfig(HttpServletRequest request, HttpServletResponse response){
		wxService.test();
	}
}
