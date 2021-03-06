package sam.jersey.utils;

import java.util.Collection;
import java.util.Map;
import java.util.function.Supplier;

public interface Utils {
	public static boolean isEmpty(CharSequence s) {
		return s == null || s.length() == 0;
	}
	public static boolean isNotEmpty(CharSequence s) {
		return !isEmpty(s);
	}
	public static boolean isEmptyTrimmed(CharSequence s) {
		if(isEmpty(s)) return true;

		int n = s.length();
		for (int i = 0; i < n; i++) { 
			char c = s.charAt(i);
			if(!(c == ' ' || c == '\t' || c == '\n'))
				return false;
		}
		return true;
	}
	public static boolean isEmpty(Collection<?> s) {
		return s == null || s.isEmpty();
	}
	public static boolean isNotEmpty(Collection<?> s) {
		return !isEmpty(s);
	}
	public static boolean isEmpty(Map<?, ?> s) {
		return s == null || s.isEmpty();
	}
	public static boolean isNotEmpty(Map<?, ?> s) {
		return !isEmpty(s);
	}
	public static <E> boolean isEmpty(E[] es) {
		return es == null || es.length == 0;
	}
	public static <E> boolean isEmpty(int[] es) {
		return es == null || es.length == 0;
	}
	public static <E> boolean isEmpty(double[] es) {
		return es == null || es.length == 0;
	}
	public static <E> boolean isEmpty(long[] es) {
		return es == null || es.length == 0;
	}
	public static <E> boolean isEmpty(char[] es) {
		return es == null || es.length == 0;
	}
	public static <E> boolean isNotEmpty(E[] es) {
		return !isEmpty(es);
	}
	public static <E> E[] fill(E[] array, Supplier<E> maker) {
		for (int i = 0; i < array.length; i++) 
			array[i] = maker.get();
		
		return array;
	}
}
