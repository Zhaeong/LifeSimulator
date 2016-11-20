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
    private Random Randomizer = new Random();

    private ArrayList<String> ChosenMessage;

    private StoryNode Storyroot;
    private StoryNode curNode;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_simulation);

        Intent intent = getIntent();

        try{

            Resources res = getResources();
            XmlResourceParser Stories = res.getXml(R.xml.stories);

            XMLParsing XMLParserObj = new XMLParsing(Stories);

            Storyroot = XMLParserObj.BuildStoryTree();
            curNode = Storyroot;
            SetCard();

        }
        catch(XmlPullParserException | IOException e)
        {
            e.printStackTrace();
        }

    }
    public void SetCard()
    {
        setMainMessage(curNode.DisplayMainText());
        setChoiceA(curNode.DisplayAText());
        setChoiceB(curNode.DisplayBText());
    }

    public void ClickedButtonA(View view)
    {
        if(curNode.GetSubnodeA() != null)
        {
            curNode =  curNode.GetSubnodeA();
            SetCard();
        }


    }

    public void ClickedButtonB(View view)
    {
        if(curNode.GetSubnodeB() != null)
        {
            curNode =  curNode.GetSubnodeB();
            SetCard();
        }

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
