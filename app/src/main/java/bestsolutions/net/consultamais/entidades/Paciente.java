package bestsolutions.net.consultamais.entidades;

public class Paciente {


    private int Id;
    private String mNome;
    private int mSexo;

    private String mTelefone;
    private String mCelular;

    private String mRua;
    private String mNumero;
    private String mComplemento;
    private String mCep;
    private String mBairro;
    private String mCidade;
    private String mEstado;


    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getSexo() {
        return mSexo;
    }

    public void setSexo(int sexo) {
        mSexo = sexo;
    }

    public String getNome() {
        return mNome;
    }

    public void setNome(String nome) {
        mNome = nome;
    }

    public String getTelefone() {
        return mTelefone;
    }

    public void setTelefone(String telefone) {
        mTelefone = telefone;
    }

    public String getCelular() {
        return mCelular;
    }

    public void setCelular(String celular) {
        mCelular = celular;
    }

    public String getRua() {
        return mRua;
    }

    public void setRua(String rua) {
        mRua = rua;
    }

    public String getNumero() {
        return mNumero;
    }

    public void setNumero(String numero) {
        mNumero = numero;
    }

    public String getComplemento() {
        return mComplemento;
    }

    public void setComplemento(String complemento) {
        mComplemento = complemento;
    }

    public String getCep() {
        return mCep;
    }

    public void setCep(String cep) {
        mCep = cep;
    }

    public String getBairro() {
        return mBairro;
    }

    public void setBairro(String bairro) {
        mBairro = bairro;
    }

    public String getCidade() {
        return mCidade;
    }

    public void setCidade(String cidade) {
        mCidade = cidade;
    }

    public String getEstado() {
        return mEstado;
    }

    public void setEstado(String estado) {
        mEstado = estado;
    }
}
