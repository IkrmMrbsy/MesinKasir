package org.example.Database.DAO;

import org.example.Database.Koneksi;
import org.example.Model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    public User login(String username, String password){
        String query = "SELECT * FROM pengguna WHERE Username = ? AND PassHash = ?";
        try(Connection connection = Koneksi.getConnection();
            PreparedStatement stmt = connection.prepareStatement(query)){
            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                User user = new User();
                user.setId(rs.getInt("UserID"));
                user.setUsername(rs.getString("Username"));
                user.setRole(rs.getString("Role"));
                return  user;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public List<User> getAllUsers(){
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM pengguna";
        try(Connection connection = Koneksi.getConnection();
        PreparedStatement stmt = connection.prepareStatement(query);
        ResultSet rs = stmt.executeQuery()){
            while (rs.next()){
                User user = new User();
                user.setId(rs.getInt("UserID"));
                user.setUsername(rs.getString("Username"));
                user.setRole(rs.getString("Role"));
                users.add(user);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return users;
    }
}

