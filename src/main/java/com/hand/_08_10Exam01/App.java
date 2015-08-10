package com.hand._08_10Exam01;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		new GetPDF().start();
	}
}

class GetPDF extends Thread {
	// HttpClient client = HttpClients.createDefault();

	@Override
	public void run() {
		/*
		 * HttpGet get = new
		 * HttpGet("Http://www.manning.com/gsmith/SampleChapter1.pdf"); try {
		 * 
		 * HttpResponse response = client.execute(get); HttpEntity entity =
		 * response.getEntity(); String result = EntityUtils.toString(entity,
		 * "UTF-8"); // System.out.println(result);
		 * 
		 * DocumentBuilderFactory factory =
		 * DocumentBuilderFactory.newInstance(); DocumentBuilder builder =
		 * factory.newDocumentBuilder(); Document document =
		 * builder.newDocument();
		 * 
		 * TransformerFactory transformerFactory =
		 * TransformerFactory.newInstance(); Transformer transformer =
		 * transformerFactory.newTransformer();
		 * 
		 * transformer.transform(new DOMSource(document), new StreamResult(new
		 * File("SampleChapter1.pdf")));
		 * 
		 * File file = new File("SampleChapter2.pdf"); FileOutputStream fos =
		 * new FileOutputStream(file); OutputStreamWriter osw = new
		 * OutputStreamWriter(fos, "UTF-8"); BufferedWriter bw = new
		 * BufferedWriter(osw);
		 * 
		 * bw.write(result); bw.flush(); bw.close(); osw.close(); fos.close();
		 * System.out.println("写入完成！");
		 * 
		 * } catch (ClientProtocolException e) { e.printStackTrace(); } catch
		 * (IOException e) { e.printStackTrace(); }
		 */

//		output();
		transform();

	}

	public void output() {
		try {

			URL url = new URL("Http://www.manning.com/gsmith/SampleChapter1.pdf");
			URLConnection connection = url.openConnection();
			InputStream is = connection.getInputStream();
			InputStreamReader isr = new InputStreamReader(is, "UTF-8");
			BufferedReader br = new BufferedReader(isr);

			String line;

			StringBuilder builder = new StringBuilder();
			System.out.println("doing!!!");

			while ((line = br.readLine()) != null) {

				builder.append(line);// 把结果一条一条地添加到SringBuilder中
			}
			System.out.println("done!!!");

			File file = new File("SampleChapter1.pdf");
			FileOutputStream fos = new FileOutputStream(file);
			OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
			BufferedWriter bw = new BufferedWriter(osw);

			bw.write(builder.toString());
			bw.flush();
			System.out.println("完成");

			bw.close();
			osw.close();
			fos.close();

			br.close();
			isr.close();
			is.close();

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void transform() {
		try {
			URL url = new URL("Http://www.manning.com/gsmith/SampleChapter1.pdf");
			URLConnection connection = url.openConnection();
			InputStream is = connection.getInputStream();

			BufferedInputStream bis = new BufferedInputStream(is);

			FileOutputStream ios = new FileOutputStream("SampleChapter1.pdf");
			BufferedOutputStream bos = new BufferedOutputStream(ios);

			byte[] input = new byte[50];
//			byte[] output = new byte[1024];
			int len = 0;
			
			System.out.println("读取中……");

			while ((len = bis.read(input)) != -1) {
				// 写数据：
				bos.write(input,0,len);
			}
			System.out.println("done!!!");
			
			bos.close();// 关闭流，节省资源
			ios.close();
			bis.close();// 关闭流，节省资源
			is.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}