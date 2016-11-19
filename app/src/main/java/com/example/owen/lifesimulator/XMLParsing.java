package com.example.owen.lifesimulator;

import android.content.res.XmlResourceParser;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by owen_ on 2016-10-28.
 * This is a class to parse the xml for stories
 */

public class XMLParsing {

    private XmlResourceParser XMLParser;

    public XMLParsing(XmlResourceParser XmlParser){
        this.XMLParser = XmlParser;
    }


    public ArrayList<String> XMLGetSubnodes(String value)
            throws XmlPullParserException , IOException
    {
        ArrayList<String> Subnodes = new ArrayList<>();
        int event = XMLParser.getEventType();

        while (event != XmlPullParser.END_DOCUMENT) {

            //System.out.println(name);
            if(event == XmlPullParser.START_TAG) {
                String name = XMLParser.getName();
                if (name.equals(value)) {
                    event = XMLParser.next();
                    name = XMLParser.getName();
                    while (!value.equals(name)) {
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

    public ArrayList<ArrayList>getMessagesForLevel(int nLevel)
            throws XmlPullParserException , IOException
    {
        ArrayList<ArrayList> MessagesArray = new ArrayList<>();
        int event = XMLParser.getEventType();
        String sLevel = "Level" + Integer.toString(nLevel);

        while (event != XmlPullParser.END_DOCUMENT)
        {
            if(event == XmlPullParser.START_TAG)
            {
                if("Level".equals(XMLParser.getName()))
                {
                    String sLevelNumber = XMLParser.getAttributeValue(null, "nlevel");
                    if(Integer.parseInt(sLevelNumber) == nLevel)
                    {
                        event = XMLParser.next();
                        while (!"Level".equals(XMLParser.getName()))
                        {
                            if(event == XmlPullParser.START_TAG)
                            {
                                if("Message".equals(XMLParser.getName()))
                                {
                                    ArrayList<String> Message = getMessage();
                                    MessagesArray.add(Message);
                                }
                            }
                            String namer = XMLParser.getName();
                            event = XMLParser.next();

                        }
                    }
                }
            }
            event = XMLParser.next();
        }
        return MessagesArray;

    }

    public StoryNode BuildStoryTree()
            throws XmlPullParserException , IOException
    {
        StoryNode rootNode = null;

        int event = XMLParser.next();

        while (event != XmlPullParser.END_DOCUMENT)
        {
            if(event == XmlPullParser.START_TAG)
            {
                String tagName = XMLParser.getName();
                if("Message".equals(tagName))
                {
                    String storyText = XMLParser.getAttributeValue(null, "txt");
                    if("end".equals(storyText))
                    {
                        rootNode = new StoryNode(storyText, null, null);
                    }
                    else
                    {
                        rootNode = BuildStoryTree();
                    }
                }

                if("ChoiceA".equals(tagName))
                {
                    String ChoiceAtext = XMLParser.getAttributeValue(null, "txt");
                }

            }

        }


        return  rootNode;
    }


}
