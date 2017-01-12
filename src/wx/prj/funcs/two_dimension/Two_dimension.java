package wx.prj.funcs.two_dimension;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import wx.http.util.HttpUtil;
import wx.prj.funcs.basic.BasicFunc;

public class Two_dimension extends BasicFunc{
	
	/**
	 * 创建临时二维码ticket
	 * @param expire		有效时间
	 * @param scene_id		
	 * @return
	 */
	public String getQR_SCENE_Ticket(Long expire, int scene_id){
		String access_token = getAccessToken();
		String url = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=" + access_token;
		String paramJson  = "{\"expire_seconds\": " + expire + ", \"action_name\": \"QR_SCENE\", \"action_info\": {\"scene\": {\"scene_id\": " + scene_id +"}}}";
		
		String response = "";
		try {
			response = HttpUtil.post(url, paramJson);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String ticket = "";
		if (response != "" && response != null) {
			Pattern pattern = Pattern.compile("\"ticket\":\"(.*?)\",");
			Matcher matcher = pattern.matcher(response);
			while (matcher.find()) {
				ticket = matcher.group(1);
			}
		}
		return ticket;
	}
	
	public String getQR_LIMIT_SCENE_Ticket(Object object){
		String access_token = getAccessToken();
		String url = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=" + access_token;
		String paramJson = "";
		if (object instanceof String) {
			paramJson = "{\"action_name\": \"QR_LIMIT_SCENE\", \"action_info\": {\"scene\": {\"scene_id\": \"" + object + "\"}}}";
		}else {
			paramJson = "{\"action_name\": \"QR_LIMIT_SCENE\", \"action_info\": {\"scene\": {\"scene_id\": " + object + "}}}";
		}
		String response = "";
		try {
			response = HttpUtil.post(url, paramJson);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String ticket = "";
		if (response != "" && response != null) {
			Pattern pattern = Pattern.compile("\"ticket\":\"(.*?)\",");
			Matcher matcher = pattern.matcher(response);
			while (matcher.find()) {
				ticket = matcher.group(1);
			}
		}
		return ticket;
	}
	
	/**
	 * 通过ticket换取二维码
	 * @param ticket
	 * @return
	 */
	public String getTDPic(String ticket){
		try {
			ticket = URLEncoder.encode(ticket, "utf-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String url ="https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=" + ticket;
		String response = ""; 
		try {
			response = HttpUtil.get(url);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}
	
	public static void main(String[] args) {
		String ticket = new Two_dimension().getQR_LIMIT_SCENE_Ticket("你好，我来了");
		String response = new Two_dimension().getTDPic(ticket);
	}
}
