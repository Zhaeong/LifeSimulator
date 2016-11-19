package com.example.owen.lifesimulator;

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
    private StoryNode parentNode;


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
