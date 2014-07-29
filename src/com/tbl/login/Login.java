package com.tbl.login;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Login {

	HttpClient httpClient;

	String loginURL = "http://www.dajie.com/?isLogin=true";

	String cookie;

	private String img_url;
	
	private String ACCEPT = "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8";
	private String USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.9; rv:30.0) Gecko/20100101 Firefox/30.0";
	private String CONNECTION = "keep-alive";

	public Login() {
		httpClient = new DefaultHttpClient();
		System.out.println("----------------------��һ������-----------------");
		try {
			login1();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("��һ������ʧ��");
		}
		System.out.println("----------------------������֤��------------------");
		try {
			login2();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("�ڶ�������ʧ��");
		}
		System.out.print("��������֤��:");
		try {
			String capatch = inputCapatch();
			System.out.println("�������֤��Ϊ--->"+ capatch);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("������֤��ʧ��");
		}

	}

	/**
	 * ��һ�ε�½,Ϊ�˵õ���֤��
	 * 
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	private void login1() throws ClientProtocolException, IOException {
		HttpGet httpGet = new HttpGet(loginURL);
		HttpResponse response = httpClient.execute(httpGet);
		if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			HttpEntity entity = response.getEntity();
			String result = EntityUtils.toString(entity);
			// ��jsoup����result
			img_url = getCapatchaURL(result);
		}
	}

	/**
	 * Ϊ�˵õ���֤��
	 * 
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	private void login2() throws ClientProtocolException, IOException {
		String uri = img_url;
		HttpGet httpGet = new HttpGet(uri);
		HttpResponse response = httpClient.execute(httpGet);
		if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			HttpEntity entity = response.getEntity();
			OutputStream os = getCapatchaIMG("copatch");
			entity.writeTo(os);
			os.close();
		}
		System.out
				.println("------------------------������֤��ɹ�---------------------");
	}
	
	private void login3(){
		String url = "http://www.dajie.com/account/newloginsubmit?callback=LOGIN_CALLBACK&ajax=1";
//		String url = http://www.dajie.com/account/login###
		HttpPost httpPost = new HttpPost(url);
//		httpPost.
	}
	
	/**
	 * ֱ�ӵ�½
	 */
	private void loginInsent(String uri){
		httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(uri);
		httpGet.setHeader("Accept", ACCEPT);
		httpGet.addHeader("User-Agernt", USER_AGENT);
		httpGet.addHeader("Connection", CONNECTION);
//		Cookie[] cookies = new cook
	}

	/**
	 * ��������֤���ַ��Ϣ
	 * 
	 * @param html
	 * @return
	 */
	private String getCapatchaURL(String html) {
		Document doc = Jsoup.parse(html);
		Elements imgs = doc.getElementsByAttributeValue("class",
				"captcha-img J_captchChange");
		Element img = imgs.first();
		String img_src = img.attr("src");
		return img_src;
	}
	
	/**
	 * ������֤��
	 * @return
	 * @throws Exception
	 */
	private String inputCapatch() throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));
		String res = reader.readLine();
		return res;
	}

	/**
	 * д����֤����
	 * 
	 * @param name
	 * @throws IOException
	 */
	private OutputStream getCapatchaIMG(String name) throws IOException {
		String pathname = name + ".png";
		File file = new File(pathname);
		if (!file.exists()) {
			file.createNewFile();
		}
		OutputStream os = new FileOutputStream(file);
		return os;
	}

	public static void main(String[] args) {
		new Login();
	}
}
