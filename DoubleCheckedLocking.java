package com.assignment;

public class DoubleCheckedLocking {

private static DoubleCheckedLocking instance = null;

private DoubleCheckedLocking()
{
	
}
/* * 1st version: creates multiple instance if two thread access * this method simultaneously */ 

public static DoubleCheckedLocking getinstance()
{
	if(instance == null)
	{
		instance=new DoubleCheckedLocking();
	}
		
	return instance;
	
}

/* * 2nd version : this definitely thread-safe and only
 *  creates one instance of Singleton on concurrent environment 
 *  but unnecessarily expensive due to cost of synchronization 
 *  at every call. */ 


public static synchronized DoubleCheckedLocking getinstanceSyn()
{
	if(instance == null)
	{
		instance=new DoubleCheckedLocking();
	}
		
	return instance;
	
}
//--------------------------------DoubleCheckedLocking Implementation--------------------------------------------
/* * 3rd version :DoubleCheckedLocking
 * An implementation of double checked locking of Singleton. 
 * Intention is to minimize cost of synchronization and improve performance,
 * by only locking critical section of code, the code which creates instance of Singleton class. 
 * By the way this is still broken, if we don't make _instance volatile, as another thread can 
 * see a half initialized instance of Singleton. */ 


public static DoubleCheckedLocking getinstanceDoubleLock()
{
	if(instance == null)
	{
		synchronized(DoubleCheckedLocking.class)
		{
			if(instance == null)
			instance=new DoubleCheckedLocking();
		}
	}
	return instance;
}
}
