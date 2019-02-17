package sam.jersey.utils;

class ANSIBase {
	protected String ansi_wrap(String prefix, Object obj) { return prefix + obj + "\u001b[0m";}

	public String black(Object obj) {return ansi_wrap("\u001b[30m",obj);}
	public String red(Object obj) {return ansi_wrap("\u001b[31m",obj);}
	public String green(Object obj) {return ansi_wrap("\u001b[32m",obj);}
	public String yellow(Object obj) {return ansi_wrap("\u001b[33m",obj);}
	public String blue(Object obj) {return ansi_wrap("\u001b[34m",obj);}
	public String magenta(Object obj) {return ansi_wrap("\u001b[35m",obj);}
	public String cyan(Object obj) {return ansi_wrap("\u001b[36m",obj);}
	public String white(Object obj) {return ansi_wrap("\u001b[37m",obj);}

	protected StringBuilder ansi_wrap(String prefix, Object obj, StringBuilder sb) { 
		return sb.append(prefix).append(obj).append("\u001b[0m");
	}
	public StringBuilder black(Object obj, StringBuilder sb) {return ansi_wrap("\u001b[30m",obj, sb);}
	public StringBuilder red(Object obj, StringBuilder sb) {return ansi_wrap("\u001b[31m",obj, sb);}
	public StringBuilder green(Object obj, StringBuilder sb) {return ansi_wrap("\u001b[32m",obj, sb);}
	public StringBuilder yellow(Object obj, StringBuilder sb) {return ansi_wrap("\u001b[33m",obj, sb);}
	public StringBuilder blue(Object obj, StringBuilder sb) {return ansi_wrap("\u001b[34m",obj, sb);}
	public StringBuilder magenta(Object obj, StringBuilder sb) {return ansi_wrap("\u001b[35m",obj, sb);}
	public StringBuilder cyan(Object obj, StringBuilder sb) {return ansi_wrap("\u001b[36m",obj, sb);}
	public StringBuilder white(Object obj, StringBuilder sb) {return ansi_wrap("\u001b[37m",obj, sb);}
}