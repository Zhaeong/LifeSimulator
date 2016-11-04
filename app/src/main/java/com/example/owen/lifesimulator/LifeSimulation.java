package com.example.owen.lifesimulator;

import android.content.Intent;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;

public class LifeSimulation extends AppCompatActivity {

    private int nHappiness = 0;
    private int curLevel = 1;

    private Random Randomizer = new Random();
    private  XMLParsing XMLParserObj;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_simulation);

        Intent intent = getIntent();
/*
        Resources res = getResources();

        XmlResourceParser Stories = res.getXml(R.xml.stories);
        String Gender = "unassigned";
        try {
            int event = Stories.getEventType();
            String text = "null";
            while (event != XmlPullParser.END_DOCUMENT) {
                String name = Stories.getName();

                switch (event) {
                    case XmlPullParser.START_TAG:
                        System.out.println(Stories.getName() + " " + Stories.getDepth());
                        if(name.equals("Possibilities"))
                        {
                            String[] stringlist = Stories.getAttributeListValue();
                        }
                        break;

                    case XmlPullParser.TEXT:
                        text = Stories.getText();

                        break;

                    case XmlPullParser.END_TAG:

                        if (name.equals("Message")) {
                            Gender = text;
                        }
                        break;
                }
                event = Stories.next();
            }

            setMainMessage(Gender);
        }
        catch (XmlPullParserException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        */
        try{

            Resources res = getResources();
            XmlResourceParser Stories = res.getXml(R.xml.stories);

            XMLParserObj = new XMLParsing(Stories);


            ArrayList<String> SubChoices =XMLParserObj.XMLGetSubnodes("Possibilities");
            int nGender = Randomizer.nextInt(2);
            setMainMessage(SubChoices.get(nGender));

        }
        catch(XmlPullParserException | IOException e)
        {
            e.printStackTrace();
        }



    }

    protected void setHappiness(int number)
    {
        nHappiness = number;
        TextView HapText = (TextView) findViewById(R.id.HappinessIndex);
        String sHappiness = Integer.toString(nHappiness);
        HapText.setText(sHappiness);
    }

    protected void setMainMessage(String sMessage)
    {
        TextView MainMsg = (TextView) findViewById(R.id.MainMessage);
        MainMsg.setText(sMessage);
    }

    //Returns a list of a subnodes of a node
    protected ArrayList<String> XMLGetSubnodes(String value)
            throws XmlPullParserException , IOException
    {
        ArrayList<String> Subnodes = new ArrayList<String>();

        Resources res = getResources();
        XmlResourceParser Stories = res.getXml(R.xml.stories);

        int event = Stories.getEventType();
        while (event != XmlPullParser.END_DOCUMENT) {

            //System.out.println(name);
            if(event == XmlPullParser.START_TAG) {
                String name = Stories.getName();
                if (name.equals(value)) {
                    event = Stories.next();
                    name = Stories.getName();
                    while (!name.equals(value)) {
                        if (event == XmlPullParser.START_TAG) {
                            Subnodes.add(name);
                        }
                        event = Stories.next();
                        name = Stories.getName();
                    }
                }
            }
            event = Stories.next();
        }
        return Subnodes;
    }

    protected String XMLGetMessageatLevel(int nlevel)
            throws XmlPullParserException , IOException
    {
        Resources res = getResources();
        XmlResourceParser Stories = res.getXml(R.xml.stories);
        String sMessage = "blank";
        int event = Stories.getEventType();
        while (event != XmlPullParser.END_DOCUMENT) {


            if(event == XmlPullParser.START_TAG)
            {
                String name = Stories.getName();
                if(name.equals("Level"))
                {
                    int nTagLevel = Stories.getAttributeIntValue(null, "nlevel", -1);
                    if(nTagLevel == nlevel)
                    {
                        while(event != XmlPullParser.END_DOCUMENT)
                        {

                        }
                    }
                }

            }

            event = Stories.next();
        }
        return sMessage;

    }

}
