import java.util.Random;

public class Mazo {
    private int cantidadBarajas;
    private int[] contadores;
    private Random r;

    public Mazo(int cantidadBarajas) {
        this.cantidadBarajas = cantidadBarajas;
        this.contadores = new int[53];
        this.r = new Random();
    }

    public int obtenerIndiceDisponible() {
        int indice = r.nextInt(52) + 1;
        while (contadores[indice] >= cantidadBarajas) {
            indice = r.nextInt(52) + 1;
        }
        contadores[indice]++;
        return indice;
    }

    public int getCartasRestantes() {
        int total = 52 * cantidadBarajas;
        int usadas = 0;
        for (int i = 1; i < 52; i++) {
            usadas += contadores[i];
        }
        return total - usadas;
    }
}
