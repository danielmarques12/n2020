package HealthTrack;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Atendimento {

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
                case 5 -> metodosAuxiliares.mensagemFinalizandoSistema();
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

            metodosAuxiliares.mensagemJoptionPane(paciente,
    "A seguir, será feita uma avaliação dos sintomas do paciente.");

            if(metodosAuxiliares.menuAtendimento() == 3){

                if(pacientesInternados.size() == 2){

                    metodosAuxiliares.mensagemJoptionPane(paciente,
            "Todos os leitos estão ocupados no momento." +
                     "\nO paciente foi adicionado na lista de espera para internação");

                    filaParaInternacao.enqueue(paciente);
                    setStatus(paciente.getCPF(), "filaInternação");
                }
                else{
                    pacientesInternados.add(paciente);
                    metodosAuxiliares.mensagemJoptionPane(paciente, "O paciente foi internado");
                    setStatus(paciente.getCPF(), "Internado");
                }
            }
            else{
                setStatus(paciente.getCPF(), "Liberado");
                metodosAuxiliares.mensagemJoptionPane(paciente, "Paciente liberado.");
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

                    setStatus(novoInternado.getCPF(), "Internado");

                    metodosAuxiliares.mensagemJoptionPane(novoInternado,
            "O leito que foi desocupado, agora receberá o paciente: ");
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

    private void setStatus(String CPF, String status){

        for(Paciente p : listaGeralDePacientes){
            if(p.getCPF().equals(CPF)){
                p.setStatus(status);
            }
        }
    }
}