package bestsolutions.net.consultamais;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.parceler.Parcels;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import bestsolutions.net.consultamais.database.DB;
import bestsolutions.net.consultamais.database.PacienteDB;
import bestsolutions.net.consultamais.entidades.Consulta;
import bestsolutions.net.consultamais.entidades.Paciente;
import butterknife.Bind;
import butterknife.ButterKnife;


public class ListagemPacientesFragment extends Fragment {

    public final int ADD_NEW_PATIENT = 340;
    public RecyclerView mListagem;
    public PacientesRecycleAdapter mAdapter;
    public ArrayList<Paciente> mPacientes;
    public FloatingActionButton mAddPaciente;
    @Bind(R.id.qtdItens)
    public TextView mQtdIntes;

    public ListagemPacientesFragment() {
    }

    public static ListagemPacientesFragment newInstance() {
        ListagemPacientesFragment fragment = new ListagemPacientesFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_listagem_pacientes, container, false);
        ButterKnife.bind(this, view);
        mListagem = (RecyclerView) view.findViewById(R.id.listaPacientes);
        mListagem.setLayoutManager(new LinearLayoutManager(getActivity()));
        mListagem.setItemAnimator(new DefaultItemAnimator());
        PacienteDB db = new PacienteDB(getActivity());
        mPacientes = db.Listagem();
        mAdapter = new PacientesRecycleAdapter(getActivity(), mPacientes);
        mListagem.setAdapter(mAdapter);
        AtualizaListagem();

        mAddPaciente = (FloatingActionButton) view.findViewById(R.id.addPaciente);
        mAddPaciente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), CrudPacienteActivity.class);
                startActivityForResult(i, ADD_NEW_PATIENT);
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ADD_NEW_PATIENT) {
            if (resultCode == Activity.RESULT_OK) {
                Paciente p = Parcels.unwrap(data.getParcelableExtra("Paciente"));
                PacienteDB db = new PacienteDB(getActivity());
                db.Inserir(p);
                AtualizaListagem();
            }
        }
    }

    public void AtualizaListagem() {
        mPacientes.clear();
        PacienteDB db = new PacienteDB(getActivity());
        mPacientes.addAll(db.Listagem());
        mAdapter.notifyDataSetChanged();
        mQtdIntes.setText("" + mPacientes.size());
    }

    private class PacientesRecycleAdapter extends RecyclerView.Adapter<PacientesViewHolderAdapter> {

        private ArrayList<Paciente> mValues;

        public PacientesRecycleAdapter(Context context, ArrayList<Paciente> items) {
            mValues = items;
        }

        @Override
        public PacientesViewHolderAdapter onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_paciente, parent, false);
            return new PacientesViewHolderAdapter(view);
        }

        public Paciente getValueAt(int position) {
            return mValues.get(position);
        }

        @Override
        public void onBindViewHolder(final PacientesViewHolderAdapter holder, final int position) {
            holder.bind(getValueAt(position));
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }
    }

    public class PacientesViewHolderAdapter extends RecyclerView.ViewHolder {
        @Bind(R.id.lblPaciente)
        public TextView mNomePaciente;
        @Bind(R.id.lblEndereco)
        public TextView mEndereco;
        @Bind(R.id.lblComplemento)
        public TextView mComplemento;

        public PacientesViewHolderAdapter(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void bind(Paciente paciente) {
            mNomePaciente.setText(paciente.getNome());
            mEndereco.setText(paciente.getRua() + ", " + paciente.getNumero());
            mComplemento.setText(paciente.getComplemento());
        }

    }
}
