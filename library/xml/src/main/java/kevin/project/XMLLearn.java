package kevin.project;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.*;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.IOException;
import java.io.InputStream;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class XMLLearn {
    public static void sax() throws ParserConfigurationException, SAXException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();
        try (InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("test.xml")
        ) {
            saxParser.parse(is, new MyHandler());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void stax() {
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        try (InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("test.xml")
        ) {
            XMLEventReader reader = xmlInputFactory.createXMLEventReader(is);
            while (reader.hasNext()) {
                XMLEvent nextEvent = reader.nextEvent();
                System.out.println(nextEvent.getLocation());
                if (nextEvent.isStartElement()) {
                    StartElement startElement = nextEvent.asStartElement();
                    System.out.println(startElement.getName());
                    return;
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (XMLStreamException e) {
            throw new RuntimeException(e);
        }

    }

    public static void document() throws ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        try (InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("test.xml")
        ) {

            Document document = builder.parse(is);
            Element root = document.getDocumentElement();
            System.out.println(root);
        } catch (IOException | SAXException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws ParserConfigurationException, SAXException {
        sax();
        stax();
        document();

    }

    static class MyHandler extends DefaultHandler {
        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            System.out.println(uri + localName + qName + attributes);
        }
    }
}