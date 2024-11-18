package org.example.Controller;

import org.example.Database.DAO.ProdukDao;
import org.example.Model.User;
import org.example.Service.UserService;

import java.util.Scanner;

public class LoginController {
    private UserService userService = new UserService();
    private ProdukController pController = new ProdukController();

    public void showLogin(){
        Scanner scanner = new Scanner(System.in);

        System.out.print("Username :");
        String username = scanner.nextLine();

        System.out.print("Password :");
        String password = scanner.nextLine();

        User user = userService.login(username, password);
        if (user!= null){
            System.out.println("Login Sukses");
            if (user.getRole().equals("Admin")){
                showAdminMenu();
            }else {
                showKasirMenu();
            }
        }else {
            System.out.println("Gagal Login");
        }
    }

    public void showAdminMenu(){
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit){
            System.out.println("\n=== Admin Menu ===");
            System.out.println("1. Tambah Produk");
            System.out.println("2. Edit Produk");
            System.out.println("3. Hapus Produk");
            System.out.println("4. Lihat Semua Produk");
            System.out.println("5. Logout");
            System.out.print("Pilih opsi: ");

            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    // Tambah produk
                    pController.addProduk();
                    break;
                case 2:
                    // Edit produk
                    pController.editProduk();
                    break;
                case 3:
                    // Hapus produk
                    pController.deleteProduk();
                    break;
                case 4:
                    // Lihat semua produk
                    pController.viewAllProduk();
                    break;
                case 5:
                    System.out.println("Logging out...");
                    exit = true;
                    showLogin();
                    break;
                default:
                    System.out.println("Opsi tidak valid.");
            }
        }
    }

    private void showKasirMenu(){
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit){
            System.out.println("\n=== Kasir Menu ===");
            System.out.println("1. Proses Transaksi");
            System.out.println("2. Lihat Semua Produk");
            System.out.println("3. Logout");
            System.out.print("Pilih opsi: ");

            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    pController.prosesTransaksi();
                    break;
                case 2:
                    pController.viewAllProduk();
                    break;
                case 3:
                    System.out.println("Logging out...");
                    exit = true;
                    break;
                default:
                    System.out.println("Opsi tidak valid.");
            }
        }
    }

//    private void managUser(){
//        Scanner scanner = new Scanner(System.in);
//        boolean exit = false;
//
//        while (!exit){
//            System.out.println("\n=== Kelola Pengguna ===");
//            System.out.println("1. Tambah Pengguna");
//            System.out.println("2. Edit Pengguna");
//            System.out.println("3. Hapus Pengguna");
//            System.out.println("4. Kembali ke Admin Menu");
//            System.out.print("Pilih opsi: ");
//
//            int option = scanner.nextInt();
//
//            switch (option) {
//                case 1:
//                    userService.addUser();
//                    break;
//                case 2:
//                    userService.editUser();
//                    break;
//                case 3:
//                    userService.deleteUser();
//                    break;
//                case 4:
//                    exit = true;
//                    break;
//                default:
//                    System.out.println("Opsi tidak valid.");
//            }
//        }
//    }
}
