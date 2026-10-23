package Pekan4;
import Pekan.Transaksi;;

public class RekeningTabungan extends Rekening {
	//atribut spesifik yang hanya dimiliki oleh Tabungan
	private double sukuBunga;
	
	//Constructor Subclass
	public RekeningTabungan(String nomor, String nama, double saldoAwal,String pinAwal, double sukuBunga) {
		//super() memanggil constructor kelas induk (Rekening). WAJIB berada dibaris pertama!
		super(nomor, nama, saldoAwal, pinAwal);
		this.sukuBunga=sukuBunga;
	}
	
	public void tambahBungaAkhirBulan() {
		//menghitung bunga
		//mengapa bisa mengakses saldo secara langsung dari class RekningTabungan?
		double nominalBunga = saldo * (sukuBunga/100);
		saldo += nominalBunga;
		
		//mencatat riwayat Transaksi
		String idTrx = "TRX-B-" + System.currentTimeMillis();
		riwayatTransaksi.add(new Transaksi(idTrx, "Bunga", nominalBunga));
	
		System.out.println("Bunga "+sukuBunga + "% berhasil ditambahkan : Rp"+ nominalBunga);
	}
}
