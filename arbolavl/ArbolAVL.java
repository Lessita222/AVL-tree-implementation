import java.util.Scanner; // Mondragon Tellez Leslye Liliana 25/05/2026
public class ArbolAVL {
    private Nodo raiz; //El nodo que va a ir hasta arriboa del arbol avl, se declara como private para que no se pueda modificar

    // Metodo principal para crear el arbol desde la consola
    public void crearÁrbol() {
        Scanner teclado = new Scanner(System.in); //guarda lo que escriba el usuario
        
        System.out.print("Ingrese la raiz del a+rbol: ");
        int infoDeLaRaiz = teclado.nextInt();
        raiz = new Nodo(infoDeLaRaiz); //pide al usuario ingresar la raiz del arbol, forzosamente esto es lo primero que se debe realizar 
        
        int continuar = 1; //esta variable va a servir para revisar si el usuario que agregar mas nodos o no
        while (continuar == 1) { // se inicializo en uno para qeu haga este while
            System.out.print("¿Desea ingresar mas nodos? (1 = Si, 0 = No): ");
            continuar = teclado.nextInt(); //aca continuar se va a actualizar para que se sepa si el usuario si quiso o no agregar un nodo
            
            if (continuar == 1) { //significa que el usuario si quiso agregar un nuevo nodo
                System.out.print("Ingrese la info del nuevo nodo: "); 
                int infoNuevoNodo = teclado.nextInt(); //se agrega la informacion del nuevvo nodo
                insertarNodo(infoNuevoNodo); //llama a la funcion de insertar nodo
            }
        }
    }
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    public void insertarNodo(int info) {
        raiz = insertar(raiz, info);
    } //Este metodo pide el numero y se utiliza desde el menu para mayor "seguridad" con el dato

