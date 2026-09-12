import java.util.Random;

import javax.swing.JPanel;

public class Jugador {

    private final int TOTAL_CARTAS = 10;
    private final int MARGEN = 10;
    private final int DISTANCIA = 40;

    private Carta[] cartas = new Carta[TOTAL_CARTAS];
    private Random r = new Random();

    public void repartir() {
        for (int i = 0; i < TOTAL_CARTAS; i++) {
            cartas[i] = new Carta(r);
        }
    }

    public void mostrar(JPanel pnl) {
        pnl.removeAll();
        pnl.setLayout(null);
        int posicion = MARGEN + TOTAL_CARTAS * DISTANCIA;
        for (Carta carta : cartas) {
            posicion -= DISTANCIA;
            carta.mostrar(pnl, posicion, MARGEN);
        }
        pnl.repaint();
    }

    public String getGrupos() {
        String resultado = "No se encontraron grupos";

        int[] contadores = new int[NombreCarta.values().length];
        boolean hayGrupos = false;
        for (Carta carta : cartas) {
            int posicion = carta.getNombre().ordinal();
            contadores[posicion]++;
            if (!hayGrupos && contadores[posicion] >= 2) {
                hayGrupos = true;
            }
        }

        if (hayGrupos) {
            resultado = "Se encontraron los siguientes grupos:\n";
            // for (int contador : contadores) {
            for (int i = 0; i < contadores.length; i++) {
                // if (contador >= 2) {
                if (contadores[i] >= 2) {
                    resultado += Grupo.values()[contadores[i]] + " de " + NombreCarta.values()[i] + "\n";
                }
            }
        }
        return resultado;
    }

    public int getPuntaje() {
        int[] contadores = new int[NombreCarta.values().length];
        for (Carta carta : cartas) {
            contadores[carta.getNombre().ordinal()]++;
        }
        int puntaje = 0;
        for (Carta carta : cartas) {
            if (contadores[carta.getNombre().ordinal()] == 1) {
                puntaje += carta.getValor();
            }
        }
        return puntaje;
    }

    public String getEscalera() {
        String resultado = "No se encontraron escalera de pinta";
        int[] contadoresPinta = new int[Pinta.values().length];

        NombreCarta[] inicioEscalera = new NombreCarta[Pinta.values().length];
        NombreCarta[] finEscalera = new NombreCarta[Pinta.values().length];

        boolean hayEscalera = false;

        for (int i = 0; i < cartas.length; i++) {
            Carta cartaRef = cartas[i];

            boolean tieneAnterior = false;

            for (int j = 0; j < cartas.length; j++) {
                Carta carta = cartas[j];

                if ((carta.getValor() == cartaRef.getValor() - 1) &&
                        (carta.getPinta() == cartaRef.getPinta())) {
                    tieneAnterior = true;
                    break;
                }
            }

            if (tieneAnterior) {
                continue;
            }

            boolean encontreSig = true;
            int contador = 1;

            while (encontreSig) {
                encontreSig = false;

                for (int j = 0; j < cartas.length; j++) {
                    Carta carta = cartas[j];

                    if ((carta.getValor() == cartaRef.getValor() + 1) &&
                            (carta.getPinta() == cartaRef.getPinta())) {
                        cartaRef = carta;
                        contador++;
                        encontreSig = true;
                        break;
                    }
                }
            }

            if (contador >= 2) {

                int posicionPinta = cartas[i].getPinta().ordinal();

                contadoresPinta[posicionPinta] = contador;
                inicioEscalera[posicionPinta] = cartas[i].getNombre();
                finEscalera[posicionPinta] = cartaRef.getNombre();

                hayEscalera = true;
            }
        }

        if (hayEscalera) {
            resultado = "Se encontraron las siguientes escaleras:\n";

            for (int i = 0; i < contadoresPinta.length; i++) {
                if (contadoresPinta[i] >= 2) {
                    resultado += Grupo.values()[contadoresPinta[i]]
                            + " de " + Pinta.values()[i]
                            + " de " + inicioEscalera[i]
                            + " a " + finEscalera[i] + "\n";
                }
            }
        }
        return resultado;
    }
}
