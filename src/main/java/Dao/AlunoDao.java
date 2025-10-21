package Dao;

import Model.Aluno;
import Util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlunoDao {

    public int inserirAluno(Aluno aluno) {
        String sql = "INSERT INTO Aluno (Nome, CPF, Email, DataNascimento) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getCpf());
            stmt.setString(3, aluno.getEmail());
            stmt.setString(4, aluno.getDataNascimento());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) return rs.getInt(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public boolean cpfExiste(String cpf) {
        String sql = "SELECT COUNT(*) FROM Aluno WHERE CPF = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return rs.getInt(1) > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public int getRaByCpf(String cpf) {
        String sql = "SELECT RA FROM Aluno WHERE CPF = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return rs.getInt("RA");
        } catch (SQLException e) { e.printStackTrace(); }
        return -1;
    }

    public List<Aluno> listarAlunos() {
        List<Aluno> alunos = new ArrayList<>();
        String sql = "SELECT * FROM Aluno";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Aluno a = new Aluno();
                a.setRa(rs.getInt("RA"));
                a.setNome(rs.getString("Nome"));
                a.setCpf(rs.getString("CPF"));
                a.setEmail(rs.getString("Email"));
                a.setDataNascimento(rs.getString("DataNascimento"));
                alunos.add(a);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return alunos;
    }

    public void atualizarAluno(int ra, String nome, String cpf, String email, String dataNascimento) {
        String sql = "UPDATE Aluno SET Nome = ?, CPF = ?, Email = ?, DataNascimento = ? WHERE RA = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nome);
            stmt.setString(2, cpf);
            stmt.setString(3, email);
            stmt.setString(4, dataNascimento);
            stmt.setInt(5, ra);
            stmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public void deletarAluno(int ra) {
        String sql = "DELETE FROM Aluno WHERE RA = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, ra);
            stmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    // Sincroniza lista de alunos com o banco
    public void popularBanco(List<Aluno> alunos) {
        for (Aluno a : alunos) {
            if (!cpfExiste(a.getCpf())) {
                inserirAluno(a);
            }
        }
    }

    // Deleta todos os alunos
    public void deletarTodos() {
        String sql = "DELETE FROM Aluno";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }
}
