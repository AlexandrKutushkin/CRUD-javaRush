package com.javarush.task.task17.task1710;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* 
CRUD
*/

public class Solution {
    public static List<Person> allPeople = new ArrayList<Person>();

    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) throws ParseException {
       switch (args[0]){
           case "-c":
               create(args[1], args[2],args[3]);
               break;
           case "-r":
               read(args[1]);
               break;
           case "-u":
               update(Integer.parseInt(args[1]), args[2],args[3], args[4]);
               break;
           case "-d":
               delete(Integer.parseInt(args[1]));
               break;
       }
        //напишите тут ваш код
    }
    static void create(String name, String sex, String bd ) throws ParseException {
        SimpleDateFormat parser = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        Date date = parser.parse(bd);
        if(sex.equals("м"))
           allPeople.add( Person.createMale(name, date));
        else if(sex.equals("ж"))
           allPeople.add( Person.createFemale(name, date));
        System.out.println(allPeople.size()-1);
    }

    static void read(String id) throws ParseException {
        int num = Integer.parseInt(id);
        Date date = allPeople.get(num).getBirthDate();
        System.out.print(allPeople.get(num).getName() + " ");
        System.out.print(allPeople.get(num).getSex().equals(Sex.MALE)? "м " : "ж ");
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy",Locale.ENGLISH);
        String message = formatter.format(date);
        System.out.println(message);
    }

    static void update(int id, String name, String sex, String bd) throws ParseException {
        SimpleDateFormat parser = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        Person up = null;
        if(sex.equals("м"))
            up = Person.createMale(name, parser.parse(bd));
        else if (sex.equals("ж"))
            up = Person.createFemale(name, parser.parse(bd));
        allPeople.set(id, up);

    }
    static void delete(int d){
        Person del = Person.createMale(null, null);
        del.setSex(null);

        allPeople.set(d,del);
    }
}
