package bestsolutions.net.consultamais.entidades;

import java.lang.ref.SoftReference;

/**
 * Created by Daniel Rodrigues on 06/04/2016.
 */
public class Usuario {
    private String Login;
    private String Senha;
    private String Nome;

    public Usuario(String login, String senha, String nome) {
        Login = login;
        Senha = senha;
        Nome = nome;
    }

    public String getLogin() {
        return Login;
    }

    public void setLogin(String login) {
        Login = login;
    }

    public String getSenha() {
        return Senha;
    }

    public void setSenha(String senha) {
        Senha = senha;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }
}
