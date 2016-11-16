package com.example.owen.lifesimulator;

import android.content.Intent;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class LifeSimulation extends AppCompatActivity {
    private int curLevel = 0;
    private int maxLevel = 10;
    private int nHappiness = 0;

    private Random Randomizer = new Random();

    private ArrayList<String> ChosenMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_simulation);

        Intent intent = getIntent();
        SetCard();
    }
    public void SetCard()
    {
        try{

            Resources res = getResources();
            XmlResourceParser Stories = res.getXml(R.xml.stories);

            XMLParsing XMLParserObj = new XMLParsing(Stories);

            ArrayList<ArrayList> AllMessage = XMLParserObj.getMessagesForLevel(curLevel);
            int nMsgNum = Randomizer.nextInt(AllMessage.size());
            ArrayList<String> ChosenMsg = AllMessage.get(nMsgNum);
            ChosenMessage = ChosenMsg;
            setMainMessage(ChosenMsg.get(0));
            setChoiceA(ChosenMsg.get(1));
            setChoiceB(ChosenMsg.get(4));

        }
        catch(XmlPullParserException | IOException e)
        {
            e.printStackTrace();
        }

    }

    public void ClickedButtonA(View view)
    {
        if(curLevel < maxLevel)
        {
            curLevel++;
            int CardHappiness = Integer.parseInt(ChosenMessage.get(2));
            nHappiness+=CardHappiness;
            setHappiness(nHappiness);
            SetCard();
        }

    }

    public void ClickedButtonB(View view)
    {
        if(curLevel < maxLevel)
        {
            curLevel++;
            int CardHappiness = Integer.parseInt(ChosenMessage.get(5));
            nHappiness+=CardHappiness;
            setHappiness(nHappiness);
            SetCard();
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

    protected  void setChoiceA(String ChoiceA)
    {
        Button ButtonA = (Button) findViewById(R.id.button_A);
        ButtonA.setText(ChoiceA);
    }

    protected  void setChoiceB(String ChoiceB)
    {
        Button ButtonA = (Button) findViewById(R.id.button_B);
        ButtonA.setText(ChoiceB);
    }



}
