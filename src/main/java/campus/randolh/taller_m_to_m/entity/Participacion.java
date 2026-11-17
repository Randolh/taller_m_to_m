package campus.randolh.taller_m_to_m.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;

// Entidad intermedia que representa la participacion de un jugador en un torneo
@Entity
@Table(name = "participaciones")
public class Participacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_participacion")
    private Long id;

    // Relacion muchos a uno hacia la entidad principal Jugador
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_jugador", nullable = false)
    private Jugador jugador;

    // Relacion muchos a uno hacia la entidad principal Torneo
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_torneo", nullable = false)
    private Torneo torneo;

    // Atributos propios de la entidad intermedia
    @Column(name = "fecha_inscripcion", nullable = false)
    private LocalDate fechaInscripcion;

    @Column(name = "posicion_final")
    private Integer posicionFinal;

    @Column(name = "puntaje")
    private Integer puntaje;

    public Participacion() {
    }

    public Participacion(Jugador jugador, Torneo torneo, LocalDate fechaInscripcion, Integer posicionFinal, Integer puntaje) {
        this.jugador = jugador;
        this.torneo = torneo;
        this.fechaInscripcion = fechaInscripcion;
        this.posicionFinal = posicionFinal;
        this.puntaje = puntaje;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public Torneo getTorneo() {
        return torneo;
    }

    public void setTorneo(Torneo torneo) {
        this.torneo = torneo;
    }

    public LocalDate getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(LocalDate fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    public Integer getPosicionFinal() {
        return posicionFinal;
    }

    public void setPosicionFinal(Integer posicionFinal) {
        this.posicionFinal = posicionFinal;
    }

    public Integer getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(Integer puntaje) {
        this.puntaje = puntaje;
    }

    @Override
    public String toString() {
        return "Participacion [id=" + id + ", fechaInscripcion=" + fechaInscripcion + ", posicionFinal=" + posicionFinal + ", puntaje=" + puntaje + "]";
    }
}