    private Nodo insertar(Nodo nodoActual, int info) { //ya conociendo el valor se usa en este metodo, el cual es el que ya viaja por el arbol
        if (nodoActual == null) { //este es el caso base, cuando se llega a una posicion vacia despues de recorrer el arbol se agrega ahi el nuevo nodo
            return new Nodo(info);
        }
//Si el valor a insertar es menor se avanza recursivamente por el subarbol izquierdo
        if (info < nodoActual.info) { 
            nodoActual.nodoIzquierdo = insertar(nodoActual.nodoIzquierdo, info);
        } else if (info > nodoActual.info) { // Si el valor es mayor, se avanza recursivamente por el subarbol derecho
            nodoActual.nodoDerecho = insertar(nodoActual.nodoDerecho, info);
        } else { //Este caso es por si el nodo ya existe, en los arboles avl no se insertan elementos duplicados
            System.out.println("--> [Aviso]: El elemento " + info + " ya existe. No se permiten duplicados.");
            return nodoActual;
        }
//Actualiza la altura del nodo actual basandose en la altura de su hijo mas alto + 1
         nodoActual.altura = 1 + Math.max(altura(nodoActual.nodoIzquierdo), altura(nodoActual.nodoDerecho));
        return balancear(nodoActual); //llama al metodo que se encarga de balancear el arbol en caso de que se necesite.
    }
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    public void eliminarNodo(int info) {
        raiz = eliminar(raiz, info);
    }
// Si el arbol esta vacio o el elemento no se encuentra, no hay nada que borrar
    private Nodo eliminar(Nodo nodoActual, int info) {
        if (nodoActual == null) {
            return nodoActual;
        }
//si el valor es menor que el valor del nodo en el que estamos, se recorre el subarbol izquierdo hasta encontrarlo
        if (info < nodoActual.info) {
            nodoActual.nodoIzquierdo = eliminar(nodoActual.nodoIzquierdo, info);
        } else if (info > nodoActual.info) { //Si el valor es mayor se busca por el subarbol derecho
            nodoActual.nodoDerecho = eliminar(nodoActual.nodoDerecho, info);
        } else { //estas instrucciones son para cuando ya se encuentra el valor que se desea eliminar   
            if (nodoActual.nodoIzquierdo == null || nodoActual.nodoDerecho == null) {
                Nodo temporal = null;
                if (nodoActual.nodoIzquierdo != null) { // Identifica cual es el unico hijo existente en caso de que lo haya
                    temporal = nodoActual.nodoIzquierdo;
                } else { //se asigna a temporal ya sea el nodo izquierdo o el nodo derecho, depende de cual sea el uncico nodo
                    temporal = nodoActual.nodoDerecho;
                }

                if (temporal == null) {
                    nodoActual = null; //si no habia ni hijo izquiero ni derecho significa que era una hoja y se elimina directamente
                } else { 
                    nodoActual = temporal; //y si tenia un unico hijo este valor se intercambia por el valor del hijo
                }
            } else { //otro caso es que el nodo tenga ambos hijos  
                Nodo temporal = getNodoConMenorValor(nodoActual.nodoDerecho); //llama al metodo que busca al hijo de menor valor
                nodoActual.info = temporal.info; //intercambia la informacion
                nodoActual.nodoDerecho = eliminar(nodoActual.nodoDerecho, temporal.info);  //
            }
        }

        if (nodoActual == null) {
            return nodoActual;
        }// Si el arbol quedo completamente vacio tras la eliminacion, finaliza retornando null

        nodoActual.altura = 1 + Math.max(altura(nodoActual.nodoIzquierdo), altura(nodoActual.nodoDerecho)); //vuelve a calcular la altura del arbol
        return balancear(nodoActual); //vuelve a balancear el arbol
    }
//--------------------------------------------------------------------------------------------------------------------------------------------------------------
    private Nodo getNodoConMenorValor(Nodo nodo) { 
        Nodo actual = nodo; //crea un nodo que se va a encargar de busacar el valor 
        while (actual.nodoIzquierdo != null) { //se va al subarbl izquierdo porque des ese lado estan los valores menores 
        // Bucle que se desplaza por el nodo izquierdo 
        // Se detiene cuando el nodo actual ya no tiene un hijo a su izquierda
            actual = actual.nodoIzquierdo;// Avanza al siguiente nivel izquierdo
        }
        return actual; // Retorna el nodo mas profundo a la izquierda, que es el menor de todos
    }
//--------------------------------------------------------------------------------------------------------------------------------------------------------------
   public void buscarNodoDentroDelÁrbol(Nodo nodoActual, int infoABuscar) {
        // El primer caso es por si no hay ningun elemento en el arbol, si es asi logicamente no se va a encontrar la clave que se desea busacr 
        if (nodoActual == null) {
            System.out.println("--> [Resultado]: El elemento " + infoABuscar + " NO se encuentra en el arbol.");
        } 
        // caso base que se enncuentre la clave  
        else if (infoABuscar == nodoActual.info) {
            System.out.println("--> [Resultado]: El elemento " + infoABuscar + " fue encontrado con exito!");
        } 
        //Si la valor a buscar es menor se va a hacai el subarbol izquierdo y lo recorre hasta encontrar el valor 
        else if (infoABuscar < nodoActual.info) {
            buscarNodoDentroDelÁrbol(nodoActual.nodoIzquierdo, infoABuscar);
        } 
        //Si el valor es mayor, se descarta el lado izquierdo y se busca a la derecha
        else {
            buscarNodoDentroDelÁrbol(nodoActual.nodoDerecho, infoABuscar);
        }
    }
//-----------------------------------------------------------------------------------------------------------------------------------------------------------
    private Nodo balancear(Nodo nodo) { //este es el metodo mas importante porque es el que se encarga de hacer que arbol sea un arbol avl 
        int fe = getBalance(nodo); //calcula el factor balance debe de dar 1 o -1 para quue el arbol se considere balanceado
// Desbalance Izquierda-Izquierda
        // El arbol esta cargado a la izquierda y el subarbol izquierdo no est inclinado a la derecha.
        if (fe > 1 && getBalance(nodo.nodoIzquierdo) >= 0) {
            return rotacionDerecha(nodo);
        }
        // Desbalance Izquierda-Derecha 
        // El arbol esta cargado a la izquierda, pero su hijo izquierdo esta inclinado a la derecha (Zig-Zag).
        if (fe > 1 && getBalance(nodo.nodoIzquierdo) < 0) {
            // Primero se alinea el hijo izquierdo rotandolo a la izquierda
            nodo.nodoIzquierdo = rotacionIzquierda(nodo.nodoIzquierdo);
            // Luego se soluciona el desbalance principal rotando a la derecha 
            return rotacionDerecha(nodo);
        }// Desvalance Derecha-Derecha 
        // El arbol esta cargado a la derecha y el subarbol derecho no esta inclinado a la izquierda.
        if (fe < -1 && getBalance(nodo.nodoDerecho) <= 0) {
            return rotacionIzquierda(nodo);
        }//Desbalance Derecha-Izquierda 
        // El arbol esta cargado a la derecha, pero su hijo derecho esta inclinado a la izquierda (Zig-Zag).
        if (fe < -1 && getBalance(nodo.nodoDerecho) > 0) {
            // Primero se alinea el hijo derecho rotándolo a la derecha
            nodo.nodoDerecho = rotacionDerecha(nodo.nodoDerecho);
            // Luego se soluciona el desbalance principal rotando a la izquierda
            return rotacionIzquierda(nodo);
        }// Si el nodo ya se encuentra balanceado (FE entre -1 y 1), se devuelve sin modificaciones
        return nodo;
    }
//-------------------------------------------------------------------------------------------------------------------------------------------------------
    private Nodo rotacionDerecha(Nodo raizActual) {
        //Esta parte se encarga de espaldar las referencias de los nodos involucrados
        Nodo nuevaRaiz = raizActual.nodoIzquierdo;         // El hijo izquierdo subira a ser el nuevo jefe
        Nodo subArbolIntermedio = nuevaRaiz.nodoDerecho;   // Se guarda el subarbol derecho del hijo para no perderlo

        // Reasociar 
        nuevaRaiz.nodoDerecho = raizActual;                // La antigua raiz pasa a ser el hijo derecho de la nueva raiz
        raizActual.nodoIzquierdo = subArbolIntermedio;     // El subarbol respaldado se asigna a la izquierda de la antigua raiz

        //Se vuelve a calcular la altura de los subarboles 
        raizActual.altura = 1 + Math.max(altura(raizActual.nodoIzquierdo), altura(raizActual.nodoDerecho));
        nuevaRaiz.altura = 1 + Math.max(altura(nuevaRaiz.nodoIzquierdo), altura(nuevaRaiz.nodoDerecho));

        // Retorna la nueva raiz de este subarbol modificado 
        return nuevaRaiz;
    }
//--------------------------------------------------------------------------------------------------------------------------------------------------------
   private Nodo rotacionIzquierda(Nodo raizActual) {
        // FASE 1: Respaldar las referencias de los nodos involucrados (Efecto Espejo)
        Nodo nuevaRaiz = raizActual.nodoDerecho;           // El hijo derecho subira a ser el nuevo jefe
        Nodo subArbolIntermedio = nuevaRaiz.nodoIzquierdo; // Se guarda el subarbol izquierdo del hijo para protegerlo

        // FASE 2: Reasociar los punteros (Efectuar la rotacion fisica)
        nuevaRaiz.nodoIzquierdo = raizActual;              // La antigua raiz pasa a ser el hijo izquierdo de la nueva raiz
        raizActual.nodoDerecho = subArbolIntermedio;       // El subarbol respaldado se asigna a la derecha de la antigua raiz

        // FASE 3: Recalcular alturas (Primero el nodo que bajo, luego el que subio)
        raizActual.altura = 1 + Math.max(altura(raizActual.nodoIzquierdo), altura(raizActual.nodoDerecho));
        nuevaRaiz.altura = 1 + Math.max(altura(nuevaRaiz.nodoIzquierdo), altura(nuevaRaiz.nodoDerecho));

        // Retorna la nueva raiz de este subarbol modificado para reconstruir los enlaces
        return nuevaRaiz;
    }
//------------------------------------------------------------------------------------------------------------------------------------------------------------
    private int altura(Nodo nodo) {
        if (nodo == null) { //si no existe ningun nodo, la altura es cero
            return 0;
        }// Si el nodo existe, retorna el valor almacenado en su atributo de altura
        return nodo.altura;
        
    }
//--------------------------------------------------------------------------------------------------------------------------------------------------------
    private int getBalance(Nodo nodo) {
        if (nodo == null) {// Si el nodo es nulo, no tiene subarboles y su balance es neutro (0)
            return 0;
        }
        return altura(nodo.nodoIzquierdo) - altura(nodo.nodoDerecho);// Aplica la formula del AVL: Altura Izquierda - Altura Derecha
    }
//-------------------------------------------------------------------------------------------------------------------------------------------------
    public Nodo getRaiz() {
        return raiz;
    }// Inicializa el acumulador de texto

    @Override
    public String toString() {// Manda a construir el dibujo empezando desde la raiz en el nivel 0
        StringBuilder arbolStr = new StringBuilder();// Devuelve todo el dibujo acumulado convertido en texto final
        imprimirÁrbolAux(raiz, 0, arbolStr);
        return arbolStr.toString();
    }
//--------------------------------------------------------------------------------------------------------------------------------------------------------
    private void imprimirÁrbolAux(Nodo nodo, int nivel, StringBuilder arbolStr) {
        // CASO BASE: Si el nodo es null, simplemente no hace nada y regresa
        if (nodo != null) {
            // Recorre primero el subarbol derecho 
            imprimirÁrbolAux(nodo.nodoDerecho, nivel + 1, arbolStr);
            
            
            for (int i = 0; i < nivel; i++) {
                arbolStr.append("    "); 
            } 
            
            // 3. Agregar el valor del nodo actual 
            arbolStr.append(nodo.info).append("\n");
            
            // 4. Recorrer finalmente el subarbol izquierdo 
            imprimirÁrbolAux(nodo.nodoIzquierdo, nivel + 1, arbolStr);
        }
    }
    }