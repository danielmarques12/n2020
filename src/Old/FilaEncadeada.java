package Old;

public class FilaEncadeada {

    private class No{
        Paciente paciente;
        No proximo;
    }

    private No inicio;
    private No fim;

    public void init(){
        inicio = fim = null;
    }

    public boolean isEmpty(){

        if(inicio == null && fim == null)
            return true;
        else
            return false;
    }

    public void enqueue(Paciente elemento){

        No novo = new No();

        novo.paciente = elemento;
        novo.proximo = null;

        if(isEmpty())
            inicio = novo;
        else
            fim.proximo = novo;

        fim = novo;
    }

    public Paciente dequeue(){

        Paciente valor = inicio.paciente;
        inicio = inicio.proximo;

        if(inicio == null){
            fim = null;
        }

        return valor;
    }

    public Paciente first(){
        return inicio.paciente;
    }

}