
public class Nodo { //Mondragon Tellez Leslye Liliana 25/05/2025
    // Atributos accesibles para la clase ArbolAVL
    public int info;
    public int altura;
    public Nodo nodoIzquierdo;
    public Nodo nodoDerecho;

    // Constructor vacio (por si acaso)
    public Nodo() {
        this.info = 0;
        this.altura = 1;
        this.nodoIzquierdo = null;
        this.nodoDerecho = null;
    }

    // Constructor principal
    public Nodo(int infoNuevoNodo) {
        this.info = infoNuevoNodo;
        this.altura = 1;
        this.nodoIzquierdo = null;
        this.nodoDerecho = null;
    }

    @Override
    public String toString() {
        return "\nInfo: " + info +
               "\nNodoIzquierdo: " + (nodoIzquierdo != null ? nodoIzquierdo.info : "null") +
               "\nNodoDerecho: " + (nodoDerecho != null ? nodoDerecho.info : "null");
    }
}