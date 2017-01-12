package wx.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class MSG {
	private String ToUserName;
	private String FromUserName;
	private String CreateTime;
	private String MsgType;
	private String Content;
	private String MsgId;
	
	/**
	 * Event���ܵ�ֵ��������¼�����
	 * CLICK:����˵���ȡ��Ϣʱ���¼�����
	 * VIEW:����˵���ת����ʱ���¼�����
	 * scancode_push:ɨ�����¼����¼�����
	 * scancode_waitmsg:ɨ�����¼��ҵ�������Ϣ�����С���ʾ����¼�����
	 * pic_sysphoto:����ϵͳ���շ�ͼ���¼�����
	 * pic_photo_or_album:�������ջ�����ᷢͼ���¼�����
	 * pic_weixin:����΢����ᷢͼ�����¼�����
	 * location_select:��������λ��ѡ�������¼�����
	 */
	private String Event;
	
	//����˵���ȡ��Ϣʱ���¼�����
	private String EventKey;
	
	//����˵���ת����ʱ���¼�����
	private String MenuID;
	
	//ɨ�����¼����¼�����[ɨ�����¼��ҵ�������Ϣ�����С���ʾ����¼�����]
	private ScanCodeInfo ScanCodeInfo;
	
	//����ϵͳ���շ�ͼ���¼�����
	private String SendPicsInfo;
	private String Count;
	private String PicList;
	private String item;
	private String PicMd5Sum;
	
	//�������ջ�����ᷢͼ���¼�����
	
	//����΢����ᷢͼ�����¼�����
	
	//��������λ��ѡ�������¼�����
	private String SendLocationInfo;
	private String Location_X;
	private String Location_Y;
	private String Scale;
	private String Label;
	private String Poiname;
	
	private String Ticket;
	
	private String Latitude;
	private String Longitude;
	private String Precision;
	
	
	
	public String getTicket() {
		return Ticket;
	}
	public void setTicket(String ticket) {
		Ticket = ticket;
	}
	public String getLatitude() {
		return Latitude;
	}
	public void setLatitude(String latitude) {
		Latitude = latitude;
	}
	public String getLongitude() {
		return Longitude;
	}
	public void setLongitude(String longitude) {
		Longitude = longitude;
	}
	public String getPrecision() {
		return Precision;
	}
	public void setPrecision(String precision) {
		Precision = precision;
	}
	public String getEvent() {
		return Event;
	}
	public void setEvent(String event) {
		Event = event;
	}
	public String getEventKey() {
		return EventKey;
	}
	public void setEventKey(String eventKey) {
		EventKey = eventKey;
	}
	public String getMenuID() {
		return MenuID;
	}
	public void setMenuID(String menuID) {
		MenuID = menuID;
	}
	public String getSendPicsInfo() {
		return SendPicsInfo;
	}
	public void setSendPicsInfo(String sendPicsInfo) {
		SendPicsInfo = sendPicsInfo;
	}
	public String getCount() {
		return Count;
	}
	public void setCount(String count) {
		Count = count;
	}
	public String getPicList() {
		return PicList;
	}
	public void setPicList(String picList) {
		PicList = picList;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public String getPicMd5Sum() {
		return PicMd5Sum;
	}
	public void setPicMd5Sum(String picMd5Sum) {
		PicMd5Sum = picMd5Sum;
	}
	public String getSendLocationInfo() {
		return SendLocationInfo;
	}
	public void setSendLocationInfo(String sendLocationInfo) {
		SendLocationInfo = sendLocationInfo;
	}
	public String getLocation_X() {
		return Location_X;
	}
	public void setLocation_X(String location_X) {
		Location_X = location_X;
	}
	public String getLocation_Y() {
		return Location_Y;
	}
	public void setLocation_Y(String location_Y) {
		Location_Y = location_Y;
	}
	public String getScale() {
		return Scale;
	}
	public void setScale(String scale) {
		Scale = scale;
	}
	public String getLabel() {
		return Label;
	}
	public void setLabel(String label) {
		Label = label;
	}
	public String getPoiname() {
		return Poiname;
	}
	public void setPoiname(String poiname) {
		Poiname = poiname;
	}
	public String getToUserName() {
		return ToUserName;
	}
	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}
	public String getFromUserName() {
		return FromUserName;
	}
	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}
	public String getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}
	public String getMsgType() {
		return MsgType;
	}
	public void setMsgType(String msgType) {
		MsgType = msgType;
	}
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	public String getMsgId() {
		return MsgId;
	}
	public void setMsgId(String msgId) {
		MsgId = msgId;
	}

//	@XmlElement(name="ScanCodeInfo")
	public ScanCodeInfo getScanCodeInfo() {
		return ScanCodeInfo;
	}
	public void setScanCodeInfo(ScanCodeInfo scanCodeInfo) {
		this.ScanCodeInfo = scanCodeInfo;
	}
	
	
}
