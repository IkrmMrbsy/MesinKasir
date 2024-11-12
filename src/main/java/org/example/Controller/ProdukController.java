package org.example.Controller;

import org.example.Database.DAO.ProdukDao;
import org.example.Database.Koneksi;
import org.example.Model.Produk;
import org.example.Model.Transaksi;
import org.example.Model.TransaksiDetail;
import org.example.Service.TransaksiService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class ProdukController {
    private ProdukDao produkDao = new ProdukDao();
    private TransaksiService transaksiService = new TransaksiService();
    Scanner scanner = new Scanner(System.in);

    public void addProduk(){
        System.out.print("Nama Produk :");
        String nama = scanner.nextLine();
        System.out.print("Harga Produk :");
        double harga = scanner.nextDouble();
        System.out.print("Stok Produk :");
        int stok = scanner.nextInt();

        scanner.nextLine();

        Produk produk = new Produk();
        produk.setNama(nama);
        produk.setHarga(harga);
        produk.setStok(stok);

        produkDao.addProduk(produk);
        System.out.println("Produk Berhasil Ditambahkan");
    }

    public void editProduk(){
        System.out.print("ID Produk yang akan di edit :");
        int id = scanner.nextInt();

        scanner.nextLine();

        System.out.print("Nama produk baru :");
        String nama = scanner.nextLine();
        System.out.print("Harga produk baru :");
        double harga = scanner.nextDouble();
        System.out.print("Stok produk baru :");
        int stok = scanner.nextInt();

        Produk produk = new Produk();
        produk.setId(id);
        produk.setNama(nama);
        produk.setHarga(harga);
        produk.setStok(stok);

        produkDao.editProduk(produk);
        System.out.println("Produk berhasil di perbarui");
    }

    public void deleteProduk(){
        System.out.print("ID Produk yang akan di hapus :");
        int id = scanner.nextInt();

        scanner.nextInt();

        produkDao.deleteProduk(id);
        System.out.println("Produk berhasil di hapus");
    }

    public void viewAllProduk(){
        System.out.println("\n=== Daftar Produk ===");
        for (Produk produk : produkDao.getAllProduk()){
            System.out.println("ID: " + produk.getId() + ", Nama: " + produk.getNama() + ", Harga: " + produk.getHarga() + ", Stok: " + produk.getStok());
        }
    }

    public void prosesTransaksi() {
        Scanner scanner = new Scanner(System.in);
        TransaksiService transaksiService = new TransaksiService();

        System.out.println("\n=== Proses Transaksi ===");

        // Menampilkan daftar produk untuk dipilih
        System.out.println("Daftar Produk:");
        viewAllProduk();

        // Memilih produk berdasarkan ID
        System.out.print("Masukkan ID produk yang ingin dibeli: ");
        int idProduk = scanner.nextInt();

        // Mendapatkan produk berdasarkan ID
        Produk produkDipilih = produkDao.getProdukById(idProduk);
        if (produkDipilih == null) {
            System.out.println("Produk tidak ditemukan.");
            return;
        }

        // Memasukkan jumlah yang dibeli
        System.out.print("Masukkan jumlah yang ingin dibeli: ");
        int jumlahBeli = scanner.nextInt();

        // Mengecek stok produk
        if (jumlahBeli > produkDipilih.getStok()) {
            System.out.println("Stok tidak mencukupi.");
            return;
        }

        // Menghitung total harga
        double totalHarga = produkDipilih.getHarga() * jumlahBeli;
        System.out.printf("Total harga untuk %d %s: Rp%.2f\n", jumlahBeli, produkDipilih.getNama(), totalHarga);

        // Mengonfirmasi transaksi
        System.out.print("Konfirmasi transaksi? (y/n): ");
        char konfirmasi = scanner.next().charAt(0);
        if (konfirmasi == 'y' || konfirmasi == 'Y') {
            // Membuat Transaksi dan TransaksiDetail
            Transaksi transaksi = new Transaksi();
            transaksi.setId(1); // ID user bisa disesuaikan
            transaksi.setTotal(totalHarga);
            transaksi.setTanggal(new java.sql.Timestamp(System.currentTimeMillis()));

            TransaksiDetail transaksiDetail = new TransaksiDetail();
            transaksiDetail.setProdukID(produkDipilih.getId());
            transaksiDetail.setJumlah(jumlahBeli);
            transaksiDetail.setHarga(produkDipilih.getHarga());

            // Menyimpan transaksi ke database
            int transaksiID = transaksiService.prosesTransaksi(transaksi, transaksiDetail);

            if (transaksiID != -1) {
                // Mengurangi stok produk
                int stokBaru = produkDipilih.getStok() - jumlahBeli;
                produkDao.updateStokProduk(produkDipilih.getId(), stokBaru);

                System.out.println("Transaksi berhasil!");
            } else {
                System.out.println("Gagal menyimpan transaksi.");
            }
        } else {
            System.out.println("Transaksi dibatalkan.");
        }
    }

}
