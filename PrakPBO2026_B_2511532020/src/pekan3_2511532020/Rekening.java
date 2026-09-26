package pekan3_2511532020;

import java.util.ArrayList;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;;

public class Rekening {
	private String nomorRekening;
	private String namaPemilik;
	private double saldo;
	private String pin;
	
	private int percobaanGagal = 0;
	private boolean isTerblokir = false;
	
	private ArrayList<Transaksi> riwayatTransaksi;
	
	public String formatRupiah(double nominal) {
		DecimalFormatSymbols simbol = new DecimalFormatSymbols();
		simbol.setGroupingSeparator('.');
		simbol.setDecimalSeparator(',');
		
		DecimalFormat formatter = new DecimalFormat("Rp#,##0.00", simbol);
		return formatter.format(nominal);
	}
	
	public Rekening(String nomor, String nama, double saldoAwal, String pinAwal) {
		nomorRekening = nomor;
		namaPemilik = nama;
		saldo = saldoAwal;
		
		if (pinAwal.length() == 6) {
			this.pin = pinAwal;
		} else {
			System.out.println("Peringatan: PIN harus 6 digit! Menggunakan PIN default 123456");
			this.pin = "123456";
		}
		
		this.riwayatTransaksi = new ArrayList<>();
		
		System.out.println("Rekening atas nama " + namaPemilik + " berhasil dibuat.");
	}
	
	public String getNomorRekening() { return nomorRekening; }
	public String getNamaPemilik() { return namaPemilik; }
	public boolean isTerblokir() { return isTerblokir; }
	
	public boolean otentikasi(String inputPin) {
		if (isTerblokir) {
			System.out.println("Akses Ditolak: akun anda terblokir!");
			return false;
		}
		
		if (this.pin.equals(inputPin)) {
			percobaanGagal = 0; 
			return true;
		} else {
			percobaanGagal++;
			System.out.println("Akses Ditolak : PIN yang anda masukkan salah!");
			
			if (percobaanGagal >= 3) {
				isTerblokir = true;
				System.out.println("akun anda terblokir");
			} else {
				System.out.println("Sisa percobaan: " + (3 - percobaanGagal));
			}
			return false;
		}
	}
	
	public void setorTunai(double nominal) {
		if (isTerblokir) {
			System.out.println("Transaksi Gagal: akun anda terblokir!");
			return;
		}
		
		if (nominal > 0) {
			saldo += nominal;
			
			String idTrxs = "TRX-S-" + System.currentTimeMillis();
			Transaksi trxsBaru = new Transaksi(idTrxs, "Kredit", nominal);
			riwayatTransaksi.add(trxsBaru);
			
			System.out.println("Setor tunai " + formatRupiah(nominal) + " berhasil. Saldo saat ini: " + formatRupiah(saldo));
		} else {
			System.out.println("Gagal: Nominal setor harus lebih dari 0!");
		}
	}
	
	public void tarikTunai(double nominal) {
		if (isTerblokir) {
			System.out.println("Transaksi Gagal: akun anda terblokir!");
			return;
		}
		
		if (nominal < 10000) {
			System.out.println("Transaksi Gagal : Minimal nominal penarikan " + formatRupiah(10000));
		} else if (nominal > saldo) {
			System.out.println("Transaksi Gagal: Saldo tidak mencukupi. Saldo Anda: " + formatRupiah(saldo));
		} else {
			saldo -= nominal;
			
			String idTrxt = "TRX-T-" + System.currentTimeMillis();
			Transaksi trxtBaru = new Transaksi(idTrxt, "Debit", nominal);
			riwayatTransaksi.add(trxtBaru);
			
			System.out.println("Tarik tunai berhasil sebesar " + formatRupiah(nominal));
			System.out.println("Saldo yang tersisa = " + formatRupiah(saldo));
		}
	}
		
	public void cekInformasi() {
		System.out.println("--- INFO REKENING ---");
		System.out.println("No. Rekening : " + nomorRekening);
		System.out.println("Nama Pemilik : " + namaPemilik);
		System.out.println("Saldo Akhir  : " + formatRupiah(saldo));
		System.out.println("----------------------");
		
	}
		
	public boolean gantiPin(String pinLama, String pinBaru) {

	    if (!this.pin.equals(pinLama)) {
	        System.out.println("Gagal: PIN lama Anda salah!");
	        return false;
	    }

	    if (pinLama.equals(pinBaru)) {
	        System.out.println("Gagal: PIN baru tidak boleh sama dengan PIN lama!");
	        return false;
	    }

	    if (!pinBaru.matches("\\d{6}")) {
	        System.out.println("Gagal: PIN baru harus berupa 6 digit angka!");
	        return false;
	    }

	    this.pin = pinBaru;
	    System.out.println("Berhasil: PIN Anda telah diperbarui!");
	    return true;
	}
	
	public void cetakMutasi() {
		System.out.println("--- RIWAYAT MUTASI REKENING ---");
		    if (riwayatTransaksi.isEmpty()) {
		        System.out.println("Belum ada transaksi pada rekening ini");
		    } else {
		        for (Transaksi t : riwayatTransaksi) {
		            t.cetakDetail(); 
		        }
		    }
		    System.out.println("-------------------------------");
		
	}
}
