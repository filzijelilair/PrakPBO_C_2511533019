package Pekan;

import java.text.NumberFormat;
import java.util.Locale;


public class Transaksi {
	String idTransaksi;
	String jenis;
	double nominal;
	
	//constructor
	public Transaksi(String id, String jenis, double nominal) {
		this.idTransaksi=id;
		this.jenis= jenis;
		this.nominal= nominal;
	}
	
	public void cetakDetail() {
		NumberFormat rupiah = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
		System.out.println("ID : " +idTransaksi + " | Jenis : "+ jenis +  " |Nominal Rp"+rupiah.format(nominal));
	}

}
