package me.yusaf;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
	public static boolean enabled = true;
	public static boolean verbose;
	private static int logLevel;
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYYMMDDHHmmssSSS");
	private String name;
	private String suffix;

	private Logger(String name, String suffix) {
		this.name = name;
		this.suffix = suffix;
	}

	public static Logger getInstance(Class<?> c, String suffix) {
		Logger logger = new Logger(c.getSimpleName(), "");
		return logger;
	}

	public static Logger getInstance(Class<?> c) {
		Logger logger = new Logger(c.getSimpleName(), "");
		return logger;
	}

	public static void setGlobalLevel(int level) {
		logLevel = level;
	}

	public void e(String message) {
		ZonedDateTime time = ZonedDateTime.now();
		System.err.print(time.format(formatter));
		if (suffix.length() > 0) {
			System.err.print(" - " + name + " [" + suffix + "]: ");
		} else {
			System.err.print(" - " + name + ": ");
		}
		System.err.println(message);
	}

	public void e(Object message) {
		e(message.toString());
	}

	public void log(String message) {
		if (enabled) {
			ZonedDateTime time = ZonedDateTime.now();
			System.out.print(time.format(formatter));
			if (suffix.length() > 0) {
				System.out.print(" - " + name + " [" + suffix + "]: ");
			} else {
				System.out.print(" - " + name + ": ");
			}
			System.out.println(message);
		}
	}

	public void log(String message, int logLevel) {
		if (logLevel < Logger.logLevel) {
			log(message);
		}
	}

	public void extra(String message) {
		if (logLevel == 3)
			log(message);
	}

	public void exception(Exception ex) {
		exception(ex.getMessage());
	}

	public void exception(String message) {
		e(message);
	}

	public void v(Object message) {
		if (message instanceof String)
			v((String) message);
		else
			v(message.toString());
	}

	public void v(String message) {
		if (enabled && verbose) {
			ZonedDateTime time = ZonedDateTime.now();
			System.out.print(time.format(formatter));
			if (suffix.length() > 0) {
				System.out.print(" - " + name + " [" + suffix + "]: ");
			} else {
				System.out.print(" - " + name + ": ");
			}
			System.out.println(message);
		}
	}

	public void v() {
		v("");
	}
}
