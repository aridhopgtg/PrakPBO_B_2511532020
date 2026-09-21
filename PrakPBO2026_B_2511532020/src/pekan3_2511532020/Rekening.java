package pekan3_2511532020;

import java.util.ArrayList;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;;

public class Rekening {
	private String nomorRekening;
	private String namaPemilik;
	private double saldo;
	private String pin;
	
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
	
	public boolean otentikasi(String inputPin) {
		return this.pin.equals(inputPin);
	}
	
	public void setorTunai(double nominal) {
		if (nominal > 0) {
			saldo += nominal;
			
			String idTrxs = "TRX-S-" + System.currentTimeMillis();
			Transaksi trxsBaru = new Transaksi(idTrxs, "Kredit", nominal);
			riwayatTransaksi.add(trxsBaru);
			
			System.out.println("Setor tunai " + formatRupiah(nominal) + " berhasil. Saldo saat ini: " + saldo);
		} else {
			System.out.println("Gagal: Nominal setor harus lebih dari 0!");
		}
	}
	
	public void tarikTunai(double nominal) {
		if (nominal < 10000) {
			System.out.println("Transaksi Gagal :  Minimal nominal penarikan " + formatRupiah(10000));
		} else if (nominal > saldo) {
			System.out.println("Transaksi Gagal: Saldo tidak mencukupi. Saldo Anda: " + formatRupiah(nominal));
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
