package bestsolutions.net.consultamais;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.parceler.Parcels;

import bestsolutions.net.consultamais.database.PacienteDB;
import bestsolutions.net.consultamais.entidades.Paciente;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class NovoPacienteFragment extends Fragment {

    @Bind(R.id.NomeCompleto)
    TextView mNome;
    /* @Bind(R.id.Sexo)
     private TextView mSexo;*/
    @Bind(R.id.Telefone)
    TextView mTelefone;
    @Bind(R.id.Celular)
    TextView mCelular;
    @Bind(R.id.Rua)
    TextView mRua;
    @Bind(R.id.Numero)
    TextView mNumero;
    @Bind(R.id.Complemento)
    TextView mComplemento;
    @Bind(R.id.Cep)
    TextView mCep;
    @Bind(R.id.Bairro)
    TextView mBairro;
    @Bind(R.id.Cidade)
    TextView mCidade;
    @Bind(R.id.Estado)
    TextView mEstado;

    Button mSalvar;


    public NovoPacienteFragment() {
    }

    public static NovoPacienteFragment newInstance() {
        NovoPacienteFragment fragment = new NovoPacienteFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_novo_paciente, container, false);
        ButterKnife.bind(this, v);
        return v;
    }

    @OnClick(R.id.btnSalvar)
    public void SalvarPaciente() {
        Paciente p = new Paciente();
        p.setNome(mNome.getText().toString());
        p.setSexo(1);
        p.setCelular(mCelular.getText().toString());
        p.setTelefone(mTelefone.getText().toString());
        p.setRua(mRua.getText().toString());
        p.setCep(mCep.getText().toString());
        p.setNumero(mNumero.getText().toString());
        p.setComplemento(mComplemento.getText().toString());
        p.setCidade(mCidade.getText().toString());
        p.setBairro(mBairro.getText().toString());
        p.setEstado(mEstado.getText().toString());


        Intent returnIntent = new Intent();
        Parcelable parcelable = Parcels.wrap(p);
        returnIntent.putExtra("Paciente", parcelable);

        getActivity().setResult(Activity.RESULT_OK, returnIntent);
        getActivity().finish();
    }
}

