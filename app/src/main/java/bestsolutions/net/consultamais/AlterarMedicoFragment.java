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

import bestsolutions.net.consultamais.entidades.AtividadesCrud;
import bestsolutions.net.consultamais.entidades.Medico;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AlterarMedicoFragment extends Fragment {

    @Bind(R.id.NomeMedico)
    TextView mNome;
    @Bind(R.id.Especialidade)
    TextView mEspecialidade;
    @Bind(R.id.Inicio)
    Spinner mInicio;
    @Bind(R.id.Fim)
    Spinner mFim;
    @Bind(R.id.TempoMedio)
    Spinner mTempoMedio;


    private static int mIdMedico;

    public AlterarMedicoFragment() {
    }

    public static AlterarMedicoFragment newInstance() {
        return new AlterarMedicoFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_alterar_medico, container, false);
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


        Intent i = getActivity().getIntent();
        if (i != null) {
            Medico m = Parcels.unwrap(i.getParcelableExtra(AtividadesCrud.OBJETO_MEDICO));
            mNome.setText(m.getNome());
            mEspecialidade.setText(m.getEspecialidade());
            mInicio.setSelection(adapter.getPosition(String.valueOf(m.getHoraInicio())));
            mFim.setSelection(adapterFim.getPosition(String.valueOf(m.getHoraFim())));
            mTempoMedio.setSelection(adapterTempoMedio.getPosition(String.valueOf(m.getTempoMedioConsulta())));
            mIdMedico = m.getId();
        }

        return v;
    }

    @OnClick(R.id.btnSalvar)
    public void SalvarMedico() {
        Medico p = new Medico();
        p.setId(mIdMedico);
        p.setNome(mNome.getText().toString());
        p.setEspecialidade(mEspecialidade.getText().toString());
        p.setHoraInicio(Integer.parseInt(mInicio.getSelectedItem().toString()));
        p.setHoraFim(Integer.parseInt(mFim.getSelectedItem().toString()));
        p.setTempoMedioConsulta(Integer.parseInt(mTempoMedio.getSelectedItem().toString()));

        Intent returnIntent = new Intent();
        Parcelable parcelable = Parcels.wrap(p);
        returnIntent.putExtra(AtividadesCrud.OBJETO_MEDICO, parcelable);

        getActivity().setResult(Activity.RESULT_OK, returnIntent);
        getActivity().finish();
    }
}
