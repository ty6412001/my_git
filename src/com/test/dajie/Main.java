package com.test.dajie;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Main {

	static String url = "http://campus.dajie.com/progress/index";

	// static String url =
	// "http://campus.dajie.com/progress/index/ajax?CSRFToken=ZDSvsyl9n9bWpCbadkhPiJhIW7eis-kh6y2dk8Ht&page=2";

	public String getSelectionUrl(String str, SeachMsg seachMsg) {
		return null;
	}

	public static List<SeachMsg> mySeach(int page) {
		List<SeachMsg> seachMsg = new ArrayList<SeachMsg>();
		seachMsg.add(new SeachMsg("city", "320100"));
		seachMsg.add(new SeachMsg("city", "440300"));
		seachMsg.add(new SeachMsg("positionIndustry", "1"));
		seachMsg.add(new SeachMsg("positionIndustry", "60"));
		seachMsg.add(new SeachMsg("positionIndustry", "61"));
		seachMsg.add(new SeachMsg("_CSRFToken",
				"ZCkHYMmNIE01ju7IBAn0XXsGr3eZRchYWPgfcspb"));
		seachMsg.add(new SeachMsg("page", page + ""));
		return seachMsg;
	}

	public static void main(String[] args) {
		Login login = new Login();
		List<GetSaveMsg> lists = new ArrayList<GetSaveMsg>();
		try {
			for (int i = 1; i < 100; i++) {
				String newUrl = login.getUrl(url, mySeach(i));
				String result = login.getData(newUrl, Login.getLoginedHeader());
				Explain explain = new Explain(result);
				List<GetSaveMsg> list = explain.getMsg();
				lists.addAll(list);
				System.out.println("得到第" + i + "页数据");
			}
			for (GetSaveMsg getSaveMsg : lists) {
				System.out.println(getSaveMsg.toString());
			}
			Save2excel excel = new Save2excel();
			excel.save("招聘信息", lists);
		} catch (Exception e) {
			System.err.println("存储失败");
			e.printStackTrace();
		}
	}
}
