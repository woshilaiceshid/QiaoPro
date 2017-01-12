package wx.prj.funcs.msg;

import javax.servlet.http.HttpServletResponse;

import wx.bean.MSG;

public class WXRecMsgUtil {
	static WXRepMsgUtil rpmUtil = null;
	static {
		rpmUtil = new WXRepMsgUtil();
	}
	/**
	 * 接受微信服务器发送的xml数据，并响应 
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
	 * 相应微信推送的事件
	 * @param msg
	 * @return
	 */
	public String replyEvent(MSG msg) {
		String event = msg.getEvent();
		String url = "";
		
		if (event.equals("subscribe") && msg.getTicket() == null) {//关注
			url = "";
			System.out.println("关注微信号");
		}else if (event.equals("unsubscribe") && msg.getTicket() == null) {//取消
			System.out.println("取消关注");
		}else if (event.equals("LOCATION")) {//上报地理位置事件
			url = "/msgSend/text.do";
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
		
		return url;
	}
	
	
}
