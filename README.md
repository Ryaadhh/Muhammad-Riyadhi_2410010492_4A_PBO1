# Proyek Akhir Pemrograman Berbasis Objek 1

Proyek ini adalah aplikasi **Rental PlayStation** berbasis Java sebagai tugas akhir dari mata kuliah Pemrograman Berbasis Objek 1.

## Deskripsi

Aplikasi ini mensimulasikan sistem penyewaan konsol PlayStation (PS4 dan PS5). Pengguna dapat melihat daftar konsol yang tersedia, menyewa konsol, mengembalikan konsol, dan melihat riwayat penyewaan. Aplikasi berjalan berbasis teks (CLI) dan menggunakan menu interaktif.

Aplikasi ini mengimplementasikan beberapa konsep penting dalam pemrograman berorientasi objek (OOP) seperti Class, Object, Atribut, Constructor, Mutator, Accessor, Encapsulation, Inheritance, Polymorphism, Seleksi, Perulangan, IO Sederhana, Array/Collection, dan Error Handling.

## Struktur Program

```
rentalps/
├── Konsol.java            # Abstract class induk (parent)
├── PS4.java               # Turunan dari Konsol (PS4)
├── PS5.java               # Turunan dari Konsol (PS5)
├── Penyewaan.java         # Menyimpan data transaksi penyewaan
├── StokKosongException.java  # Custom exception
└── RentalPS.java          # Class utama (method main)
```

## Penjelasan Kode

Berikut adalah bagian kode yang relevan dengan setiap konsep OOP yang diimplementasikan:

### 1. Class
Class adalah blueprint atau template untuk membuat object. Pada proyek ini terdapat 6 class: `Konsol`, `PS4`, `PS5`, `Penyewaan`, `StokKosongException`, dan `RentalPS`.

```java
public abstract class Konsol { ... }

public class PS4 extends Konsol { ... }

public class Penyewaan { ... }
```

### 2. Object
Object adalah instance yang dibuat dari class. Pada kode ini, object dibuat saat inisialisasi data konsol dan saat transaksi penyewaan berlangsung.

```java
daftarKonsol.add(new PS4("PS4-TV1"));
daftarKonsol.add(new PS5("PS5-TV1"));

Penyewaan sewa = new Penyewaan(nama, konsol.getKodeKonsol(), konsol.getTipe(), jam, total);
```

### 3. Atribut
Atribut adalah variabel yang dimiliki oleh sebuah class. Pada kode ini, `Konsol` memiliki atribut `kodeKonsol`, `tipe`, dan `tersedia`, sedangkan `Penyewaan` memiliki `namaPenyewa`, `kodeKonsol`, `tipeKonsol`, `jumlahJam`, dan `totalBiaya`.

```java
// Atribut di class Konsol
private String kodeKonsol;
private String tipe;
private boolean tersedia;

// Atribut di class Penyewaan
private String namaPenyewa;
private int jumlahJam;
private double totalBiaya;
```

### 4. Constructor
Constructor adalah method khusus yang dijalankan pertama kali saat object dibuat. Setiap class dalam proyek ini memiliki constructor.

```java
// Constructor di Konsol
public Konsol(String kodeKonsol, String tipe) {
    this.kodeKonsol = kodeKonsol;
    this.tipe = tipe;
    this.tersedia = true;
}

// Constructor di PS4, memanggil constructor induk dengan super()
public PS4(String kodeKonsol) {
    super(kodeKonsol, "PlayStation 4");
}
```

### 5. Mutator
Mutator (setter) adalah method yang digunakan untuk mengubah nilai atribut. Pada kode ini, `setTersedia()` digunakan untuk mengubah status ketersediaan konsol saat disewa maupun dikembalikan.

```java
public void setTersedia(boolean tersedia) {
    this.tersedia = tersedia;
}

// Dipanggil saat menyewa
konsol.setTersedia(false);

// Dipanggil saat mengembalikan
konsol.setTersedia(true);
```

### 6. Accessor
Accessor (getter) adalah method yang digunakan untuk mengambil nilai atribut yang bersifat private. Pada kode ini terdapat beberapa getter di class `Konsol` dan `Penyewaan`.

```java
public String getKodeKonsol() { return kodeKonsol; }
public String getTipe()       { return tipe; }
public boolean isTersedia()   { return tersedia; }

public String getNamaPenyewa() { return namaPenyewa; }
public int getJumlahJam()      { return jumlahJam; }
public double getTotalBiaya()  { return totalBiaya; }
```

### 7. Encapsulation
Encapsulation adalah konsep menyembunyikan data dengan menjadikan atribut `private` dan hanya bisa diakses lewat method getter/setter. Semua atribut di class `Konsol` dan `Penyewaan` bersifat private.

```java
// Atribut private -> tidak bisa diakses langsung dari luar class
private String kodeKonsol;
private String tipe;
private boolean tersedia;

// Hanya bisa diakses melalui getter dan setter
public String getKodeKonsol() { return kodeKonsol; }
public void setTersedia(boolean tersedia) { this.tersedia = tersedia; }
```

### 8. Inheritance
Inheritance adalah konsep di mana sebuah class mewarisi atribut dan method dari class lain. Class `PS4` dan `PS5` mewarisi class `Konsol` menggunakan kata kunci `extends`.

```java
public class PS4 extends Konsol {
    public PS4(String kodeKonsol) {
        super(kodeKonsol, "PlayStation 4");
    }
    ...
}

public class PS5 extends Konsol {
    public PS5(String kodeKonsol) {
        super(kodeKonsol, "PlayStation 5");
    }
    ...
}
```

