package wx.prj.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/msgSend")
public class MsgSendController {
	//文本消息模板
	String textXml = "<xml>"
			+ "<ToUserName><![CDATA[%s]]></ToUserName>"
			+ "<FromUserName><![CDATA[%s]]></FromUserName>"
			+ "<CreateTime>%d</CreateTime>"
			+ "<MsgType><![CDATA[text]]></MsgType>"
			+ "<Content><![CDATA[%s]]></Content>"
			+ "</xml>";
	
	
	/**
	 * 恢复文本消息给微信服务器
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/text.do")
	public void sendText(HttpServletRequest request, HttpServletResponse response) {
		String content = String.format(textXml, 
				"oTZqOtz1vdzRso1CIAPIMjE_8x3Y", "gh_53de325d820e", 
				System.currentTimeMillis()/1000, "测试应答");
		PrintWriter writer = null;
		try {
			response.setContentType("text/plain; charset=utf-8");
			writer = response.getWriter();
			writer.print(content);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			writer.close();
		}
	}
	
}
