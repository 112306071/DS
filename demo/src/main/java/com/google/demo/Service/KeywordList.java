package com.google.demo.Service;
 import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
public class KeywordList {
	private ArrayList<Keyword> kl;
	public KeywordList(String filter, String searchKeyword) {
		kl = new ArrayList<Keyword>();
		kl.add(new Keyword (searchKeyword, 1.2));
		add();
		add(filter);
	}
	public void add() {
		try {
			File file = new File("initial.txt");
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine())
			{
				String name = scanner.next();
				double weight = scanner.nextDouble();
				kl.add(new Keyword(name, weight));
			}
			scanner.close();
		} catch (Exception e) {
			 System.out.println("Error reading file 1");
		}
		
	}
	public void add(String filter) {
		try {
			while (!filter.isEmpty()) {
				File file = new File(filter + ".TXT");
				Scanner scanner = new Scanner(file);
				while (scanner.hasNextLine())
				{
					String name = scanner.next();
					double weight = scanner.nextDouble();
					kl.add(new Keyword(name, weight));
				}
				scanner.close();
			}
		} catch (Exception e) {
			 System.out.println("Error reading file2");
		}	
	}
	public ArrayList<Keyword> getList(){
		return kl;
	}
}
