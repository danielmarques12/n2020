package HealthTrack;

import javax.swing.*;

public class MetodosAuxiliares {

    public int menuOpcoes(){

        return Integer.parseInt(JOptionPane.showInputDialog(null,
    "Pressione (1) para cadastrar um novo paciente.\n" +
            "Pressione (2) para iniciar o atendimento do próximo paciente da fila.\n" +
            "Pressione (3) para dar alta para um paciente.\n" +
            "Pressione (4) para acessar o registro de um paciente.\n" +
            "Pressione (5) para encerrar as atividades do dia."));
    }

    public int menuAtendimento(){

        int sintomas = 0;

        int sintoma1 = Integer.parseInt(JOptionPane.showInputDialog(null,
            "O paciente está com febre?" +
            "Digite 1 para SIM e 2 para NÃO (S/N):"));
        if(sintoma1 == 1){
            sintomas++;
        }
        int sintoma2 = Integer.parseInt(JOptionPane.showInputDialog(null,
                "O paciente está com cansaço?" +
                        "Digite 1 para SIM e 2 para NÃO (S/N):"));
        if(sintoma2 == 1){
            sintomas++;
        }
        int sintoma3 = Integer.parseInt(JOptionPane.showInputDialog(null,
                "O paciente está com tosse seca?" +
                        "Digite 1 para SIM e 2 para NÃO (S/N):"));
        if(sintoma3 == 1){
            sintomas++;
        }

        return sintomas;
    }

    public String obterCPFDoPaciente(){

        return JOptionPane.showInputDialog(null,
            "Digite o CPF do paciente: ");
    }

    public void mensagemJoptionPane(Paciente paciente, String mensagem){

        JOptionPane.showMessageDialog(null, mensagem +
            "\nNome: " + paciente.getNome() +
            "\nCPF: " + paciente.getCPF());
    }

    public void mensagemFinalizandoSistema(){
        JOptionPane.showMessageDialog(null, "Finalizando sistema...");
    }
}
