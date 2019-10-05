package com.example.tetris;

import android.graphics.Color;
import android.graphics.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class Tablero{

    private final int alturaTablero =20;
    private final int anchuraTablero=10;
    public int tab[][]=new int[alturaTablero][anchuraTablero];
    private final Random random = new Random();
    public ArrayList<Piezas> listaPiezas = new ArrayList<Piezas>();
    private  final int numeroPiezas = 7;



    //genera dos Piezas random (1.actual, 2.siguiente)

    public Tablero() {
        this.limpiarTablero();
        generarPieza();
        generarPieza();
    }
    public void  generarPieza (){
        listaPiezas.add(new Piezas(random.nextInt(numeroPiezas)+1));
    }

    public void borrarPieza(){
        listaPiezas.remove(0);
    }
     //transforma numeros de matriz a color

    public int parseaColor(int x, int y) {
        if(tab[x][y]==0)  Color.parseColor("#00FFFF"); ; // I piece Cyan
        if(tab[x][y]==1)  Color.parseColor("#0000FF"); ; // J piece Blue
        if(tab[x][y]==2)  Color.parseColor("#FF0000"); ; // Z piece Red
        if(tab[x][y]==3)  Color.parseColor("#ffbf00"); ; // L piece Orange
        if(tab[x][y]==4)  Color.parseColor("#00FF00"); ; // S Green
        if(tab[x][y]==5)  Color.parseColor("#FFFF00"); ; // cuadrado yellow
        if(tab[x][y]==6)  Color.parseColor("#572364"); ; //  T piece Morado
        if(tab[x][y]==7)  Color.parseColor("#BEBEBE");  // gris fondo
        return -1;
    }
    public void bajarFila (Tablero T, int y){
        int x;
        int filaVacio=0;
        boolean vacio = false;
        while ((y != 20)&&(!vacio)){
            for (x=0;x<10;x++) {
                T.tab[x][y] = T.tab [x][y+1];
                if (T.tab[x][y+1] == 7){
                    filaVacio ++;
                }
            }
            y++;
            if(filaVacio == 10){
                vacio = true;
            }
            filaVacio =0;
        }
    }

    //todas las posiciones a 7
    public void limpiarTablero() {
        for(int i=0; i<alturaTablero; i++) {
            for(int j=0; j<anchuraTablero; j++) {
                tab[i][j]= 7;
            }
        }
    }

    public  ArrayList<Piezas> getListaPiezas(){
        return listaPiezas;
    }

    //coge la Piezas actual
    public Piezas getPieza()  {
        return listaPiezas.get(listaPiezas.size() - 2);
    }

    //coge siguiente Piezas
    public Piezas getSiguientePieza() {
        return listaPiezas.get(listaPiezas.size()-1);
    }

    //crear en clase Piezas atributo entero colorCode

    private void colocaPieza(Piezas piezasActual) {
        //tablero[piezasActual.x1][piezasActual.y1] = piezasActual.identificador;
        //tablero[piezasActual.x2][piezasActual.y2] = piezasActual.identificador;
        //tablero[piezasActual.x3][piezasActual.y3] = piezasActual.identificador;
        //tablero[piezasActual.x4][piezasActual.y4] = piezasActual.identificador;
        piezasActual.coor.setValX(4);
        piezasActual.coor.setValY(20);

    }
    /*este metodo mueve la pieza despues de comprobarlo abajo,izquierda o derecha segun el char
    que le pasen, para ello usamos comprueba y el tipo de movimiento y en comprueba hay que
    comprobar que la PIEZA  no se salga del tablero y que no choque con otras.
    Puede salirse la matriz auxiiar de rangos pero nunca se puede salir la pieza
     */
    private void moverPiezas(Piezas piezasActual, char x ){
        switch (x){
            case 'i':
                if (compruebaIzq(piezasActual)){
                    piezasActual.coor.setValX(piezasActual.coor.getValX()-1);
                }
                break;
            case 'd':
                if (compruebaDcha(piezasActual)){
                    piezasActual.coor.setValX(piezasActual.coor.getValX()+1);
                }
                break;
            case 'a':
                if (compruebaAbajo(piezasActual)){
                    piezasActual.coor.setValY(piezasActual.coor.getValY()-1);
                }
                break;
        }

    }


    //comprueba movimiento a la izq
    private  boolean compruebaIzq(Piezas piezasActual) {
        ArrayList<Coordenada> l;
        l = piezasActual.ObtenerPosiciones(piezasActual);
        boolean problema = false;
        int posyahellegado=0;
        while (problema==false)  {
            if (posyahellegado==4) {
                return true;
            }
            else{
                Coordenada aux = l.get(posyahellegado);
                if((aux.getValX()==0)||(this.tab[aux.getValX()-1][aux.getValY()]!=7)){
                    return false;
                }
                else{
                    posyahellegado++;
                }
            }
        }return true;
    }

    //comprueba movimiento a la dcha
    private  boolean compruebaDcha(Piezas piezasActual) {
        ArrayList<Coordenada> l;
        l = piezasActual.ObtenerPosiciones(piezasActual);
        boolean problema = false;
        int posyahellegado=0;
        while (problema==false)  {
            if (posyahellegado==4) {
                return true;
            }
            else{
                Coordenada aux = l.get(posyahellegado);
                if((aux.getValX()==10)||(this.tab[aux.getValX()+1][aux.getValY()]!=7)){
                    return false;
                }
                else{
                    posyahellegado++;
                }
            }
        }return true;
    }

    //comprueba movimiento hacia abajo
    public  boolean compruebaAbajo(Piezas piezasActual) {
        ArrayList<Coordenada> l;
        l = piezasActual.ObtenerPosiciones(piezasActual);
        boolean problema = false;
        int posyahellegado=0;
        while (problema==false)  {
            if (posyahellegado==4) {
                return true;
            }
            else{
                Coordenada aux = l.get(posyahellegado);
                if((aux.getValY()==0)||(this.tab[aux.getValX()][aux.getValY()-1]!=7)){
                    return false;
                }
                else{
                    posyahellegado++;
                }
            }
        }return true;
    }

    /*
    public boolean compruebaFinJuego(Piezas spielStein) {
        if(compruebaAbajo(spielStein) == false && spielStein.getMinXCoordinate(
                spielStein.x1, spielStein.x2, spielStein.x3, spielStein.x4)<=1) {
            return true;
        }
        return false;
    }
    */
    public  int getAlturaTablero() {
        return this.alturaTablero;
    }

    public int getAnchoTablero() {
        return this.anchuraTablero;
    }
}