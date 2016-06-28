package bestsolutions.net.consultamais.database;

import java.util.ArrayList;
import java.util.Date;

import bestsolutions.net.consultamais.entidades.Consulta;
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

    /*static public ArrayList<Consulta> consultas = new ArrayList<Consulta>() {{
        // String paciente, String especialidadeMedico, Date dataHoraAtendimento
        add(new Consulta("Felipe Generico", "Clinico Geral", new Date(), "Daniel Magalhães"));
        add(new Consulta("Elton", "Pediatra", new Date(), "Daniel Magalhães"));
        add(new Consulta("Marcia", "Pediatra", new Date(), "Daniel Magalhães"));
        add(new Consulta("Jurandir Araujo Santos", "Pediatra", new Date(), "Daniel Magalhães"));
        add(new Consulta("Raissa Guerra de Magalhães Melo", "Ortopedista", new Date(), "Daniel Magalhães"));
        add(new Consulta("Ana Cristina de Souza Bezerra", "Pediatra", new Date(), "Daniel Magalhães"));
        add(new Consulta("Ivlison Souza", "Pediatra", new Date(), "Daniel Magalhães"));
        add(new Consulta("Nelson Glauber", "Pediatra", new Date(), "Daniel Magalhães"));

    }};*/
}
