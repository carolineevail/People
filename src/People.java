import jodd.json.JsonParser;
import jodd.json.JsonSerializer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class People {



    public static void main(String[] args) throws IOException {

        HashMap<String, ArrayList<Person>> map = readFile();
        File f = new File("people.json");
        System.out.println(map.toString());

        //write json
        JsonSerializer serializer = new JsonSerializer();
        String json = serializer.include("*").serialize(map);
        FileWriter fw = new FileWriter(f);
        fw.write(json);
        fw.close();



    }

    static HashMap<String, ArrayList<Person>> readFile() throws IOException {
        HashMap<String, ArrayList<Person>> map = new HashMap<>();
        File f = new File("people.csv");
        Scanner scanner = new Scanner(f);
        scanner.nextLine();
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] columns = line.split(",");
            Person person = new Person(Integer.valueOf(columns [0]), columns [1], columns [2], columns [3], columns [4], columns [5]);
            if (!map.containsKey(person.country)) {
                map.put(person.country, new ArrayList<>());
            }
            map.get(person.country).add(person);

        }

        for (ArrayList<Person> people : map.values()) {
            Collections.sort(people);
        }
        return map;

    }

    /*static void writeFile(String fileName, String fileContent) throws IOException {
        File f = new File(fileName);
        FileWriter fw = new FileWriter(f);
        fw.write(fileContent);
        fw.close();
    } */
}



/*Description

        Fork the People project. Read the csv file into a HashMap<String, ArrayList<Person>> that maps country name to a list of
            people who are from that country. Then loop over the lists in your HashMap and sort them by last name.

        Requirements

        Create a Person class to store all the columns in the csv file.
        Create a HashMap<String, ArrayList<Person>> that maps country name to a list of people from that country.
        Loop over the HashMap and sort each list by last name.
        Override toString in the Person class to print out a nicely-formatted string for that person (something like "Martha
            Jenkins from France").
        Print out the entire HashMap at the end.
        Break your code into separate methods.
        Write the resulting HashMap to a file called "people.json" formatted as JSON.
        Optional:
        Write tests for your methods.
        Modify your compareTo method so it sorts by first name if the last names are the same.
        Status API Training Shop Blog About Pricing*/
