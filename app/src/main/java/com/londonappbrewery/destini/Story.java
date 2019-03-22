package com.londonappbrewery.destini;

import java.util.ArrayList;
import java.util.List;

public class Story {
    private int mStoryTextViev;
    private int mAnswearTop;
    private  int mAnswearBottom;
    private  boolean mStoryEnd;
    private List<Integer> mStoryList;

    public final static int TOP_STORY=1;// check top answear
    public final static int BOTTOM_STORY=0;// check bottom answear
    public final static  int START_STORY=2;//beginning of your story

    public Story(int storyTextViev, int answearTop, int answearBottom, List<Integer> storyList)
    {
        mStoryList= new ArrayList<Integer>();
        mStoryList.addAll(storyList);
        mAnswearTop =answearTop;
        mAnswearBottom =answearBottom;
        mStoryTextViev=storyTextViev;
        mStoryEnd=false;
    }
    public Story(int storyTextViev, List<Integer> storyList)
    {
        mStoryList= new ArrayList<Integer>();
        mStoryList.addAll(storyList);
        mStoryTextViev=storyTextViev;
        mStoryEnd=true;
    }
    public boolean isStoryEnd() {
        return mStoryEnd;
    }

    public void setStoryEnd(boolean storyEnd) {
        mStoryEnd = storyEnd;
    }
    public boolean isItTheSameStory(List<Integer> storyList)
    {
        if(mStoryList.size()!=storyList.size()) {
            return false;
        }
            for(int i=0; i<mStoryList.size();i++)
            {
                if(storyList.get(i)!=mStoryList.get(i))
                {
                    return false;
                }
            }
        return true;

    }
    public int getStoryTextViev() {
        return mStoryTextViev;
    }

    public void setStoryTextViev(int storyTextViev) {
        mStoryTextViev = storyTextViev;
    }

    public int getAnswearTop() {
        return mAnswearTop;
    }

    public void setAnswearTop(int answearTop) {
        mAnswearTop = answearTop;
    }

    public int getAnswearBottom() {
        return mAnswearBottom;
    }

    public void setAnswearBottom(int answearBottom) {
        mAnswearBottom = answearBottom;
    }
}
