package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class NepaliSpellChecker {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Scanner s=new Scanner(System.in);
		String input=null;
		while(true){
			System.out.println("Enter sth: ");
			input=s.next();
			if(check(input)){
				System.out.println("Found");
			}else{
				System.out.println("Not Found");
			}
		}
		
		

	}

	private static boolean check(String input) {
		File f=new File("/home/opnchaudhary/ne_NP.dic");
		Scanner sc=null;
		int count=0;
		try {
			sc=new Scanner(f);
			while(sc.hasNext()){
				System.out.print(sc.nextLine());
				if(sc.nextLine().equals(input)){
					count++;
				}
			}			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}finally{
			sc.close();
		}
		if(count>0){
			return true;			
		}else{
			return false;
		}
	}
}
