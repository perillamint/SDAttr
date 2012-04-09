package com.maneulyori.sdattr;

import java.io.*;
import android.util.Log;

public class FileCrawler
{
	public String[] listDir(String dirName)
	{
		File dir = new File(dirName);
		
		String dirChildren[] = dir.list();
		
		if(dirChildren == null)
		{
			//TODO: write a routine for nonpresense of card or dir
			Log.i("SDAttr", "Cannot find dir: " + dirName + ". Skipping!");
		}
		else
		{
			//TODO: write a routine for recursive search.
		}
		return dirChildren;
	}
	
	public void FileCrawler(String rootDir)
	{
		//TODO: add tree and add nodes in it.
	}
}
