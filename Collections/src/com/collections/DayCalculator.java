package com.collections;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DayCalculator {

	public static void main(String[] args) {
		System.out.println("Choose an operator : +/-");
		Scanner sc = new Scanner(System.in);
		String operator = sc.next();
		System.out.println("Choose any day : ");
		String day = sc.next();
		System.out.println("Choose any number: ");
		int number = sc.nextInt();
		sc.close();

		validateInputs(operator, day, number);
		
		String result = dayCalculator(operator, day, number);
		System.out.println("Result is :" + result);

	}

	public static void validateInputs(String op, String dayInput, int num) {
		
		List<String> validDays = Stream.of("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")
				.collect(Collectors.toList());
		
		String selectedDay = null;
		for (String day : validDays) {
			if (day.equalsIgnoreCase(dayInput)) {
				selectedDay = dayInput;
				break;
			}
		}
		if (selectedDay == null) {
			throw new IllegalArgumentException("Invalid day selected :" + dayInput);
		}

		if (num < 0 || num > 6) {
			throw new IllegalArgumentException(
					"Invalid number selected. Number must be positive and less than or equal to 6");
		}

		if (!(op.equals("+") || op.equals("-"))) {
			throw new IllegalArgumentException("Invalid operation selected.Only Addition or Substraction is allowed");
		}

	}

	public static String dayCalculator(String op, String day, int num) {

		HashMap<String, Integer> map = new HashMap<>();
		map.put("Monday", 1);
		map.put("Tuesday", 2);
		map.put("Wednesday", 3);
		map.put("Thursday", 4);
		map.put("Friday", 5);
		map.put("Saturday", 6);
		map.put("Sunday", 7);

		String resultantDay = null;
		for (Entry<String, Integer> entry : map.entrySet()) {
			if (entry.getKey().equalsIgnoreCase(day)) {
				if (op.equals("+")) {
					int sum = entry.getValue() + num;
					if (sum > 7) {
						resultantDay = getKey(map, sum - 7);
					} else {
						resultantDay = getKey(map, sum);
					}
					return resultantDay;
				} else if (op.equals("-")) {
					int diff = entry.getValue() - num;
					if (diff < 1) {
						resultantDay = getKey(map, diff + 7);
					} else {
						resultantDay = getKey(map, diff);
					}
					return resultantDay;
				}
			}
		}

		return resultantDay;

	}

	public static <K, V> K getKey(Map<K, V> map, V value) {
		for (K key : map.keySet()) {
			if (value.equals(map.get(key))) {
				return key;
			}
		}
		return null;
	}
}
