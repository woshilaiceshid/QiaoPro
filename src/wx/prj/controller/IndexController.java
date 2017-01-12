package wx.prj.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import wx.bean.MSG;
import wx.constant.WXAccount;
import wx.http.util.XMLUtil;
import wx.prj.funcs.msg.WXRecMsgUtil;

@Controller
@RequestMapping(value = "/index")
public class IndexController extends WXRecMsgUtil{

	@RequestMapping(value = "/msg.do")
	public void msg(HttpServletRequest request, HttpServletResponse response) throws Exception{
		ServletInputStream sis = request.getInputStream();
		StringBuffer sb = new StringBuffer();
		int len = -1;
		byte[] buffer = new byte[1024];
		while ((len = sis.read(buffer)) != -1) {
			sb.append(new String(buffer, 0, len, "utf-8"));
		}
		String xml = sb.toString();
		if (xml.length() == 0) {//微信服务器如果没有发送xml形式的参数，则最有可能是在验证url
			check(request, response);
			return;
		}
		
		//将xml转换为对象
		Object object = XMLUtil.XML2Entity(xml, MSG.class);
		MSG msg = object == null ? null : (MSG)object;
		
		recieveMsg(msg, response);
	}
	
	/**
	 * 验证消息是否来自微信服务器
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/msg1.do")
	public void check(HttpServletRequest request, HttpServletResponse response) {
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");
		
		boolean isOK = checkSign(signature, timestamp, nonce);
		if (isOK) {
			PrintWriter writer = null;
			try {
				writer = response.getWriter();
				writer.print(echostr);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				writer.close();
			}
		}else {
			
		}

	}
	
	/**
	 * 检查签名是否一致
	 * @param signature
	 * @param timestamp
	 * @param nonce
	 * @return
	 */
	public boolean checkSign(String signature, String timestamp, String nonce){
		String [] arr = new String[]{WXAccount.token, timestamp, nonce};
		Arrays.sort(arr);
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < arr.length; i++) {
			sb.append(arr[i]);
		}
		MessageDigest md = null;
        String tmpStr = null;  
        
        try {
			md = MessageDigest.getInstance("SHA-1");
			byte[] b = md.digest(sb.toString().getBytes());
			tmpStr = byteToStr(b);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        sb = null;
		return tmpStr == null ? false : tmpStr.equals(signature.toUpperCase());
	}
	
	 /** 
     * 将字节数组转换为十六进制字符串 
     *  
     * @param byteArray 
     * @return 
     */  
    private static String byteToStr(byte[] byteArray) {  
        String strDigest = "";  
        for (int i = 0; i < byteArray.length; i++) {  
            strDigest += byteToHexStr(byteArray[i]);  
        }  
        return strDigest;  
    }  
  
    /** 
     * 将字节转换为十六进制字符串 
     *  
     * @param mByte 
     * @return 
     */  
    private static String byteToHexStr(byte mByte) {  
        char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };  
        char[] tempArr = new char[2];  
        tempArr[0] = Digit[(mByte >>> 4) & 0X0F];  
        tempArr[1] = Digit[mByte & 0X0F];  
  
        String s = new String(tempArr);  
        return s;  
    }  
}
