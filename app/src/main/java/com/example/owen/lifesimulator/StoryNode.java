package com.example.owen.lifesimulator;

import java.util.ArrayList;

/**
 * Created by owen_ on 2016-11-17.
 * A story tree obj to easily traverse the story
 */

public class StoryNode {
    private String storyText;
    private String ChoiceAText;
    private String ChoiceBText
    private ArrayList<StoryNode> subNodes;

    public StoryNode(String nodeValue)
    {
        this.storyText = nodeValue;
    }

    public void AddSubNode(StoryNode Node)
    {
        this.subNodes.add(Node);
    }
    public String DisplayValue()
    {
        return storyText;
    }
}
