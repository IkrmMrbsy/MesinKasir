package org.example.Database.DAO;

import org.example.Database.Koneksi;
import org.example.Model.Transaksi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransaksiDao {
    public int insertTransaksi(Transaksi transaksi){
        String query = "INSERT INTO Transaksi (TransaksiID, Total, Tanggal) VALUES (?, ?, ?)";
        try(Connection connection = Koneksi.getConnection();
            PreparedStatement stmt = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)){

            stmt.setInt(1, transaksi.getId());
            stmt.setDouble(2, transaksi.getTotal());
            stmt.setTimestamp(3, transaksi.getTanggal());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0){
                try(var rs = stmt.getGeneratedKeys()){
                    if (rs.next()){
                        return rs.getInt(1);
                    }
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return -1;
    }
}
