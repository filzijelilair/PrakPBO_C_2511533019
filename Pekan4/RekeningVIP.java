package Pekan4;
import Pekan.Transaksi;

public class RekeningVIP extends Rekening{
	private double Bonus = 100000;
	
	public RekeningVIP(String nomor, String nama, double saldoAwal, String pinAwal) {
	    super(nomor, nama, saldoAwal, pinAwal);

	    saldo += Bonus;

	    String idTrx = "TRX-BONUS-" + System.currentTimeMillis();
	    riwayatTransaksi.add(new Transaksi(idTrx, "Bonus Pendaftaran", Bonus));

	    System.out.println("Selamat! Bonus pendaftaran Rp" + Bonus + 
	                       " telah ditambahkan ke saldo Anda.");
	}
	public double getBonus() {
	    return Bonus;
	}
}
