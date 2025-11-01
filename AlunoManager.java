import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AlunoManager {

    private static final String URL = "jdbc:mysql://localhost:3306/escola";
    private static final String USUARIO = "root";
    private static final String SENHA = "";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Erro fatal: Driver JDBC do MySQL não encontrado.");
        }
    }

    public static void inserirAluno(String nome, String curso, int idade) {
        String sql = "INSERT INTO alunos (nome, curso, idade) VALUES (?, ?, ?)";

        try (Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, nome);
            stmt.setString(2, curso);
            stmt.setInt(3, idade);

            stmt.executeUpdate();
            System.out.println("Aluno inserido com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao inserir aluno: " + e.getMessage());
        }
    }

    public static void listarAlunos() {
        String sql = "SELECT id, nome, curso, idade FROM alunos";

        try (Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet resultado = stmt.executeQuery()) {

            System.out.println("\n--- Lista de Alunos ---");
            boolean encontrou = false;

            while (resultado.next()) {
                encontrou = true;
                int id = resultado.getInt("id");
                String nome = resultado.getString("nome");
                String curso = resultado.getString("curso");
                int idade = resultado.getInt("idade");

                Aluno aluno = new Aluno(id, nome, curso, idade);
                System.out.println(aluno.toString());
            }

            if (!encontrou) {
                System.out.println("Nenhum aluno cadastrado.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar alunos: " + e.getMessage());
        }
    }

    public static void atualizarAluno(int id, String novoNome, String novoCurso, int novaIdade) {
        String sql = "UPDATE alunos SET nome = ?, curso = ?, idade = ? WHERE id = ?";

        try (Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, novoNome);
            stmt.setString(2, novoCurso);
            stmt.setInt(3, novaIdade);
            stmt.setInt(4, id);

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Aluno atualizado com sucesso!");
            } else {
                System.out.println("Aluno com o ID " + id + " não encontrado.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar aluno: " + e.getMessage());
        }
    }

    public static void excluirAluno(int id) {
        String sql = "DELETE FROM alunos WHERE id = ?";

        try (Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Aluno excluído com sucesso!");
            } else {
                System.out.println("Aluno com o ID " + id + " não encontrado.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao excluir aluno: " + e.getMessage());
        }
    }
}