package campus.randolh.taller_m_to_m.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;

// Entidad principal Torneo
@Entity
@Table(name = "torneos")
public class Torneo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_torneo")
    private Long id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "videojuego", nullable = false, length = 100)
    private String videojuego;

    @Column(name = "premio")
    private Double premio;

    // Relacion uno a muchos con la entidad intermedia Participacion
    @OneToMany(mappedBy = "torneo", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Participacion> participaciones = new ArrayList<>();

    public Torneo() {
    }

    public Torneo(String nombre, String videojuego, Double premio) {
        this.nombre = nombre;
        this.videojuego = videojuego;
        this.premio = premio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getVideojuego() {
        return videojuego;
    }

    public void setVideojuego(String videojuego) {
        this.videojuego = videojuego;
    }

    public Double getPremio() {
        return premio;
    }

    public void setPremio(Double premio) {
        this.premio = premio;
    }

    public List<Participacion> getParticipaciones() {
        return participaciones;
    }

    public void setParticipaciones(List<Participacion> participaciones) {
        this.participaciones = participaciones;
    }

    // Metodo helper para asociar una participacion
    public void agregarParticipacion(Participacion participacion) {
        this.participaciones.add(participacion);
        participacion.setTorneo(this);
    }

    @Override
    public String toString() {
        return "Torneo [id=" + id + ", nombre=" + nombre + ", videojuego=" + videojuego + ", premio=" + premio + "]";
    }
}
