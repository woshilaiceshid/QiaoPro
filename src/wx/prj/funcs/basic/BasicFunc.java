package wx.prj.funcs.basic;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import wx.constant.WXAccount;
import wx.http.util.HttpUtil;

public class BasicFunc {
	
	/**
	 * 获取接口调用凭证
	 * @param appid		
	 * @param appSecret
	 * @return
	 */
	public String getAccessToken(){
		String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + WXAccount.appid + "&secret=" + WXAccount.appSecret;
		String response = "";
		try {
			response = HttpUtil.get(url);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String result = "";
		if (response != "") {//处理返回的json数据
			Pattern pattern = Pattern.compile("\"access_token\":\"(.*?)\",");
			Matcher matcher = pattern.matcher(response);
			while(matcher.find()){
				result = matcher.group(1);
			}
		}
		return result;
	}
}
