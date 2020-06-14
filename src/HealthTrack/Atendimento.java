package HealthTrack;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Atendimento {

    List<Paciente> listaGeralDePacientes = new ArrayList<>();
    int opcao = 0;

    MetodosAuxiliares metodosAuxiliares = new MetodosAuxiliares();

    public void menuPrincipal(){

        while(opcao != 5){

            opcao = metodosAuxiliares.menuOpcoes();

            switch (opcao) {
                case 1 -> cadastrarPaciente();
                case 2 -> System.out.println("case 2");
                case 3 -> System.out.println("case 3");
                case 4 -> mostrarPaciente(metodosAuxiliares.obterCPFDoPaciente());
                case 5 -> JOptionPane.showMessageDialog(null, "Finalizando sistema...");
            }
        }
    }

    private void cadastrarPaciente(){

        String nomePaciente = JOptionPane.showInputDialog("Nome do paciente: ");
        String CPFPaciente = JOptionPane.showInputDialog("CPF do paciente: ");

        inserirPacienteNaListaGeral(nomePaciente, CPFPaciente);

    }

    private void inserirPacienteNaListaGeral(String nome, String CPF){

        Paciente paciente = new Paciente();

        paciente.setNome(nome);
        paciente.setCPF(CPF);
        paciente.setStatus("filaAtendimento");

        listaGeralDePacientes.add(paciente);

        JOptionPane.showMessageDialog(null, "Paciente adicionado com sucesso");
    }

    public void mostrarPaciente(String CPF){

        for(Paciente paciente : listaGeralDePacientes){

            if(paciente.getCPF().equals(CPF)){

            JOptionPane.showMessageDialog(null,
        "Nome: " + paciente.getNome() +
                "\nCPF: " + paciente.getCPF() +
                "\nStatus: " + paciente.getStatus());
            }
        }
    }

}
