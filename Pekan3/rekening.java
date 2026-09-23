package Pekan3;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import Pekan.Transaksi;

public class rekening {
	//mengunci atribut dgn private
	private String nomorRekening;
	private String namaPemilik;
	private double saldo;
	private String pin;//data sensitif!
	
	// Menyimpan riwayat PIN agar PIN lama tidak bisa dipakai lagi
    private ArrayList<String> riwayatPin;
    private ArrayList<Transaksi> riwayatTransaksi;
	
    public rekening(String nomor, String nama, double saldoAwal, String pinAwal) {
        this.nomorRekening = nomor;
        this.namaPemilik = nama;
        this.saldo = saldoAwal;

        // validasi PIN
        if (pinAwal != null && pinAwal.matches("\\d{6}") && pinValid(pinAwal)) {
            this.pin = pinAwal;
        } else {
            System.out.println("OWWW PIN harus kombinasi 6 digit, tidak boleh berulang/berurutan!");
            this.pin = "583041";
        }

        // inisialisasi ArrayList
        this.riwayatPin = new ArrayList<>();
        this.riwayatPin.add(this.pin);

        this.riwayatTransaksi = new ArrayList<>();

        NumberFormat rupiah = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
        System.out.println("Rekening atas nama " + namaPemilik +
                           " berhasil dibuat dengan saldo " + rupiah.format(saldo));
    }
	
	//getter untuk atribut yg diizinkan dibaca publik
	public String getNomorRekening( ) {return nomorRekening;}
	public String getNamaPemilik() {return namaPemilik;}
	
	//method otentikasi internal (validasi enkapsulasi)
	public boolean otentikasi(String inputPin) {
		return this.pin.equals(inputPin);
	}
	public static boolean pinValid(String pin) {
        boolean semuaSama = true;
        boolean berurutanNaik = true;
        boolean berurutanTurun = true;

        for (int i = 1; i < 6; i++) {
            if (pin.charAt(i) != pin.charAt(0)) semuaSama = false;
            if (pin.charAt(i) != pin.charAt(i - 1) + 1) berurutanNaik = false;
            if (pin.charAt(i) != pin.charAt(i - 1) - 1) berurutanTurun = false;
        }

        if (semuaSama || berurutanNaik || berurutanTurun) {
            return false; 
        }
        return true; 
    }
	// fitur ganti PIN
	public boolean gantiPin(String pinLama, String pinBaru) {

	    // Cek PIN lama
	    if (!otentikasi(pinLama)) {
	        System.out.println("Gagal: PIN lama Anda salah!");
	        return false;
	    }

	    // Harus 6 digit angka
	    if (pinBaru == null || !pinBaru.matches("\\d{6}")) {
	        System.out.println("Gagal: PIN harus terdiri dari 6 digit angka!");
	        return false;
	    }

	    // Tidak boleh ada angka yang berulang
	    for (int i = 0; i < pinBaru.length(); i++) {
	        for (int j = i + 1; j < pinBaru.length(); j++) {
	            if (pinBaru.charAt(i) == pinBaru.charAt(j)) {
	                System.out.println("Gagal: PIN tidak boleh memiliki angka yang berulang!");
	                return false;
	            }
	        }
	    }

	    // Tidak boleh berurutan naik atau turun
	    boolean naik = true;
	    boolean turun = true;

	    for (int i = 0; i < pinBaru.length() - 1; i++) {
	        int angka1 = Character.getNumericValue(pinBaru.charAt(i));
	        int angka2 = Character.getNumericValue(pinBaru.charAt(i + 1));

	        if (angka2 != angka1 + 1) {
	            naik = false;
	        }

	        if (angka2 != angka1 - 1) {
	            turun = false;
	        }
	    }

	    if (naik || turun) {
	        System.out.println("Gagal: PIN tidak boleh menggunakan angka berurutan!");
	        return false;
	    }

	    // Tidak boleh menggunakan PIN yang pernah dipakai
	    if (riwayatPin.contains(pinBaru)) {
	        System.out.println("Gagal: PIN pernah digunakan sebelumnya!");
	        return false;
	    }

	    // Simpan PIN baru
	    this.pin = pinBaru;
	    this.riwayatPin.add(pinBaru);

	    System.out.println("Berhasil, PIN Anda telah diperbarui!");
	    return true;
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
			System.out.println("Gagal : Nominal setor harus lebih dari Rp10.000!");
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
	
}
