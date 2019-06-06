package cn.digitalpublishing.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class XmlParser {

	public static String createHtmls(String root, String path) throws Exception {
		File workPath = new File(path);
		if (workPath.isDirectory()) {
			File[] files = workPath.listFiles();
			for (File f : files) {
				if (f.getPath().toString().toLowerCase().endsWith(".xml")) {
					create(f, root);
				}
			}
		} else if (path.toLowerCase().endsWith(".xml")) {
			return create(workPath, root);
		}

		// TODO
		return null;
	}

	@SuppressWarnings({ "unused", "unchecked" })
	public static String create(File xml, String root) throws Exception {
		SAXReader saxReader = new SAXReader();
		saxReader.setEntityResolver(new EntityResolver() {
			public InputSource resolveEntity(String publicId, String systemId) throws SAXException, IOException {
				return new InputSource(new ByteArrayInputStream("<?xml version='1.0' encoding='UTF-8'?>".getBytes()));
			}
		});
		Document document = saxReader.read(xml);

		// 第1部分
		Node publisherName = document.getRootElement().selectSingleNode("/page/publisher/name");
		Node logourl = document.getRootElement().selectSingleNode("/page/publisher/logourl");
		Node publisherIntroduce = document.getRootElement().selectSingleNode("/page/publisher/introduce");
		Node background = document.getRootElement().selectSingleNode("/page/publisher/background");
		Node publisherUrl = document.getRootElement().selectSingleNode("/page/publisher/url");
		StringBuilder textCont = new StringBuilder();
		textCont.append("<div class=\"main\">");
		// 背景图片
		if (background.getText() != null && !background.getText().equals("")) {
			textCont.append("<div style=\"padding: 10px; border:1px solid #dadada; margin: 10px 0; overflow: hidden; background:url(../images/bgText2.jpg) repeat; background:" + background.getText() + "\">" + "\r\n");
		} else {
			textCont.append("<div style=\"padding: 10px; border:1px solid #dadada; margin: 10px 0; overflow: hidden; background:none\">" + "\r\n");
		}
		textCont.append("<a href=\"" + publisherUrl.getText() + "\">");
		textCont.append("<h1 style=\"text-align: center; background: none; border-bottom: none; font-size: 26px; padding-left: 0; margin-bottom: 20px;\">" + publisherName.getText() + "<span>" + "<img style=\"vertical-align:middle; margin-left:5px; width:auto;height:50px" + "\" src=\"" + logourl.getText() + "\"/></span></h1></a>" + "\r\n");
		textCont.append(publisherIntroduce.getText().replace("<h2>", "<h2 style='font-size: 16px; margin-top: 10px; margin-bottom: 8px;'>").replace("<ul  style=\"float:left;width:260px;\">", "<ul style=\"float: left; width: 222px;\">") + "\r\n");
		textCont.append("</div>\r\n");

		// 第2部分
		List<Node> subjects = document.getRootElement().selectNodes("/page/subject");
		int i = 0;

		for (Node node : subjects) {
			Node subName = node.selectSingleNode("child::name");
			Node subIntroduce = node.selectSingleNode("child::introduce");
			Node subHeadcolor = node.selectSingleNode("child::head-color");
			Node subBackcolor = node.selectSingleNode("child::back-color");
			textCont.append("<div style='padding: 10px; border:1px solid #dadada; margin: 10px 0; overflow: hidden; background-image: none;'>" + "\r\n");
			StringBuilder sbback = new StringBuilder();
			sbback.append("style=\"");
			if (subHeadcolor.getText() != null && !subHeadcolor.getText().equals("")) {
				sbback.append("background:" + subHeadcolor.getText());
			}
			if (subBackcolor.getText() != null && !subBackcolor.getText().equals("")) {
				sbback.append(";color:" + subBackcolor.getText());
			}
			sbback.append("\"");
			if (subHeadcolor.getText() != null && !subHeadcolor.getText().equals("") && subBackcolor.getText() != null && !subBackcolor.getText().equals("")) {
				textCont.append("<h1 style='height: 50px; line-height: 50px; background: #0081cb; color: #fff; text-align: center; font-size: 26px; padding-left: 0; margin-bottom: 20px;'" + sbback + ">" + subName.getText() + "</h1>" + subIntroduce.getText() + "\r\n");
			} else if (subHeadcolor.getText() != null && !subHeadcolor.getText().equals("")) {

			}
			textCont.append("<div style='float:left; border-top:1px solid #fff; margin-bottom:10px; overflow:hidden;'>" + "\r\n");

			List<Node> publications = node.selectNodes("child::publication");
			for (Node pubNode : publications) {
				Node pubName = pubNode.selectSingleNode("child::name");
				Node pubCover = pubNode.selectSingleNode("child::cover");
				Node pubUrl = pubNode.selectSingleNode("child::url");
				Node pubAuthor = pubNode.selectSingleNode("child::author");
				Node pubIsbn = pubNode.selectSingleNode("child::isbn");

				textCont.append("<div style='width: 210px; float:left; margin:10px 0 10px 10px; padding-left: 20px; height:280px;'>" + "\r\n");
				textCont.append("<p style='text-indent:0; text-align:center;'><a target=\"_blank\" href=\"" + pubUrl.getText() + "\"><img style='width: 140px; height: 180px; margin-bottom: 10px;' src=\"" + pubCover.getText() + "\" /></a></p>" + "\r\n");
				textCont.append("<p style='text-indent:0; text-align:center; overflow:hidden; text-overflow: initial; height: 40px;'><a target=\"_blank\" href=\"" + pubUrl.getText() + "\">" + pubName.getText() + "</a></p>" + "\r\n");
				textCont.append("<p style='text-indent:0; text-align:center; overflow:hidden; text-overflow: initial; height: 40px;'>" + pubAuthor.getText() + "</p>" + "\r\n");
				textCont.append("</div>" + "\r\n");

				i++;
			}
			textCont.append("</div>" + "\r\n");
			textCont.append("</div>" + "\r\n");
		}
		textCont.append("</div>");
		// String htmlFile = xml.getParent() + "/" +
		// xml.getName().toLowerCase().replace(" ", "_").replace(".xml",
		// ".html");
		String htmlFile = root + xml.getName().toLowerCase().replace(" ", "_").replace(".xml", ".html");
		File txt = new File(htmlFile);
		OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(txt), "UTF-8");
		out.write(textCont.toString().toCharArray());
		out.flush();
		out.close();
		return textCont.toString();
	}

	// main
	public static void main(String[] args) throws Exception {
		System.out.println(XmlParser.createHtmls("/root/Desktop/", "Bentham Science Publishers.xml"));
	}

}