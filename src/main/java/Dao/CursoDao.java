package Dao;

import Model.Curso;
import Util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CursoDao {

    public int inserirCurso(Curso curso) {
        String sql = "INSERT INTO Curso (Nome, CargaHoraria, Modalidade) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, curso.getNome());
            stmt.setInt(2, curso.getCargaHoraria());
            stmt.setString(3, curso.getModalidade());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) return rs.getInt(1);

        } catch (SQLException e) { e.printStackTrace(); }
        return -1;
    }

    public boolean nomeExiste(String nome) {
        String sql = "SELECT COUNT(*) FROM Curso WHERE Nome = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return rs.getInt(1) > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public int getIdByNome(String nome) {
        String sql = "SELECT IDCurso FROM Curso WHERE Nome = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return rs.getInt("IDCurso");
        } catch (SQLException e) { e.printStackTrace(); }
        return -1;
    }

    public List<Curso> listarCursos() {
        List<Curso> cursos = new ArrayList<>();
        String sql = "SELECT * FROM Curso";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Curso c = new Curso();
                c.setIdCurso(rs.getInt("IDCurso"));
                c.setNome(rs.getString("Nome"));
                c.setCargaHoraria(rs.getInt("CargaHoraria"));
                c.setModalidade(rs.getString("Modalidade"));
                cursos.add(c);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return cursos;
    }

    public void atualizarCurso(int idCurso, String novoNome, int novaCarga, String novaModalidade) {
        String sql = "UPDATE Curso SET Nome = ?, CargaHoraria = ?, Modalidade = ? WHERE IDCurso = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, novoNome);
            stmt.setInt(2, novaCarga);
            stmt.setString(3, novaModalidade);
            stmt.setInt(4, idCurso);
            stmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public void deletarCurso(int idCurso) {
        String sql = "DELETE FROM Curso WHERE IDCurso = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idCurso);
            stmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    // Sincroniza lista de cursos com o banco
    public void popularBanco(List<Curso> cursos) {
        for (Curso c : cursos) {
            if (!nomeExiste(c.getNome())) {
                inserirCurso(c);
            }
        }
    }

    // Deleta todos os cursos
    public void deletarTodos() {
        String sql = "DELETE FROM Curso";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }
}
