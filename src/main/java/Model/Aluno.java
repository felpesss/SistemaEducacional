package Model;

public class Aluno {
    private int ra;
    private String nome;
    private String cpf;
    private String email;
    private String dataNascimento;

    // Construtor completo
    public Aluno(int ra, String nome, String cpf, String email, String dataNascimento) {
        this.ra = ra;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.dataNascimento = dataNascimento;
    }

    // Construtor sem RA (para inserção)
    public Aluno(String nome, String cpf, String email, String dataNascimento) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.dataNascimento = dataNascimento;
    }

    // Construtor vazio
    public Aluno() {}

    // Getters e Setters
    public int getRa() { return ra; }
    public void setRa(int ra) { this.ra = ra; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(String dataNascimento) { this.dataNascimento = dataNascimento; }
}
