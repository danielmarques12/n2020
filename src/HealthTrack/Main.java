package HealthTrack;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        FilaEncadeada filaAguardandoAtendimento = new FilaEncadeada();
        FilaEncadeada filaAguardandoInternacao = new FilaEncadeada();
        FilaSequencial leitos = new FilaSequencial();

        Scanner teclado = new Scanner(System.in);
        Paciente paciente = new Paciente();
        Atendimento atendimento = new Atendimento();

        for (int contador = 1; contador <= 4; contador++){

            atendimento.realizaCadastroPaciente(paciente, teclado);

            if (atendimento.podeSerAtendido(teclado)) {

                atendimento.realizaConsulta(atendimento, teclado);
                atendimento.internaOuRecebeAlta(paciente, atendimento, leitos, filaAguardandoInternacao);
            }
            else{
                filaAguardandoAtendimento.enqueue(paciente);
                System.out.println("O paciente deverá aguardar até ser atendido.");
            }
        }
        teclado.close();
    }
}