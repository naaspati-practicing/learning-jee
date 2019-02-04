package _toast_java;

import java.lang.reflect.Modifier;
import java.util.stream.Stream;

public class Main {

	public static void main(String[] args) {
		/*
		 * ThreadLocalRandom ran = ThreadLocalRandom.current();
		for (int i = 0; i < 100; i++) {
			System.out.println(ran.nextDouble(-0.5, 0.5));	
		}
		 */
		double d = .123445;
		Stream.of(StrictMath.class.getMethods())
		.filter(m -> Modifier.isStatic(m.getModifiers()))
		.filter(m -> m.getReturnType() == double.class)
		.forEach(s -> System.out.println(s));
	}
	
	private static final int[][] hours = {
			{1,2,3,4,5,6,23,24},
			{7,13,18},
			{8,9,10,12,14,16,17},
			{11,15},
			{19,20, 21},
			{22}
	};
	private static final String[] moods = {
			"sleepy",
			"hungry",
			"alert",
			"in need of coffee",
			"thoughtful",
			"lethargic"
	};
	
	private static final String[] mood_at_hour = new String[24];
	static {
		
		for (int i = 0; i < hours.length; i++) {
			int[] h = hours[i];
			String mood = moods[i];
			
			for (int j : h) {
				j = j - 1;
				if(mood_at_hour[j] != null)
					throw new IllegalStateException("expected null at: "+j+", found \""+mood_at_hour[j]+"\"");
				
				mood_at_hour[j] = mood;
			}
		} 
		
		for (int i = 0; i < mood_at_hour.length; i++) {
			if(mood_at_hour[i] == null)
				throw new IllegalStateException("expected a value at: "+i);
		}
	}
	
	public static String get(int n) {
		switch (n) {
            case 23:
            case 24:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                return  "sleepy";
            case 7:
            case 13:
            case 18:
                return  "hungry";
            case 8:
            case 9:
            case 10:
            case 12:
            case 14:
            case 16:
            case 17:
                return  "alert";
            case 11:
            case 15:
                return  "in need of coffee";
            case 19:
            case 20:
            case 21:
                return  "thoughtful";
            case 22:
                return  "lethargic";
        }
		
		return null;
	}

}
