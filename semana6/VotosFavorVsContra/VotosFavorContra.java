package semana6.VotosFavorVsContra;
//Enunciado. Dados dos contadores: upvotes y downvotes, modela cada voto en una cola como +1
//(a favor) o -1 (en contra). Procesa la cola sumando todos los valores hasta vaciarla. Retorna la
//diferencia: (a favor) − (en contra).


import java.util.*;

public class VotosFavorContra {
    public static void main(String[] args) {
        Queue<String> v = new LinkedList<>();

        Votos.registrar(v,"Jose");
        Votos.registrar(v,"Jorge");
        Votos.registrar(v,"Carla");
        Votos.registrar(v,"Carla");
        Votos.aFavor(v);
        Votos.aFavor(v);
        Votos.enContra(v);
        Votos.mostrar(v);
        
    }
}
