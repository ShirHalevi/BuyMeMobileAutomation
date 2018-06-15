import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class SetUpXML {
    public static String getAppSetting(String mobileApp) throws Exception {
        //return the app package or app activity from the xml file
        File fXmlFile = new File("\\C:\\Users\\shir halevi\\Desktop\\qaexpert\\buyMeMobile\\package.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(fXmlFile);
        doc.getDocumentElement().normalize();
        return doc.getElementsByTagName(mobileApp).item(0).getTextContent();
    }
}
