package campus.randolh.taller_m_to_m.repository;

import campus.randolh.taller_m_to_m.entity.Torneo;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

// Repositorio para la entidad Torneo
@Repository
public interface TorneoRepository extends JpaRepository<Torneo, Long> {

    // Consulta un torneo cargando sus participaciones y jugadores asociados
    @Query("SELECT t FROM Torneo t LEFT JOIN FETCH t.participaciones p LEFT JOIN FETCH p.jugador WHERE t.id = :id")
    Optional<Torneo> findByIdWithParticipaciones(@Param("id") Long id);
}
