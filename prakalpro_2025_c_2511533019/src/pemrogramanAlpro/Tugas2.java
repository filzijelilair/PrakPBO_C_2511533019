package pemrogramanAlpro;

import java.util.Scanner;

public class Tugas2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//menghitung upah mingguan karyawan
		String nama;
		char golongan;
		int Jam;
		int UpahPerJam = 0;
		double upahPokok;
        double jamLembur = 0;
        double upahLembur = 0;
        double upahTotal;
        final int TARIF_LEMBUR = 5000;
		
		Scanner keyboard = new Scanner(System.in);
		System.out.print("Nama Karyawan: ");
		nama = keyboard.next();
		System.out.print("Golongan(A/B/C/D): ");
		golongan = keyboard.next().charAt(0);
		System.out.print("Jumlah Jam Kerja: ");
		Jam = keyboard.nextInt();
		keyboard.close ();
		
		//upah per jam karyawan
		if (golongan == 'A') {
			UpahPerJam = 1000;}
		else if (golongan == 'B') {
			UpahPerJam = 2000;} 
		else if (golongan == 'C') {
	        UpahPerJam = 3000;}
	    else if (golongan == 'D') {
	        UpahPerJam = 4000;}
	    else { System.out.println("Golongan tidak valid.");
	        UpahPerJam = 0; // Golongan tidak valid
	    }
		//upah pokok
	    upahPokok= UpahPerJam*Jam;
	    
	    //Upah Lembur 
        if (Jam > 60) { 
            jamLembur = Jam - 60;
            upahLembur = jamLembur * TARIF_LEMBUR;
        }

        //Upah Total
        upahTotal = upahPokok + upahLembur;

        //Output Hasil
        System.out.println("Hasil Perhitungan Upah ");
        System.out.println("Nama Karyawan: " + nama);
        System.out.println("Golongan: " + golongan);
        System.out.println("Jumlah Jam Kerja: " + Jam + " jam");
        System.out.println("Upah per Jam: Rp" + UpahPerJam);
        
        if (jamLembur > 0) {
            System.out.println("Jam Lembur: " + jamLembur + " jam");
            System.out.println("Upah Lembur Tambahan: Rp" + (int)upahLembur);
        }
        
        System.out.println("Upah Mingguan Total: **Rp" + (int)upahTotal + "**");
      
    }
		
	}


