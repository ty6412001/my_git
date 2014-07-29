package com.test.dajie;

import java.io.IOException;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

public class Login {

	private HttpClient httpClient;

	public Login() {
		httpClient = new DefaultHttpClient();
	}

	public String getData(String url, Header... headers)
			throws ClientProtocolException, IOException {
		String result = "";
		HttpGet get = new HttpGet(url);
		if (headers != null) {
			for (Header header : headers) {
				get.addHeader(header);
			}
		}
		Header[] h_user_agent = get.getHeaders("User-Agent");
		if (h_user_agent != null) {
			get.addHeader(
					"User-Agent",
					"Mozilla/5.0 (Macintosh; Intel Mac OS X 10.9; rv:30.0) Gecko/20100101 Firefox/30.0");
		}
		HttpResponse response = httpClient.execute(get);
		if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			HttpEntity entity = response.getEntity();
			result = EntityUtils.toString(entity);
		}
		get.abort();
		return result;
	}

	public static Header[] getLoginedHeader() {
		String cookieStr = "DJ_RF=empty; DJ_EU=http%3A%2F%2Fcampus.dajie.com%2FcampusIndex; DJ_UVID=MTQwNjI5NTQyOTUzNzU2MzA0; dj_cap=987a962b437b3fcebe8ee4d4ba35a25c; __reg_tips=1; dj_auth_v3=M5LL3RE3NdHrc4VVJFuZI_uqWSA0NdfdlwJJk-oKKWvqM5Xni1cU6YxNLpWqc1EaeQ**; uchome_loginuser=24620782; USER_ACTION=request^ACampus^ANORMAL^A-^A-; Hm_lvt_38e2415f86d73cdfa1e9c3f4d8560b8e=1406295432; Hm_lpvt_38e2415f86d73cdfa1e9c3f4d8560b8e=1406295451";
		Header d_cookie = new BasicHeader("Cookie", cookieStr);
		Header d_connection = new BasicHeader("Connection", "keep-alive");
		Header[] headers = { d_cookie, d_connection };
		return headers;
	}

	public String getUrl(String o_url, List<SeachMsg> seachMsgs) {
		StringBuffer resrult = new StringBuffer();
		resrult.append(o_url);
		if (seachMsgs != null) {
			resrult.append("/ajax?");
			int i = 0;
			for (SeachMsg seachMsg : seachMsgs) {
				if (i != 0) {
					resrult.append("&");
				}
				String str = seachMsg.getKey() + "=" + seachMsg.getValue();
				resrult.append(str);
				i++;
			}
		}
//		System.out.println("连接地址为-->" + resrult.toString());
		return resrult.toString();

	}
}
