import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.apache.http.client.utils.URIUtils;

public class PingOMatic {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String sitesParameters = "&chk_weblogscom=on&chk_blogs=on&chk_feedburner=on&chk_newsgator=on&chk_myyahoo=on&chk_pubsubcom=on&chk_blogdigger=on&chk_weblogalot=on&chk_newsisfree=on&chk_topicexchange=on&chk_google=on&chk_tailrank=on&chk_skygrid=on&chk_collecta=on&chk_superfeedr=on&chk_audioweblogs=on&chk_rubhub=on&chk_a2b=on&chk_blogshares=on";

		File file = new File(
				("E:/IC_Dropbox/Dropbox/MeetAV/SEO/FO82AD2B4EA1/not yet pinged.txt"));
		BufferedReader in = new BufferedReader(new FileReader(file));
		int counter = 0;
		File fileKeywords = new File(
				"E:/IC_Dropbox/Dropbox/MeetAV/SEO/keywords.txt");
		BufferedReader keyword_in = new BufferedReader(new FileReader(fileKeywords));
		ArrayList<String> keywords = new ArrayList<String>();
		while (keyword_in.ready()) {
			String s = keyword_in.readLine();
			keywords.add(s);
		}
		int keyword_counter = 0;
		int total_keywords = keywords.size();
		while (in.ready() && counter < 100) {
			String s = URLEncoder.encode(in.readLine());

			String title = URLEncoder.encode(keywords.get(keyword_counter));
			keyword_counter++;
			if (keyword_counter == total_keywords) {
				keyword_counter = 0;
			}

			String url = "http://pingomatic.com/ping/?" + "title=" + title
					+ "&blogurl=" + s + "&rssurl=http%3A%2F%2F"+sitesParameters;

			System.out.println(url);
			URL pingometic = new URL(url);
			URLConnection connection = pingometic.openConnection();
			DataInputStream dis = new DataInputStream(
					connection.getInputStream());
			String inputLine;
			while ((inputLine = dis.readLine()) != null) {
				System.out.println(inputLine);
			}
			dis.close();
			counter++;
		}
		in.close();

	}

}
