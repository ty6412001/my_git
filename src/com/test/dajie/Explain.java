package com.test.dajie;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Explain {

	Document doc;

	public Explain(String html) {
		doc = Jsoup.parse(html);
	}

	public Document obj() {
		return doc;
	}

	public List<GetSaveMsg> getMsg() {
		List<GetSaveMsg> results = new ArrayList<GetSaveMsg>();
		Elements lists = doc.getElementsByAttributeValue("class", "listT");
		if (lists != null) {
			for (Element element : lists) {
				String name = "";
				String time = "";
				Elements e_names = element.getElementsByAttributeValue("class",
						"name-td");
				if (!e_names.isEmpty()) {
					name = e_names.get(0).text();
					// name = "111";
				}
				Elements e_times = element.getElementsByAttributeValue("class",
						"bg-status status-easy");
				if (!e_times.isEmpty()) {
					time = e_times.get(0).text();
					// time = "222";
				}else{
					e_times = element.getElementsByAttributeValue("class",
							"bg-status status-weight");
					if(!e_times.isEmpty()){
						time = e_times.get(0).text();
					}
				}
//				System.out.println("name->" + name + ";time->" + time);
				if (name == null && time == null) {
					continue;
				}
				results.add(new GetSaveMsg(name, time));
			}

		}
		return results;
	}
}
