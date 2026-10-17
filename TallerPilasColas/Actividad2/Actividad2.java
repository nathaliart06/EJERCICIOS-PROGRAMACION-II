//Actividad 2. Matrices de objetos: Inventario de productos
//Una empresa requiere organizar sus productos en una matriz de 3 × 4, donde cada posición representa una ubicación de almacenamiento.
//Cada producto debe tener código, nombre, precio y cantidad disponible.
//Requerimientos:
//•	Registrar productos en posiciones específicas.
//•	Mostrar los productos indicando fila y columna.
//•	Buscar un producto por código.
//•	Calcular el valor total del inventario.
//•	Identificar el producto con mayor cantidad disponible y contar las posiciones vacías.


public class Actividad2 {
    public static void main(String[] args) {
        // Matriz de objetos 3 x 4 (cada posicion es una ubicacion del almacen)
        Producto[][] inventario = new Producto[3][4];

        System.out.println("----------------- INVENTARIO VACIO ----------------");
        System.out.println("Posiciones vacias: " + Producto.contarVacias(inventario));
        System.out.println("Valor total: " + Producto.valorTotal(inventario));

        System.out.println("----------------- REGISTRO DE PRODUCTOS ----------------");
        Producto.registrar(inventario, 0, 0, new Producto(1, "Teclado", 50000, 10));
        Producto.registrar(inventario, 0, 2, new Producto(2, "Mouse", 25000, 30));
        Producto.registrar(inventario, 1, 1, new Producto(3, "Monitor", 600000, 5));
        Producto.registrar(inventario, 2, 3, new Producto(4, "Audifonos", 80000, 15));
        Producto.registrar(inventario, 2, 0, new Producto(5, "Portatil", 2500000, 3));
        // Casos invalidos
        Producto.registrar(inventario, 1, 1, new Producto(6, "Camara", 150000, 8)); // ocupada
        Producto.registrar(inventario, 5, 7, new Producto(7, "Parlante", 90000, 4)); // fuera de rango

        System.out.println("----------------- MOSTRAR INVENTARIO ----------------");
        Producto.mostrar(inventario);

        System.out.println("----------------- BUSCAR POR CODIGO ----------------");
        Producto p = Producto.buscarPorCodigo(inventario, 3);
        System.out.println("Buscar 3: " + (p != null ? p : "No existe"));
        p = Producto.buscarPorCodigo(inventario, 99);
        System.out.println("Buscar 99: " + (p != null ? p : "No existe"));

        System.out.println("----------------- CALCULOS ----------------");
        System.out.println("Valor total del inventario: " + Producto.valorTotal(inventario));
        System.out.println("Producto con mayor cantidad: " + Producto.mayorCantidad(inventario));
        System.out.println("Posiciones vacias: " + Producto.contarVacias(inventario));
    }
}