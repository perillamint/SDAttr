package com.maneulyori.sdattr;

import java.io.*;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;
import android.util.Log;
import android.content.res.*;
import com.maneulyori.sdattr.utils.*;
import com.maneulyori.sdattr.*;

public class SDAttrActivity extends Activity {
    /** Called when the activity is first created. */
	
	private Resources resources;
	private InputStream fatattrstream;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		
		resources = getResources();
		
		try{
			fatattrstream = resources.getAssets().open("fatattr");
			byte[] buffer = new byte[fatattrstream.available()];
			fatattrstream.read(buffer);
			
			OutputStream fdattrFile = new FileOutputStream("/data/data/com.maneulyori.sdattr/fatattr");
			
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			
			outStream.write(buffer);
			Log.i("SDAttr", "writing fatattr binary...");
			outStream.writeTo(fdattrFile);
			outStream.close();
		}
		catch (IOException e)
		{
			Log.e("SDAttr", "file not found! Cannot write fatattr binary!");
		}
		
		Log.i("SDAttr", "TEST");
		
	
		
		if(ShellInterface.isSuAvailable())
		{
			Log.i("SDAttr", "Executing chmod 755 on fatattr");
			ShellInterface.runCommand("chmod 755 /data/data/com.maneulyori.sdattr/fatattr");
			
			//TODO: Someday, I'll clean this finding routine.
			Toast toast = Toast.makeText(this, "FIXING...", Toast.LENGTH_LONG);
			toast.show();
			
			ShellInterface.runCommand("/data/data/com.maneulyori.sdattr/fatattr -h -a -s `find /sdcard -maxdepth 1`");
			ShellInterface.runCommand("/data/data/com.maneulyori.sdattr/fatattr -h -a -s /sdcard/*");
		}
		else
		{
			Toast toast = Toast.makeText(this, "This app require ROOT permission to run!", Toast.LENGTH_LONG);
			toast.show();
		}
    }
}
