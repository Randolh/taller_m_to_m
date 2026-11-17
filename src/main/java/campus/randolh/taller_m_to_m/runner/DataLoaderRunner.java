package campus.randolh.taller_m_to_m.runner;

import campus.randolh.taller_m_to_m.entity.Jugador;
import campus.randolh.taller_m_to_m.entity.Participacion;
import campus.randolh.taller_m_to_m.entity.Torneo;
import campus.randolh.taller_m_to_m.repository.JugadorRepository;
import campus.randolh.taller_m_to_m.repository.ParticipacionRepository;
import campus.randolh.taller_m_to_m.repository.TorneoRepository;
import jakarta.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DataLoaderRunner implements CommandLineRunner {

    private final JugadorRepository jugadorRepository;
    private final TorneoRepository torneoRepository;
    private final ParticipacionRepository participacionRepository;
    private final EntityManager entityManager;

    public DataLoaderRunner(JugadorRepository jugadorRepository,
                            TorneoRepository torneoRepository,
                            ParticipacionRepository participacionRepository,
                            EntityManager entityManager) {
        this.jugadorRepository = jugadorRepository;
        this.torneoRepository = torneoRepository;
        this.participacionRepository = participacionRepository;
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        // Limpiar datos anteriores
        participacionRepository.deleteAll();
        jugadorRepository.deleteAll();
        torneoRepository.deleteAll();
        entityManager.flush();

        // Jugadores
        Jugador jugador1 = new Jugador("Faker", "Lee Sang-hyeok", "Corea del Sur");
        Jugador jugador2 = new Jugador("s1mple", "Oleksandr Kostyliev", "Ucrania");
        Jugador jugador3 = new Jugador("TenZ", "Tyson Ngo", "Canada");

        jugadorRepository.saveAll(List.of(jugador1, jugador2, jugador3));

        // Torneos
        Torneo torneo1 = new Torneo("Worlds 2026", "League of Legends", 2225000.0);
        Torneo torneo2 = new Torneo("Major Masters", "Counter-Strike 2", 1250000.0);
        Torneo torneo3 = new Torneo("Champions Tour", "Valorant", 1000000.0);

        torneoRepository.saveAll(List.of(torneo1, torneo2, torneo3));

        System.out.println("Jugadores y torneos guardados.");

        // Asignar jugadores a torneos
        Participacion part1 = new Participacion(jugador1, torneo1, LocalDate.of(2026, 3, 15), 1, 3500);
        Participacion part2 = new Participacion(jugador1, torneo3, LocalDate.of(2026, 4, 10), 4, 1800);

        Participacion part3 = new Participacion(jugador2, torneo2, LocalDate.of(2026, 2, 20), 1, 4200);
        Participacion part4 = new Participacion(jugador2, torneo1, LocalDate.of(2026, 3, 18), 7, 1200);

        Participacion part5 = new Participacion(jugador3, torneo3, LocalDate.of(2026, 4, 12), 2, 3100);
        Participacion part6 = new Participacion(jugador3, torneo2, LocalDate.of(2026, 2, 22), 3, 2600);

        participacionRepository.saveAll(List.of(part1, part2, part3, part4, part5, part6));

        // Limpiar para consultar directo desde la BD
        entityManager.flush();
        entityManager.clear();

        System.out.println("Participaciones guardadas.");

        // Buscar un jugador con sus participaciones
        Jugador jugadorConsultado = jugadorRepository.findByIdWithParticipaciones(jugador1.getId())
                .orElseThrow(() -> new RuntimeException("Jugador no encontrado"));

        System.out.println("\nJugador: " + jugadorConsultado.getNickname() + " (" + jugadorConsultado.getNombre() + ")");
        System.out.println("Torneos en los que participa (" + jugadorConsultado.getParticipaciones().size() + "):");
        for (Participacion p : jugadorConsultado.getParticipaciones()) {
            System.out.println(" - Torneo: " + p.getTorneo().getNombre()
                    + " | Videojuego: " + p.getTorneo().getVideojuego()
                    + " | Posicion final: " + p.getPosicionFinal()
                    + " | Puntaje: " + p.getPuntaje()
                    + " | Fecha: " + p.getFechaInscripcion());
        }

        // Buscar un torneo con sus participaciones
        Torneo torneoConsultado = torneoRepository.findByIdWithParticipaciones(torneo1.getId())
                .orElseThrow(() -> new RuntimeException("Torneo no encontrado"));

        System.out.println("\nTorneo: " + torneoConsultado.getNombre() + " (" + torneoConsultado.getVideojuego() + ")");
        System.out.println("Premio acumulado: $" + torneoConsultado.getPremio());
        System.out.println("Jugadores participantes (" + torneoConsultado.getParticipaciones().size() + "):");
        for (Participacion p : torneoConsultado.getParticipaciones()) {
            System.out.println(" - Jugador: " + p.getJugador().getNickname()
                    + " | Pais: " + p.getJugador().getPais()
                    + " | Posicion final: " + p.getPosicionFinal()
                    + " | Puntaje: " + p.getPuntaje());
        }

        // Listar todas las participaciones
        System.out.println("\nTodas las participaciones:");
        List<Participacion> todasParticipaciones = participacionRepository.findAllWithDetalles();
        for (Participacion p : todasParticipaciones) {
            System.out.println("Participacion #" + p.getId()
                    + " -> Jugador: " + p.getJugador().getNickname()
                    + " | Torneo: " + p.getTorneo().getNombre()
                    + " | Juego: " + p.getTorneo().getVideojuego()
                    + " | Puntaje: " + p.getPuntaje()
                    + " | Posicion: " + p.getPosicionFinal());
        }
    }
}
