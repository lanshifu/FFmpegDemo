package com.tomatolive.library.utils.litepal.parser;

import android.content.res.AssetManager;
import android.content.res.Resources.NotFoundException;
import com.tomatolive.library.utils.litepal.LitePalApplication;
import com.tomatolive.library.utils.litepal.exceptions.ParseConfigurationFileException;
import com.tomatolive.library.utils.litepal.util.Const.Config;
import java.io.IOException;
import java.io.InputStream;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public class LitePalParser {
    static final String ATTR_CLASS = "class";
    static final String ATTR_VALUE = "value";
    static final String NODE_CASES = "cases";
    static final String NODE_DB_NAME = "dbname";
    static final String NODE_LIST = "list";
    static final String NODE_MAPPING = "mapping";
    static final String NODE_STORAGE = "storage";
    static final String NODE_VERSION = "version";
    private static LitePalParser parser;

    public static LitePalConfig parseLitePalConfiguration() {
        if (parser == null) {
            parser = new LitePalParser();
        }
        return parser.usePullParse();
    }

    private void useSAXParser() {
        try {
            XMLReader xMLReader = SAXParserFactory.newInstance().newSAXParser().getXMLReader();
            xMLReader.setContentHandler(new LitePalContentHandler());
            xMLReader.parse(new InputSource(getConfigInputStream()));
        } catch (NotFoundException unused) {
            throw new ParseConfigurationFileException("litepal.xml file is missing. Please ensure it under assets folder.");
        } catch (SAXException unused2) {
            throw new ParseConfigurationFileException("can not parse the litepal.xml, check if it's in correct format");
        } catch (ParserConfigurationException unused3) {
            throw new ParseConfigurationFileException("parse configuration is failed");
        } catch (IOException unused4) {
            throw new ParseConfigurationFileException("IO exception happened");
        }
    }

    private LitePalConfig usePullParse() {
        try {
            LitePalConfig litePalConfig = new LitePalConfig();
            XmlPullParser newPullParser = XmlPullParserFactory.newInstance().newPullParser();
            newPullParser.setInput(getConfigInputStream(), "UTF-8");
            for (int eventType = newPullParser.getEventType(); eventType != 1; eventType = newPullParser.next()) {
                String name = newPullParser.getName();
                if (eventType == 2) {
                    if (NODE_DB_NAME.equals(name)) {
                        litePalConfig.setDbName(newPullParser.getAttributeValue("", ATTR_VALUE));
                    } else if (NODE_VERSION.equals(name)) {
                        litePalConfig.setVersion(Integer.parseInt(newPullParser.getAttributeValue("", ATTR_VALUE)));
                    } else if (NODE_MAPPING.equals(name)) {
                        litePalConfig.addClassName(newPullParser.getAttributeValue("", ATTR_CLASS));
                    } else if (NODE_CASES.equals(name)) {
                        litePalConfig.setCases(newPullParser.getAttributeValue("", ATTR_VALUE));
                    } else if (NODE_STORAGE.equals(name)) {
                        litePalConfig.setStorage(newPullParser.getAttributeValue("", ATTR_VALUE));
                    }
                }
            }
            return litePalConfig;
        } catch (XmlPullParserException unused) {
            throw new ParseConfigurationFileException("can not parse the litepal.xml, check if it's in correct format");
        } catch (IOException unused2) {
            throw new ParseConfigurationFileException("IO exception happened");
        }
    }

    private InputStream getConfigInputStream() throws IOException {
        AssetManager assets = LitePalApplication.getContext().getAssets();
        String[] list = assets.list("");
        if (list != null && list.length > 0) {
            for (String str : list) {
                if (Config.CONFIGURATION_FILE_NAME.equalsIgnoreCase(str)) {
                    return assets.open(str, 3);
                }
            }
        }
        throw new ParseConfigurationFileException("litepal.xml file is missing. Please ensure it under assets folder.");
    }
}
