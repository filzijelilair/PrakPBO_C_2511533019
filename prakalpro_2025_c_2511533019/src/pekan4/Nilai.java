package pekan4;
import java.util.Scanner;
public class Nilai {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int nilai;
		Scanner Input = new Scanner(System.in);
			System.out.print ("Input nilai angka: ");
			nilai = Input.nextInt();
			Input.close();
			
			if (nilai>=81) {
				System.out.print("A");
			} else if (nilai >= 70) {
				System.out.print("B");
			} else if (nilai >= 60) {
				System.out.print("C");
			} else if (nilai >= 50) {
				System.out.print("D");
			} else {
				System.out.print("E");
			}
	}

}
