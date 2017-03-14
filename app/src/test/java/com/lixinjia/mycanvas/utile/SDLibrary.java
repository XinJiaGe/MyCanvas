package com.lixinjia.mycanvas.utile;

import android.app.Application;

import com.lixinjia.mycanvas.model.SDConfig;
import com.lixinjia.mycanvas.model.SDLibraryConfig;


public class SDLibrary
{

	private static SDLibrary mInstance;
	private Application mApplication;

	private SDLibraryConfig mConfig;

	public SDLibraryConfig getConfig()
	{
		return mConfig;
	}

	/**
	 * 用setConfig替代此方法
	 * 
	 * @param config
	 */
	@Deprecated
	public void initConfig(SDLibraryConfig config)
	{
		this.mConfig = config;
	}

	public void setConfig(SDLibraryConfig config)
	{
		this.mConfig = config;
	}

	private SDLibrary()
	{
		mConfig = new SDLibraryConfig();
	}

	public Application getApplication()
	{
		return mApplication;
	}

	public void init(Application application)
	{
		this.mApplication = application;
		SDConfig.getInstance().init(application);
	}

	public static SDLibrary getInstance()
	{
		if (mInstance == null)
		{
			mInstance = new SDLibrary();
		}
		return mInstance;
	}

}
