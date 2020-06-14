package HealthTrack;

import javax.swing.*;

public class MetodosAuxiliares {

    public int menuOpcoes(){

        return Integer.parseInt(JOptionPane.showInputDialog(null,
    "Pressione (1) para cadastrar um novo paciente.\n" +
            "Pressione (2) para iniciar o atendimento do próximo paciente.\n" +
            "Pressione (3) para dar alta para um paciente.\n" +
            "Pressione (4) para acessar o registro de um paciente.\n" +
            "Pressione (5) para encerrar as atividades do dia."));
    }

    public String obterCPFDoPaciente(){

        return JOptionPane.showInputDialog(null,
            "Digite o CPF do paciente que deseja puxar o registro: ");
    }
}
