package com.example.owen.lifesimulator;

import android.content.res.XmlResourceParser;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by owen_ on 2016-10-28.
 */

public class XMLParsing {

    private XmlResourceParser XMLParser;

    public XMLParsing(XmlResourceParser XmlParser){
        this.XMLParser = XmlParser;
    }


    public ArrayList<String> XMLGetSubnodes(String value)
            throws XmlPullParserException , IOException
    {
        ArrayList<String> Subnodes = new ArrayList<String>();
        int event = XMLParser.getEventType();

        while (event != XmlPullParser.END_DOCUMENT) {

            //System.out.println(name);
            if(event == XmlPullParser.START_TAG) {
                String name = XMLParser.getName();
                if (name.equals(value)) {
                    event = XMLParser.next();
                    name = XMLParser.getName();
                    while (!name.equals(value)) {
                        if (event == XmlPullParser.START_TAG) {
                            Subnodes.add(name);
                        }
                        event = XMLParser.next();
                        name = XMLParser.getName();
                    }
                }
            }
            event = XMLParser.next();
        }

        return Subnodes;
    }

}
