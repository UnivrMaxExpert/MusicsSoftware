package com.dashapp.model;

import com.dashapp.util.DatabaseManager;
import com.dashapp.util.PasswordHasher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccessoDao
{
    public AccessoDao() throws SQLException
    {}

    public boolean loginControllo(UtenteBean utente) {
        String sql = "SELECT password FROM utenti WHERE username = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, utente.getUsername());

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String hashedPassword = rs.getString("password");
                // Verifica la password usando PasswordHasher
                return PasswordHasher.checkPassword(utente.getPassword(), hashedPassword);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }


    public boolean registrazioneControllo(UtenteBean utente) {
        String sql = "INSERT INTO utenti (username, password) VALUES (?, ?)";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            String hashedPassword = PasswordHasher.hashPassword(utente.getPassword());

            stmt.setString(1, utente.getUsername());
            stmt.setString(2, hashedPassword); // Salva l'hash, non la password in chiaro

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
