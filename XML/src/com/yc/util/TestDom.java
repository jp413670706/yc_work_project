package com.yc.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.yc.domian.Book;

public class TestDom {

	public static void main(String[] args) {
		TestDom  td = new TestDom();
		File file = new File("books.xml");
		List<Book> list = td.getBooks(file);
		for(int i = 0 ;i<list.size();i++){
			System.out.println(list.get(i).getBookName()+"--"+list.get(i).getBookAuthor());
		}
	}

	public List<Book> getBooks(File file){
		List <Book> bookList = new ArrayList<Book>();

		try {
			//创建一个文档工厂对象，解析器对象
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

			//通过工厂对象生产一个DocumentBuilder对象

			DocumentBuilder builder =  factory.newDocumentBuilder();

			//接指定的文件File解析 创建一个新的Document对象
			Document  doc = builder.parse(file);

			Element element = doc.getDocumentElement();
			//			System.out.println(element);
			NodeList nodeList = doc.getElementsByTagName("book");  //获取到book节点的对象

			//			System.out.println(nodeList.getLength());

			int len = nodeList.getLength();
			
			for(int i = 0 ; i<len; i++){
				Book  book = new Book();
				int len2 = nodeList.item(i).getChildNodes().getLength();

				for(int j=0 ; j<len2;j++){
					Node node = nodeList.item(i).getChildNodes().item(j);

					if(node.getNodeType() == 1){   //1-ELEMENT 2-ATTRIBUTE 3-TEXT
						String content  = node.getFirstChild().getNodeValue();
						//System.out.println(node.getNodeName()+"--"+content);
						if("bookName".equals(node.getNodeName())){
							book.setBookName(content);
						}else if("bookAuthor".equals(node.getNodeName())){
							book.setBookAuthor(content);
						}else if("bookISBN".equals(node.getNodeName())){
							book.setBookISBN(content);
						}else if("bookPrice".equals(node.getNodeName())){
							book.setBookPrice(Double.parseDouble(content));
						}

					}
				}
                bookList.add(book);
				//System.out.println(nodeList.item(i).getChildNodes().item(3).getFirstChild().getNodeValue());
			}



		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}




		return bookList;
	}

}
