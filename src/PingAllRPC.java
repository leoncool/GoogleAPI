import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

public class PingAllRPC {

	/**
	 * @param args
	 */
	// String inputUnPingedFileName="AllUnPingedLinks.txt";
	// String tempFile = "tempfile.txt";
	// BufferedReader reader;
	// BufferedWriter writer;
	// InputStream in;

	public static List<String> rpcList = new ArrayList<String>();

	public static void main(String[] args){
		// TODO Auto-generated method stub

		rpcList.add("http://rpc.pingomatic.com");

		rpcList.add("http://blogsearch.google.com/ping/RPC2");

		rpcList.add("http://ping.blo.gs/");
		rpcList.add("http://ping.feedburner.com");
		rpcList.add("http://rpc.weblogs.com/RPC2");
		rpcList.add("http://www.blogpeople.net/servlet/weblogUpdates");
		// rpcList.add("http://ping.myblog.jp");
		rpcList.add("http://rpc.technorati.com/rpc/ping");
		// rpcList.add("http://xping.pubsub.com/ping/");
		rpcList.add("http://services.newsgator.com/ngws/xmlrpcping.aspx");
		rpcList.add("http://blogsearch.google.lv/ping/RPC2");
		rpcList.add("http://api.my.yahoo.co.jp/RPC2");
		// rpcList.add("http://www.ping.blo.gs");
		rpcList.add("http://rpc.aitellu.com");
		// rpcList.add("http://snipsnap.org/RPC2");
		rpcList.add("http://rpc.twingly.com/");
		rpcList.add("http://ping.fc2.com/");
		PingAllRPC pingDao = new PingAllRPC();
		// List<URL> urls = new ArrayList<URL>();
		// urls.add(new URL("http://www.leonli.co.uk/blog"));

		System.out.println("SEO Links File Location: ");

//		Scanner scanIn = new Scanner(System.in);
//		String inputName = scanIn.nextLine();

//		scanIn.close();
		String inputName="AllUnPingedLinks.txt";
		System.out.println(inputName);

		File inputFile = new File(inputName);
		File tempFile = new File(inputName + "_finished");
		File logFile = new File(inputName + "_log");
		try{
		BufferedReader reader = new BufferedReader(new FileReader(inputFile));
		BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile,
				true));
		BufferedWriter log_writer = new BufferedWriter(new FileWriter(logFile,
				true));
		String currentLine;
		int counter = 0;
		int max=1;

		while ((currentLine = reader.readLine()) != null) {
			// trim newline when comparing with lineToRemove
			// System.out.println(currentLine);
			if(counter>=1)
			{
				break;
			}
			Scanner scanner = null;
			try {
				scanner = new Scanner(tempFile);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			boolean alreadyDone=false;
			if (scanner != null) {
				String line;
				while (scanner.hasNextLine()) {
					line = scanner.nextLine();
					if (line.equalsIgnoreCase(currentLine)) {
						System.out.println("Already Done this:"+currentLine);
						alreadyDone=true;
						break;
					}
				}
			}
			if(alreadyDone)
			{
				continue;
			}
			writer.write(currentLine + "\n");
			for (String url : rpcList) {
				String log = pingDao.doPing("MeetAV", currentLine, url);
				if (log != null) {
					log_writer.write(log);
					log_writer.flush();
				}
			}

			writer.flush();
			if (counter > 5) {
				break;
			}
			Date now=new Date();
			System.out.println("Going to sleep..."+now.toString());
			log_writer.write("Going to sleep..."+now.toString());
			log_writer.flush();
//			Thread.sleep(120000);
			counter++;
		}
		Date now=new Date();
		System.out.println("---------finished----------:" + now.toString()+", counter:"+counter);
	
		log_writer.write("---------finished----------:" + now.toString()+", counter:"+counter+"\n");
		log_writer.flush();
		writer.close();
		log_writer.close();
		reader.close();
		
		// if (!inputFile.delete()) {
		// System.out.println("Could not delete file");
		// return;
		// }
		// if (!tempFile.renameTo(inputFile)) {
		// System.out.println("Could not rename file");
		// return;
		// }
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}

	public String doPing(String siteName, String siteURL, String rpcUrl) {
		// if (urls.size()<=0) return;
		// StringBuffer buf = new StringBuffer();
		// for (URL url : urls) {
		// buf.append(url+"\n");
		// }
		XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
		try {
			config.setServerURL(new URL(rpcUrl));
			XmlRpcClient client = new XmlRpcClient();
			client.setConfig(config);

			// Object[] params = new Object[]{
			// "(Optional)The name of your application here", buf.toString()
			// };
			Object[] params = new Object[] { siteName, siteURL };
			Object result = client.execute("weblogUpdates.ping", params);

			System.out.println("RPC:" + rpcUrl + ",Sent: " + siteURL
					+ ",Result: " + result + "\n");
			return "RPC:" + rpcUrl + ",Sent: " + siteURL + ",Result: " + result
					+ "\n";
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			System.out.println("ERROR-------------rpcUrl----------:" + rpcUrl);
		} catch (XmlRpcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("ERROR-------------rpcUrl----------:" + rpcUrl);
		}
		return null;
	}
}
