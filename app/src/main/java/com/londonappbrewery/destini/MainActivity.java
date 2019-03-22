package com.londonappbrewery.destini;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // TODO: Steps 4 & 8 - Declare member variables here:
        private Button mAnswearTopButton;
        private Button mAnswearBottomButtonm;
        private TextView mStoryTextView;
        private List<Integer> mMyStoryList;
        private List<Story> mStories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // TODO: Step 5 - Wire up the 3 views from the layout to the member variables:
    mAnswearTopButton=findViewById(R.id.buttonTop);
    mAnswearBottomButtonm=findViewById(R.id.buttonBottom);
    mStoryTextView=findViewById(R.id.storyTextView);
    mMyStoryList= new ArrayList<Integer>();
    mStories= new ArrayList<Story>();
    mStories.add(new Story(R.string.T1_Story,R.string.T1_Ans1,R.string.T1_Ans2, createNewListStory(new int[]{Story.START_STORY})));
    mStories.add(new Story(R.string.T2_Story,R.string.T2_Ans1,R.string.T2_Ans2, createNewListStory(new int[]{Story.BOTTOM_STORY})));
    mStories.add(new Story(R.string.T3_Story,R.string.T3_Ans1,R.string.T3_Ans2, createNewListStory(new int[]{Story.TOP_STORY})));
    mStories.add(new Story(R.string.T3_Story,R.string.T3_Ans1,R.string.T3_Ans2, createNewListStory(new int[]{Story.BOTTOM_STORY,Story.TOP_STORY})));
    mStories.add(new Story(R.string.T6_End, createNewListStory(new int[]{Story.BOTTOM_STORY,Story.TOP_STORY,Story.TOP_STORY})));
    mStories.add(new Story(R.string.T5_End, createNewListStory(new int[]{Story.BOTTOM_STORY,Story.TOP_STORY,Story.BOTTOM_STORY})));
    mStories.add(new Story(R.string.T4_End, createNewListStory(new int[]{Story.BOTTOM_STORY,Story.BOTTOM_STORY})));
    mStories.add(new Story(R.string.T6_End, createNewListStory(new int[]{Story.TOP_STORY,Story.TOP_STORY})));
    mStories.add(new Story(R.string.T5_End, createNewListStory(new int[]{Story.TOP_STORY,Story.BOTTOM_STORY})));

        if(savedInstanceState !=null)
        {
            mMyStoryList.clear();
            mMyStoryList.addAll(savedInstanceState.getIntegerArrayList("UserStoryKey"));
            changeYourStory();
        }


        // TODO: Steps 6, 7, & 9 - Set a listener on the top button:

        mAnswearTopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewSituation(Story.TOP_STORY);
                changeYourStory();
            }
        });



        // TODO: Steps 6, 7, & 9 - Set a listener on the bottom button:
        mAnswearBottomButtonm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewSituation(Story.BOTTOM_STORY);
                changeYourStory();
            }
        });

    }
    private List<Integer> createNewListStory(int[] tab){
        List<Integer> temp=new ArrayList<Integer>();
        for(int i=0; i<tab.length;i++)
        {
            temp.add(tab[i]);
        }
        return temp;
    }
    private void addNewSituation(int choice)
    {
        Toast.makeText(this,"You made a decision",Toast.LENGTH_SHORT).show();
        mMyStoryList.add(choice);
    }
    private void changeYourStory()
    {
        int index=0;
        for(int i=0; i<mStories.size(); i++)
        {
            if(mStories.get(i).isItTheSameStory(mMyStoryList))
            {
                index=i;
            }
        }
        mStoryTextView.setText(mStories.get(index).getStoryTextViev());
        if(!mStories.get(index).isStoryEnd()) {
            mAnswearTopButton.setText(mStories.get(index).getAnswearTop());
            mAnswearTopButton.setText(mStories.get(index).getAnswearBottom());
        }
        else
        {
            mAnswearTopButton.setVisibility(View.GONE);
            mAnswearBottomButtonm.setVisibility(View.GONE);
            AlertDialog.Builder alert= new AlertDialog.Builder(this);
            alert.setTitle("End your Story");
            alert.setMessage("do you want to play one time more?");
            alert.setCancelable(false);
            alert.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {


                    mAnswearTopButton.setVisibility(View.VISIBLE);
                    mAnswearBottomButtonm.setVisibility(View.VISIBLE);
                        mMyStoryList= new ArrayList<Integer>();

                    int startStory=0;
                    mStoryTextView.setText(mStories.get(startStory).getStoryTextViev());
                    mAnswearTopButton.setText(mStories.get(startStory).getAnswearTop());
                    mAnswearTopButton.setText(mStories.get(startStory).getAnswearBottom());
                }
            });
            alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            alert.show();
        }
    }
    @Override
    public void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        outState.putIntegerArrayList("UserStoryKey",(ArrayList<Integer>) mMyStoryList);
    }
}
