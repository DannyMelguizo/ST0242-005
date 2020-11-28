
/**
 * Esta clase maneja el juego
 * Se tiene una referencia al tablero y al pacman
 * En esta clase se hace la interacción con el usuario
 * @author Helmuth Trefftz
 */
import java.util.Scanner;
import java.util.Random;

public class Juego {

    /**
     * El número de puntos iniciales de vida del pacman
     */
    public static final int PUNTOS_VIDA_INICIALES = 10;
    int vida = PUNTOS_VIDA_INICIALES;
    Tablero tablero;
    Pacman pacman;

    /**
     * Constructor
     * Se crea un tablero
     */
    public Juego() {
        tablero = new Tablero(this);
    }

    /**
     * Interacción con el usuario
     */
    public void jugar() {
        boolean ganaElJuego = false;
        tablero.dibujarTablero();
        int salidaFila = 0;
        int salidaCol = 0;  
        for(int i = 0; i < tablero.tablero.length; i++){
            for(int j = i; j < tablero.tablero[i].length; j++){
                if(tablero.tablero[i][j].esSalida == true){
                    salidaFila = salidaFila + i;
                    salidaCol = salidaCol + j;
                    break;
                }
            }   
        }

        while (!ganaElJuego) {
            if(vecinosPosicion(pacman) == true){
                ganaElJuego = true;
                break;
            }
            tablero.dibujarTablero();
        }
    }

    /**
     * En este metodo se debe chequear las siguientes condiciones:
     * (i) Que el usuario no se salga de las filas del tablero
     * (ii) Que el usuario no se salga de las columnas del tablero
     * (iii) Que la posición no sea un muro
     * (iv) Que no haya un caracter en esa posición
     * 
     * @param nuevaFila Fila hacia donde se quiere mover el usuario
     * @param nuevaCol Columna hacia donde se quiere mover el usuario
     * @return true si es una jugada válida, false de lo contrario
     */
    private boolean validarCasilla(int nuevaFila, int nuevaCol) {
        // Aquí hay que verificar que sea un movimiento válido
        // Ver los comentarios del método
        boolean validarCasilla = true;
        if(tablero.tablero[nuevaFila][nuevaCol].caracterCelda() == '*'){
            return validarCasilla = false;
        } else if (tablero.tablero[nuevaFila][nuevaCol].caracterCelda() == 'X') {
            return validarCasilla = false;
        }
        return validarCasilla;
    }

