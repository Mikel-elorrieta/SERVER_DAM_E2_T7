package modelo;

public class VistaHorariosUsuarios implements java.io.Serializable {

    private static final long serialVersionUID = -4385937591523216572L;
    private int alumId;
    private String hora;
    private String dia;
    private String moduloNombre;

    public VistaHorariosUsuarios() {
    }

    public VistaHorariosUsuarios(int alumId, String hora, String dia, String moduloNombre) {
        this.alumId = alumId;
        this.hora = hora;
        this.dia = dia;
        this.moduloNombre = moduloNombre;
    }

    public int getAlumId() {
        return alumId;
    }

    public void setAlumId(int alumId) {
        this.alumId = alumId;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getModuloNombre() {
        return moduloNombre;
    }

    public void setModuloNombre(String moduloNombre) {
        this.moduloNombre = moduloNombre;
    }

    @Override
    public String toString() {
        return "VistaHorariosUsuarios [alumId=" + alumId + ", hora=" + hora + ", dia=" + dia + ", moduloNombre=" + moduloNombre + "]";
    }
}
