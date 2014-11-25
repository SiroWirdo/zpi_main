package xml.reader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

public class XMLReader {
	private final String PATH = "src/main/resources/layout/layout.xml";

	public int readInt(String object, String name){
		int value = 0;
		try{
			XMLInputFactory inputFactory = XMLInputFactory.newInstance();
			InputStream in = new FileInputStream(PATH);
			XMLEventReader eventReader = inputFactory.createXMLEventReader(in);

			while(eventReader.hasNext()){
				XMLEvent event = eventReader.nextEvent();

				if(event.isStartElement()){
					StartElement startElement = event.asStartElement();

					if(startElement.getName().getLocalPart().equals(object)){
						event = eventReader.nextEvent();
						value = getIntValue(name, eventReader);
						break;
					}
				}
			}
		} catch (FileNotFoundException e) {
		      e.printStackTrace();
	    } catch (XMLStreamException e) {
	      e.printStackTrace();
	    }
		return value;
	}

	public int getIntValue(String name, XMLEventReader eventReader){
		int value = 0;
		try{

			while(eventReader.hasNext()){
				XMLEvent event = eventReader.nextEvent();

				if(event.isStartElement()){
					StartElement startElement = event.asStartElement();

					if(startElement.getName().getLocalPart().equals(name)){
						event = eventReader.nextEvent();
						value = new Integer(event.asCharacters().getData());
						break;
					}
				}
			}
	    } catch (XMLStreamException e) {
	      e.printStackTrace();
	    }
		return value;
	}

	public String readString(String object, String name){
		String value = "";
		try{
			XMLInputFactory inputFactory = XMLInputFactory.newInstance();
			InputStream in = new FileInputStream(PATH);
			XMLEventReader eventReader = inputFactory.createXMLEventReader(in);

			while(eventReader.hasNext()){
				XMLEvent event = eventReader.nextEvent();

				if(event.isStartElement()){
					StartElement startElement = event.asStartElement();

					if(startElement.getName().getLocalPart().equals(object)){
						event = eventReader.nextEvent();
						value = getStringValue(name, eventReader);
						break;
					}
				}
			}
		} catch (FileNotFoundException e) {
		      e.printStackTrace();
	    } catch (XMLStreamException e) {
	      e.printStackTrace();
	    }
		return value;
	}

	public String getStringValue(String name, XMLEventReader eventReader){
		String value = "";
		try{

			while(eventReader.hasNext()){
				XMLEvent event = eventReader.nextEvent();

				if(event.isStartElement()){
					StartElement startElement = event.asStartElement();

					if(startElement.getName().getLocalPart().equals(name)){
						event = eventReader.nextEvent();
						value = new String(event.asCharacters().getData());
						break;
					}
				}
			}
	    } catch (XMLStreamException e) {
	      e.printStackTrace();
	    }
		return value;
	}
}
