package willigross.desktop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.ConsoleAppender;
import ch.qos.logback.core.FileAppender;
import willigross.core.Controller;
import willigross.core.IApplication;
import willigross.core.logic.MainLogic;
import willigross.desktop.data.MainData;
import willigross.desktop.data.Strings;
import willigross.desktop.gui.FrameMain;

/**
 * This is the main class for the MathFunctions desktop application. The main action it performs is creating a
 * controller and saving a reference to it. This is separated to keep the API clean and allow it to be used on mobile
 * operating systems like android in the future.
 */
public class MathFunctionsDesktop implements IApplication {
	
	private static final Logger			logger	= LoggerFactory.getLogger(MathFunctionsDesktop.class);
	
	private static MathFunctionsDesktop	instance;
	private final Controller			controller;
	
	/**
	 * Application entry point
	 *
	 * @param args parameters when starting the application, for example to force a specific language
	 */
	public static void main(String[] args) {
		ch.qos.logback.classic.Logger root = (ch.qos.logback.classic.Logger) LoggerFactory
				.getLogger(Logger.ROOT_LOGGER_NAME);
		LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
		String encodingPattern = "%date %level [%thread] %logger{10} [%file:%line] %msg%n"; //$NON-NLS-1$
		
		PatternLayoutEncoder patternLayoutEncoder_FileAppender = new PatternLayoutEncoder();
		patternLayoutEncoder_FileAppender.setPattern(encodingPattern);
		patternLayoutEncoder_FileAppender.setContext(loggerContext);
		patternLayoutEncoder_FileAppender.start();

		PatternLayoutEncoder patternLayoutEncoder_ConsoleAppender = new PatternLayoutEncoder();
		patternLayoutEncoder_ConsoleAppender.setPattern(encodingPattern);
		patternLayoutEncoder_ConsoleAppender.setContext(loggerContext);
		patternLayoutEncoder_ConsoleAppender.start();
		
		FileAppender<ILoggingEvent> fileAppender = new FileAppender<>();
		fileAppender.setFile(MainData.applicationRoot.getAbsolutePath() + "\\" //$NON-NLS-1$
				+ Strings.getString("MathFunctionsDesktop.logFileName"));
		fileAppender.setEncoder(patternLayoutEncoder_FileAppender);
		fileAppender.setContext(loggerContext);
		fileAppender.start();
		
		ConsoleAppender<ILoggingEvent> consoleAppender = new ConsoleAppender<>();
		consoleAppender.setEncoder(patternLayoutEncoder_ConsoleAppender);
		consoleAppender.setContext(loggerContext);
		consoleAppender.start();
		
		root.detachAndStopAllAppenders();
		root.addAppender(consoleAppender);
		root.addAppender(fileAppender);
		root.setAdditive(true);
		
		logger.info("Starting application..."); //$NON-NLS-1$
		instance = new MathFunctionsDesktop();
		logger.info("Application startup finished"); //$NON-NLS-1$
	}
	
	private MathFunctionsDesktop() {
		logger.info("Creating new controller"); //$NON-NLS-1$
		controller = new Controller(this, new MainLogic(), new MainData(), new FrameMain());
		logger.info("Controller creation complete"); //$NON-NLS-1$
	}
	
	/**
	 * @return the controller
	 */
	@Override
	public Controller getController() {
		return controller;
	}
	
	/**
	 * @return the instance
	 */
	public static MathFunctionsDesktop getInstance() {
		return instance;
	}
	
}
