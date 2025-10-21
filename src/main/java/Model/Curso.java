package Model;

public class Curso {
    private int idCurso;
    private String nome;
    private int cargaHoraria;
    private String modalidade;

    // Construtor completo
    public Curso(int idCurso, String nome, int cargaHoraria, String modalidade) {
        this.idCurso = idCurso;
        this.nome = nome;
        this.cargaHoraria = cargaHoraria;
        this.modalidade = modalidade;
    }

    // Construtor sem ID (para inserção)
    public Curso(String nome, int cargaHoraria, String modalidade) {
        this.nome = nome;
        this.cargaHoraria = cargaHoraria;
        this.modalidade = modalidade;
    }

    // Construtor vazio
    public Curso() {}

    // Getters e Setters
    public int getIdCurso() { return idCurso; }
    public void setIdCurso(int idCurso) { this.idCurso = idCurso; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public int getCargaHoraria() { return cargaHoraria; }
    public void setCargaHoraria(int cargaHoraria) { this.cargaHoraria = cargaHoraria; }

    public String getModalidade() { return modalidade; }
    public void setModalidade(String modalidade) { this.modalidade = modalidade; }
}
