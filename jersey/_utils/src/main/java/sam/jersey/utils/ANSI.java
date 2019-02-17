package sam.jersey.utils;

import java.util.Arrays;

public final class ANSI {
	private static final ANSIBase ansi;
	
	static {
		String[] array = {"utils.ansi", "ConEmuANSI", "TERM"};
		
		String cond = null;
		for (String s : array) {
			cond = System.getProperty(s);
			if(cond != null)
				break;
		}
		if(cond == null) {
			for (String s : array) {
				cond = System.getenv(s);
				if(cond != null)
					break;
			}	
		}
		
		if(cond != null && Arrays.asList("on", "true", "yes").contains(cond.trim().toLowerCase())) {
			ansi = new ANSIBase();
		} else {
			ansi = new ANSIBase() {
				@Override
				public String ansi_wrap(String prefix, Object obj) {
					return String.valueOf(obj);
				}
				@Override
				public StringBuilder ansi_wrap(String prefix, Object obj, StringBuilder sb) {
					return sb.append(obj);
				}
			};
		}
	}

	public static String ansi_wrap(String prefix, Object obj) {
		return ansi.ansi_wrap(prefix, obj);
	}
	public static String black(Object obj) {
		return ansi.black(obj);
	}
	public static String red(Object obj) {
		return ansi.red(obj);
	}
	public static String green(Object obj) {
		return ansi.green(obj);
	}
	public static String yellow(Object obj) {
		return ansi.yellow(obj);
	}
	public static String blue(Object obj) {
		return ansi.blue(obj);
	}
	public static String magenta(Object obj) {
		return ansi.magenta(obj);
	}
	public static String cyan(Object obj) {
		return ansi.cyan(obj);
	}
	public static String white(Object obj) {
		return ansi.white(obj);
	}
	public static StringBuilder ansi_wrap(String prefix, Object obj, StringBuilder sb) {
		return ansi.ansi_wrap(prefix, obj, sb);
	}
	public static StringBuilder black(Object obj, StringBuilder sb) {
		return ansi.black(obj, sb);
	}
	public static StringBuilder red(Object obj, StringBuilder sb) {
		return ansi.red(obj, sb);
	}
	public static StringBuilder green(Object obj, StringBuilder sb) {
		return ansi.green(obj, sb);
	}
	public static StringBuilder yellow(Object obj, StringBuilder sb) {
		return ansi.yellow(obj, sb);
	}
	public static StringBuilder blue(Object obj, StringBuilder sb) {
		return ansi.blue(obj, sb);
	}
	public static StringBuilder magenta(Object obj, StringBuilder sb) {
		return ansi.magenta(obj, sb);
	}
	public static StringBuilder cyan(Object obj, StringBuilder sb) {
		return ansi.cyan(obj, sb);
	}
	public static StringBuilder white(Object obj, StringBuilder sb) {
		return ansi.white(obj, sb);
	}
}
