package HealthTrack;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Atendimento {

    List<Paciente> listaGeralDePacientes = new ArrayList<>();
    List<Paciente> pacientesInternados = new ArrayList<>();
    FilaSequencial filaParaAtendimento = new FilaSequencial();
    FilaSequencial filaParaInternacao = new FilaSequencial();
    MetodosAuxiliares metodosAuxiliares = new MetodosAuxiliares();
    int opcao = 0;

    public void menuPrincipal(){

        while(opcao != 5){

            opcao = metodosAuxiliares.menuOpcoes();

            switch (opcao) {
                case 1 -> cadastrarPaciente();
                case 2 -> realizarAtendimento();
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

        Paciente paciente = new Paciente(nome, CPF, "filaAtendimento");

        listaGeralDePacientes.add(paciente);
        filaParaAtendimento.enqueue(paciente);

        JOptionPane.showMessageDialog(null, "Paciente adicionado com sucesso");
    }

    private void realizarAtendimento(){

        Paciente paciente = filaParaAtendimento.dequeue();
        String CPF = paciente.getCPF();

        JOptionPane.showMessageDialog(null,
    "Será feito a seguir uma avaliação dos sintomas do paciente. " +
            "\nNome: " + paciente.getNome() +
            "\nCPF: " + paciente.getCPF());

        if(metodosAuxiliares.menuAtendimento() == 3){
            System.out.println("oi");
            // Trocar List<Paciente> pacientesInternados = new ArrayList<>() por um List Manual (ver material)
        }
        else{
            for (Paciente p : listaGeralDePacientes){
                if(p.getCPF().equals(CPF)){
                    p.setStatus("liberado");
                    JOptionPane.showMessageDialog(null,
                "Paciente " + p.getNome() + " liberado" +
                        "\nCPF: " + p.getCPF());
                }
            }
        }
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
