package com.grey_zoo.lottery.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnRouteParams;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import com.grey_zoo.lottery.global.GlobalValue;

public class HttpUtils {

	//Http的客户端
	HttpClient client;
	
	//POST请求
	HttpPost post;
	//GET请求
	HttpGet get;
	
	public HttpUtils() {
		// TODO 自动生成的构造函数存根
		client=new DefaultHttpClient();
		client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT,6000);
		if(GlobalValue.MYPROXY!=null)
		{
			HttpHost host=new HttpHost(GlobalValue.MYPROXY,GlobalValue.PORT);
			client.getParams().setParameter(ConnRouteParams.DEFAULT_PROXY, host);
		}
		
		//设置超时
//		HttpParams httpParams = new BasicHttpParams();  
//		HttpConnectionParams.setConnectionTimeout(httpParams,  
//		6000);
////		HttpConnectionParams.setSoTimeout(httpParams,  
////		30000);
//		
//		//新建HttpClient对象  
//		client = new DefaultHttpClient(httpParams);  
//		
//		//设置主机
//		if(GlobalValue.MYPROXY!=null)
//		{
//			HttpHost host=new HttpHost(GlobalValue.MYPROXY,GlobalValue.PORT);
//			client.getParams().setParameter(ConnRouteParams.DEFAULT_PROXY, host);
//		}

	}
	
	/**
	 * 
	 * @param uri
	 * @param xml
	 * @return
	 */
	public InputStream sendXml(String uri,String xml)
	{
		post=new HttpPost(uri);
		try {
			//传输体
			StringEntity entity=new StringEntity(xml,GlobalValue.ENCODEING);
			post.setEntity(entity);
			
			HttpResponse response=client.execute(post);
			
			if(response.getStatusLine().getStatusCode()==200)
			{
				return response.getEntity().getContent();
			}else {
				return null;
			}
			
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return null;
	}
	
	public JSONObject sendGet(String url) throws Exception{
		//第一步，创建HttpGet对象
		HttpGet httpGet = new HttpGet(url);
		//第二步，使用execute方法发送HTTP GET请求，并返回HttpResponse对象
		HttpResponse httpResponse = new DefaultHttpClient().execute(httpGet);
		String result = null;
		if (httpResponse.getStatusLine().getStatusCode() == 200)
		{
			//第三步，使用getEntity方法活得返回结果
			result = EntityUtils.toString(httpResponse.getEntity(),"utf-8");
		}
		
		//解析JSON
		JSONObject jsonObject=new JSONObject(result);
		
		return jsonObject;
	}
	
}
