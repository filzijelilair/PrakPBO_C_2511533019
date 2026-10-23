package Pekan3;
import java.util.Scanner;
import java.util.ArrayList;

public class main {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		ArrayList<rekening> bukaRekening = new ArrayList<>();
		rekening akunAktif= null;//objek belum diinisialisasi (null)
		boolean isRunning = true;
		
		System.out.println("=== SISTEM PERBANKAN MINI ===");
		
		while (isRunning) {
			System.out.println("\nMenu Utama:");
			System.out.println("1. Buka Rekening Baru");
			System.out.println("2. Setor Tunai");
			System.out.println("3. Tarik Tunai");
			System.out.println("4. Cek Informasi Rekening");
			System.out.println("5. Ganti Akun");
			System.out.println("6. Cetak Mutasi(Riwayat)");
			System.out.println("7. Riwayat 3 Transaksi terbaru");
			System.out.println("8. Ganti PIN");
			System.out.println("0. Keluar");
			System.out.println("Pilih Menu : ");
			
			int pilihan = input.nextInt();
			input.nextLine();//membersihkan buffer enter
		
			switch (pilihan) {
			case 1 :

			    System.out.print("Masukkan No Rekening : ");
			    String no = input.nextLine();
			    System.out.print("Masukkan Nama Pemilik : ");
			    String nama = input.nextLine();
			    System.out.print("Masukkan Saldo Awal : ");
			    double saldo = input.nextDouble();
			    input.nextLine();
			    String pin = "";
			    boolean validasiPin = false;

			    while (!validasiPin) {
			        System.out.print("Masukkan PIN anda (6 digit): ");
			        pin = input.nextLine();
			        if (rekening.pinValid(pin)) {
			            validasiPin = true;
			        } else {
			            System.out.println("PIN tidak valid!");
			            System.out.println("PIN harus kombinasi acak, tidak boleh berulang atau berurutan.");
			        }
			    }

			    // Membuat rekening setelah PIN valid
			    akunAktif = new rekening(no, nama, saldo, pin);
			    bukaRekening.add(akunAktif);

			    break;
				
			case 2 :
				if (akunAktif == null) {
					System.out.println("Error : Mohon maaf, anda belum memiliki norek!");
				}else {
					System.out.print("Masukkan nominal setor: ");
					double setor = input.nextDouble();
					akunAktif.setorTunai(setor);//memanggil behavior/method
				}
				break;
				
			case 3 :
				if (akunAktif == null) {
					System.out.println("Error : Mohon maaf, anda belum memiliki norek!");
				}else {
					// pin sblm tarik tunai
					System.out.print("Masukkan PIN anda: ");
					String pinInput = input.nextLine();
					
					if(akunAktif.otentikasi(pinInput)) {
						System.out.print("Masukkan nominal yang akan ditarik: ");
						double tarik = input.nextDouble();
						akunAktif.tarikTunai(tarik);//memanggil behavior/method
					}else{
						System.out.println("Akses Ditolak: PIN yang Anda masukkan salah!");
					}
				}
				break;
				
			case 4:
				if(akunAktif == null) {
					System.out.println("Error : Anda belum membuka rekening!");
				}else {
					akunAktif.cekInformasi();
				}
				break;
			case 0 :
				isRunning = false;
				System.out.println("Sistem ditutup. Terimaksih!");
				break;
			case 5 :
				if (bukaRekening.isEmpty()) {
					System.out.println("Error : Belum ada rekening yang tersedia!");
				} else {
					System.out.print("Masukkan no Rekening yang ingin digunakan: ");
					String noCari = input.nextLine();
					
					rekening rekeningDitemukan = null;
					for (rekening rekening : bukaRekening) {
						if (rekening.getNomorRekening().equals(noCari)) {
							rekeningDitemukan = rekening;
							break;
						}
					}
					if (rekeningDitemukan != null ) {
						akunAktif = rekeningDitemukan;
						System.out.println("Berhasil ganti akun ke rekening atas nama "+ akunAktif.getNamaPemilik());
					}else {
						System.out.println("Gagal : no rekening tidak ditemukan !");
					}
				}
				break;
			case 6 : 
				if(akunAktif == null) {
					System.out.println("Error : Mohon maaf, anda belum memiliki norek!");
				}else {
					//PIN untuk Cetak Mutasi
                    System.out.print("Masukkan PIN Anda: ");
                    String pinInput = input.nextLine();
                    
                    if(akunAktif.otentikasi(pinInput)) {
                        akunAktif.cetakMutasi();
                    } else {
                    	System.out.println("Akses Ditolak: PIN yang Anda masukkan salah!");
                    }
				}
				break;
			case 7 :
				if(akunAktif == null) {
					System.out.println("Error : Mohon maaf, anda belum memiliki norek!");
				}else {
					akunAktif.cetakRiwayat();
				}
				break;
			case 8 :
				if(akunAktif == null) {
					System.out.println("Error : Mohon maaf, anda belum memiliki norek!");
				}else {
					System.out.print("Masukkan PIN: ");
                    String pinLama = input.nextLine();
                    System.out.print("Masukkan PIN Baru (6 digit): ");
                    String pinBaru = input.nextLine();
                    
                    akunAktif.gantiPin(pinLama, pinBaru);
				}
				break;
		
			default :
				System.out.println("Pilihan tidak Valid!!");
			}
		}
		input.close();
	}
}
