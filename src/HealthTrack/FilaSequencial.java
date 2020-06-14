package HealthTrack;

public class FilaSequencial {
    public final int N = 10;

    Paciente[] paciente = new Paciente[N];
    int ini, fim, cont;

    public void init() {
        ini = fim = cont = 0;
    }

    public boolean isEmpty() {
        if (cont == 0)
            return true;
        else
            return false;
    }

    public boolean isFull() {
        if (cont == N)
            return true;
        else
            return false;
    }

    public void enqueue(Paciente elem) {
        if (isFull() == false) {
            paciente[fim] = elem;
            fim = (fim+1) % N;
            cont++;
        }
        else
            System.out.println("Fila Cheia");
    }

    public Paciente dequeue() {
        Paciente valor = paciente[ini];
        ini = (ini+1) % N;
        cont--;
        return valor;
    }
}
