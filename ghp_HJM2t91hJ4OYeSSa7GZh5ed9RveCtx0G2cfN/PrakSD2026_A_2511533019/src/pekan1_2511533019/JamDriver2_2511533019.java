package pekan1_2511533019;
import java.util.Scanner;
public class JamDriver2_2511533019 {

	private static Scanner sc;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		System.out.println("=== Program Driver objek Jam ===");
		//1. input jam pertama
		System.out.println("\n--- Input jam 1 ---");
		Jam_2511533019 j1 = buatJamDariInput(input);
		
		//2.input jam kedua
		System.out.println ("\n--- Input Jam 2 ---");
		Jam_2511533019 j2 = buatJamDariInput(input);
		
		//3.mnmpilkan data
		System.out.println("\n--- hasil operasi ---");
		System.out.println("Jam 1 (String)     : " +j1.toString());
		System.out.println("Jam 2 (String)     : " +j2.toString());
		System.out.println("Jam 1 dalam detik  : " +j1.toSeconds());
		System.out.println("Jam 2 dalam detik  : " +j2.toSeconds());
		
		//4. operasi relasional (Perbandingan)
		int perbandingan = j1.compareTo(j2);
		if (perbandingan > 0 ) {
			System.out.println("Status        : Jam 1 lebih lambat (setelah) Jam 2");
		}else if (perbandingan <0 ) {
			System.out.println("Status        : Jam 1 lebih awal (sebelum) Jam 2");
		}else {
			System.out.println("Status        : Jam 1 dan Jam 2 sama persis");
		}
		
		//5. operasi aritmatika
		System.out.println ("Durasi (J1 ke J2)     : " + Jam_2511533019.durasiDetik(j1, j2)+ " detik");
		
		Jam_2511533019 jNext = j1.nextSeconds();
		System.out.println("Jam 1 Detik berikutnya : " +jNext);
		
		Jam_2511533019 jPrev = j1.prevSecond();
		System.out.println("Jam 1 Detik sebelumnya : " +jPrev);
		
		//6. operasi penjumlahan jam
		Jam_2511533019 jHasilPlus= j1.plus(j2);
		System.out.println("Hasil J1+J2        : "+ jHasilPlus);
		
		input.close();
		System.out.println("\nProgram Selesai.");
	}
	/**
	 * Prosedur pembantu untuk melakukan input dan validasi secara berulang
	 * sampai user memasukkan angka yang benar (0..23, O..59).
	 */

	private static Jam_2511533019 buatJamDariInput(Scanner sc) {
		// TODO Auto-generated method stub
		int h, m, s;
		while (true) {
		System.out.print ("Masukkan Jam (0-23) : ");
		h = sc.nextInt();
		System.out.print ("Masukkan Menit (0-59) : ");
		m = sc.nextInt ();
		System.out.print ("Masukkan Detik (0-59) : ");
		s = sc.nextInt ();
		
		
		//memanggil method static isValid dari kelas Jam
		if (Jam_2511533019.isValid(h, m , s)) {
			return new Jam_2511533019 (h, m , s);
		} else {
		System.out.println("[Error] Input tidak valid! Silahkan ulangi. \n");
		}
		}
	}

}
