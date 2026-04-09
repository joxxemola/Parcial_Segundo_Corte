package modelo;

/**
 * Clase abstracta Persona — base de la jerarquía de herencia.
 * Contiene los datos personales comunes a Profesor y Estudiante.
 *
 * POLIMORFISMO DE SOBREESCRITURA: declara mostrarInfo() como abstracto
 * para que cada subclase lo implemente de forma distinta.
 */
public abstract class Persona {

    // ── Atributos ──────────────────────────────────────────────────────────
    private String nombre;
    private String direccion;
    private String telefono;
    private String fechaNacimiento;

    // ── Constructor completo ───────────────────────────────────────────────
    public Persona(String nombre, String direccion,
                   String telefono, String fechaNacimiento) {
        this.nombre         = nombre;
        this.direccion      = direccion;
        this.telefono       = telefono;
        this.fechaNacimiento = fechaNacimiento;
    }

    // Constructor vacío — necesario para Estudiante (sin constructor propio)
    public Persona() {
        this("", "", "", "");
    }

    // ── Método abstracto (polimorfismo de sobreescritura) ─────────────────
    /**
     * Cada subclase devuelve su información formateada.
     */
    public abstract String mostrarInfo();

    // ── toString sobreescrito ──────────────────────────────────────────────
    @Override
    public String toString() {
        return nombre + " | Tel: " + telefono + " | Nac: " + fechaNacimiento;
    }

    // ── Getters y Setters ─────────────────────────────────────────────────
    public String getNombre()           { return nombre; }
    public void   setNombre(String n)   { this.nombre = n; }

    public String getDireccion()           { return direccion; }
    public void   setDireccion(String d)   { this.direccion = d; }

    public String getTelefono()           { return telefono; }
    public void   setTelefono(String t)   { this.telefono = t; }

    public String getFechaNacimiento()              { return fechaNacimiento; }
    public void   setFechaNacimiento(String fecha)  { this.fechaNacimiento = fecha; }
}