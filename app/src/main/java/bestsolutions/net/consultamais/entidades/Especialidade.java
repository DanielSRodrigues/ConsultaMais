package bestsolutions.net.consultamais.entidades;

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
