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
	 * ��xml����ת��Ϊָ�������Ͷ���
	 * @param xmlContent	XML����
	 * @param ob			ת���������
	 * @return				ת��������ݶ���
	 */
	@SuppressWarnings("all")
	public static Object XML2Entity(String xmlContent, Class<? extends Object> ob){
		// ����JAXBContext �����Ķ���
		JAXBContext context;
		Object result = null;
		try {
			// ͨ��ָ��ӳ����ഴ��������
			context = JAXBContext.newInstance(ob);
			// ͨ�������Ĵ���javaת��xml����
			Unmarshaller u = context.createUnmarshaller();
			Object object = u.unmarshal(new ByteArrayInputStream(xmlContent.getBytes("utf-8")));
			Constructor[] constructor = ob.getConstructors();
			Class<? extends Object> msg = Class.forName("wx.bean.MSG");
			//���ص�ʵ����
			result = msg.newInstance();
			String name = object.getClass().getName();
			Field[] fields = object.getClass().getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {//ѭ��ȡobject��fieldֵ�������Ƹ�result����Ӧ����
				String fieldName = fields[i].getName();
				Object fieldValue = null;
				Class paramType = fields[i].getType();
				
				//������
				String setMethodName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
				String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
				Method getMethod = object.getClass().getMethod(getMethodName);
				Method setMethod = msg.getMethod(setMethodName, paramType);
				
				if(fields[i].getModifiers() == 2){//˽�����ԣ�����ͨ��get�������ֵ
					fieldValue = getMethod.invoke(object, null);
					
				}else {
					fieldValue = object.getClass().getField(fieldName).get(object);
				}
				//��result�����Ը�ֵ
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
