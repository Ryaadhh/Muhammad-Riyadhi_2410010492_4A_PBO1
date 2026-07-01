package rentalps;
public class Penyewaan {
    private String namaPenyewa;
    private String kodeKonsol;
    private String tipeKonsol;
    private int jumlahJam;
    private double totalBiaya;

    public Penyewaan(String namaPenyewa, String kodeKonsol, String tipeKonsol, int jumlahJam, double totalBiaya) {
        this.namaPenyewa = namaPenyewa;
        this.kodeKonsol = kodeKonsol;
        this.tipeKonsol = tipeKonsol;
        this.jumlahJam = jumlahJam;
        this.totalBiaya = totalBiaya;
    }

    public String getNamaPenyewa() {
        return namaPenyewa;
    }

    public String getKodeKonsol() {
        return kodeKonsol;
    }

    public String getTipeKonsol() {
        return tipeKonsol;
    }

    public int getJumlahJam() {
        return jumlahJam;
    }

    public double getTotalBiaya() {
        return totalBiaya;
    }

    @Override
    public String toString() {
        return "Penyewa: " + namaPenyewa
                + " | Konsol: " + kodeKonsol + " (" + tipeKonsol + ")"
                + " | Lama: " + jumlahJam + " jam"
                + " | Total: Rp" + totalBiaya;
    }
}