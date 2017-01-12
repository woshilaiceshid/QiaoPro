package wx.prj.funcs;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;
import wx.bean.User;
import wx.bean.UserList;
import wx.constant.WXAccount;
import wx.http.util.HttpUtil;
import wx.prj.funcs.basic.BasicFunc;

public class UserUtil extends BasicFunc{
	
	/**
	 * 获取单个用户基本信息，返回json数据格式
	 * @param openid
	 * @return
	 */
	public User getPersonInfo(String openid) {
		String access_token = getAccessToken();
		String url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=" + access_token + "&openid=" + openid + "&lang=zh_CN";
		String response = "";
		try {
			response = HttpUtil.get(url);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		User user = null;
		if (response != "") {
			Object ob = JSONObject.toBean(JSONObject.fromObject(response), User.class);
			user = (User)ob;
		}
		return user;
	}
	
	/**
	 * 批量获取用户信息，返回json数据格式
	 * @return
	 */
	public List<User> getPersonsInfo(List<String> openidList){
		String access_token = getAccessToken();
		String url = "https://api.weixin.qq.com/cgi-bin/user/info/batchget?access_token=" + access_token;
		String response = "";
		StringBuffer paramJson = new StringBuffer("{\"user_list\":[");
		for (int i = 0; i < openidList.size(); i++) {
			paramJson.append("{\"openid\": \"").append(openidList.get(i)).append("\",\"lang\": \"zh-CN\"}");
			if (i != openidList.size() - 1) {
				paramJson.append(",");
			}
		}
		paramJson.append("]}");
		try {
			response = HttpUtil.post(url, paramJson.toString());
			paramJson = null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<User> userList = new ArrayList<>();
		if (response != "") {
			Map<String, Class> classMap = new HashMap<String, Class>();
			classMap.put("user_info_list", User.class);
			Object ob = JSONObject.toBean(JSONObject.fromObject(response), UserList.class, classMap);
			UserList ul = (UserList)ob;
			userList = ul.getUser_info_list();
		}
		return userList;
	}

	/**
	 * 获取关注公众号的用户列表(一次最多获取10000个)
	 * @return
	 */
	public String getUserList(){
		String access_token = getAccessToken();
		String url = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=" + access_token;
		String response = "";
		try {
			response = HttpUtil.get(url);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return response;
	}
	
	/**
	 * 获取关注公众号的用户列表(一次最多获取10000个),指定了第一个拉取的openid
	 * @return
	 */
	public String getUserList(String next_openid){
		String access_token = getAccessToken();
		String url = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=" + access_token + "&next_openid=" + next_openid;
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
		List<String> list = new ArrayList<>();
		list.add("oTZqOtz1vdzRso1CIAPIMjE_8x3Y");
		list.add("oTZqOt0iT9iKpa-CAuLSf0GE17YE");
		new UserUtil().getPersonsInfo(list);
	}
}
