package com.example.owen.lifesimulator;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by owen_ on 2016-11-17.
 * A story tree obj to easily traverse the story
 */

class StoryNode {
    String storyText;
    String ChoiceAText;
    String ChoiceBText;
    StoryNode subNodeA;
    StoryNode subNodeB;


    StoryNode(String mainText, String ChoiceAtext, String ChoiceBtext)
    {
        this.storyText = mainText;
        this.ChoiceAText = ChoiceAtext;
        this.ChoiceBText = ChoiceBtext;
    }

    void AssignSubnodeA(StoryNode Node)
    {
        this.subNodeA = Node;
    }
    void AssignSubnodeB(StoryNode Node)
    {
        this.subNodeB = Node;
    }

    StoryNode GetSubnodeA()
    {
        return subNodeA;
    }
    StoryNode GetSubnodeB()
    {
        return subNodeB;
    }

    void AssignTextA(String TextA)
    {
        if(!TextA.isEmpty())
            ChoiceAText = TextA;
    }

    void AssignTextB(String TextB)
    {
        if(!TextB.isEmpty())
            ChoiceBText = TextB;
    }


    String DisplayMainText()
    {
        if(storyText == null)
        {
            return "";
        }
        else
        {
            return storyText;
        }

    }
    String DisplayAText()
    {
        if(ChoiceAText == null)
        {
            return "";
        }
        else
        {
            return ChoiceAText;
        }
    }
    String DisplayBText()
    {
        if(ChoiceBText == null)
        {
            return "";
        }
        else
        {
            return ChoiceBText;
        }
    }
}
