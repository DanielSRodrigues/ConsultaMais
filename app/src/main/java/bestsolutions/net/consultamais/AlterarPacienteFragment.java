package bestsolutions.net.consultamais;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.parceler.Parcels;

import bestsolutions.net.consultamais.entidades.AtividadesCrud;
import bestsolutions.net.consultamais.entidades.Paciente;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AlterarPacienteFragment extends Fragment {

    @Bind(R.id.NomeCompleto)
    TextView mNome;
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

    private static int mIdPaciente;


    public AlterarPacienteFragment() {
    }

    public static AlterarPacienteFragment newInstance() {
        return new AlterarPacienteFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_alterar_paciente, container, false);
        ButterKnife.bind(this, v);
        Intent i = getActivity().getIntent();
        if (i != null) {
            Paciente p = Parcels.unwrap(i.getParcelableExtra(AtividadesCrud.OBJETO_PACIENTE));
            mNome.setText(p.getNome());
            mTelefone.setText(p.getTelefone());
            mCelular.setText(p.getCelular());
            mRua.setText(p.getRua());
            mNumero.setText(p.getNumero());
            mComplemento.setText(p.getComplemento());
            mCep.setText(p.getCep());
            mBairro.setText(p.getBairro());
            mCidade.setText(p.getCidade());
            mEstado.setText(p.getEstado());
            mIdPaciente = p.getId();
        }

        return v;
    }

    @OnClick(R.id.btnSalvar)
    public void SalvarPaciente() {
        Paciente p = new Paciente();
        p.setId(mIdPaciente);
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
        returnIntent.putExtra(AtividadesCrud.OBJETO_PACIENTE, parcelable);

        getActivity().setResult(Activity.RESULT_OK, returnIntent);
        getActivity().finish();
    }

}
