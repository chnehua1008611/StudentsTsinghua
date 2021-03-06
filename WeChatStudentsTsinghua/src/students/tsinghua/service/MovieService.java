package students.tsinghua.service;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.qq.weixin.sdk.message.ItemArticle;

/**
 * service for movie
 * 
 * @author hujiawei
 * 
 */
public class MovieService {

	// the url to fetch show information
	public static final String TAG_MOVIE = "http://studentstsinghua.diandian.com/?tag=%E6%B0%B4%E6%9C%A8%E2%80%9C%E7%94%B5%E5%BD%B1%E2%80%9D";

	// default news size for movie
	public static final int DEFAULT_MOVIE_NEWS_SIZE = 1;

	// get the list of show
	public List<ItemArticle> getItemList() throws Exception {
		Document document = Jsoup.connect(TAG_MOVIE).get();
		// System.out.println(document.html());//
		Elements element_post = document.getElementsByClass("post");
		List<ItemArticle> items = new ArrayList<ItemArticle>();
		ItemArticle item;
		for (int i = 0; i < DEFAULT_MOVIE_NEWS_SIZE && i < element_post.size(); i++) {
			item = new ItemArticle();
			item.setTitle(element_post.get(i).getElementsByTag("a").get(0).text());
			item.setDescription(element_post.get(i).getElementsByTag("a").get(0).text());
			item.setPicUrl(element_post.get(i).getElementsByTag("img").get(0).attr("src"));
			item.setUrl(getSourceUrl(element_post.get(i).getElementsByTag("a").get(0).attr("href")));
			items.add(item);
		}
		return items;
	}

	private String getSourceUrl(String url) throws Exception {
		Document document = Jsoup.connect(url).get();
		return document.getElementsByClass("text_content").first().getElementsByTag("a").first().attr("href");
	}

	// for test
	public static void main(String[] args) throws Exception {
		MovieService showService = new MovieService();
		showService.getItemList();
	}

}
