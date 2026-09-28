package pekan4_2511532020;

public class RekeningGiro extends Rekening {
	private double batasOverdraft;
	
	public RekeningGiro(String nomor, String nama, double saldoAwal, String pinAwal, double batasOverdraft) {
		super(nomor, nama, saldoAwal, pinAwal);
		this.batasOverdraft = batasOverdraft;
	}
	
	public double getBatasOverdraft() {
		return batasOverdraft;
	}

}
