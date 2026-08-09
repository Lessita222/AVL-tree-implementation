import java.util.Scanner; //Mondragon Tellez Leslye Liliana 25/05/2026

public class Main {
    public static void main(String[] args) {
        ArbolAVL arbolAVL = new ArbolAVL(); //se inicializa el arbol
        Scanner teclado = new Scanner(System.in); //Se inicializa teclado para que pueda leer lo que necesita el usuario
        int opcion; //para leer la opcion que indique el usuario 
        
        do { //Menu de usruario 
            // Se utiliza un ciclo do-while para que el menu se muestre al menos una vez,
        // y se repita continuamente hasta que el usuario decida salir opcion= 0.
            System.out.println("\n--- MENU OPERACIONES ARBOLES AVL ---");
            System.out.println("1) CREACION DEL ARBOL AVL");
            System.out.println("2) BUSCAR DENTRO DEL ARBOL");
            System.out.println("3) INSERTAR EN EL ARBOL");
            System.out.println("4) ELIMINAR ALGUN ELEMENTO");
            System.out.println("5) VER ARBOL AVL");
            System.out.println("0) SALIR");
            System.out.print("Elija una opcion: ");
            
            opcion = teclado.nextInt();
            System.out.println(); 
                                    
            switch (opcion) {
                case 1:
                    // Llama al metodo para inicializar el arbol 
                    arbolAVL.crearÁrbol();
                    // Muestra el estado del arbol. Al concatenar 'arbolAVL' con un String, 
                    // Java llama automaticamente al metodo toString
                    System.out.println("El arbol AVL ha quedado de la siguiente manera:\n" + arbolAVL);
                    break;
                case 2:
                    // Solicita al usuario el numero entero que desea buscar.
                    System.out.print("Ingrese la info que se desea buscar: ");
                    int infoABuscar = teclado.nextInt(); //en teclado se guarda el nodo que se desea buscar
                    arbolAVL.buscarNodoDentroDelÁrbol(arbolAVL.getRaiz(), infoABuscar);//utiliza get raiz pq necesita comenzar desde el inicio del arbol
                    break;
                case 3:
                    System.out.print("Ingrese la info del nuevo nodo: ");
                    // Solicita el valor del nuevo nodo que se va a agregar.
                    int infoNuevoNodo = teclado.nextInt();
                    arbolAVL.insertarNodo(infoNuevoNodo); //llama al metodo que inserta el noso y este metodo ademnas balancea el arbol 
                    System.out.println("El arbol AVL ha quedado de la siguiente manera:\n" + arbolAVL);
                    break;
                case 4:
                    System.out.print("Ingrese la info que se desea eliminar: ");
                    int infoAEliminar = teclado.nextInt();// Solicita el valor del elemento que se quiere remover de la estructura.
                    arbolAVL.eliminarNodo(infoAEliminar);//llama al metodo de eliminar 
                    System.out.println("El arbol AVL ha quedado de la siguiente manera:\n" + arbolAVL);
                    break;
                case 5:
                    System.out.println("El arbol AVL es el siguiente:\n" + arbolAVL);
                    break;//imprime el arbol
                case 0:
                    System.out.println("FIN DEL PROGRAMA");
                    break;
                default:
                    System.out.println("ERROR. Elija una opcion valida.");
                    break;
            }
        } while (opcion != 0);
        
        teclado.close();
    }
}