package wx.prj.funcs.msg;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletWebRequest;

import wx.bean.MSG;

public class WXRepMsgUtil {
	Logger logger = Logger.getLogger(WXRepMsgUtil.class);
	String textXml = "<xml>"
			+ "<ToUserName><![CDATA[%s]]></ToUserName>"
			+ "<FromUserName><![CDATA[%s]]></FromUserName>"
			+ "<CreateTime>%d</CreateTime>"
			+ "<MsgType><![CDATA[text]]></MsgType>"
			+ "<Content><![CDATA[%s]]></Content>"
			+ "</xml>";
	
	public void replyEvent(MSG msg, HttpServletResponse response) {
		String event = msg.getEvent();
		
		if (event.equals("subscribe") && msg.getTicket() == null) {//关注
			System.out.println("关注微信号");
		}else if (event.equals("unsubscribe") && msg.getTicket() == null) {//取消
			System.out.println("取消关注");
		}else if (event.equals("LOCATION")) {//上报地理位置事件
			System.out.println("上报地理位置事件");
		}else if (event.equals("CLICK")) {//点击菜单拉取消息时的事件推送
			System.out.println("自定义菜单事件");
		}else if (event.equals("VIEW")) {//点击菜单跳转链接时的事件推送
			System.out.println("点击菜单跳转链接时的事件推送");
		}else if (event.equals("subscribe") && msg.getTicket() != null) {//扫描带参数二维码事件,用户未关注时，进行关注后的事件推送
			System.out.println("扫描二维码关注微信号");
		}else if (event.equals("SCAN") && msg.getTicket() != null) {//扫描带参数二维码事件,用户已关注时的事件推送
			System.out.println("扫描二维码已关注微信号");
		}else {
			System.out.println("未识别的事件！");
		}
	}


	public void replyLink(MSG msg, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}


	public void replyLocation(MSG msg, HttpServletResponse response) {
		// TODO Auto-generated method stub
		System.out.println("地理位置消息");
	}


	public void replySV(MSG msg, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}


	public void replyVideo(MSG msg, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}


	public void replyVoice(MSG msg, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}


	public void replyImg(MSG msg, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}


	public void replyText(MSG msg, HttpServletResponse response) {
		logger.info("返回文本信息==========");
		String content = String.format(textXml, msg.getFromUserName(),
				msg.getToUserName(), System.currentTimeMillis()/1000, "测试应答");
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
