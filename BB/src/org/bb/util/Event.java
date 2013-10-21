package org.bb.util;

import java.io.Serializable;

public class Event implements Serializable{
  
	/** Name of the method being called on this event */
	private String   methodName = null;
  
	/** Method parameters, may be null */
	private Object[] arguments  = null;
  
	public Event(){
	
	}
  
	public Event(String methodName, Object[] param){
		this.arguments  = param;
		this.methodName = methodName;
	}
  
	public Event(Object[] param){
		this.arguments = param;
	}

	public Object[] getArguments(){
		return arguments;
	}

	public String getMethodName(){
		return methodName;
	}

	public void setArguments(Object[] arguments){
		this.arguments = arguments;
	}

	public void setMethodName(String methodName){
		this.methodName = methodName;
	}
  
}
