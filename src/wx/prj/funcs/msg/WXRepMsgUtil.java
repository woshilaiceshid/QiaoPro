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
		
		if (event.equals("subscribe") && msg.getTicket() == null) {//��ע
			System.out.println("��ע΢�ź�");
		}else if (event.equals("unsubscribe") && msg.getTicket() == null) {//ȡ��
			System.out.println("ȡ����ע");
		}else if (event.equals("LOCATION")) {//�ϱ�����λ���¼�
			System.out.println("�ϱ�����λ���¼�");
		}else if (event.equals("CLICK")) {//����˵���ȡ��Ϣʱ���¼�����
			System.out.println("�Զ���˵��¼�");
		}else if (event.equals("VIEW")) {//����˵���ת����ʱ���¼�����
			System.out.println("����˵���ת����ʱ���¼�����");
		}else if (event.equals("subscribe") && msg.getTicket() != null) {//ɨ���������ά���¼�,�û�δ��עʱ�����й�ע����¼�����
			System.out.println("ɨ���ά���ע΢�ź�");
		}else if (event.equals("SCAN") && msg.getTicket() != null) {//ɨ���������ά���¼�,�û��ѹ�עʱ���¼�����
			System.out.println("ɨ���ά���ѹ�ע΢�ź�");
		}else {
			System.out.println("δʶ����¼���");
		}
	}


	public void replyLink(MSG msg, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}


	public void replyLocation(MSG msg, HttpServletResponse response) {
		// TODO Auto-generated method stub
		System.out.println("����λ����Ϣ");
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
		logger.info("�����ı���Ϣ==========");
		String content = String.format(textXml, msg.getFromUserName(),
				msg.getToUserName(), System.currentTimeMillis()/1000, "����Ӧ��");
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
