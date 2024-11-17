package CuatroDestinos.aplicacion;

public class Personaje {

    String nombre;
    int edad;
    String profesion;
    String problema;
    String sexo;

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public String getProblema() {
        return problema;
    }

    public void setProblema(String problema) {
        this.problema = problema;
    }

    public Personaje(String nombre, int edad, String profesion, String problema, String sexo) {
        this.nombre = nombre;
        this.edad = edad;
        this.profesion = profesion;
        this.problema = problema;
        this.sexo = sexo;
    }
}
