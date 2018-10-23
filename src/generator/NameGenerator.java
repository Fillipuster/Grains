package generator;

import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonValue;

import jdk.nashorn.api.scripting.URLReader;

public class NameGenerator {

	private static NameGenerator instance;
	
	private ArrayList<String> firstNames = new ArrayList<>();
	private ArrayList<String> lastNames = new ArrayList<>();
	
	private Random rand = new Random();
	
	private NameGenerator() {
		try {
			JsonArray firstNamesJson = Json.parse(new URLReader(new URL("https://raw.githubusercontent.com/dominictarr/random-name/master/first-names.json"))).asArray();
			for (JsonValue val : firstNamesJson.values()) {
				firstNames.add(val.asString());
			}
			
			JsonArray lastNamesJson = Json.parse(new URLReader(new URL("https://raw.githubusercontent.com/dominictarr/random-name/master/names.json"))).asArray();
			for (JsonValue val : lastNamesJson.values()) {
				lastNames.add(val.asString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static NameGenerator getInstance() {
		if (instance == null) {
			instance = new NameGenerator();
		}
		
		return instance;
	}
	
	public String generateFirstName() {
		return firstNames.get(rand.nextInt(firstNames.size()));
	}
	
	public ArrayList<String> generateFirstNames(int amount) {
		ArrayList<String> result = new ArrayList<>();
		for (int i = 0; i < amount; i++) {
			result.add(firstNames.get(rand.nextInt(firstNames.size())));
		}
		
		return result;
	}
	
	public String generateLastName() {
		return lastNames.get(rand.nextInt(lastNames.size()));
	}
	
	public ArrayList<String> generateLastNames(int amount) {
		ArrayList<String> result = new ArrayList<>();
		for (int i = 0; i < amount; i++) {
			result.add(lastNames.get(rand.nextInt(lastNames.size())));
		}
		
		return result;
	}
	
	public int generateAge(int minimumAge) {
		return rand.nextInt(90 - minimumAge) + minimumAge;
	}
	
	public int generateAge() {
		return generateAge(1);
	}
	
	public ArrayList<Integer> generateAges(int amount, int minimumAge) {
		ArrayList<Integer> result = new ArrayList<>();
		for (int i = 0; i < amount; i++) {
			result.add(generateAge(minimumAge));
		}
		
		return result;
	}
	
	public ArrayList<Integer> generateAges(int amount) {
		ArrayList<Integer> result = new ArrayList<>();
		for (int i = 0; i < amount; i++) {
			result.add(generateAge());
		}
		
		return result;
	}
	
}
