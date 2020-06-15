package HealthTrack;

public class Paciente {

    private String Nome, CPF, Status;

    public Paciente(String Nome, String CPF, String Status){
        this.Nome = Nome;
        this.CPF = CPF;
        this.Status = Status;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}