    private boolean vecinosPosicion(Pacman pacman1){
        boolean salida = false;
        int fila = pacman1.posicion.fila;
        int col = pacman1.posicion.col;
        int salidaFila = 0;
        int salidaCol = 0;  
        for(int i = 0; i < tablero.tablero.length; i++){
            for(int j = i; j < tablero.tablero[i].length; j++){
                if(tablero.tablero[i][j].esSalida == true){
                    salidaFila = salidaFila + i;
                    salidaCol = salidaCol + j;
                    break;
                }
            }   
        }
        int movimientos = 0;
        int nuevaFila = fila;
        int nuevaCol = col;
        //vecino de la derecha en la misma fila
        if(pacman1.posicion.col < salidaCol && pacman1.posicion.fila == salidaFila && movimientos == 0){
            if(tablero.tablero[fila][col+1].caracterCelda() == '*'){
                if(tablero.tablero[fila-1][col].caracterCelda()=='X'){
                    nuevaFila = fila + 1;
                    movimientos = movimientos + 1;
                } else if(tablero.tablero[fila+1][col].caracterCelda()=='X'){
                    nuevaFila = fila - 1;
                    movimientos = movimientos + 1;
                }
            } else {
                nuevaCol = col + 1;
                movimientos = movimientos + 1;
            }
        }
        //vecino de la derecha en fila menor
        if(pacman1.posicion.col < salidaCol && pacman1.posicion.fila < salidaFila && movimientos == 0){
            if(tablero.tablero[fila][col+1].caracterCelda() == '*' || tablero.tablero[fila][col+1].caracterCelda() == 'X'){
                if(tablero.tablero[fila-1][col].caracterCelda()=='X' || tablero.tablero[fila-1][col].caracterCelda()=='*' && tablero.tablero[fila+1][col].caracterCelda()!='*'){
                    nuevaFila = fila + 1;
                    movimientos = movimientos + 1;
                } else if(tablero.tablero[fila+1][col].caracterCelda()=='X' || tablero.tablero[fila+1][col].caracterCelda()=='*' && tablero.tablero[fila-1][col].caracterCelda()!='*'){
                    nuevaFila = fila - 1;
                    movimientos = movimientos + 1;
                } else if(tablero.tablero[fila-1][col].caracterCelda()=='X' || tablero.tablero[fila-1][col].caracterCelda()=='*' && tablero.tablero[fila+1][col].caracterCelda()=='*'){
                    nuevaCol = col - 1;
                    movimientos = movimientos + 1;
                } else if(tablero.tablero[fila+1][col].caracterCelda()=='X' || tablero.tablero[fila+1][col].caracterCelda()=='*' && tablero.tablero[fila-1][col].caracterCelda()=='*'){
                    nuevaCol = col + 1;
                    movimientos = movimientos + 1;    
                }   
            } else {
                nuevaCol = col + 1;
                movimientos  = movimientos + 1;
            }
        }
        //vecino de la derecha en fila mayor
        if(pacman1.posicion.col < salidaCol && pacman1.posicion.fila > salidaFila && movimientos == 0){
            if(tablero.tablero[fila][col+1].caracterCelda() == '*'){
                if(tablero.tablero[fila-1][col].caracterCelda()=='X'){
                    nuevaFila = fila + 1;
                    movimientos = movimientos + 1;
                } else if(tablero.tablero[fila+1][col].caracterCelda()=='X'){
                    nuevaFila = fila - 1;
                    movimientos = movimientos + 1;
                }
            } else {
                nuevaCol = col + 1;
                movimientos  = movimientos + 1;
            }
        }
        //vecino de la izquierda en la misma fila
        if(pacman1.posicion.col > salidaCol && pacman1.posicion.fila == salidaFila && movimientos == 0){
            if(tablero.tablero[fila][col-1].caracterCelda() == '*'){
                if(tablero.tablero[fila-1][col].caracterCelda()=='X' || tablero.tablero[fila-1][col].caracterCelda()=='*'){
                    nuevaFila = fila + 1;
                    movimientos = movimientos + 1;
                } else if(tablero.tablero[fila+1][col].caracterCelda()=='X' || tablero.tablero[fila-1][col].caracterCelda()=='*'){
                    nuevaFila = fila - 1;
                    movimientos = movimientos + 1;
                }
            } else {    
                nuevaCol = col - 1;
                movimientos = movimientos + 1;
            }
        }
        //vecino de la izquierda en fila menor
        if(pacman1.posicion.col > salidaCol && pacman1.posicion.fila < salidaFila && movimientos == 0){
            if(tablero.tablero[fila][col-1].caracterCelda() == '*' || tablero.tablero[fila][col-1].caracterCelda() == 'X'){
                if(tablero.tablero[fila-1][col].caracterCelda()=='X' || tablero.tablero[fila-1][col].caracterCelda()=='*' && tablero.tablero[fila+1][col].caracterCelda() !='*' ){
                    nuevaFila = fila + 1;
                    movimientos = movimientos + 1;
                } else if(tablero.tablero[fila+1][col].caracterCelda()=='X' || tablero.tablero[fila-1][col].caracterCelda()=='*' && tablero.tablero[fila-1][col].caracterCelda()!='*'){
                    nuevaFila = fila - 1;
                    movimientos = movimientos + 1;
                } else if(tablero.tablero[fila-1][col].caracterCelda()=='X' || tablero.tablero[fila-1][col].caracterCelda()=='*' && tablero.tablero[fila+1][col].caracterCelda() =='*' ){
                    nuevaCol = col + 1;
                    movimientos = movimientos + 1;
                } else if(tablero.tablero[fila+1][col].caracterCelda()=='X' || tablero.tablero[fila-1][col].caracterCelda()=='*' && tablero.tablero[fila-1][col].caracterCelda()=='*'){
                    nuevaCol = col - 1;
                    movimientos = movimientos + 1;
                }
            } else {
                nuevaCol = col - 1;
                movimientos = movimientos + 1;
            }      
        }
        //vecino de la izquierda en fila mayor
        if(pacman1.posicion.col > salidaCol && pacman1.posicion.fila > salidaFila && movimientos == 0){
            if(tablero.tablero[fila][col-1].caracterCelda() == '*' || tablero.tablero[fila][col-1].caracterCelda() == 'X'){
                if(tablero.tablero[fila-1][col].caracterCelda()=='X' || tablero.tablero[fila-1][col].caracterCelda()=='*'){
                    nuevaFila = fila + 1;
                    movimientos = movimientos + 1;
                } else if(tablero.tablero[fila+1][col].caracterCelda()=='X' || tablero.tablero[fila-1][col].caracterCelda()=='*'){
                    nuevaFila = fila - 1;
                    movimientos = movimientos + 1;
                }
            } else{
                nuevaCol = col - 1;
                movimientos = movimientos + 1;
            }      
        }
        //vecino abajo en misma columna
        if(pacman1.posicion.col == salidaCol && pacman1.posicion.fila < salidaFila && movimientos == 0){
            if(tablero.tablero[fila+1][col].caracterCelda() == '*'){
                if(tablero.tablero[fila][col-1].caracterCelda()=='X' || tablero.tablero[fila][col-1].caracterCelda()=='*'){
                    nuevaCol = col + 1;
                    movimientos = movimientos + 1;
                } else if(tablero.tablero[fila][col+1].caracterCelda()=='X' || tablero.tablero[fila][col+1].caracterCelda()=='*'){
                    nuevaCol = col - 1;
                    movimientos = movimientos + 1;
                } else if(tablero.tablero[fila][col+1].caracterCelda()=='X' || tablero.tablero[fila][col+1].caracterCelda()=='*'){

                }
            } else{
                nuevaFila = fila + 1;
                movimientos = movimientos + 1;
            }      
        }
        //vecino arriba en misma columna
        if(pacman1.posicion.col == salidaCol && pacman1.posicion.fila > salidaFila && movimientos == 0){
            if(tablero.tablero[fila-1][col].caracterCelda() == '*'){
                if(tablero.tablero[fila][col-1].caracterCelda()=='X' || tablero.tablero[fila][col-1].caracterCelda()=='*'){
                    nuevaCol = col + 1;
                    movimientos = movimientos + 1;
                } else if(tablero.tablero[fila][col+1].caracterCelda()=='X' || tablero.tablero[fila][col+1].caracterCelda()=='*'){
                    nuevaCol = col - 1;
                    movimientos = movimientos + 1;
                }
            } else{
                nuevaFila = fila - 1;
                movimientos = movimientos + 1;
            }      
        }
        if(validarCasilla(nuevaFila,nuevaCol)){
            Celda anterior = tablero.tablero[fila][col];
            Celda nueva = tablero.tablero[nuevaFila][nuevaCol];
            anterior.caracter = pacman;
            nueva.caracter = pacman;
            pacman.posicion = new Posicion(nuevaFila,nuevaCol);
            if(nueva.esSalida == true){
                salida = true;
            }   
        } 
        return salida;
    }     
}
