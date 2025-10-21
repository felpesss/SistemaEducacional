package Model;

public class Aluno {
    private int ra;
    private String nome;
    private String email;
    private String telefone;
    private int idCurso;

    // Construtor completo
    public Aluno(int ra, String nome, String email, String telefone, int idCurso) {
        this.ra = ra;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.idCurso = idCurso;
    }

    // Construtor sem RA (para inserção)
    public Aluno(String nome, String email, String telefone, int idCurso) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.idCurso = idCurso;
    }

    // Construtor vazio
    public Aluno() {}

    // Getters e Setters
    public int getRa() { return ra; }
    public void setRa(int ra) { this.ra = ra; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public int getIdCurso() { return idCurso; }
    public void setIdCurso(int idCurso) { this.idCurso = idCurso; }
}
