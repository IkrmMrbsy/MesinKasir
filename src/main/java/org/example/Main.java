package org.example;

import org.example.Controller.LoginController;

public class Main {
    public static void main(String[] args) {
        LoginController loginController = new LoginController();

        // Menampilkan form login
        loginController.showLogin();
    }
}