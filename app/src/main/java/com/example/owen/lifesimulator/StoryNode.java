package com.example.owen.lifesimulator;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by owen_ on 2016-11-17.
 * A story tree obj to easily traverse the story
 */

public class StoryNode {
    private String storyText;
    private String ChoiceAText;
    private String ChoiceBText;
    private StoryNode subNodeA;
    private StoryNode subNodeB;


    public StoryNode(String mainText, String ChoiceAtext, String ChoiceBtext)
    {
        this.storyText = mainText;
        this.ChoiceAText = ChoiceAtext;
        this.ChoiceBText = ChoiceBtext;
    }

    public void AssignSubnodeA(StoryNode Node)
    {
        this.subNodeA = Node;
    }
    public void AssignSubnodeB(StoryNode Node)
    {
        this.subNodeB = Node;
    }

    public StoryNode GetSubnodeA()
    {
        return subNodeA;
    }
    public StoryNode GetSubnodeB()
    {
        return subNodeB;
    }

    public void AssignTextA(String TextA)
    {
        if(!TextA.isEmpty())
            ChoiceAText = TextA;
    }

    public void AssignTextB(String TextB)
    {
        if(!TextB.isEmpty())
            ChoiceBText = TextB;
    }


    public String DisplayMainText()
    {
        return storyText;
    }
    public String DisplayAText()
    {
        return ChoiceAText;
    }
    public String DisplayBText()
    {
        return ChoiceBText;
    }
}
