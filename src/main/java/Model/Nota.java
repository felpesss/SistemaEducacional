package Model;

public class Nota {
    private int idNota;
    private int ra;
    private int idCurso;
    private double valor;
    private String data;

    // Construtor completo
    public Nota(int idNota, int ra, int idCurso, double valor, String data) {
        this.idNota = idNota;
        this.ra = ra;
        this.idCurso = idCurso;
        this.valor = valor;
        this.data = data;
    }

    // Construtor sem ID (para inserção)
    public Nota(int ra, int idCurso, double valor, String data) {
        this.ra = ra;
        this.idCurso = idCurso;
        this.valor = valor;
        this.data = data;
    }

    // Construtor vazio
    public Nota() {}

    // Getters e Setters
    public int getIdNota() { return idNota; }
    public void setIdNota(int idNota) { this.idNota = idNota; }

    public int getRa() { return ra; }
    public void setRa(int ra) { this.ra = ra; }

    public int getIdCurso() { return idCurso; }
    public void setIdCurso(int idCurso) { this.idCurso = idCurso; }

    public double getValor() { return valor; }
    public void setValor(double valor) { this.valor = valor; }

    public String getData() { return data; }
    public void setData(String data) { this.data = data; }
}
