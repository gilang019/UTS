/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.uts;

/**
 *
 * @author GILANG_ALI
 */
import java.util.*;

public class UTS1 {

    record Mahasiswa(String nim, String nama, String kelas, String alamat) {}

    public static double konversiNilai(String huruf) {
        return switch (huruf) {
            case "A" -> 4.0;
            case "AB" -> 3.5;
            case "B" -> 3.0;
            case "BC" -> 2.5;
            case "C" -> 2.0;
            case "CD" -> 1.5;
            case "D" -> 1.0;
            case "E" -> 0.0;
            default -> -1;
        };
    }

    public static long faktorial(int n) {
        if (n <= 1) return 1;
        return n * faktorial(n - 1);
    }

    public static void cetakNilaiRekursif(List<String> nimList, Map<String, Map<String, String>> nilaiMap, int index) {
        if (index >= nimList.size()) return;
        String nim = nimList.get(index);
        Map<String, String> nilai = nilaiMap.get(nim);
        for (Map.Entry<String, String> entry : nilai.entrySet()) {
            System.out.println("Nilai " + nim + ": " + entry.getKey() + "=" + entry.getValue());
        }
        cetakNilaiRekursif(nimList, nilaiMap, index + 1);
    }

    public static void cariNilaiBerdasarkanDigit(List<Mahasiswa> mhsList, Map<String, Map<String, String>> nilaiMap, boolean ganjil) {
        System.out.println("\n=== Mahasiswa dengan NIM terakhir " + (ganjil ? "GANJIL" : "GENAP") + " ===");

        for (Mahasiswa m : mhsList) {
            char lastDigit = m.nim.charAt(m.nim.length() - 1);
            int digit = Character.getNumericValue(lastDigit);

            if ((digit % 2 == 1) == ganjil) {
                System.out.println("\nNama: " + m.nama + " (NIM: " + m.nim + ")");
                Map<String, String> nilai = nilaiMap.get(m.nim);
                for (Map.Entry<String, String> entry : nilai.entrySet()) {
                    String huruf = entry.getValue();
                    double bobot = konversiNilai(huruf);
                    boolean tampil = ganjil ? (huruf.equals("A") || huruf.equals("C") || huruf.equals("E")) :
                                              (huruf.equals("AB") || huruf.equals("B") || huruf.equals("BC") || huruf.equals("CD"));
                    if (tampil) {
                        System.out.println("  " + entry.getKey() + ": " + huruf + " (Bobot: " + bobot + ")");
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        // Data Mahasiswa (Update nama)
        List<Mahasiswa> mahasiswaList = new ArrayList<>();
        mahasiswaList.add(new Mahasiswa("220301001", "Elis Syafitri", "IF-01", "Bandung"));
        mahasiswaList.add(new Mahasiswa("220301002", "Gilang", "IF-01", "Jakarta"));
        mahasiswaList.add(new Mahasiswa("220301003", "Ali", "IF-01", "Surabaya"));

        // Matriks nilai
        Map<String, Map<String, String>> nilaiMahasiswa = new HashMap<>();

        Map<String, String> nilai1 = new HashMap<>();
        nilai1.put("DDTI", "BC");
        nilai1.put("Algoritma 1", "B");
        nilaiMahasiswa.put("220301002", nilai1);

        Map<String, String> nilai2 = new HashMap<>();
        nilai2.put("DDTI", "E");
        nilai2.put("Algoritma 1", "C");
        nilaiMahasiswa.put("220301003", nilai2);

        Map<String, String> nilai3 = new HashMap<>();
        nilai3.put("DDTI", "A");
        nilai3.put("Algoritma 1", "AB");
        nilaiMahasiswa.put("220301001", nilai3);

        // Tampilkan data
        System.out.println("=== DATA MAHASISWA ===");
        for (Mahasiswa m : mahasiswaList) {
            System.out.println(m.nim + " - " + m.nama + " - " + m.kelas + " - " + m.alamat);
        }

        // Tampilkan nilai
        System.out.println("\n=== NILAI MAHASISWA ===");
        for (Map.Entry<String, Map<String, String>> entry : nilaiMahasiswa.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        // Pencarian berdasarkan digit akhir NIM
        cariNilaiBerdasarkanDigit(mahasiswaList, nilaiMahasiswa, true);  // Ganjil
        cariNilaiBerdasarkanDigit(mahasiswaList, nilaiMahasiswa, false); // Genap

        // Total nilai dan faktorial
        int totalNilai = 0;
        for (Map<String, String> nilai : nilaiMahasiswa.values()) {
            for (String huruf : nilai.values()) {
                totalNilai += konversiNilai(huruf);
            }
        }
        System.out.println("\nFaktorial dari total jumlah nilai: " + faktorial(totalNilai));

        // Cetak rekursif
        System.out.println("\n=== Cetak Matriks Secara Rekursif ===");
        List<String> nimList = new ArrayList<>(nilaiMahasiswa.keySet());
        cetakNilaiRekursif(nimList, nilaiMahasiswa, 0);
    }
}

