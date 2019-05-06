package com.example.question4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    ListView search_words;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        search_words = (ListView) findViewById(R.id.search_words);
        ArrayList<String> arrayTypos = new ArrayList<>();
        arrayTypos.addAll(Arrays.asList(getResources().getStringArray(R.array.my_words)));
        adapter = new ArrayAdapter<String>(
                MainActivity.this,
                android.R.layout.simple_list_item_1,
                arrayTypos
        );
        search_words.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu,menu);
        MenuItem item = menu.findItem(R.id.search_words);
        final SearchView searchView = (SearchView)item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if(s.equalsIgnoreCase("")){
                    search_words.setAdapter(adapter);
                }
                else{
                search_words.setAdapter(search(s));}
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
    public ArrayAdapter<String> search(String s){
        ArrayList<String> arrayTypos = new ArrayList<>();
        arrayTypos.addAll(Arrays.asList(getResources().getStringArray(R.array.my_words)));
        ArrayList<String> newArray = new ArrayList<>();
        for (String word:arrayTypos){
            if(checkTypo(word,s)==true ^ checkIsJumbled(word,s)){
                newArray.add(word);
            }
            else if(word.equalsIgnoreCase(s)){
                newArray.add(word);
            }
        }
        ArrayAdapter<String> result;
        result =  new ArrayAdapter<String>(
                MainActivity.this,
                android.R.layout.simple_list_item_1,
                newArray
        );
        return result;



    }
    public static boolean checkTypo(String word1, String word2) {
        boolean result=false;
        if(word1.length()==word2.length()) {result=checkLetters(word1,word2)<=1;}
        else if(Math.abs(word1.length()-word2.length())>1) {result=false;}
        else if(word1.length()>word2.length()) {result=checkLetterDifferentSize(word1,word2);}
        else if(word1.length()<word2.length()) {result=checkLetterDifferentSize(word2,word1);}
        return result;
    }
    public static int checkLetters(String word1, String word2) {
        if(word1.equalsIgnoreCase(word2)) {return 0;}
        int differentLetters=0;
        for (int i = 0; i < word1.length(); i++) {
            if(word1.charAt(i)!=word2.charAt(i)) {differentLetters+=1;}
        }
        return differentLetters;
    }
    private static boolean checkLetterDifferentSize(String word1, String word2) {
        if(word1.length()==1)return true;
        else if(word1.charAt(0)!=word2.charAt(0)) {
            return checkLetters(word1.substring(1), word2)==0;}
        else {
            return checkLetterDifferentSize(word1.substring(1), word2.substring(1));
        }
    }
    public static boolean checkIsJumbled(String rightWord,String wrongWord) {
        boolean isFirstLetterEqual = checkSameLetter(rightWord, wrongWord,0);
        boolean isPercentageAccepted = checkPercentage(rightWord, wrongWord);
        boolean result = isFirstLetterEqual && isPercentageAccepted;
        return result;
    }
    public static boolean checkSameLetter(String rightWord,String wrongWord,int i) {
        return rightWord.charAt(i)==wrongWord.charAt(i);
    }
    public static boolean checkPercentage(String rightWord,String wrongWord) {
        boolean hasSameLength = checkLength(rightWord, wrongWord);
        if(hasSameLength==false) {return false;}
        else if(rightWord.length()<=3) {return true;}
        else {
            double percentage=0;
            for (int i = 0; i < rightWord.length(); i++) {
                boolean sameLetter = checkSameLetter(rightWord, wrongWord, i);
                if(sameLetter==false) {
                    percentage+=100f/rightWord.length();
                }
            }
            return percentage<(2f/3f)*100;
        }
    }
    public static boolean checkLength(String rightWord,String wrongWord) {
        return rightWord.length()==wrongWord.length();
    }
}
