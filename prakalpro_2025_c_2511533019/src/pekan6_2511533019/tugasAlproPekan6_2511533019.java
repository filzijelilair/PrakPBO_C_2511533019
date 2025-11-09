package pekan6_2511533019;

import java.util.Random;
import java.util.Scanner;

public class tugasAlproPekan6_2511533019 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub		        
		Scanner input = new Scanner(System.in);
		Random rand = new Random();
		int tries = 0;
		int sum = 0;
		boolean menang = false;
		boolean running = true;

		while (running) {
		tries++;
		      int dadu1 = rand.nextInt(6) + 1;
		      int dadu2 = rand.nextInt(6) + 1;
		            sum = dadu1 + dadu2;
		            System.out.println(dadu1+" + " +dadu2+ " = "+sum);

		      if (sum == 7) {
		            System.out.println("Tebakan Anda Benar");
		            System.out.println("Anda menang setelah " +tries+ " percobaan!");
		            menang = true;
		            running = false;
		      } else {
		    	  	System.out.println("Tebakan Anda Salah");
		            System.out.print("Apakah mau lempar dadu (ya/tidak?)");
						String jawab = input.nextLine();
			 
			  if (!jawab.equalsIgnoreCase("ya")) {
				  running = false;
			  } else if (tries >= 5) {
				  running = false;
			  }
		  }
		}
			  if (!menang) {
		         System.out.println("Anda gagal menang");
		      }
			  
		      input.close();
		    }
		}
	