### 9. Polymorphism
Polymorphism adalah konsep di mana method yang sama dapat berperilaku berbeda tergantung pada object yang memanggilnya. Pada kode ini terdapat dua bentuk polymorphism:

- **Overriding** → method `getTarifPerJam()` dideklarasikan `abstract` di `Konsol` dan di-override berbeda di `PS4` (Rp8.000) dan `PS5` (Rp15.000).
- **Overriding** → method `toString()` di-override di `Konsol` dan `Penyewaan`.

```java
// Di class Konsol (abstract)
public abstract double getTarifPerJam();

// Di class PS4 -> Override
@Override
public double getTarifPerJam() {
    return 8000;
}

// Di class PS5 -> Override
@Override
public double getTarifPerJam() {
    return 15000;
}

// Dipanggil polimorfik -> hasilnya berbeda tergantung tipe object
double total = konsol.hitungBiaya(jam); // konsol bisa PS4 atau PS5
```

### 10. Seleksi
Seleksi adalah struktur kontrol untuk mengambil keputusan berdasarkan kondisi. Pada kode ini digunakan `switch-case` untuk menu utama dan `if` untuk validasi kondisi konsol.

```java
// switch-case untuk pilihan menu
switch (pilihan) {
    case 1: tampilkanDaftarKonsol(); break;
    case 2: prosesSewa();            break;
    case 3: prosesPengembalian();    break;
    case 4: tampilkanRiwayat();      break;
    case 5: System.out.println("Terima kasih..."); break;
    default: System.out.println(">> Menu tidak tersedia.");
}

// if untuk validasi status konsol
if (!konsol.isTersedia()) {
    throw new StokKosongException("Konsol " + kode + " sedang disewa orang lain.");
}
```

### 11. Perulangan
Perulangan digunakan untuk menjalankan blok kode berulang kali. Pada kode ini digunakan `do-while` untuk menu utama dan `for-each` untuk menampilkan daftar konsol dan riwayat.

```java
// do-while -> menu terus muncul sampai pilih keluar
do {
    tampilkanMenu();
    pilihan = inputAngka("Pilih menu: ");
    ...
} while (pilihan != 5);

// for-each -> iterasi semua konsol di ArrayList
for (Konsol k : daftarKonsol) {
    System.out.println(k);
}
```

### 12. IO Sederhana
IO Sederhana digunakan untuk menerima input dari pengguna dan menampilkan output ke layar. Pada kode ini digunakan class `Scanner` untuk input dan `System.out.println` untuk output.

```java
// Input menggunakan Scanner
private static Scanner scanner = new Scanner(System.in);
System.out.print("Masukkan kode konsol yang ingin disewa: ");
String kode = scanner.nextLine();

// Output menggunakan System.out
System.out.println(">> Penyewaan berhasil!");
System.out.println(sewa);
```

### 13. Array / Collection
Pada kode ini digunakan `ArrayList` sebagai Collection untuk menyimpan banyak object konsol dan riwayat penyewaan secara dinamis. `ArrayList` adalah implementasi dari `List` di Java yang merupakan bagian dari Java Collections Framework.

```java
private static ArrayList<Konsol> daftarKonsol = new ArrayList<>();
private static ArrayList<Penyewaan> riwayatSewa = new ArrayList<>();

// Menambahkan object ke dalam ArrayList
daftarKonsol.add(new PS4("PS4-TV1"));
daftarKonsol.add(new PS5("PS5-TV1"));

// Mengambil dan mengiterasi isi ArrayList
for (Konsol k : daftarKonsol) {
    System.out.println(k);
}
```

### 14. Error Handling
Error Handling digunakan untuk menangani kesalahan yang mungkin terjadi saat program berjalan. Pada kode ini terdapat custom exception `StokKosongException` dan penanganan `NumberFormatException`.

```java
// Custom exception
public class StokKosongException extends Exception {
    public StokKosongException(String pesan) {
        super(pesan);
    }
}

// Melempar dan menangkap custom exception
try {
    Konsol konsol = cariKonsol(kode);
    if (!konsol.isTersedia()) {
        throw new StokKosongException("Konsol " + kode + " sedang disewa orang lain.");
    }
} catch (StokKosongException e) {
    System.out.println(">> Gagal menyewa: " + e.getMessage());
}

// Menangkap NumberFormatException saat input angka tidak valid
try {
    angka = Integer.parseInt(scanner.nextLine().trim());
} catch (NumberFormatException e) {
    System.out.println(">> Input harus berupa angka, coba lagi.");
}
```

## Usulan Nilai

| No  | Materi         |  Nilai  |
| :-: | -------------- | :-----: |
|  1  | Class          |    5    |
|  2  | Object         |    5    |
|  3  | Atribut        |    5    |
|  4  | Constructor    |    5    |
|  5  | Mutator        |    5    |
|  6  | Accessor       |    5    |
|  7  | Encapsulation  |    5    |
|  8  | Inheritance    |    5    |
|  9  | Polymorphism   |   10    |
| 10  | Seleksi        |    5    |
| 11  | Perulangan     |    5    |
| 12  | IO Sederhana   |   10    |
| 13  | Array          |   15    |
| 14  | Error Handling |   15    |
|     | **TOTAL**      | **100** |

## Pembuat

Nama: Muhammad Riyadhi 
NPM: 2410010492