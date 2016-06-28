package bestsolutions.net.consultamais;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import org.parceler.Parcels;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import bestsolutions.net.consultamais.database.MedicoDB;
import bestsolutions.net.consultamais.database.PacienteDB;
import bestsolutions.net.consultamais.entidades.AtividadesCrud;
import bestsolutions.net.consultamais.entidades.Consulta;
import bestsolutions.net.consultamais.entidades.Medico;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NovaConsultaFragment extends Fragment {

    @Bind(R.id.listaPacientes)
    Spinner mPacientes;
    @Bind(R.id.listaMedicos)
    Spinner mMedicos;

    @Bind(R.id.btnData)
    Button mBtnData;

    @Bind(R.id.btnHora)
    Button mBtnHora;

    private DatePickerDialog mDataDialog;
    private TimePickerDialog mTimeDialog;
    private SimpleDateFormat dateFormatter;

    public NovaConsultaFragment() {
    }

    public static NovaConsultaFragment newInstance() {
        NovaConsultaFragment fragment = new NovaConsultaFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_nova_consulta, container, false);
        ButterKnife.bind(this, v);

        PacienteDB db = new PacienteDB(getContext());
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, db.ListagemNomesPacientes());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mPacientes.setAdapter(adapter);

        MedicoDB dbMedico = new MedicoDB(getContext());
        MedicoSpinner adpMedico = new MedicoSpinner(getActivity(), R.layout.spinner_medico_layout, R.id.NomeMedico, dbMedico.Listagem());

        adpMedico.setDropDownViewResource(R.layout.spinner_medico_layout);
        mMedicos.setAdapter(adpMedico);
        mMedicos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Medico medico = (Medico) parent.getItemAtPosition(position);
                TextView t = (TextView) view.findViewById(R.id.NomeMedico);
                t.setText(medico.getNome());
                TextView tEspecialidade = (TextView) view.findViewById(R.id.Especialidade);
                tEspecialidade.setText(medico.getEspecialidade());
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        Calendar newCalendar = Calendar.getInstance();
        mBtnData.setText(dateFormatter.format(newCalendar.getTime()));
        mDataDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                mBtnData.setText(dateFormatter.format(newDate.getTime()));
            }
        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        mTimeDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                mBtnHora.setText(hourOfDay + ":" + minute);
            }
        }, hour, minute, DateFormat.is24HourFormat(getActivity()));

        return v;
    }

    @OnClick(R.id.btnSalvar)
    public void Salvar() {
        Consulta consulta = new Consulta();

        Medico m = (Medico) mMedicos.getSelectedItem();
        consulta.setNomeMedico(m.getNome());
        consulta.setPaciente(mPacientes.getSelectedItem().toString());
        consulta.setDataAtendimento(mBtnData.getText().toString());
        consulta.setHoraAtendimento(mBtnHora.getText().toString());

        Intent returnIntent = new Intent();
        Parcelable parcelable = Parcels.wrap(consulta);
        returnIntent.putExtra(AtividadesCrud.OBJETO_CONSULTA, parcelable);

        getActivity().setResult(Activity.RESULT_OK, returnIntent);
        getActivity().finish();
    }

    @OnClick(R.id.btnData)
    public void ShowData() {
        mDataDialog.show();
    }

    @OnClick(R.id.btnHora)
    public void ShowHora() {
        mTimeDialog.show();
    }
}
