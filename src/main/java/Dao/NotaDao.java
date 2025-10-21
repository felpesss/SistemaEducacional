package Dao;

import Model.Nota;
import Util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NotaDao {

    public void inserirNota(Nota nota) {
        String sql = "INSERT INTO Nota (RA, IDCurso, Valor, Data) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, nota.getRa());
            stmt.setInt(2, nota.getIdCurso());
            stmt.setDouble(3, nota.getValor());
            stmt.setString(4, nota.getData());
            stmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public boolean existeNota(int ra, int idCurso) {
        String sql = "SELECT COUNT(*) FROM Nota WHERE RA = ? AND IDCurso = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, ra);
            stmt.setInt(2, idCurso);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return rs.getInt(1) > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public List<Nota> listarNotas() {
        List<Nota> notas = new ArrayList<>();
        String sql = "SELECT * FROM Nota";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Nota n = new Nota();
                n.setRa(rs.getInt("RA"));
                n.setIdCurso(rs.getInt("IDCurso"));
                n.setValor(rs.getDouble("Valor"));
                n.setData(rs.getString("Data"));
                notas.add(n);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return notas;
    }

    public void atualizarNota(int ra, int idCurso, double valor) {
        String sql = "UPDATE Nota SET Valor = ? WHERE RA = ? AND IDCurso = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, valor);
            stmt.setInt(2, ra);
            stmt.setInt(3, idCurso);
            stmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public void deletarNota(int ra, int idCurso) {
        String sql = "DELETE FROM Nota WHERE RA = ? AND IDCurso = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, ra);
            stmt.setInt(2, idCurso);
            stmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public void deletarNotasPorRA(int ra) {
        String sql = "DELETE FROM Nota WHERE RA = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, ra);
            stmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public void deletarNotasPorCurso(int idCurso) {
        String sql = "DELETE FROM Nota WHERE IDCurso = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idCurso);
            stmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    // Sincroniza lista de notas com o banco
    public void popularBanco(List<Nota> notas) {
        for (Nota n : notas) {
            if (!existeNota(n.getRa(), n.getIdCurso())) {
                inserirNota(n);
            }
        }
    }

    // Deleta todas as notas
    public void deletarTodos() {
        String sql = "DELETE FROM Nota";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }
}
