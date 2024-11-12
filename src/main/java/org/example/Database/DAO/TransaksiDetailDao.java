package org.example.Database.DAO;

import org.example.Database.Koneksi;
import org.example.Model.TransaksiDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransaksiDetailDao {
    public void insertTransaksiDetail(TransaksiDetail transaksiDetail){
        String query = "INSERT INTO TransaksiDetail (TransaksiID, ProdukID, Jumlah, Harga) VALUES (?, ?, ?, ?)";
        try(Connection connection = Koneksi.getConnection();
            PreparedStatement stmt = connection.prepareStatement(query)){

            stmt.setInt(1, transaksiDetail.getTransaksiID());
            stmt.setInt(2, transaksiDetail.getProdukID());
            stmt.setInt(3, transaksiDetail.getJumlah());
            stmt.setDouble(4, transaksiDetail.getHarga());

            stmt.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
