package com.padeltrophy.util.log;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

public class Tracer {

	
	public static String LEVEL_DEBUG="DEBUG";
	public static String LEVEL_INFO="INFO";
	public static String LEVEL_ERROR="ERROR";
	public static String LEVEL_FATAL="FATAL";
	
	private String className = null;
	
	public Tracer(String className){
		this.className = className;
	}
	
	public void trace(String msg, String level){
		System.out.println("["+level+"]::["+this.className+"]:> "+msg);
	}
	
	public void trace(String msg){
		System.out.println("["+LEVEL_DEBUG+"]:["+this.className+"]:> "+msg);
	}

	public void trace(String msg, String level, Throwable e){
		System.out.println("["+level+"]::["+this.className+"]:> Error trace:> "+ getInfoExcepcion(e));
	}


	private static String getInfoExcepcion(Throwable aThrowable)
	{
		// STE: Stack Trace Element.
		StackTraceElement[] vSTE;
		StackTraceElement topSTE;

		// Se obtiene el elemento TOP del stack trace.
		vSTE = aThrowable.getStackTrace();
		if (vSTE.length == 0)
			return "";

		topSTE = vSTE[0];

		// Se va imprimiendo informacion de la excepcion.
		Writer result = new StringWriter();
		PrintWriter printWriter = new PrintWriter(result);

		printWriter.println(" ");
		printWriter.println(" **************************************************");
		printWriter.println("             Exception information");
		printWriter.println("             ------------------------             ");
		printWriter.println(" ");
		printWriter.println(" - File: " + topSTE.getFileName());
		printWriter.println(" - Class:   " + topSTE.getClassName());
		printWriter.println(" - Method:  " + topSTE.getMethodName());
		printWriter.println(" - Line:   " + topSTE.getLineNumber());
		printWriter.println("");
		printWriter.println(" --- Stack trace:");
		aThrowable.printStackTrace(printWriter);
		printWriter.println(" **************************************************");
		return result.toString();
	}
}
