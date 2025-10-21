import Dao.AlunoDao;
import Dao.CursoDao;
import Dao.NotaDao;
import Model.Aluno;
import Model.Curso;
import Model.Nota;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AlunoDao alunoDao = new AlunoDao();
        CursoDao cursoDao = new CursoDao();
        NotaDao notaDao = new NotaDao();

        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n=== MENU ===");
            System.out.println("1 - Inserir aluno");
            System.out.println("2 - Listar alunos");
            System.out.println("3 - Atualizar aluno");
            System.out.println("4 - Deletar aluno");
            System.out.println("5 - Inserir curso");
            System.out.println("6 - Listar cursos");
            System.out.println("7 - Atualizar curso");
            System.out.println("8 - Deletar curso");
            System.out.println("9 - Inserir nota");
            System.out.println("10 - Listar notas");
            System.out.println("11 - Atualizar nota");
            System.out.println("12 - Deletar nota");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // limpar buffer

            switch (opcao) {
                case 1: // Inserir aluno
                    System.out.print("Nome: "); String nome = scanner.nextLine();
                    System.out.print("CPF: "); String cpf = scanner.nextLine();
                    System.out.print("Email: "); String email = scanner.nextLine();
                    System.out.print("Data Nascimento (YYYY-MM-DD): "); String dataStr = scanner.nextLine();
                    LocalDate data = LocalDate.parse(dataStr); // converte String -> LocalDate
                    Aluno aluno = new Aluno(null, nome, cpf, email, data); // RA null
                    int ra = alunoDao.cpfExiste(cpf) ? alunoDao.getRaByCpf(cpf) : alunoDao.inserirAluno(aluno);
                    System.out.println("Aluno inserido com RA: " + ra);
                    break;

                case 2: // Listar alunos
                    List<Aluno> alunos = alunoDao.listarAlunos();
                    for (Aluno a : alunos)
                        System.out.println(a.getRa() + " - " + a.getNome() + " - " + a.getCpf());
                    break;

                case 3: // Atualizar aluno
                    System.out.print("RA: "); int raUp = scanner.nextInt(); scanner.nextLine();
                    System.out.print("Novo nome: "); String nomeUp = scanner.nextLine();
                    System.out.print("Novo CPF: "); String cpfUp = scanner.nextLine();
                    System.out.print("Novo Email: "); String emailUp = scanner.nextLine();
                    System.out.print("Nova Data (YYYY-MM-DD): "); String dataUpStr = scanner.nextLine();
                    LocalDate dataUp = LocalDate.parse(dataUpStr);
                    alunoDao.atualizarAluno(raUp, nomeUp, cpfUp, emailUp, dataUp);
                    System.out.println("Aluno atualizado!");
                    break;

                case 4: // Deletar aluno
                    System.out.print("RA: "); int raDel = scanner.nextInt(); scanner.nextLine();
                    notaDao.deletarNotasPorRA(raDel);
                    alunoDao.deletarAluno(raDel);
                    System.out.println("Aluno deletado!");
                    break;

                case 5: // Inserir curso
                    System.out.print("Nome: "); String nomeCurso = scanner.nextLine();
                    System.out.print("Carga: "); int carga = scanner.nextInt(); scanner.nextLine();
                    System.out.print("Modalidade: "); String modalidade = scanner.nextLine();
                    Curso curso = new Curso(nomeCurso, carga, modalidade);
                    int idCurso = cursoDao.nomeExiste(nomeCurso) ? cursoDao.getIdByNome(nomeCurso) : cursoDao.inserirCurso(curso);
                    System.out.println("Curso inserido com ID: " + idCurso);
                    break;

                case 6: // Listar cursos
                    List<Curso> cursos = cursoDao.listarCursos();
                    for (Curso c : cursos)
                        System.out.println(c.getIdCurso() + " - " + c.getNome() + " - " + c.getCargaHoraria());
                    break;

                case 7: // Atualizar curso
                    System.out.print("ID: "); int idUp = scanner.nextInt(); scanner.nextLine();
                    System.out.print("Novo nome: "); String nCurso = scanner.nextLine();
                    System.out.print("Nova carga: "); int cCurso = scanner.nextInt(); scanner.nextLine();
                    System.out.print("Nova modalidade: "); String mCurso = scanner.nextLine();
                    cursoDao.atualizarCurso(idUp, nCurso, cCurso, mCurso);
                    System.out.println("Curso atualizado!");
                    break;

                case 8: // Deletar curso
                    System.out.print("ID: "); int idDel = scanner.nextInt(); scanner.nextLine();
                    notaDao.deletarNotasPorCurso(idDel);
                    cursoDao.deletarCurso(idDel);
                    System.out.println("Curso deletado!");
                    break;

                case 9: // Inserir nota
                    System.out.print("RA: "); int raN = scanner.nextInt();
                    System.out.print("ID Curso: "); int idN = scanner.nextInt();
                    System.out.print("Nota: "); double val = scanner.nextDouble(); scanner.nextLine();
                    System.out.print("Data (YYYY-MM-DD): "); String dNStr = scanner.nextLine();
                    LocalDate dN = LocalDate.parse(dNStr);
                    Nota nota = new Nota(raN, idN, val, dN); // Nota agora espera LocalDate
                    if (!notaDao.existeNota(raN, idN)) notaDao.inserirNota(nota);
                    else System.out.println("Nota já existe!");
                    break;

                case 10: // Listar notas
                    List<Nota> notas = notaDao.listarNotas();
                    for (Nota n : notas)
                        System.out.println("RA: " + n.getRa() + " | Curso: " + n.getIdCurso() + " | Nota: " + n.getValor());
                    break;

                case 11: // Atualizar nota
                    System.out.print("RA: "); int raUpN = scanner.nextInt();
                    System.out.print("ID Curso: "); int idUpN = scanner.nextInt();
                    System.out.print("Nova nota: "); double vUp = scanner.nextDouble(); scanner.nextLine();
                    notaDao.atualizarNota(raUpN, idUpN, vUp);
                    System.out.println("Nota atualizada!");
                    break;

                case 12: // Deletar nota
                    System.out.print("RA: "); int raDelN = scanner.nextInt();
                    System.out.print("ID Curso: "); int idDelN = scanner.nextInt(); scanner.nextLine();
                    notaDao.deletarNota(raDelN, idDelN);
                    System.out.println("Nota deletada!");
                    break;

                case 0:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }
        }

        scanner.close();
    }
}
