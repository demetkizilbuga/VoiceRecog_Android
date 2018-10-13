package com.tr.kodgraf.voicerecog;

import java.util.ArrayList;
import java.util.Locale;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.support.v4.app.NavUtils;

public class VoiceRecog extends Activity implements OnClickListener,OnInitListener{

	ListView lv;
	static final int recogCheckNumber = 1000;
	TextToSpeech tts;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice_recog);
        tts=new TextToSpeech(this,this);
        tts.setLanguage(Locale.ENGLISH);
        
        lv = (ListView) findViewById(R.id.listView1);
        Button b = (Button) findViewById(R.id.button1);
        b.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_voice_recog, menu);
        return true;
    }

	@Override
	public void onClick(View arg0) {
		Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
		intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
		intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Konuş!");
		
		startActivityForResult(intent, recogCheckNumber);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		if( requestCode == recogCheckNumber && resultCode == RESULT_OK) {
			ArrayList<String> results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
			String deneme= results.get(0);
			tts.speak("merhaba  "+deneme, TextToSpeech.QUEUE_ADD, null);
			
			Toast.makeText(this,deneme,Toast.LENGTH_LONG).show();
			lv.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, results));
		
		
		
		}
		super.onActivityResult(requestCode, resultCode, data);
		
		
	}

	@Override
	public void onInit(int status) {
		// TODO Auto-generated method stub
		
		
	}

    
}
