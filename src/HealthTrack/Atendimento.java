package HealthTrack;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Atendimento {

    public final int leitos = 10;
    ArrayList<Paciente> listaGeralDePacientes = new ArrayList();
    ArrayList<Paciente> pacientesInternados = new ArrayList();
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
                case 3 -> liberacaoDeVagaNaUnidade(metodosAuxiliares.obterCPFDoPaciente());
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

        JOptionPane.showMessageDialog(null, "Paciente cadastrado com sucesso");
    }

    private void realizarAtendimento(){

        if(filaParaAtendimento.isEmpty()){
            JOptionPane.showMessageDialog(null,
        "Não há pacientes aguardando atendimento");
        }
        else{
            Paciente paciente = filaParaAtendimento.dequeue();
            String CPF = paciente.getCPF();

            JOptionPane.showMessageDialog(null,
        "A seguir, será feita uma avaliação dos sintomas do paciente. " +
                "\nNome: " + paciente.getNome() +
                "\nCPF: " + paciente.getCPF());

            if(metodosAuxiliares.menuAtendimento() == 3){

                if(pacientesInternados.size() == 2){

                    JOptionPane.showMessageDialog(null,
                "Todos os leitos estão ocupados no momento." +
                        "O paciente " + paciente.getNome() + " foi adicionado na lista de espera para internação" +
                        "\nCPF: " + paciente.getCPF());

                    filaParaInternacao.enqueue(paciente);

                    for (Paciente p : listaGeralDePacientes){
                        if(p.getCPF().equals(CPF)){
                            p.setStatus("filaInternação");
                        }
                    }
                }
                else{
                    pacientesInternados.add(paciente);

                    JOptionPane.showMessageDialog(null,
                "O paciente " + paciente.getNome() + " foi internado" +
                        "\nCPF: " + paciente.getCPF());

                    for (Paciente p : listaGeralDePacientes){
                        if(p.getCPF().equals(CPF)){
                            p.setStatus("internado");
                        }
                    }
                }
            }
            else{
                for (Paciente p : listaGeralDePacientes){
                    if(p.getCPF().equals(CPF)){
                        p.setStatus("liberado");
                    }
                }
                JOptionPane.showMessageDialog(null,
            "Paciente " + paciente.getNome() + " liberado." +
                    "\nCPF: " + paciente.getCPF());
            }
        }
    }

    private void liberacaoDeVagaNaUnidade(String CPF){

        String altaOuObito;

        int situacaoPaciente = Integer.parseInt(JOptionPane.showInputDialog(null,
                "Situação do paciente: 1 para ALTA e 2 para ÓBITO"));

        if(situacaoPaciente == 1){
            altaOuObito = "Alta";
        }
        else{
            altaOuObito = "Óbito";
        }

        for (Paciente paciente : listaGeralDePacientes){
            if (paciente.getCPF().equals(CPF)){
                paciente.setStatus(altaOuObito);
                pacientesInternados.remove(paciente);

                if(!filaParaInternacao.isEmpty()){
                    Paciente novoInternado = filaParaInternacao.dequeue();
                    pacientesInternados.add(novoInternado);

                    for(Paciente p : listaGeralDePacientes){
                        if(p.getCPF().equals(novoInternado.getCPF())){
                            p.setStatus("Internado");
                        }
                    }

                    JOptionPane.showMessageDialog(null,
                        novoInternado.getNome() + " foi internado na vaga de leito que surgiu." +
                        "CPF: " + novoInternado.getCPF());
                }
            }
        }
    }

    private void mostrarPaciente(String CPF){

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