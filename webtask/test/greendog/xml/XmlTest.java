package greendog.xml;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.junit.Test;

public class XmlTest {

	public void befor(){
		
	}
	public void after(){
		
	}
	
	@Test
	public void writeXML(){
		Document document = DocumentHelper.createDocument();
		Element root = document.addElement("root"); 
		
		Element e1 = root.addElement("element1");
		e1.addAttribute("id", "123");
		
		Element e2 = DocumentHelper.createElement("user");
		e2.setText("张飞");
		root.add(e2);
		try {
			Writer writer = new FileWriter("d:/a.xml");
			XMLWriter xmlWriter = new XMLWriter(writer); 
			xmlWriter.write(document);
			xmlWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void readXML(){
		File file = new File("d:/a.xml");
		SAXReader reader = new SAXReader();
		try {
			Document document = reader.read(file);
			Element rootElement = document.getRootElement();
			System.out.println("root_name:"+rootElement.getName());
			System.out.println(rootElement.element("user").getText());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
