package pekan6_2511533019;

import java.util.Scanner;

public final class perulanganWhile1_2511533019 {

	public perulanganWhile1_2511533019() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int counter=0;
		String jawab;
		boolean running=true;
		//deklarasi scanner
		Scanner scan=new Scanner (System.in);
		while (running) {
			counter++;
			System.out.println("jumlah="+counter);
			System.out.print("apakah lanjut (ya/tidak?)");
			jawab= scan.nextLine();
			//cek jawab=tidak, perulangan berhenti
			if (jawab.equalsIgnoreCase("tidak")) {
				running=false;
			}
		}
		System.out.println("anda sudah melakukan perulangan sebanyak"+counter+"kali");
	}

}
