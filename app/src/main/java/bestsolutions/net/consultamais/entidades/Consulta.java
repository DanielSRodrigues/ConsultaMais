package bestsolutions.net.consultamais.entidades;

import org.parceler.Parcel;

@Parcel
public class Consulta {

    private int Id;
    private String Paciente;
    private String NomeMedico;
    private String DataAtendimento;
    private String HoraAtendimento;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getPaciente() {
        return Paciente;
    }

    public void setPaciente(String paciente) {
        Paciente = paciente;
    }

    public String getNomeMedico() {
        return NomeMedico;
    }

    public void setNomeMedico(String nomeMedico) {
        NomeMedico = nomeMedico;
    }

    public String getDataAtendimento() {
        return DataAtendimento;
    }

    public void setDataAtendimento(String dataAtendimento) {
        DataAtendimento = dataAtendimento;
    }

    public String getHoraAtendimento() {
        return HoraAtendimento;
    }

    public void setHoraAtendimento(String horaAtendimento) {
        HoraAtendimento = horaAtendimento;
    }
}
