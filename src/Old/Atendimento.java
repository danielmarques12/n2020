package Old;

import java.util.Scanner;

public class Atendimento {

    private int quantidadeDeSintomas = 0;

    public void contadorSintomas(){
        quantidadeDeSintomas++;
    }

    public int getContadorSintomas(){
        return quantidadeDeSintomas;
    }

    public void realizaCadastroPaciente(Paciente paciente, Scanner teclado){

        System.out.print("Digite o nome do novo paciente: ");
        paciente.setNome(teclado.next());
        System.out.print("Digite o CPF do novo paciente: ");
        paciente.setCPF(teclado.nextInt());
    }

    public boolean podeSerAtendido(Scanner teclado){

        System.out.print("Há algum médico disponível para atender o paciente? (1 para SIM e 2 para NÃO): ");

        if(teclado.nextInt() == 1){
            return true;
        }
        else{
            return false;
        }
    }

    public void realizaConsulta(Atendimento atendimento, Scanner teclado){

        System.out.println("\nFaremos a seguir uma breve avaliação dos seus sintomas.");

        System.out.print("Você está com febre? (1 para SIM e 2 para NÃO): ");
        int respostaSintoma = teclado.nextInt();
        if(respostaSintoma == 1){
            atendimento.contadorSintomas();
        }

        System.out.print("Você está com tosse seca? (1 para SIM e 2 para NÃO): ");
        int respostaSintoma1 = teclado.nextInt();
        if(respostaSintoma1 == 1){
            atendimento.contadorSintomas();
        }

        System.out.print("Você está com cansa�o? (1 para SIM e 2 para NÃO): ");
        int respostaSintoma2 = teclado.nextInt();
        if(respostaSintoma2 == 1){
            atendimento.contadorSintomas();
        }
    }

    public void internaOuRecebeAlta(Paciente paciente, Atendimento atendimento,
                FilaSequencial leitos, FilaEncadeada filaAguardandoInternacao){

        if(atendimento.getContadorSintomas() != 3){
            System.out.println("O paciente recebeu alta!\n");
        }
        else{
            if(leitos.isFull()){
                filaAguardandoInternacao.enqueue(paciente);
                System.out.println("Não há leitos disponíveis no momento.");
                System.out.println("O paciente foi adicionado na fila de espera.");
            }
            else{
                System.out.println("O paciente foi internado.\n");
                leitos.enqueue(paciente);
            }
        }
    }
}
