package pekan3_2511532020;

import java.util.Scanner;
import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		ArrayList<Rekening> daftarRekening = new ArrayList<>();
		Rekening akunAktif = null; //Objek belum diinialisasi
		boolean isRunning = true;
		
		System.out.println("=== SISTEM PERBANKAN MINI ===");
		
		while (isRunning) {
			System.out.println("\nMenu Utama:");
			System.out.println("1. Buka Rekening Baru");
			System.out.println("2. Setor Tunai");
			System.out.println("3. Tarik Tunai");
			System.out.println("4. Cek Informasi Rekening");
			System.out.println("5. Ganti PIN");
			System.out.println("6. Cetak Mutasi (Riwayat)");
			System.out.println("0. Keluar");
			System.out.print("Pilih menu: ");
			
			int pilihan = input.nextInt();
			input.nextLine(); // Membersihkan buffer enter
			
			switch (pilihan) {
			case 1:
				System.out.print("Masukkan No Rekening: ");
				String no = input.nextLine();
				System.out.print("Masukkan Nama Pemilik: ");
				String nama = input.nextLine();
				System.out.print("Masukkan Saldo Awal: ");
				double saldo = input.nextDouble();
				input.nextLine();
				String pin = "";
			    while (true) {
			        System.out.print("Masukkan PIN (6 digit angka): ");
			        pin = input.nextLine();
			        
			        if (pin.matches("\\d{6}")) {
			            break; 
			        } else {
			            System.out.println("Error: PIN harus berupa 6 digit angka! Silakan coba lagi.");
			        }
			    }
				Rekening rekeningBaru = new Rekening(no, nama, saldo, pin);
				daftarRekening.add(rekeningBaru);
				akunAktif = rekeningBaru;
				break;
				
			case 2:
				if (akunAktif == null) {
					System.out.println("Error: Mohon maaf, Anda belum memiliki nomor rekening!");
				} else if (akunAktif.isTerblokir()) {
					System.out.println("Transaksi Gagal: akun anda terblokir!");
				} else {
					System.out.print("Masukkan PIN Anda: ");
			        String pinYangDiinput = input.nextLine();

			        if (akunAktif.otentikasi(pinYangDiinput)) {
					System.out.print("Masukkan nominal setor: ");
					double setor = input.nextDouble();
					akunAktif.setorTunai(setor); // Memanggil Behavior / method
				   }
				}
				break;
				
			case 3: 
				if (akunAktif == null) {
					System.out.println("Error: Mohon maaf, Anda belum memiliki nomor rekening!");
				} else if (akunAktif.isTerblokir()) {
					System.out.println("Transaksi Gagal: akun anda terblokir!");
				} else {
					System.out.print("Masukkan PIN Anda: ");
			        String pinYangDiinput = input.nextLine();

			        if (akunAktif.otentikasi(pinYangDiinput)) {
			            System.out.print("Masukkan nominal tarik: ");
			            double tarik = input.nextDouble();
			            input.nextLine(); 
			            
			            akunAktif.tarikTunai(tarik);
			        } else {
			        	System.out.println("Akses Ditolak : PIN yang anda masukkan salah!");
			        }
				}
				break;
				
			case 4:
				if (akunAktif == null) {
					System.out.println("Error: Anda belum membuka rekening!");
				} else {
					akunAktif.cekInformasi();
				}
				break;
				
			case 5:
				if (akunAktif == null) {
			        System.out.println("Error: Anda belum membuka rekening!");
			    } else {
			        System.out.print("Masukkan PIN Lama: ");
			        String pinLama = input.nextLine();
			        
			        System.out.print("Masukkan PIN Baru (6 digit angka): ");
			        String pinBaru = input.nextLine();
			        
			        akunAktif.gantiPin(pinLama, pinBaru);
			    }
			    break;

			case 6:
				if (akunAktif == null) {
				    System.out.println("Error: Anda belum membuka rekening!");
				} else {
					System.out.print("Masukkan PIN Anda: ");
			        String pinYangDiinput = input.nextLine();
			        
			        if (akunAktif.otentikasi(pinYangDiinput)) {
			        	akunAktif.cetakMutasi();
			        } else {
			        	System.out.println("Akses Ditolak : PIN yang anda masukkan salah!");
			        }
				}
				break;
				
			case 0:
				isRunning = false;
				System.out.println("Sistem ditutup. Terima kasih!");
				break;
				
			default:
				System.out.println("Pilihan tidak valid!");
			}
		}
		input.close();
	}

}
