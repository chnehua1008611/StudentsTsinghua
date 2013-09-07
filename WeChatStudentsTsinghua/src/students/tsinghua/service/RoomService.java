package students.tsinghua.service;

import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * service for reading room
 * 
 * @author hujiawei
 * 
 */
public class RoomService {

	// the url to fetch room information
	public static final String TAG_ROOM = "http://ske.lib.tsinghua.edu.cn/roomshow/";
	
	public String getMessageResultText() throws Exception{
		StringBuffer result = new StringBuffer("");
		Document document = Jsoup.connect(TAG_ROOM).get();
		List<Element> trElements = document.getElementsByTag("tr");
		result.append("时间:"+trElements.get(1).child(0).text()+"\n地点和空位数:\n");
		Element element = null;
		for (int i = 3; i < trElements.size(); i++) {
			element=trElements.get(i);
//			System.out.println(element.child(0).text()+" "+element.child(1).text()+" "+element.child(2).text());
			result.append(element.child(0).text()+" ("+element.child(2).text()+")\n");
		}
		return result.toString();
	}

	private String getSourceUrl(String url) throws Exception {
		Document document = Jsoup.connect(url).get();
		return document.html();
	}

	// for test
	public static void main(String[] args) throws Exception {
		RoomService showService = new RoomService();
		String content = showService.getSourceUrl(TAG_ROOM);
		System.out.println(content);
//		System.out.println(showService.getMessageResultText());
	}

}
