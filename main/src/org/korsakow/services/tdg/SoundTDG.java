package org.korsakow.services.tdg;

import javax.xml.xpath.XPathException;
import javax.xml.xpath.XPathExpressionException;

import org.korsakow.ide.DataRegistry;
import org.korsakow.ide.XPathHelper;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class SoundTDG
{
	public static final String NODE_NAME = "Sound";
	
	public static int insert(long version, String name, String filename, String subtitles) throws XPathException{
		return insert(DataRegistry.getMaxId(), version, name, filename, subtitles);
	}
	public static int insert(long id, long version, String name, String filename, String subtitles) throws XPathExpressionException{
		Element element = DataRegistry.getHelper().appendElement("/korsakow/sounds", NODE_NAME);
		DataRegistry.getHelper().setLong(element, "id", id);
		DataRegistry.getHelper().setLong(element, "version", version);
		return update(id, version, name, filename, subtitles);
	}

	public static int delete(long id, long version) throws XPathExpressionException {
		NodeList list = DataRegistry.getHelper().removeNodes(XPathHelper.formatQuery("/korsakow/sounds/Sound[id=? and version=?]", id, version));
		return list.getLength();
	}
	
	public static int update(long id, long version, String name, String filename, String subtitles) throws XPathExpressionException{
		Element element = DataRegistry.getHelper().findElementByIdTag(id);
//		Element element = DataRegistry.getHelper().xpathAsElement(XPathHelper.formatQuery("/korsakow/sounds/Sound[id=? and version=?]", id, version));
		if (element == null)
			return 0;
		DataRegistry.getHelper().setLong(element, "id", id);
		DataRegistry.getHelper().setLong(element, "version", version+1);
		DataRegistry.getHelper().setString(element, "name", name);
		DataRegistry.getHelper().setString(element, "filename", filename);
		DataRegistry.getHelper().setString(element, "subtitles", subtitles);
		return 1;
	}
	
	public static void  createSoundTable() throws XPathExpressionException {
		if (DataRegistry.getHelper().xpathAsElement("/korsakow/sounds") == null) {
			DataRegistry.getHelper().appendElement("/korsakow", "sounds");
		}
	}
	public static void dropTable () throws XPathExpressionException{
		DataRegistry.getHelper().removeNodes(XPathHelper.formatQuery("/korsakow/sounds"));
	}
}
