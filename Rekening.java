package Pekan1;
import java.text.NumberFormat;
import java.util.Locale;

public class Rekening {
	String nomorRekening;
	String namaPemilik;
	double saldo;
	
	public Rekening(String nomor, String nama, double saldoAwal) {
		nomorRekening = nomor;
		namaPemilik = nama;
		saldo = saldoAwal;
		NumberFormat rupiah = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
		System.out.println("Rekening atas nama "+ namaPemilik + " berhasil dibuat dengan saldo Rp" + rupiah.format(saldo));
	}
	
	public void setorTunai(double nominal) {
		if (nominal > 10000 ) {
			saldo += nominal;
			System.out.println("Setor tunai Rp"+ nominal + " berhasil. Saldo saat ini : "+ saldo);
		}else {
			System.out.println("Gagal : Nominal setor harus lebih dari 0!");
		}
	}
	public void cekInformasi() {
		System.out.println("--- INFO REKENING ---");
		System.out.println("No. Rekening : "+ nomorRekening);
		System.out.println("Nama Pemilik : "+ namaPemilik);
		NumberFormat rupiah = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
		System.out.println("Saldo akhir : " + rupiah.format(saldo));
		System.out.println("---------------------");
	}
	public void tarikTunai(double nominal) {
		if (nominal < 10000) {
			System.out.println("Transaksi Gagal :  Minimal nominal penarikan 10.000!");
		}else if (nominal > saldo){
			System.out.println("Transaksi Gagal: Saldo tidak mencukupi. Saldo Anda: Rp"+ saldo);
		}else { 
			saldo -= nominal;
			NumberFormat rupiah = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
			System.out.println("Tarik tunai Rp"+ nominal + " berhasil. Saldo saat ini : "+ rupiah.format(saldo));
		}
	}

}
