import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Carta {

    private int indice;

    // método constructor
    public Carta(Random r) {
        // generar un numero al azar entre 1 y 52
        indice = r.nextInt(52) + 1;
    }

    public Carta(int indice) {
        this.indice = indice;
    }

    public void mostrar(JPanel pnl, int x, int y) {
        // cargar imagen
        String rutaImagen = "imagenes/CARTA" + indice + ".JPG";
        ImageIcon imgCarta = new ImageIcon(getClass().getResource(rutaImagen));

        // mostrar en un JLABEL
        JLabel lblCarta = new JLabel(imgCarta);
        lblCarta.setBounds(x, y, imgCarta.getIconWidth(), imgCarta.getIconHeight());
        pnl.add(lblCarta);

        // evento CLICK de la CARTA
        lblCarta.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evento) {
                JOptionPane.showMessageDialog(null, getNombre() + " de " + getPinta());
            }
        });

    }

    // Getters
    public Pinta getPinta() {
        if (indice <= 13) {
            return Pinta.TREBOL;
        } else if (indice <= 26) {
            return Pinta.PICA;
        } else if (indice <= 39) {
            return Pinta.CORAZON;
        } else {
            return Pinta.DIAMANTE;
        }
    }

    public NombreCarta getNombre() {
        int residuo = indice % 13;
        if (residuo == 0) {
            residuo = 13;
        }
        return NombreCarta.values()[residuo - 1];
    }

    public int getValor() {
        // switch ( getNombre()) {
        // case AS:
        // case JACK:
        // case QUEEN:
        // case KING:
        // return 10;
        // default:
        // return getNombre().ordinal() + 1;
        // }
        return getNombre().ordinal() + 1;
    }

}
