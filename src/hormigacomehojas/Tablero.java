package hormigacomehojas;

/*
CLASE Tablero
 */
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Tablero extends JPanel {

    private static final int medida = 20;//número de filas o columnas
    private final int totalcasillas = medida * medida;//número total de casillas
    private final int tamanio = 40;//tamaño en altura o ancho de la casilla
    private BufferedImage img = null;
    private static final Color NEGRO = Color.BLACK;
    private final Casilla[][] casillas = new Casilla[medida][medida];
    private String hormiga = "hormiga_n.png";//El juego se iniciará con la hormiga dirigida hacia ariba 
    Random aleatorio = new Random();
    //situamos a la hormiga en una posición aletoria
    private final int xaleatorio = aleatorio.nextInt(20);
    private final int yaleatorio = aleatorio.nextInt(20);
    //posición en el tablero de la hormiga
    private int XHormiga = xaleatorio * tamanio;
    private int YHormiga = yaleatorio * tamanio;
    private int interacciones = 0;//contabilizamos las casillas que no tienen hoja
    private final int dimension = 785;//dimensión del tablero

    /**
     * Método que crea un array bidimensional de casillas.
     */
    public Tablero() {
        for (int fila = 0; fila < medida; fila++) {
            int x = 0;
            int y = fila * tamanio;
            for (int columna = 0; columna < medida; columna++) {
                casillas[fila][columna] = new Casilla(x, y);
                x = x + tamanio;
            }
        }
    }

    /**
     * Método para indicar la imagen de la hormiga correspondiete a la dirección
     * introducida por parámetro.
     *
     * @param hormiga
     */
    public void hormiga(String hormiga) {
        this.hormiga = hormiga;

    }

    /**
     * Devuelve las dimensiones del panel del tablero (dimensión x dimensión).
     *
     * @return Dimension
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(dimension, dimension);
    }

    /**
     * Método que pinta las casillas del tablero, con sus corresponendientes
     * imagenes, en el panel. En el caso de que la casilla este ocupada pinta la
     * imágen de una hoja, en el caso que la hormiga se haya comido la hoja
     * pinta una imágen en blanco, después pinta a la hormiga en la posición
     * aleatória. Finalmente pinta los bordes de las casillas en negro
     *
     * @param g
     */
    @Override
    public void paint(Graphics g) {

        for (int fila = 0; fila < medida; fila++) {
            int x = 0;
            int y = fila * tamanio;
            for (int columna = 0; columna < medida; columna++) {
                if (casillas[fila][columna].estado()) {
                    try {
                        img = ImageIO.read(new File("hoja.png"));
                    } catch (IOException ex) {
                        System.err.println("ERROR: No se ha podido cargar la imagen.");
                    }

                    Graphics2D g2d = (Graphics2D) g;
                    g2d.drawImage(img, null, x, y);
                } else {
                    try {
                        img = ImageIO.read(new File("nada.png"));
                    } catch (IOException ex) {
                        System.err.println("ERROR: No se ha podido cargar la imagen.");
                    }
                    Graphics2D g2d = (Graphics2D) g;
                    g2d.drawImage(img, null, x, y);
                }
                x = x + tamanio;
            }
        }
        try {
            img = ImageIO.read(new File(hormiga));
        } catch (IOException ex) {
            System.err.println("ERROR: No se ha podido cargar la imagen.");
        }

        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(img, null, XHormiga, YHormiga);
        g.setColor(NEGRO);
        for (int fila = 0; fila < medida; fila++) {
            g.drawLine(0, (int) casillas[fila][0].getY(),
                    1000, (int) casillas[fila][0].getY());
        }
        g.drawLine((int) 0, (int) casillas[medida - 1][0].getY() + tamanio,
                1000, (int) casillas[medida - 1][0].getY() + tamanio);

        for (int columna = 0; columna < medida; columna++) {
            g.drawLine((int) casillas[0][columna].getX(), 0,
                    (int) casillas[0][columna].getX(), 1000);
        }
        g.drawLine((int) casillas[0][medida - 1].getX() + tamanio, 0,
                (int) casillas[0][medida - 1].getX() + tamanio, 1000);

    }

    /**
     * Métodos para saber la posición de la hormiga.
     *
     * @return XHormiga
     * @return YHormiga
     */
    public int getXal() {
        return XHormiga;
    }

    public int getYal() {
        return YHormiga;
    }

    /**
     * Métodos para configurar la posición de la hormiga.
     *
     * @param X
     * @param Y
     *
     */
    public void setXal(int X) {
        XHormiga = X;
    }

    public void setYal(int Y) {
        YHormiga = Y;
    }

    /**
     * Método para cambiar el estado de la casilla en el momento en que la
     * hormiga se come la hoja, una vez comida la hoja el estado siempre será:
     * ocupada = false.
     *
     * @param fila
     * @param columna
     */
    public void cambiarEstadoCasilla(int fila, int columna) {
        if (casillas[fila][columna].estado()) {
            casillas[fila][columna].setLiberada();
            interacciones++;
            if (interacciones == totalcasillas) {
                JOptionPane.showMessageDialog(this, "                        ENHORABUENA\n       "
                        + "        ¡¡¡has completado el juego!!!  "
                        + "     \nLA HORMIGA SE HA COMIDO TODAS LAS HOJAS   ");
                System.exit(0);

            }
        }
    }

}
