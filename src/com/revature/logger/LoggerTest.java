package com.revature.logger;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

public class LoggerTest
{
	static Logger logger = Logger.getLogger( LoggerTest.class );
	
	public static void main( String[ ] args )
	{
		DOMConfigurator.configure( "WebContent/WEB-INF/Config.xml" );
		
		logger.debug( "Here is some DEBUG" );
		logger.info( "Here is some INFO" );
		logger.warn( "Here is some WARNING" );
		logger.error( "Here is some ERROR" );
	}
}
