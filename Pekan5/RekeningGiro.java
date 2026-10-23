package Pekan5;

import java.text.NumberFormat;
import java.util.Locale;

import Pekan.Transaksi;
import Pekan5.Rekening;

public class RekeningGiro extends Rekening{
	private double batasOverdraft;
	
	public RekeningGiro(String nomor, String nama, double saldoAwal, String pinAwal, double batasOverdraft) {
		//memanggil inisialisasi dasar dari Superclass
		super(nomor, nama, saldoAwal, pinAwal);
		this.batasOverdraft = batasOverdraft;
	}
	//implementasi overriding
	@Override
	public boolean tarikTunai(double nominal) {
		if (nominal <=0) {
			System.out.println("Transaksi Gagal :  Minimal tarik harus lebih dari 0!");
			return false;
		}else if (nominal > (saldo + batasOverdraft)){
			System.out.println("Transaksi Gagal: Saldo tidak mencukupi. Saldo Anda: Rp"+ (saldo + batasOverdraft));
			return false;
		}else { 
			saldo -= nominal;
			
			String idTrx = "TRX-T"+System.currentTimeMillis();
			NumberFormat rupiah = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
			riwayatTransaksi.add(new Transaksi(idTrx, "Debit", nominal));
			
			System.out.println("Tarik tunai Rp"+ nominal + " berhasil. Saldo saat ini : "+ rupiah.format(saldo));
			return true;
		}
	}
	
	public double getBatasOverdraft() {return batasOverdraft;}
}