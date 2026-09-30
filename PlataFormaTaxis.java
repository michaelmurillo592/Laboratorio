import java.util.Queue;
import java.util.LinkedList;
import java.util.HashMap;

public class PlataFormaTaxis {

    // Cola para mantener el orden de las solicitudes
    static Queue<Integer> cola = new LinkedList<>();

    // HashMap para guardar las solicitudes
    static HashMap<Integer, Solicitud> solicitudes = new HashMap<>();

    public static void main(String[] args) {

        // Registrar solicitudes
        registrarSolicitud(1, "Carlos", "Centro");
        registrarSolicitud(2, "Ana", "Universidad");
        registrarSolicitud(3, "Pedro", "Aeropuerto");

        // Mostrar solicitudes pendientes
        mostrarSolicitudes();

        // Atender la solicitud más antigua
        atenderSolicitud();

        // Cancelar una solicitud
        cancelarSolicitud(2);

        // Mostrar nuevamente
        mostrarSolicitudes();
    }

    // Registrar solicitud
    public static void registrarSolicitud(int id, String usuario, String destino) {

        Solicitud solicitud = new Solicitud(id, usuario, destino);

        // Guardamos la solicitud en el HashMap
        solicitudes.put(id, solicitud);

        // Guardamos el ID en la cola
        cola.add(id);

        System.out.println("Solicitud registrada: " + usuario);
    }

    // Atender la solicitud más antigua
    public static void atenderSolicitud() {

        if (cola.isEmpty()) {
            System.out.println("No hay solicitudes pendientes.");
            return;
        }

        // Sacamos el primer ID de la cola
        int id = cola.poll();

        // Buscamos la solicitud en el HashMap
        Solicitud solicitud = solicitudes.remove(id);

        System.out.println("\nAtendiendo solicitud:");
        System.out.println("ID: " + solicitud.id);
        System.out.println("Usuario: " + solicitud.usuario);
        System.out.println("Destino: " + solicitud.destino);
    }

    // Cancelar una solicitud específica
    public static void cancelarSolicitud(int id) {

        if (solicitudes.containsKey(id)) {

            // Eliminamos del HashMap
            solicitudes.remove(id);

            // Eliminamos de la cola
            cola.remove(id);

            System.out.println("\nSolicitud " + id + " cancelada.");

        } else {
            System.out.println("\nNo existe la solicitud " + id);
        }
    }

    // Mostrar solicitudes pendientes
    public static void mostrarSolicitudes() {

        System.out.println("\n--- Solicitudes pendientes ---");

        if (cola.isEmpty()) {
            System.out.println("No hay solicitudes pendientes.");
            return;
        }

        for (int id : cola) {

            Solicitud solicitud = solicitudes.get(id);

            System.out.println(
                "ID: " + solicitud.id +
                " | Usuario: " + solicitud.usuario +
                " | Destino: " + solicitud.destino
            );
        }
    }
}


// Clase Solicitud
class Solicitud {

    int id;
    String usuario;
    String destino;

    public Solicitud(int id, String usuario, String destino) {
        this.id = id;
        this.usuario = usuario;
        this.destino = destino;
    }
}