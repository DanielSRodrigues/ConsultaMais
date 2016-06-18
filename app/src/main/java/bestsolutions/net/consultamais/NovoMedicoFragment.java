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
import android.widget.TextView;

import org.parceler.Parcels;
import org.w3c.dom.Text;

import bestsolutions.net.consultamais.entidades.AtividadesCrud;
import bestsolutions.net.consultamais.entidades.Medico;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NovoMedicoFragment extends Fragment {

    @Bind(R.id.NomeMedico)
    TextView mNomeMedico;
    @Bind(R.id.Especialidade)
    TextView mEspecialidade;

    public NovoMedicoFragment() {
    }

    public static NovoMedicoFragment newInstance() {
        NovoMedicoFragment fragment = new NovoMedicoFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_novo_medico, container, false);
        ButterKnife.bind(this, v);
        return v;
    }

    @OnClick(R.id.btnSalvar)
    public void SalvarMedico() {
        Medico medico = new Medico();
        medico.setNome(mNomeMedico.getText().toString());
        medico.setEspecialidade(mEspecialidade.getText().toString());

        Intent returnIntent = new Intent();
        Parcelable parcelable = Parcels.wrap(medico);
        returnIntent.putExtra(AtividadesCrud.OBJETO_MEDICO, parcelable);

        getActivity().setResult(Activity.RESULT_OK, returnIntent);
        getActivity().finish();
    }
}
