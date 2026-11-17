package campus.randolh.taller_m_to_m.repository;

import campus.randolh.taller_m_to_m.entity.Participacion;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

// Repositorio para la entidad intermedia Participacion
@Repository
public interface ParticipacionRepository extends JpaRepository<Participacion, Long> {

    // Lista participaciones por id de jugador
    List<Participacion> findByJugadorId(Long jugadorId);

    // Lista participaciones por id de torneo
    List<Participacion> findByTorneoId(Long torneoId);

    // Consulta una participacion con jugador y torneo cargados
    @Query("SELECT p FROM Participacion p JOIN FETCH p.jugador JOIN FETCH p.torneo WHERE p.id = :id")
    Optional<Participacion> findByIdWithDetalles(@Param("id") Long id);

    // Consulta todas las participaciones con jugador y torneo cargados
    @Query("SELECT p FROM Participacion p JOIN FETCH p.jugador JOIN FETCH p.torneo")
    List<Participacion> findAllWithDetalles();
}
