package hormigacomehojas;


/*
 * CLASE Casilla
 */
class Casilla {

    private boolean ocupada;
    int x, y;
    
    /**
     * Una casilla se identifica por su posición en el eje X=x
     * y en el eje Y=y, y puede estar ocupada o no.
     * 
     * @param x
     * @param y 
     */
    public Casilla(int x, int y) {
        this.x = x;
        this.y = y;
        ocupada = true;
    }
    /**
     * pone la casilla en estado vacio --> ocupado=false
     */
    public void setLiberada() {
        ocupada = false;

    }
    
    /**
     * Devuelve true si la casilla está ocupada. Devuelve false si la casilla está
     * libre.
     *
     * @return ocupada
     */
    boolean estado() {
        return ocupada;

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
