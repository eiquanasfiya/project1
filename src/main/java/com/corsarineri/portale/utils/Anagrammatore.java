package com.corsarineri.portale.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Anagrammatore {
	Logger logger = LoggerFactory.getLogger(Anagrammatore.class);
	
	
	private static char[] letters = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
	
	public static List<String> anagramma(String parola, List<String> dizionario) {
		
		List<String> anagrammi = new ArrayList<>();
		if (dizionario.isEmpty()) return anagrammi;
		for (String parolaDizionario : dizionario) {
			int[] arrayParolaDizionario = stringToIntArray(parolaDizionario);
			int[] arrayParola = stringToIntArray(parola);
			int[] result = new int[26];
			for(int i =0; i< 26; i++) {
		      result[i] = arrayParola[i] - arrayParolaDizionario[i];
		    }
			if ((Arrays.stream(result).filter(i -> i < 0).count()==0) && !anagrammi.contains(parolaDizionario)) anagrammi.add(parolaDizionario);
		}
		List<String> sortedAnagrammi = anagrammi.stream()
		        .sorted((s1, s2) -> s2.length() - s1.length())
		        .collect(Collectors.toList());

		return sortedAnagrammi;
	}
	
	public static int[] stringToIntArray(String parola) {
		int[] array = new int[26];
			for (int i=0; i<26; i++) {
				char letter = letters[i];
				array[i] = StringUtils.countMatches(parola, letter);
			}

		
		return array;
	}
}
