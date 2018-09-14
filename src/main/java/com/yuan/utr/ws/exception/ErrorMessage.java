package com.yuan.utr.ws.exception;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ErrorMessage
{
	int status;

	String message;

	public ErrorMessage()
	{

	}


	public ErrorMessage(int status, String message)
	{
		this.status = status;
		this.message = message;
	}

	public int getStatus()
	{
		return status;
	}

	public void setStatus(int status)
	{
		this.status = status;
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}
}