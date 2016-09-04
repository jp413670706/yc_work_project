package com.yc.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import com.yc.domian.Book;

public class TestSax {
	
	public static void main(String[] args) {
		TestSax ts = new TestSax();
		File file = new File("books.xml");
		//System.out.println(ts.findAll(file).size());
		List <Book>  list = ts.findAll(file);
		for(int i = 0;i<list.size();i++){
			System.out.println(list.get(i).getId()+"--"+list.get(i).getBookName());
		}
	}

	public List<Book> findAll(File file){
		List<Book> bookList = new ArrayList<Book>();
		
		//获取解析器工厂实例
		SAXParserFactory spf = SAXParserFactory.newInstance();
		
		try {
			SAXParser parser = spf.newSAXParser();
			
			SaxXML sx = new SaxXML();
			
			parser.parse(file, sx);
			
			bookList = sx.getBookList();
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
