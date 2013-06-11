import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URLEncoder;
import java.util.ArrayList;


public class UrlPrint {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String sitesParameters = "&chk_weblogscom=on&chk_blogs=on&chk_feedburner=on&chk_newsgator=on&chk_myyahoo=on&chk_pubsubcom=on&chk_blogdigger=on&chk_weblogalot=on&chk_newsisfree=on&chk_topicexchange=on&chk_google=on&chk_tailrank=on&chk_skygrid=on&chk_collecta=on&chk_superfeedr=on&chk_audioweblogs=on&chk_rubhub=on&chk_a2b=on&chk_blogshares=on";
		String title="meetav12312312";
		String s="http://www.sdfdsfd.com/dsdfsdf/";
			String url = "http://pingomatic.com/ping/?" + "title=" + title
					+ "&blogurl=" + s + "&rssurl=http%3A%2F%2F"+sitesParameters;
			System.out.println(url);
	}

}
