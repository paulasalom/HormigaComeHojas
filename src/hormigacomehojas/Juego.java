/*
 * clase principal que trata las entradas por teclado
 * 
 * 
 */
package hormigacomehojas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

public class Juego extends JFrame implements ActionListener, KeyListener {

    private Tablero tablero;
    private boolean arriba = true;//El juego se iniciará con la hormiga dirigida hacia ariba
    private boolean abajo;
    private boolean izquierda;
    private boolean derecha;

    public Juego() {
        super("JUEGO DE LA HORMIGA COME HOJAS");
        initComponents();
    }
    /**
     * Componentes gráficas de la ventana
     */
    private void initComponents() {
        tablero = new Tablero();
        addKeyListener(this);
        add(tablero);
        setSize(tablero.getPreferredSize());
        pack();
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    /**
     * Control de eventos del teclado. Las hormiga se pueden dirigir
     * con las flechas y avanzar con el espacio.
     * Indicamos la foto que corresponde a cada dirección y ponemos a
     * true la dirección indicada, el resto serán false.
     * Por último llamamos al método repaint() para visualizar 
     * el tablero adecuado
     * 
     * @param ke
     */
    @Override
    public void keyPressed(KeyEvent ke) {
        switch (ke.getKeyCode()) {
            case KeyEvent.VK_UP://arriba
                tablero.hormiga("hormiga_n.png");
                arriba = true;
                abajo = false;
                izquierda = false;
                derecha = false;
                break;
            case KeyEvent.VK_DOWN://abajo
                tablero.hormiga("hormiga_s.png");
                arriba = false;
                abajo = true;
                izquierda = false;
                derecha = false;
                break;
            case KeyEvent.VK_LEFT://izquierda
                tablero.hormiga("hormiga_o.png");
                arriba = false;
                abajo = false;
                izquierda = true;
                derecha = false;
                break;
            case KeyEvent.VK_RIGHT://Drerecha
                tablero.hormiga("hormiga_e.png");
                arriba = false;
                abajo = false;
                izquierda = false;
                derecha = true;
                break;
            case KeyEvent.VK_SPACE:
                movimiento();//método mara mover la hormiga
            default:
                break;
        }
        repaint();
    }

    /**
     * Control del movimiento de la hormiga. 
     * La hormiga se moverá según la dirección indicada 
     * por teclado utilizando el espácio.
     * cuando la hormiga se coma una hoja el estado de la
     * casilla cambiará a ocupado=false
     * 
     */
    public void movimiento() {
        //extraemos la posición de la hormiga en ese momento
        int x = tablero.getXal();
        int y = tablero.getYal();
        if (arriba) {
            //si la posición es 0 la hormiga aparecerá en la última casilla
            if(y == 0){
            int fila = y/40;
            int columna = x/40;
            tablero.cambiarEstadoCasilla(fila,columna);
            y=760;//última casilla
            tablero.setYal(y);//indicamos cual es la nueva posición de la hormiga
            }
            //si la posición es distinta de 0 la hormiga avanza 
            //hacia arriba restando 40 a su posición actual en el eje Y
            else{
            int fila = y/40;
            int columna = x/40;
            tablero.cambiarEstadoCasilla(fila,columna);
            y=y-40;
            tablero.setYal(y);//indicamos cual es la nueva posición de la hormiga
            }
        }
        if (abajo) {
            //si la posición es 760 la hormiga aparecerá en la primera casilla
            if(y == 760){
            int fila = y/40;
            int columna = x/40;
            tablero.cambiarEstadoCasilla(fila,columna);
            y=0;//primera casilla
            tablero.setYal(y);//indicamos cual es la nueva posición de la hormiga
            }
            //si la posición es distinta de 760 la hormiga avanza 
            //hacia abajo sumando 40 a su posición actual en el eje Y
            else{
            int fila = y/40;
            int columna = x/40;
            tablero.cambiarEstadoCasilla(fila,columna);
            y=y+40;
            tablero.setYal(y);//indicamos cual es la nueva posición de la hormiga
            }
        }
        if (izquierda) {
            //si la posición es 0 la hormiga aparecerá en la derecha 
            if(x == 0){
            int fila = y/40;
            int columna = x/40;
            tablero.cambiarEstadoCasilla(fila,columna);
            x=760;//primera casilla de la derecha
            tablero.setXal(x);//indicamos cual es la nueva posición de la hormiga
            }
            //si la posición es distinta de 0 la hormiga avanza 
            //hacia la izquierda restando 40 a su posición actual en el eje X
            else{
            int fila = y/40;
            int columna = x/40;
            tablero.cambiarEstadoCasilla(fila,columna);
            x=x-40;
            tablero.setXal(x);//indicamos cual es la nueva posición de la hormiga
            }
        }
        if (derecha) {
            //si la posición es 760 la hormiga aparecerá en la izquierda 
            if(x == 760){
            int fila = y/40;
            int columna = x/40;
            tablero.cambiarEstadoCasilla(fila,columna);
            x=0;//primera casilla de la izquierda
            tablero.setXal(x);//indicamos cual es la nueva posición de la hormiga
            }
            //si la posición es distinta de 760 la hormiga avanza 
            //hacia la derecha sumando 40 a su posición actual en el eje X
            else{
            int fila = y/40;
            int columna = x/40;
            tablero.cambiarEstadoCasilla(fila,columna);
            x=x+40;
            tablero.setXal(x);//indicamos cual es la nueva posición de la hormiga
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
    }

    @Override
    public void keyTyped(KeyEvent ke) {
    }
    
    /**
     * @param args
     */
    public static void main(String[] args) {
        Juego taller = new Juego();
        taller.setVisible(true);
    }
}
