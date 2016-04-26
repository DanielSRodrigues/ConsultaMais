package bestsolutions.net.consultamais.entidades;

/**
 * Created by Daniel Rodrigues on 06/04/2016.
 */
public class Especialidade {
    private int Id;
    private String Especialidade;

    public Especialidade(String especialidade) {
        Especialidade = especialidade;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getEspecialidade() {
        return Especialidade;
    }

    public void setEspecialidade(String especialidade) {
        Especialidade = especialidade;
    }
}
