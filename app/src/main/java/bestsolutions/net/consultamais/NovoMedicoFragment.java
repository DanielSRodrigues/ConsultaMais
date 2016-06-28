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
import android.widget.ArrayAdapter;
import android.widget.Spinner;
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
    @Bind(R.id.Inicio)
    Spinner mInicio;
    @Bind(R.id.Fim)
    Spinner mFim;
    @Bind(R.id.TempoMedio)
    Spinner mTempoMedio;

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

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.horas, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mInicio.setAdapter(adapter);

        ArrayAdapter<CharSequence> adapterFim = ArrayAdapter.createFromResource(getActivity(),
                R.array.horas, android.R.layout.simple_spinner_item);
        adapterFim.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mFim.setAdapter(adapterFim);

        ArrayAdapter<CharSequence> adapterTempoMedio = ArrayAdapter.createFromResource(getActivity(),
                R.array.tempoMedio, android.R.layout.simple_spinner_item);
        adapterTempoMedio.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mTempoMedio.setAdapter(adapterTempoMedio);
        return v;
    }

    @OnClick(R.id.btnSalvar)
    public void SalvarMedico() {
        Medico medico = new Medico();
        medico.setNome(mNomeMedico.getText().toString());
        medico.setEspecialidade(mEspecialidade.getText().toString());
        medico.setHoraInicio(Integer.parseInt(mInicio.getSelectedItem().toString()));
        medico.setHoraFim(Integer.parseInt(mFim.getSelectedItem().toString()));
        medico.setTempoMedioConsulta(Integer.parseInt(mTempoMedio.getSelectedItem().toString()));

        Intent returnIntent = new Intent();
        Parcelable parcelable = Parcels.wrap(medico);
        returnIntent.putExtra(AtividadesCrud.OBJETO_MEDICO, parcelable);

        getActivity().setResult(Activity.RESULT_OK, returnIntent);
        getActivity().finish();
    }
}
