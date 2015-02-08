package com.neumiiller.oeno;

import android.app.Application;
import com.neumiiller.oeno.managers.WineryManager;

public class OenoApplication extends Application {
	
	public static OenoApplication singleton; 

	private WineryManager wineryManager;
	
	public static OenoApplication getInstance(){
		return singleton;
	}
	
	public final void onCreate() {
        super.onCreate(); 
        singleton = this;
        
        wineryManager = new WineryManager(this);
    }  
	
	public WineryManager getWineryManager(){
		return wineryManager;
	}
}
