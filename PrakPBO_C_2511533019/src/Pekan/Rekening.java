package Pekan;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class Rekening {
	String nomorRekening;
	String namaPemilik;
	double saldo;
	//Implementasi Asosiasi (1-to-many)
	ArrayList<Transaksi> riwayatTransaksi;
	
	public Rekening(String nomor, String nama, double saldoAwal) {
		this.nomorRekening = nomor;
		this.namaPemilik = nama;
		this.saldo = saldoAwal;
		
		//Wajib menginisialisasi Arraylist di dalam constructor agar tidak NullPointerException
		this.riwayatTransaksi=new ArrayList<>();
		
		NumberFormat rupiah = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
		System.out.println("Rekening atas nama "+ namaPemilik + " berhasil dibuat dengan saldo " + rupiah.format(saldo));
	}
	
	public void setorTunai(double nominal) {
		if (nominal > 10000 ) {
			saldo += nominal;
			//merekan riwayat (pembuatan objek transaksi di dalam method)
			String idTrx = "TRX-S"+System.currentTimeMillis();
			Transaksi trxBaru = new Transaksi(idTrx, "Kredit", nominal);
			riwayatTransaksi.add(trxBaru);
			
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
			String idTrx = "TRX-T"+System.currentTimeMillis();
			Transaksi trxBaru = new Transaksi(idTrx, "Debit", nominal);
			riwayatTransaksi.add(trxBaru);
			NumberFormat rupiah = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
			System.out.println("Tarik tunai Rp"+ nominal + " berhasil. Saldo saat ini : "+ rupiah.format(saldo));
		}
	}
	public void cetakMutasi() {
		if (riwayatTransaksi.isEmpty()) { 
			System.out.println("Belum ada transaksi pada rekening ini"); 
		} else { 
			System.out.println("--- MUTASI REKENING ---"); 
			for (Transaksi trx : riwayatTransaksi) { 
				trx.cetakDetail(); 
			} 
			System.out.println("-----------------------"); 
		} 
	}
	public void cetakRiwayat() {
		if (riwayatTransaksi.isEmpty()) {
			System.out.println("Belum ada transaksi pada rekening ini");
		} else {
		    System.out.println("--- 3 TRANSAKSI TERBARU ---");
		    int jumlah = Math.min(3, riwayatTransaksi.size());
		    for (int i = riwayatTransaksi.size() - 1; i >= riwayatTransaksi.size() - jumlah; i--) {
		    	riwayatTransaksi.get(i).cetakDetail();
		    }
		    System.out.println("---------------------------");
		}
	}
	public void chal3() {
		if (riwayatTransaksi.isEmpty()) {
			System.out.println("Belum ada transaksi pada rekening ini");
			return;
		}
			double totalSetor=0;
			double totalTarik=0;
			  		  
			for (Transaksi trx : riwayatTransaksi) {
		        if (trx.jenis.equalsIgnoreCase("Kredit")) {
		            totalSetor += trx.nominal;
		        } else if (trx.jenis.equalsIgnoreCase("Debit")) {
		            totalTarik += trx.nominal;
		        }

		    }
			double akumulasi = totalSetor - totalTarik;
			NumberFormat rupiah = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
		    System.out.println("\n=== Challange 3 ===");
		    System.out.println("No. Rekening   : " + nomorRekening);
		    System.out.println("Nama Pemilik   : " + namaPemilik);
		    System.out.println("------------------------------------");
		    System.out.println("Total Setor    : " + rupiah.format(totalSetor));
		    System.out.println("Total Tarik    : " + rupiah.format(totalTarik));
		    System.out.println("Akumulasi Trx  : " + rupiah.format(akumulasi));
		    System.out.println("Saldo Saat Ini : " + rupiah.format(saldo));
		    System.out.println("------------------------------------");
		}

}
