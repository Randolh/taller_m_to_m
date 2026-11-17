package campus.randolh.taller_m_to_m.repository;

import campus.randolh.taller_m_to_m.entity.Jugador;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

// Repositorio para la entidad Jugador
@Repository
public interface JugadorRepository extends JpaRepository<Jugador, Long> {

    // Consulta un jugador cargando sus participaciones y torneos asociados
    @Query("SELECT j FROM Jugador j LEFT JOIN FETCH j.participaciones p LEFT JOIN FETCH p.torneo WHERE j.id = :id")
    Optional<Jugador> findByIdWithParticipaciones(@Param("id") Long id);

    Optional<Jugador> findByNickname(String nickname);
}
