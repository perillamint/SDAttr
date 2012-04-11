package com.maneulyori.sdattr;

import java.lang.*;
import android.util.Log;
import android.content.res.*;
import com.maneulyori.sdattr.utils.*;

public class FileFixer implements Runnable
{
	public void run()
	{
		if(ShellInterface.isSuAvailable())
		{
			//TODO: Someday, I'll add recursive feature in here.
			ShellInterface.runCommand("/data/data/com.maneulyori.sdattr/fatattr -h -a -s /sdcard/*");
		}
	}
}
