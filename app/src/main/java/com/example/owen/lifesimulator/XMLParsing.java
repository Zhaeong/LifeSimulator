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

    private ArrayList<String> getMessage()
            throws XmlPullParserException , IOException
    {
        ArrayList<String> Message = new ArrayList<>();
        int event = XMLParser.getEventType();
        //Get Message Text
        if(event == XmlPullParser.START_TAG)
        {
            if("Message".equals(XMLParser.getName()))
            {
                String Msg = XMLParser.getAttributeValue(null, "txt");
                Message.add(Msg);

            }

        }
        event = XMLParser.next(); //get ChoiceA
        if(event == XmlPullParser.START_TAG)
        {
            if("ChoiceA".equals(XMLParser.getName()))
            {
                String Happiness = XMLParser.getAttributeValue(null, "happiness");
                String Money = XMLParser.getAttributeValue(null, "money");
                XMLParser.next();
                String ChoiceA = XMLParser.getText();
                Message.add(ChoiceA);
                Message.add(Happiness);
                Message.add(Money);

            }
        }
        XMLParser.next(); //get ChoiceB
        event = XMLParser.next();
        if(event == XmlPullParser.START_TAG)
        {
            if("ChoiceB".equals(XMLParser.getName()))
            {
                String Happiness = XMLParser.getAttributeValue(null, "happiness");
                String Money = XMLParser.getAttributeValue(null, "money");
                XMLParser.next(); //Get choiceB text
                String ChoiceB = XMLParser.getText();
                Message.add(ChoiceB);
                Message.add(Happiness);
                Message.add(Money);

            }
        }
        return Message;

    }


}
