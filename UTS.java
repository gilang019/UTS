/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.uts;

import java.util.*;
/**
 *
 * @author GILANG_ALI
 */
public class UTS {

    // Kelas Mahasiswa
    static class Mahasiswa {
        String nim;
        String nama;
        String kelas;
        String alamat;

        Mahasiswa(String nim, String nama, String kelas, String alamat) {
            this.nim = nim;
            this.nama = nama;
            this.kelas = kelas;
            this.alamat = alamat;
        }
    }

    // Data Mahasiswa
    static List<Mahasiswa> mahasiswaList = Arrays.asList(
            new Mahasiswa("220301001", "Andi", "IF-01", "Bandung"),
            new Mahasiswa("220301002", "Budi", "IF-01", "Jakarta"),
            new Mahasiswa("220301003", "Citra", "IF-01", "Surabaya")
    );

    // Data Nilai Mahasiswa (NIM → Mata Kuliah → Nilai Huruf)
    static Map<String, Map<String, String>> nilaiMahasiswa = new HashMap<>();

    public static void main(String[] args) {

        // Inisialisasi nilai mahasiswa
        nilaiMahasiswa.put("220301001", new HashMap<>() {{
            put("Algoritma 1", "AB");
            put("DDTI", "A");
        }});
        nilaiMahasiswa.put("220301002", new HashMap<>() {{
            put("Algoritma 1", "B");
            put("DDTI", "BC");
        }});
        nilaiMahasiswa.put("220301003", new HashMap<>() {{
            put("Algoritma 1", "C");
            put("DDTI", "E");
        }});

        // Tampilkan data mahasiswa
        System.out.println("=== DATA MAHASISWA ===");
        for (Mahasiswa m : mahasiswaList) {
            System.out.println(m.nim + " - " + m.nama + " - " + m.kelas + " - " + m.alamat);
        }

        // Tampilkan nilai mahasiswa
        System.out.println("\n=== NILAI MAHASISWA ===");
        for (String nim : nilaiMahasiswa.keySet()) {
            System.out.println(nim + ": " + nilaiMahasiswa.get(nim));
        }

        // Tampilkan mahasiswa dengan digit akhir ganjil
        cariNilaiBerdasarkanDigit(true);  // Ganjil

        // Tampilkan mahasiswa dengan digit akhir genap
        cariNilaiBerdasarkanDigit(false); // Genap

        // Hitung faktorial dari jumlah total nilai
        int totalNilai = mahasiswaList.size() * 2; // 2 mapel per mahasiswa
        System.out.println("\nFaktorial dari total jumlah nilai: " + faktorial(totalNilai));

        // Cetak nilai rekursif
        System.out.println("\n=== Cetak Matriks Secara Rekursif ===");
        cetakMatriksNilaiRekursif(new ArrayList<>(nilaiMahasiswa.keySet()), 0);
    }

    // Fungsi cetak nilai berdasarkan digit terakhir NIM
    static void cariNilaiBerdasarkanDigit(boolean ganjil) {
        String[] nilaiDicari = ganjil ? new String[]{"A", "C", "E"} : new String[]{"AB", "B", "BC", "CD"};
        String status = ganjil ? "GANJIL" : "GENAP";

        System.out.println("\n=== Mahasiswa dengan NIM terakhir " + status + " ===");

        for (Mahasiswa m : mahasiswaList) {
            char lastDigit = m.nim.charAt(m.nim.length() - 1);
            int digit = Character.getNumericValue(lastDigit);
            boolean cocok = (digit % 2 == 1) == ganjil;

            if (cocok) {
                System.out.println("\nNama: " + m.nama + " (NIM: " + m.nim + ")");
                Map<String, String> nilaiMapel = nilaiMahasiswa.get(m.nim);
                for (String mapel : nilaiMapel.keySet()) {
                    String nilai = nilaiMapel.get(mapel);
                    for (String n : nilaiDicari) {
                        if (nilai.equals(n)) {
                            System.out.println("  " + mapel + ": " + nilai);
                        }
                    }
                }
            }
        }
    }

    // Fungsi faktorial rekursif
    static int faktorial(int n) {
        if (n <= 1) return 1;
        return n * faktorial(n - 1);
    }

    // Fungsi rekursif cetak nilai
    static void cetakMatriksNilaiRekursif(List<String> nimList, int index) {
        if (index >= nimList.size()) return;
        String nim = nimList.get(index);
        System.out.println("Nilai " + nim + ": " + nilaiMahasiswa.get(nim));
        cetakMatriksNilaiRekursif(nimList, index + 1);
    }
}
