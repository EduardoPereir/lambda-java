package application;

import entities.Employee;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter salary: ");
        Double salarySearch = sc.nextDouble();
        String path = "/home/eduardo/Documents/x.txt";
        List<Employee> list = new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new FileReader(path))){

            String line = br.readLine();

            while (line != null){

                String[] fields = line.split(",");
                list.add(new Employee(fields[0], fields[1] ,Double.parseDouble(fields[2])));
                line = br.readLine();
            }

            List<String> emails = list.stream()
                    .filter(x -> x.getSalary() > salarySearch)
                    .map(x -> x.getEmail())
                    .sorted((s1, s2) -> s1.toUpperCase().compareTo(s2.toUpperCase())).toList();

            System.out.println("E-mail of people whose the salary is more than $" + salarySearch + ":");
            emails.forEach(System.out::println);

            List<Double> startsWithM = list.stream()
                    .filter(x -> x.getName().startsWith("M"))
                    .map(x -> x.getSalary())
                    .reduce((x, y) -> x + y).stream().toList();

            System.out.println("Sum salary of people whose starts with 'M': ");
            startsWithM.forEach(System.out::println);

        }
        catch( IOException e ){
            System.out.println("ERROR: "+ e.getMessage());
        }

        sc.close();
    }
}
