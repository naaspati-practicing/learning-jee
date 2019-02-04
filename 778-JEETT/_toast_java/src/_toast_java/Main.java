package _toast_java;

import java.lang.reflect.Modifier;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Calendar;
import java.util.stream.Stream;

public class Main {

	public static void main(String[] args) {
		System.out.println(ChronoUnit.YEARS.between(LocalDate.of(1992, 1, 12), LocalDate.now()));
	}
	
	

}
