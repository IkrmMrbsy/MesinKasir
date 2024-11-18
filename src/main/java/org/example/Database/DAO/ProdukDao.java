package org.example.Database.DAO;

import org.example.Database.Koneksi;
import org.example.Model.Produk;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdukDao {

    public List<Produk> getAllProduk(){
        List<Produk> produkList = new ArrayList<>();
        String query = "SELECT * FROM produk";
        try(Connection connection = Koneksi.getConnection();
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery()){

            while (rs.next()){
                Produk produk = new Produk();
                produk.setId(rs.getInt("ProdukID"));
                produk.setNama(rs.getString("Nama"));
                produk.setHarga(rs.getDouble("Harga"));
                produk.setStok(rs.getInt("Stok"));
                produkList.add(produk);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return produkList;
    }

    public void addProduk(Produk produk){
        String query = "INSERT INTO produk (Nama, Harga, Stok) VALUES (?, ?, ?)";
        try(Connection connection = Koneksi.getConnection();
        PreparedStatement stmt = connection.prepareStatement(query)){

            stmt.setString(1, produk.getNama());
            stmt.setDouble(2, produk.getHarga());
            stmt.setInt(3, produk.getStok());
            stmt.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void editProduk(Produk produk){
        String query = "UPDATE produk SET Nama = ?, Harga = ?, Stok = ? WHERE ProdukID = ?";
        try(Connection connection = Koneksi.getConnection();
        PreparedStatement stmt = connection.prepareStatement(query)){
            stmt.setString(1, produk.getNama());
            stmt.setDouble(2, produk.getHarga());
            stmt.setInt(3, produk.getStok());
            stmt.setInt(4, produk.getId());
            stmt.executeUpdate();

            System.out.println("Produk berhasil di perbarui");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void updateStokProduk(int id, int stokBaru) {
        String query = "UPDATE produk SET Stok = ? WHERE ProdukID = ?";
        try (Connection connection = Koneksi.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, stokBaru);
            stmt.setInt(2, id);
            stmt.executeUpdate();

            System.out.println("Stok produk berhasil diperbarui.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Produk getProdukById(int id) {
        String query = "SELECT * FROM produk WHERE ProdukID = ?";
        try (Connection connection = Koneksi.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Produk produk = new Produk();
                produk.setId(rs.getInt("ProdukID"));
                produk.setNama(rs.getString("Nama"));
                produk.setHarga(rs.getDouble("Harga"));
                produk.setStok(rs.getInt("Stok"));
                return produk;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteProduk(int id){
        String deleteTransaksiQuery = "DELETE FROM transaksiDetail WHERE ProdukID = ?";
        String deleteProdukquery = "DELETE FROM produk WHERE ProdukID = ?";

        try(Connection connection = Koneksi.getConnection()){
            try(PreparedStatement stmtTransaksi = connection.prepareStatement(deleteTransaksiQuery)){
                stmtTransaksi.setInt(1, id);
                stmtTransaksi.executeUpdate();
            }

            try(PreparedStatement stmtProduk= connection.prepareStatement(deleteProdukquery)){
                stmtProduk.setInt(1, id);
                stmtProduk.executeUpdate();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public boolean canDeleteProduk(int id){
        String query = "SELECT COUNT(*) FROM transaksidetail WHERE ProdukID = ?";

        try(Connection connection = Koneksi.getConnection();
        PreparedStatement stmt = connection.prepareStatement(query)){
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if(rs.next() && rs.getInt(1) > 0){
                return false;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }
}
