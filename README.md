# Taller Práctico: Relación Muchos a Muchos con Entidad Intermedia en Spring Data JPA

## 1. Temática Seleccionada
**Videojuegos**: Relación de muchos a muchos entre **Jugadores** y **Torneos**, resuelta mediante la entidad intermedia **Participación**.

## 2. Modelo de Datos y Entidades
* **`Jugador` (`jugadores`)**: Entidad principal que almacena el nickname, nombre real y país del jugador. Posee una relación `@OneToMany(mappedBy = "jugador")` con `Participacion`.
* **`Torneo` (`torneos`)**: Entidad principal que almacena el nombre del torneo, videojuego y premio acumulado. Posee una relación `@OneToMany(mappedBy = "torneo")` con `Participacion`.
* **`Participacion` (`participaciones`)**: Entidad intermedia independiente que posee su propia clave primaria (`id_participacion`), dos relaciones `@ManyToOne` (hacia `Jugador` y hacia `Torneo`) y atributos adicionales del negocio:
  * `fechaInscripcion` (LocalDate)
  * `posicionFinal` (Integer)
  * `puntaje` (Integer)

### Relación Propietaria y `mappedBy`
* La entidad intermedia `Participacion` es el lado **propietario** de la relación, ya que contiene las claves foráneas (`id_jugador`, `id_torneo`) mediante `@JoinColumn`.
* Las entidades principales (`Jugador` y `Torneo`) son el lado **inverso**, por lo que usan `mappedBy` apuntando a las propiedades `jugador` y `torneo` de `Participacion`.

## 3. Requisitos Previos
* Java 17 o superior
* Maven (o el wrapper `./mvnw` incluido)
* MySQL 8.0 o superior

## 4. Clonar el Repositorio
```bash
git clone https://github.com/Randolh/many_to_many.git
cd many_to_many
```

## 5. Base de Datos y Configuración
1. Crear la base de datos en MySQL:
```sql
CREATE DATABASE IF NOT EXISTS torneos_db;
```

2. Credenciales configuradas en `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/torneos_db?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=123456
```

## 6. Ejecución del Proyecto
Para ejecutar la aplicación y observar la demostración en consola:
```bash
./mvnw clean spring-boot:run
```

## 7. Video Explicativo
* [VIDEO](https://drive.google.com/file/d/1XXfLPR2D3N5gosT-S7CM4k6b_pZxImw_/view?usp=sharing)
