package students.tsinghua.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.sun.xml.internal.xsom.impl.scd.Iterators.Map;

/**
 * service for reading room
 * 
 * @author hujiawei
 * 
 */
public class CourseService {

	// the url to fetch room information
	// xianxingdaishu:http://v.163.com/special/opencourse/daishu.html
	// tongjixue:http://v.163.com/special/Khan/khstatistics.html
	// machinelearning:http://v.163.com/special/opencourse/machinelearning.html
	// usa:http://v.163.com/special/Khan/usa.html
	// khan xianxingdaishu:http://v.163.com/special/Khan/linearalgebra.html
	public static final String URL_COURSE = "http://v.163.com/special/Khan/linearalgebra.html";

	public static HashMap<String, String> getFileMap() throws Exception {
		HashMap<String, String> fileMap = new HashMap<String, String>();
		Document document = Jsoup.connect(URL_COURSE).get();
		Element tableElement = document.getElementById("list2");
		if (tableElement == null) {
			tableElement = document.getElementById("list1");
		}
		List<Element> trElements = tableElement.getElementsByTag("tr");
		Element element = null;
		String filename = null;
		for (int i = 1; i < trElements.size(); i++) {
			element = trElements.get(i);
			filename = element.getElementsByTag("td").get(1).getElementsByTag("a").get(0).attr("href");
			filename = filename.substring(filename.lastIndexOf("/") + 1);
			fileMap.put(filename, String.valueOf((i)) + "." + element.getElementsByTag("td").get(0).getElementsByTag("a").get(0).text()
					+ ".mp4");
		}
		return fileMap;
	}

	public static void getDownloadFileList() throws Exception {
		Document document = Jsoup.connect(URL_COURSE).get();
		Element tableElement = document.getElementById("list2");
		if (tableElement == null) {
			tableElement = document.getElementById("list1");
		}
		List<Element> trElements = tableElement.getElementsByTag("tr");
		Element element = null;
		for (int i = 1; i < trElements.size(); i++) {
			element = trElements.get(i);
			System.out.println(element.getElementsByTag("td").get(1).getElementsByTag("a").get(0).attr("href"));
		}
	}

	public static void chanageFileNames(String folderName, HashMap<String, String> fileMap) throws Exception {
		File folder = new File(folderName);
		File[] files = folder.listFiles();
		for (int i = 0; i < files.length; i++) {
			if (files[i].isFile() && fileMap.containsKey(files[i].getName())) {
				File destFile = new File(folderName + fileMap.get(files[i].getName()));
				System.out.println(files[i].getName() + " -> " + fileMap.get(files[i].getName()));
				files[i].renameTo(destFile);
			}
		}
	}

	// for test
	public static void main(String[] args) throws Exception {
		HashMap<String, String> fileMap = getFileMap();
		chanageFileNames("/Users/hujiawei/百度云同步盘/我的文档/在线教育资料/Khan-线性代数/", fileMap);
//		getDownloadFileList();
		
	}

}
