package bestsolutions.net.consultamais.database;

import java.util.ArrayList;
import java.util.Date;

import bestsolutions.net.consultamais.entidades.Atendimento;
import bestsolutions.net.consultamais.entidades.Especialidade;
import bestsolutions.net.consultamais.entidades.Usuario;


public class DB {
    static public ArrayList<Usuario> Usuarios = new ArrayList<Usuario>() {{
        // String login, String senha, String nome
        add(new Usuario("teste", "123456", "Daniel de Souza Rodrigues"));
        add(new Usuario("elton", "123456", "Elton Bento"));
        add(new Usuario("marcia", "123456", "Marcia"));
    }};

    static public ArrayList<Especialidade> Especialidades = new ArrayList<Especialidade>() {{
        // String especialidade
        add(new Especialidade("Clinico Geral"));
        add(new Especialidade("Pediatra"));
        add(new Especialidade("Ortopedista"));
    }};

    static public ArrayList<Atendimento> Atendimentos = new ArrayList<Atendimento>() {{
        // String paciente, String especialidadeMedico, Date dataHoraAtendimento
        add(new Atendimento("Felipe Generico", "Clinico Geral", new Date()));
        add(new Atendimento("Elton", "Pediatra", new Date()));
        add(new Atendimento("Marcia", "Pediatra", new Date()));
    }};
}
