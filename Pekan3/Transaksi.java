package Pekan3;

import java.text.NumberFormat;
import java.util.Locale;


public class Transaksi {
	//ngubaj semua atribut menjadi private
	private String idTransaksi;
	private String jenis;
	private double nominal;
	
	//constructor
	public Transaksi(String id, String jenis, double nominal) {
		this.idTransaksi=id;
		this.jenis= jenis;
		this.nominal= nominal;
	}
	
	// hanya menyediakan getter(read-only)
	public String getIdTransaksi() {return idTransaksi;}
	public String getJenis() {return jenis;}
	public double getnominal() {return nominal;}
	
	public void cetakDetail() {
		NumberFormat rupiah = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
		System.out.println("ID : " +idTransaksi + " | Jenis : "+ jenis +  " |Nominal Rp"+rupiah.format(nominal));
	}

}
