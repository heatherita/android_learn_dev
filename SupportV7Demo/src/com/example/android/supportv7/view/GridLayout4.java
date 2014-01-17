/*
 * Copyright (C) 2011 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.supportv7.view;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.example.android.supportv7.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

/**
 * Demonstrates using GridLayout to build the same "Simple Form" as in the
 * LinearLayout and RelativeLayout demos.
 */
public class GridLayout4 extends Activity {
	public final static String EXTRA_MESSAGE = "com.example.android.supportv7.view.MESSAGE";
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_layout_4);
    }
    
    /** Called when the user clicks the Send button */
    public void sendMessage(View view) {
        // Do something in response to button
    	Intent intent = new Intent(this, DisplayMessageActivity.class);
    	EditText editText = (EditText) findViewById(R.id.edit_message);
    	String message = editText.getText().toString();
    	    	
    	intent.putExtra(EXTRA_MESSAGE, message);
    	startActivity(intent);    	
    }
    
    public void saveMessage(View view){    	
    	/* let's save the message to storage too */
    	EditText editText = (EditText) findViewById(R.id.edit_message);
    	String message = editText.getText().toString();
    	message += "\n";
    	String filename = "heather.txt";    	
    	FileOutputStream outputStream;
    	try {    	
    	  outputStream = openFileOutput(filename, Context.MODE_APPEND);    	 
    	  outputStream.write(message.getBytes());
    	  outputStream.close();
    	} catch (Exception e) {
    	  e.printStackTrace();
    	}
    	/*end saving message */
    	editText.setText("");
    }
    
    public void getMessage(View view){    
    /* let's save the message to storage too */
	EditText editText = (EditText) findViewById(R.id.edit_message);

	try {
		InputStream inputStream = openFileInput("heather.txt");
		 if ( inputStream != null ) {
	            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
	            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
	            String receiveString = "";
	            StringBuilder stringBuilder = new StringBuilder();

	            while ( (receiveString = bufferedReader.readLine()) != null ) {
	                stringBuilder.append(receiveString);
	            }
	            inputStream.close();
	            String everything = stringBuilder.toString();
	            Intent intent = new Intent(this, DisplayMessageActivity.class);
	        	intent.putExtra(EXTRA_MESSAGE, everything);
	        	startActivity(intent);   	
	        }
	} catch (FileNotFoundException e2) {
		// TODO Auto-generated catch block
		e2.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    }
        
    
}
