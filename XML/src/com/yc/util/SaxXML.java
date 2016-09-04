package com.yc.util;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.yc.domian.Book;

public class SaxXML extends DefaultHandler {
	
	private  List<Book> bookList;
	
	private Book  book;
	
	private String tagName;   //存放每一次读到的标签名
	
	


    //当解析器解析到book元素时  触发
	@Override
	public void startDocument() throws SAXException {
		System.out.println("startDocument开始了");
		bookList = new ArrayList<Book> ();
	}

	@Override
	public void endDocument() throws SAXException {
		System.out.println("endDocument结束了");
		
	}
	//当解析器解析到元素节点时  触发
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		System.out.println("startElement开始了");
		//判断是不是book元素  如果是 初始化
		if("book".equals(qName)){
			book  = new Book();
			book.setId(Integer.parseInt(attributes.getValue("id")));
		}
		tagName = qName;
	}

	//元素结束时
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		System.out.println("endElement结束了");
		if("book".equals(qName)){
			bookList.add(book);
			book = null;
		}
		tagName = "";
	}

	//当每次解析文本节点   
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		System.out.println("characters开始了");
		if(book != null){
			String content = new String (ch,start,length);
			
			if("bookName".equals(tagName)){
				book.setBookName(content);
			}else if("bookAuthor".equals(tagName)){
				book.setBookAuthor(content);
			}else if("bookISBN".equals(tagName)){
				book.setBookISBN(content);
			}else if("bookPrice".equals(tagName)){
				book.setBookPrice(Double.parseDouble(content));
			}
		}
	}

	
	public List<Book> getBookList() {
		return bookList;
	}

	public void setBookList(List<Book> bookList) {
		this.bookList = bookList;
	}
}
