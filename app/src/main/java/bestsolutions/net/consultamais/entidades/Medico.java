package bestsolutions.net.consultamais.entidades;


import org.parceler.Parcel;

@Parcel
public class Medico {
    private int Id;
    private String Nome;
    private String Especialidade;

    private int HoraInicio;
    private int HoraFim;
    private int TempoMedioConsulta;

    public int getHoraInicio() {
        return HoraInicio;
    }

    public void setHoraInicio(int horaInicio) {
        HoraInicio = horaInicio;
    }

    public int getHoraFim() {
        return HoraFim;
    }

    public void setHoraFim(int horaFim) {
        HoraFim = horaFim;
    }

    public int getTempoMedioConsulta() {
        return TempoMedioConsulta;
    }

    public void setTempoMedioConsulta(int tempoMedioConsulta) {
        TempoMedioConsulta = tempoMedioConsulta;
    }

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
