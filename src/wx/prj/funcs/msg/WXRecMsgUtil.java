package wx.prj.funcs.msg;

import javax.servlet.http.HttpServletResponse;

import wx.bean.MSG;

public class WXRecMsgUtil {
	static WXRepMsgUtil rpmUtil = null;
	static {
		rpmUtil = new WXRepMsgUtil();
	}
	/**
	 * ����΢�ŷ��������͵�xml���ݣ�����Ӧ 
	 * @param msg
	 */
	public void recieveMsg(MSG msg, HttpServletResponse response){
		String msgType = msg.getMsgType();
		switch (msgType) {
		case "text":
			rpmUtil.replyText(msg, response);
			break;
		case "image":
			rpmUtil.replyText(msg, response);
			break;
		case "voice":
			rpmUtil.replyText(msg, response);
			break;
		case "video":
			rpmUtil.replyText(msg, response);
			break;
		case "shortvideo":
			rpmUtil.replyText(msg, response);
			break;
		case "location":
			rpmUtil.replyText(msg, response);
			break;
		case "link":
			rpmUtil.replyText(msg, response);
			break;
		case "event":
			rpmUtil.replyEvent(msg, response);
			break;

		default:
			break;
		}
	}

	/**
	 * ��Ӧ΢�����͵��¼�
	 * @param msg
	 * @return
	 */
	public String replyEvent(MSG msg) {
		String event = msg.getEvent();
		String url = "";
		
		if (event.equals("subscribe") && msg.getTicket() == null) {//��ע
			url = "";
			System.out.println("��ע΢�ź�");
		}else if (event.equals("unsubscribe") && msg.getTicket() == null) {//ȡ��
			System.out.println("ȡ����ע");
		}else if (event.equals("LOCATION")) {//�ϱ�����λ���¼�
			url = "/msgSend/text.do";
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
		
		return url;
	}
	
	
}
