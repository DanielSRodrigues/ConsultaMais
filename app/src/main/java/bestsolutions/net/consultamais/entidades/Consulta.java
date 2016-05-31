package bestsolutions.net.consultamais.entidades;

import java.sql.Time;
import java.util.Date;
import java.util.Timer;

/**
 * Created by Daniel Rodrigues on 06/04/2016.
 */
public class Consulta {
    private String Paciente;
    private String NomeMedico;
    private String EspecialidadeMedico;
    private Date DataHoraAtendimento;

    public Consulta(String paciente, String especialidadeMedico, Date dataHoraAtendimento, String nomeMedico) {
        Paciente = paciente;
        EspecialidadeMedico = especialidadeMedico;
        DataHoraAtendimento = dataHoraAtendimento;
        NomeMedico = nomeMedico;
    }

    public String getNomeMedico() {
        return NomeMedico;
    }

    public void setNomeMedico(String nomeMedico) {
        NomeMedico = nomeMedico;
    }

    public String getEspecialidadeMedico() {
        return EspecialidadeMedico;
    }

    public void setEspecialidadeMedico(String especialidadeMedico) {
        EspecialidadeMedico = especialidadeMedico;
    }

    public String getPaciente() {
        return Paciente;
    }

    public void setPaciente(String paciente) {
        Paciente = paciente;
    }

    public Date getDataHoraAtendimento() {
        return DataHoraAtendimento;
    }

    public void setDataHoraAtendimento(Date dataHoraAtendimento) {
        DataHoraAtendimento = dataHoraAtendimento;
    }
}
