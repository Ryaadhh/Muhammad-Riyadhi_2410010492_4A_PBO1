package rentalps;
import java.util.ArrayList;
import java.util.Scanner;

public class RentalPS {

    private static ArrayList<Konsol> daftarKonsol = new ArrayList<>();
    private static ArrayList<Penyewaan> riwayatSewa = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        inisialisasiData();

        int pilihan;

        do {
            tampilkanMenu();
            pilihan = inputAngka("Pilih menu: ");

            switch (pilihan) {
                case 1:
                    tampilkanDaftarKonsol();
                    break;
                case 2:
                    prosesSewa();
                    break;
                case 3:
                    prosesPengembalian();
                    break;
                case 4:
                    tampilkanRiwayat();
                    break;
                case 5:
                    System.out.println("Terima kasih telah menggunakan Data Rental PS. Sampai jumpa!");
                    break;
                default:
                    System.out.println(">> Menu tidak tersedia, silakan pilih ulang.\n");
            }

        } while (pilihan != 5);

        scanner.close();
    }

    private static void inisialisasiData() {
        daftarKonsol.add(new PS4("PS4-TV1"));
        daftarKonsol.add(new PS4("PS4-TV2"));
        daftarKonsol.add(new PS4("PS4-TV3"));
        daftarKonsol.add(new PS5("PS5-TV1"));
        daftarKonsol.add(new PS5("PS5-TV2"));
        daftarKonsol.add(new PS5("PS5-TV3"));
    }

    private static void tampilkanMenu() {
        System.out.println("=================================");
        System.out.println("       RENTAL PLAYSTATION");
        System.out.println("=================================");
        System.out.println("1. Lihat Daftar Konsol");
        System.out.println("2. Sewa Konsol");
        System.out.println("3. Kembalikan Konsol");
        System.out.println("4. Riwayat Penyewaan");
        System.out.println("5. Keluar");
        System.out.println("=================================");
    }

    private static void tampilkanDaftarKonsol() {
        System.out.println("\n--- DAFTAR KONSOL ---");
        for (Konsol k : daftarKonsol) {
            System.out.println(k);
        }
        System.out.println();
    }

    private static void prosesSewa() {
        tampilkanDaftarKonsol();
        System.out.print("Masukkan kode konsol yang ingin disewa: ");
        String kode = scanner.nextLine();

        try {
            Konsol konsol = cariKonsol(kode);

            if (!konsol.isTersedia()) {
                throw new StokKosongException("Konsol " + kode + " sedang disewa orang lain.");
            }

            System.out.print("Masukkan nama penyewa: ");
            String nama = scanner.nextLine();

            int jam = inputAngka("Masukkan lama sewa (jam): ");

            if (jam <= 0) {
                System.out.println(">> Jumlah jam harus lebih dari 0.\n");
                return;
            }

            double total = konsol.hitungBiaya(jam); 
            konsol.setTersedia(false);

            Penyewaan sewa = new Penyewaan(nama, konsol.getKodeKonsol(), konsol.getTipe(), jam, total);
            riwayatSewa.add(sewa);

            System.out.println("\n>> Penyewaan berhasil!");
            System.out.println(sewa);
            System.out.println();

        } catch (StokKosongException e) {
            System.out.println(">> Gagal menyewa: " + e.getMessage() + "\n");
        }
    }

    private static void prosesPengembalian() {
        System.out.print("Masukkan kode konsol yang dikembalikan: ");
        String kode = scanner.nextLine();

        try {
            Konsol konsol = cariKonsol(kode);

            if (konsol.isTersedia()) {
                System.out.println(">> Konsol ini tidak sedang disewa.\n");
                return;
            }

            konsol.setTersedia(true);
            System.out.println(">> Konsol " + kode + " berhasil dikembalikan.\n");

        } catch (StokKosongException e) {
            System.out.println(">> Gagal mengembalikan: " + e.getMessage() + "\n");
        }
    }

    private static void tampilkanRiwayat() {
        System.out.println("\n--- RIWAYAT PENYEWAAN ---");
        if (riwayatSewa.isEmpty()) {
            System.out.println("Belum ada riwayat penyewaan.\n");
            return;
        }
        for (Penyewaan p : riwayatSewa) {
            System.out.println(p);
        }
        System.out.println();
    }

    private static Konsol cariKonsol(String kode) throws StokKosongException {
        for (Konsol k : daftarKonsol) {
            if (k.getKodeKonsol().equalsIgnoreCase(kode)) {
                return k;
            }
        }
        throw new StokKosongException("Kode konsol \"" + kode + "\" tidak ditemukan.");
    }

    private static int inputAngka(String pesan) {
        int angka = -1;
        boolean valid = false;

        while (!valid) {
            System.out.print(pesan);
            try {
                angka = Integer.parseInt(scanner.nextLine().trim());
                valid = true;
            } catch (NumberFormatException e) {
                System.out.println(">> Input harus berupa angka, coba lagi.");
            }
        }
        return angka;
    }
}