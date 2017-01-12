package wx.http.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletContext;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

public class HttpUtil extends DefaultHttpClient{
	private static String charset = "utf-8";
	
	/**
	 * 发送 post请求，返回响应结果
	 * @param url		发送的目的服务器地址
	 * @param params	post的参数
	 * @return
	 * @throws Exception
	 */
	public static String post(String url, Map<String, String> params) throws Exception{
		HttpClient client = new HttpUtil();
		HttpPost post = new HttpPost(url);
		List<NameValuePair> list = new ArrayList<NameValuePair>();  
		Iterator iterator = params.entrySet().iterator();  
        while(iterator.hasNext()){  
            Entry<String,String> elem = (Entry<String, String>) iterator.next();  
            list.add(new BasicNameValuePair(elem.getKey(),elem.getValue()));  
        }  
        if(list.size() > 0){  
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list,charset);  
            post.setEntity(entity);  
        }  
		
		HttpResponse response = null;
		response = client.execute(post);
		String responseText = "";
		if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
			HttpEntity entity = response.getEntity();
			responseText = EntityUtils.toString(entity);
		}
		
		return responseText;
	}
	/**
	 * 发送post
	 * @param url
	 * @param jsonstring	json字符串参数
	 * @return				字符串
	 * @throws Exception
	 */
	public static String post(String url, String jsonstring) throws Exception{
		HttpClient client = new HttpUtil();
		HttpPost post = new HttpPost(url);
		StringEntity s = new StringEntity(jsonstring);
		s.setContentEncoding("utf-8");
		s.setContentType("application/json");
		post.setEntity(s);
		HttpResponse response = client.execute(post);
		String responseText = "";
		if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
	        HttpEntity entity = response.getEntity();
	        InputStream in = entity.getContent();
	        int len = -1;
	        byte[] buffer = new byte[1024];
	        ByteArrayOutputStream out = new ByteArrayOutputStream();
	        while((len = in.read(buffer)) != -1){
	        	out.write(buffer);
	        }
	        out.close();
	        in.close();
	        responseText = new String(out.toByteArray(), charset);
	    }
		return responseText;
	}
	
	
	public static String get(String url) throws Exception{
		HttpClient client = new HttpUtil();
		HttpGet post = new HttpGet(url);
		
		HttpResponse response = null;
		response = client.execute(post);
		String responseText = "";
		if(response != null){
			String contentType = response.getFirstHeader("Content-Type").getValue();
			HttpEntity entity = response.getEntity();
			InputStream in = entity.getContent();
			if (!contentType.equals("image/jpg")) {
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				byte[] buffer = new byte[1024];
				int len = -1;
				while((len = in.read(buffer)) != -1){
					out.write(buffer, 0, len);
				}
				out.close();
				in.close();
				byte[] result = out.toByteArray();
				responseText = new String(result, charset);
			}else {
				String absoluteUrl = System.getProperty("user.dir");
				FileOutputStream out = new FileOutputStream(new File(absoluteUrl + "/WebContent/img/two_dimension.jpg"));
				byte[] temp = new byte[1024];
				int len = -1;
				while ((len = in.read(temp)) != -1) {
					out.write(temp, 0, len);
				}
				temp = null;
				out.close();
				in.close();
			}
		}
		return responseText;
	}
}
