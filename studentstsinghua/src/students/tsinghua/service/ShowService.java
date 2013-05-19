package students.tsinghua.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import students.tsinghua.pojo.TShow;

public class ShowService {

	// the url to fetch show information
	public static final String URL_YCZX = "http://www.hall.tsinghua.edu.cn/yczx.aspx";

	// get the table content of show
	public Element getShowTable(int pid) {
		try {
			Document document = Jsoup.connect(URL_YCZX).data("pid", pid + "").get();
			Elements elements = document.getElementsByAttributeValue("width", "694");// important!
			return elements.get(0);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	// get the list of show by the content
	public List<TShow> getShowList(Element element) {
		List<TShow> shows = new ArrayList<TShow>();
		Elements elements = element.getElementsByTag("tr");
		TShow show;
		Element childElement;
		for (int i = 1; i < elements.size() - 1; i++) {// metion here, from 1 to size-1
			show = new TShow();
			childElement = elements.get(i);
			show.setDate(childElement.child(0).ownText());
			show.setTime(childElement.child(1).ownText());
			show.setLocation(childElement.child(2).ownText());
			show.setShowId(parseShowId(childElement.child(3).child(0).attr("href")));// <a href="ycshow.aspx?id=238" class="white3">
			show.setPrice(childElement.child(4).ownText());
			show.setTicketstate(childElement.child(5).ownText());
			shows.add(show);
		}
		return shows;
	}

	// url=ycshow.aspx?id=238
	private int parseShowId(String url) {
		return Integer.parseInt(url.substring(url.lastIndexOf("=") + 1, url.length()));
	}

	// for test
	public static void main(String[] args) {
		ShowService showService = new ShowService();
		showService.getShowList(showService.getShowTable(1));
		// System.out.println(showService.getShowTable(1));
	}

}

// demo of table content of show
// <tbody>
// <tr>
// <td width="70" height="40" align="center" bgcolor="#FFFFFF" class="text3">日 期</td>
// <td width="30" align="center" bgcolor="#FFFFFF" class="text3">时 间</td>
// <td width="120" align="center" bgcolor="#FFFFFF" class="text3">地 点</td>
// <td width="180" align="center" bgcolor="#FFFFFF" class="text3">节 目</td>
// <td width="230" align="center" bgcolor="#FFFFFF" class="text3">票 价</td>
// <td align="center" bgcolor="#FFFFFF" class="text3">售票状态</td>
// </tr>
// <tr>
// <td height="70" align="center" valign="middle" bgcolor="#FCE9E6" class="text3">2013-06-08</td>
// <td width="0" bgcolor="#FCE9E6" class="text3">19:30</td>
// <td width="0" align="center" bgcolor="#FCE9E6" class="text3">新清华学堂</td>
// <td align="left" bgcolor="#FCE9E6"><a href="ycshow.aspx?id=239" class="white3">万玛舞蹈剧团《香巴拉》校园行清华大学专场演出</a></td>
// <td align="left" bgcolor="#FCE9E6" class="text3">20/40/60/100/120/180/VIP280元</td>
// <td align="center" bgcolor="#FCE9E6" class="text3">售票中</td>
// </tr>
// <tr>
// <td height="70" align="center" valign="middle" bgcolor="#FCE9E6" class="text3">2013-06-08</td>
// <td width="0" bgcolor="#FCE9E6" class="text3">19:00</td>
// <td width="0" align="center" bgcolor="#FCE9E6" class="text3">蒙民伟音乐厅</td>
// <td align="left" bgcolor="#FCE9E6"><a href="ycshow.aspx?id=238" class="white3">奥地利古典吉他二重奏音乐会</a></td>
// <td align="left" bgcolor="#FCE9E6" class="text3">10售罄/20售罄/40/60/100/VIP180元</td>
// <td align="center" bgcolor="#FCE9E6" class="text3">售票中</td>
// </tr>
// <tr>
// <td height="70" align="center" valign="middle" bgcolor="#FCE9E6" class="text3">2013-06-06</td>
// <td width="0" bgcolor="#FCE9E6" class="text3">19:00</td>
// <td width="0" align="center" bgcolor="#FCE9E6" class="text3">蒙民伟音乐厅</td>
// <td align="left" bgcolor="#FCE9E6"><a href="ycshow.aspx?id=208" class="white3">“2013国际迷你古典”系列音乐会之四 “手足琴声”瑞士阿尔贝克姐妹二重奏 </a></td>
// <td align="left" bgcolor="#FCE9E6" class="text3">10售罄/20/40/60/100/VIP180元</td>
// <td align="center" bgcolor="#FCE9E6" class="text3">售票中</td>
// </tr>
// <tr>
// <td height="70" align="center" valign="middle" bgcolor="#FCE9E6" class="text3">2013-06-01</td>
// <td width="0" bgcolor="#FCE9E6" class="text3">19:30</td>
// <td width="0" align="center" bgcolor="#FCE9E6" class="text3">新清华学堂</td>
// <td align="left" bgcolor="#FCE9E6"><a href="ycshow.aspx?id=236" class="white3">中央芭蕾舞团芭蕾舞剧《天鹅湖》</a></td>
// <td align="left" bgcolor="#FCE9E6" class="text3">20元售罄/40售罄/60元售罄/100元售罄/180售罄/280/380/480/VIP680元 </td>
// <td align="center" bgcolor="#FCE9E6" class="text3">售票中</td>
// </tr>
// <tr>
// <td height="70" align="center" valign="middle" bgcolor="#FCE9E6" class="text3">2013-05-31</td>
// <td width="0" bgcolor="#FCE9E6" class="text3">19:30</td>
// <td width="0" align="center" bgcolor="#FCE9E6" class="text3">新清华学堂</td>
// <td align="left" bgcolor="#FCE9E6"><a href="ycshow.aspx?id=235" class="white3">中央芭蕾舞团芭蕾舞剧《天鹅湖》</a></td>
// <td align="left" bgcolor="#FCE9E6" class="text3">20元售罄/40售罄/60元售罄/100售罄/180售罄/280/380/480/VIP680元 </td>
// <td align="center" bgcolor="#FCE9E6" class="text3">售票中</td>
// </tr>
// <tr>
// <td height="70" align="center" valign="middle" bgcolor="#FCE9E6" class="text3">2013-05-30</td>
// <td width="0" bgcolor="#FCE9E6" class="text3">19:00</td>
// <td width="0" align="center" bgcolor="#FCE9E6" class="text3">蒙民伟音乐厅</td>
// <td align="left" bgcolor="#FCE9E6"><a href="ycshow.aspx?id=237" class="white3">&quot;单声复调“以色列大提琴家利普金独奏音乐会</a></td>
// <td align="left" bgcolor="#FCE9E6" class="text3">10元售罄/20/40/60售罄/100/VIP180元</td>
// <td align="center" bgcolor="#FCE9E6" class="text3">售票中</td>
// </tr>
// <tr>
// <td height="70" align="center" valign="middle" bgcolor="#FEFBDE" class="text3">2013-12-06</td>
// <td width="0" bgcolor="#FEFBDE" class="text3">19:00</td>
// <td width="0" align="center" bgcolor="#FEFBDE" class="text3">蒙民伟音乐厅</td>
// <td align="left" bgcolor="#FEFBDE"><a href="ycshow.aspx?id=197" class="white3">“2013国际迷你古典”系列音乐会之十二 “午夜巴塞罗那”西班牙钢琴家阿尔芭&#x2022;温图拉
// </a></td>
// <td align="left" bgcolor="#FEFBDE" class="text3">待定</td>
// <td align="center" bgcolor="#FEFBDE" class="text3">待售票</td>
// </tr>
// <tr>
// <td height="70" align="center" valign="middle" bgcolor="#FEFBDE" class="text3">2013-11-15</td>
// <td width="0" bgcolor="#FEFBDE" class="text3">19:00</td>
// <td width="0" align="center" bgcolor="#FEFBDE" class="text3">蒙民伟音乐厅</td>
// <td align="left" bgcolor="#FEFBDE"><a href="ycshow.aspx?id=196" class="white3">“2013国际迷你古典”系列音乐会之十一 “枫情弦韵”加拿大莫里纳利弦乐四重奏 </a></td>
// <td align="left" bgcolor="#FEFBDE" class="text3">待定</td>
// <td align="center" bgcolor="#FEFBDE" class="text3">待售票</td>
// </tr>
// <tr>
// <td height="70" align="center" valign="middle" bgcolor="#FEFBDE" class="text3">2013-11-01</td>
// <td width="0" bgcolor="#FEFBDE" class="text3">19:00</td>
// <td width="0" align="center" bgcolor="#FEFBDE" class="text3">蒙民伟音乐厅</td>
// <td align="left" bgcolor="#FEFBDE"><a href="ycshow.aspx?id=195" class="white3">“2013国际迷你古典”系列音乐会之十 “秋日私语”格鲁吉亚钢琴家爱丽索&#x2022;博尔科瓦茨
// </a></td>
// <td align="left" bgcolor="#FEFBDE" class="text3">待定</td>
// <td align="center" bgcolor="#FEFBDE" class="text3">待售票</td>
// </tr>
// <tr>
// <td height="70" align="center" valign="middle" bgcolor="#FEFBDE" class="text3">2013-10-24</td>
// <td width="0" bgcolor="#FEFBDE" class="text3">19:00</td>
// <td width="0" align="center" bgcolor="#FEFBDE" class="text3">蒙民伟音乐厅</td>
// <td align="left" bgcolor="#FEFBDE"><a href="ycshow.aspx?id=194" class="white3">“2013国际迷你古典”系列音乐会之九 “地中海风情“意大利翁布里亚弦乐四重奏 </a></td>
// <td align="left" bgcolor="#FEFBDE" class="text3">待定</td>
// <td align="center" bgcolor="#FEFBDE" class="text3">待售票</td>
// </tr>
// <tr>
// <td height="70" align="center" valign="middle" bgcolor="#FEFBDE" class="text3">2013-09-20</td>
// <td width="0" bgcolor="#FEFBDE" class="text3">19:00</td>
// <td width="0" align="center" bgcolor="#FEFBDE" class="text3">蒙民伟音乐厅</td>
// <td align="left" bgcolor="#FEFBDE"><a href="ycshow.aspx?id=193" class="white3">“2013国际迷你古典”系列音乐会之八 “雕刻时光”莱尼塔斯弦乐三重奏（瑞士/法国） </a></td>
// <td align="left" bgcolor="#FEFBDE" class="text3">待定</td>
// <td align="center" bgcolor="#FEFBDE" class="text3">待售票</td>
// </tr>
// <tr>
// <td height="70" align="center" valign="middle" bgcolor="#FEFBDE" class="text3">2013-09-05</td>
// <td width="0" bgcolor="#FEFBDE" class="text3">19:00</td>
// <td width="0" align="center" bgcolor="#FEFBDE" class="text3">蒙民伟音乐厅</td>
// <td align="left" bgcolor="#FEFBDE"><a href="ycshow.aspx?id=192" class="white3">“2013国际迷你古典”系列音乐会之七 “日落左岸”法国钢琴家艾曼努埃尔&#x2022;斯瓦耶茨 </a></td>
// <td align="left" bgcolor="#FEFBDE" class="text3">待定</td>
// <td align="center" bgcolor="#FEFBDE" class="text3">待售票</td>
// </tr>
// <tr>
// <td height="70" align="center" valign="middle" bgcolor="#FEFBDE" class="text3">2013-07-12</td>
// <td width="0" bgcolor="#FEFBDE" class="text3">19:00</td>
// <td width="0" align="center" bgcolor="#FEFBDE" class="text3">蒙民伟音乐厅</td>
// <td align="left" bgcolor="#FEFBDE"><a href="ycshow.aspx?id=191" class="white3">“2013国际迷你古典”系列音乐会之六 “英伦风范”英国钢琴家马求斯&#x2022;伯劳维亚克 </a></td>
// <td align="left" bgcolor="#FEFBDE" class="text3">待定</td>
// <td align="center" bgcolor="#FEFBDE" class="text3">待售票</td>
// </tr>
// <tr>
// <td height="70" align="center" valign="middle" bgcolor="#FEFBDE" class="text3">2013-06-27</td>
// <td width="0" bgcolor="#FEFBDE" class="text3">19:00</td>
// <td width="0" align="center" bgcolor="#FEFBDE" class="text3">蒙民伟音乐厅</td>
// <td align="left" bgcolor="#FEFBDE"><a href="ycshow.aspx?id=190" class="white3">“2013国际迷你古典”系列音乐会之五 “罗马假日”意大利钢琴家马可&#x2022;格里艾克 </a></td>
// <td align="left" bgcolor="#FEFBDE" class="text3">待定</td>
// <td align="center" bgcolor="#FEFBDE" class="text3">待售票</td>
// </tr>
// <tr>
// <td height="70" align="center" valign="middle" bgcolor="#FEFBDE" class="text3">2013-05-21</td>
// <td width="0" bgcolor="#FEFBDE" class="text3">19:00</td>
// <td width="0" align="center" bgcolor="#FEFBDE" class="text3">清华大礼堂</td>
// <td align="left" bgcolor="#FEFBDE"><a href="ycshow.aspx?id=240" class="white3">波兰克拉科维亚宫廷芭蕾舞团清华大学专场</a></td>
// <td align="left" bgcolor="#FEFBDE" class="text3">5月20日12:00在新清华学堂票厅凭清华有效证件免费领票，每人每证限领2张</td>
// <td align="center" bgcolor="#FEFBDE" class="text3">待售票</td>
// </tr>
// <tr>
// <td colspan="6"> 第 1/9页&nbsp;共 121 条&nbsp;每页显示 15 条&nbsp;&nbsp;<span class="disabled">&lt;</span><span class="current">1</span><a
// href="http://www.hall.tsinghua.edu.cn/yczx.aspx?pid=2">2</a><a href="http://www.hall.tsinghua.edu.cn/yczx.aspx?pid=3">3</a><a
// href="http://www.hall.tsinghua.edu.cn/yczx.aspx?pid=4">4</a><a href="http://www.hall.tsinghua.edu.cn/yczx.aspx?pid=5">5</a><a
// href="http://www.hall.tsinghua.edu.cn/yczx.aspx?pid=6">6</a><a href="http://www.hall.tsinghua.edu.cn/yczx.aspx?pid=7">7</a><a
// href="http://www.hall.tsinghua.edu.cn/yczx.aspx?pid=8">8</a><a href="http://www.hall.tsinghua.edu.cn/yczx.aspx?pid=9">9</a><a
// href="http://www.hall.tsinghua.edu.cn/yczx.aspx?pid=2">&gt;</a></td>
// </tr>
// </tbody>

