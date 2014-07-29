package com.tbl.test;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;


public class TestHttpClient {
	
	public static void saveImg(InputStream is) throws IOException{
//		String name = "bueaty.jpg";
		Long time = System.currentTimeMillis();
		String name = String.valueOf(time) + ".jpg";
		File img = new File(name);
		if(!img.exists()){
			img.createNewFile();
		}
			OutputStream fos = new FileOutputStream(img);
//			byte[] b = new byte[1024];
			BufferedInputStream ios = new BufferedInputStream(is);
//			ios.
//			is.
			boolean flag = true;
			while(flag){
				int b = is.read();
				fos.write(b);
				if(b==-1){
					flag = false;
				}
			}
			is.close();
			fos.close();
			System.out.println("保存成功->" + img.getPath());
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HttpClient hc = new DefaultHttpClient();
		String url = "http://a.hiphotos.baidu.com/image/pic/item/8326cffc1e178a8269817a55f403738da877e882.jpg";
		HttpGet httpGet = new HttpGet(url);
		try {
			HttpResponse response = hc.execute(httpGet);
			System.out.println(response.getStatusLine());
			HttpEntity httpEntity = response.getEntity();
			InputStream is = httpEntity.getContent();
			saveImg(is);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
