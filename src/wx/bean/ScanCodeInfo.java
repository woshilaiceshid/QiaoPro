package wx.bean;

import javax.xml.bind.annotation.XmlElement;

public class ScanCodeInfo {
	private String ScanType;
	private String ScanResult;
	
	@XmlElement(name="ScanType")
	public String getScanType() {
		return ScanType;
	}
	public void setScanType(String scanType) {
		ScanType = scanType;
	}
	@XmlElement(name="ScanResult")
	public String getScanResult() {
		return ScanResult;
	}
	public void setScanResult(String scanResult) {
		ScanResult = scanResult;
	}
	
	
}
