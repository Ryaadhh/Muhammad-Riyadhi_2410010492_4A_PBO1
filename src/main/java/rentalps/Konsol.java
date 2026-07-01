package rentalps;
public abstract class Konsol {
    private String kodeKonsol;
    private String tipe;
    private boolean tersedia;

    public Konsol(String kodeKonsol, String tipe) {
        this.kodeKonsol = kodeKonsol;
        this.tipe = tipe;
        this.tersedia = true;
    }

    public String getKodeKonsol() {
        return kodeKonsol;
    }

    public String getTipe() {
        return tipe;
    }

    public boolean isTersedia() {
        return tersedia;
    }

    public void setTersedia(boolean tersedia) {
        this.tersedia = tersedia;
    }

    public abstract double getTarifPerJam();

    public double hitungBiaya(int jumlahJam) {
        return getTarifPerJam() * jumlahJam;
    }

    @Override
    public String toString() {
        String status = tersedia ? "Tersedia" : "Sedang Disewa";
        return "[" + kodeKonsol + "] " + tipe + " - Tarif: Rp" + getTarifPerJam() + "/jam - Status: " + status;
    }
}