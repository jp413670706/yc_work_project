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
	
	private String tagName;   //���ÿһ�ζ����ı�ǩ��
	
	


    //��������������bookԪ��ʱ  ����
	@Override
	public void startDocument() throws SAXException {
		System.out.println("startDocument��ʼ��");
		bookList = new ArrayList<Book> ();
	}

	@Override
	public void endDocument() throws SAXException {
		System.out.println("endDocument������");
		
	}
	//��������������Ԫ�ؽڵ�ʱ  ����
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		System.out.println("startElement��ʼ��");
		//�ж��ǲ���bookԪ��  ����� ��ʼ��
		if("book".equals(qName)){
			book  = new Book();
			book.setId(Integer.parseInt(attributes.getValue("id")));
		}
		tagName = qName;
	}

	//Ԫ�ؽ���ʱ
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		System.out.println("endElement������");
		if("book".equals(qName)){
			bookList.add(book);
			book = null;
		}
		tagName = "";
	}

	//��ÿ�ν����ı��ڵ�   
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		System.out.println("characters��ʼ��");
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
