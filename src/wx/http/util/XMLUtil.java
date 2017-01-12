package wx.http.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import javax.swing.text.FieldView;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import wx.bean.MSG;
import wx.bean.ScanCodeInfo;

public class XMLUtil {
	
	/**
	 * 将xml数据转换为指定的类型对象
	 * @param xmlContent	XML数据
	 * @param ob			转换后的类型
	 * @return				转换后的数据对象
	 */
	@SuppressWarnings("all")
	public static Object XML2Entity(String xmlContent, Class<? extends Object> ob){
		// 声明JAXBContext 上下文对象
		JAXBContext context;
		Object result = null;
		try {
			// 通过指定映射的类创建上下文
			context = JAXBContext.newInstance(ob);
			// 通过上下文创建java转换xml对象
			Unmarshaller u = context.createUnmarshaller();
			Object object = u.unmarshal(new ByteArrayInputStream(xmlContent.getBytes("utf-8")));
			Constructor[] constructor = ob.getConstructors();
			Class<? extends Object> msg = Class.forName("wx.bean.MSG");
			//返回的实体类
			result = msg.newInstance();
			String name = object.getClass().getName();
			Field[] fields = object.getClass().getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {//循环取object的field值，并复制给result的相应属性
				String fieldName = fields[i].getName();
				Object fieldValue = null;
				Class paramType = fields[i].getType();
				
				//方法名
				String setMethodName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
				String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
				Method getMethod = object.getClass().getMethod(getMethodName);
				Method setMethod = msg.getMethod(setMethodName, paramType);
				
				if(fields[i].getModifiers() == 2){//私有属性，必须通过get方法获得值
					fieldValue = getMethod.invoke(object, null);
					
				}else {
					fieldValue = object.getClass().getField(fieldName).get(object);
				}
				//给result的属性赋值
				if(paramType == String.class){
					setMethod.invoke(result, (String)fieldValue);
				}else if(paramType == Integer.class){
					setMethod.invoke(result, (Integer)fieldValue);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static void main(String[] args) {
//		String xml = "<xml><scanCodeInfo><ScanResult>result</ScanResult><ScanType>type</ScanType></scanCodeInfo></xml>";
		String xml = "<xml><ToUserName><![CDATA[gh_e136c6e50636]]></ToUserName>"
				+ "<FromUserName><![CDATA[oMgHVjngRipVsoxg6TuX3vz6glDg]]></FromUserName>"
				+ "<CreateTime>1408090502</CreateTime>"
				+ "<MsgType><![CDATA[event]]></MsgType>"
				+ "<Event><![CDATA[scancode_push]]></Event>"
				+ "<EventKey><![CDATA[6]]></EventKey>"
				+ "<ScanCodeInfo><ScanType><![CDATA[qrcode]]></ScanType>"
				+ "<ScanResult><![CDATA[1]]></ScanResult>"
				+ "</ScanCodeInfo>"
				+ "</xml>";
		/*String xml = "<xml>"
				+ "<ScanCodeInfo><ScanType><![CDATA[qrcode]]></ScanType>"
				+ "<ScanResult><![CDATA[1]]></ScanResult>"
				+ "</ScanCodeInfo>"
				+ "</xml>";*/
		XML2Entity(xml, MSG.class);
		/*JAXBContext context;
		Object result = null;
		try {
			MSG msg = new MSG();
			ScanCodeInfo codeInfo = new ScanCodeInfo();
			codeInfo.setScanResult("result");
			codeInfo.setScanType("type");
			msg.setScanCodeInfo(codeInfo);
			context = JAXBContext.newInstance(MSG.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.marshal(msg, System.out);
			
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
}
