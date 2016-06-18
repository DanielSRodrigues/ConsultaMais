package bestsolutions.net.consultamais.entidades;


import org.parceler.Parcel;

@Parcel
public class Medico {
    private int Id;
    private String Nome;
    private String Especialidade;


    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getEspecialidade() {
        return Especialidade;
    }

    public void setEspecialidade(String esp) {
        Especialidade = esp;
    }
}
