package bestsolutions.net.consultamais;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
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

import bestsolutions.net.consultamais.database.ConsultaDB;
import bestsolutions.net.consultamais.database.DB;
import bestsolutions.net.consultamais.database.MedicoDB;
import bestsolutions.net.consultamais.entidades.AtividadesCrud;
import bestsolutions.net.consultamais.entidades.Consulta;
import bestsolutions.net.consultamais.entidades.Medico;
import butterknife.Bind;
import butterknife.ButterKnife;


public class ListagemConsultasFragment extends Fragment {

    @Bind(R.id.listConsultas)
    public RecyclerView mListagemConsulta;
    @Bind(R.id.qtdItens)
    public TextView mQtdIntes;

    public AtendimentosRecycleAdapter mAdapter;

    public ArrayList<Consulta> mConsultas = new ArrayList<>();

    public ListagemConsultasFragment() {
    }

    public static ListagemConsultasFragment newInstance() {
        ListagemConsultasFragment fragment = new ListagemConsultasFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_listagem_consultas, container, false);
        ButterKnife.bind(this, view);

        mListagemConsulta.setLayoutManager(new LinearLayoutManager(getActivity()));
        mListagemConsulta.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new AtendimentosRecycleAdapter(getActivity(), mConsultas);
        mListagemConsulta.setAdapter(mAdapter);
        AtualizaListagem();

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), CrudConsultaActivity.class);
                startActivityForResult(i, AtividadesCrud.ACAO_NOVO_CRUD);
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Consulta m = null;
        ConsultaDB db = new ConsultaDB(getActivity());
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == AtividadesCrud.ACAO_NOVO_CRUD) {
                m = Parcels.unwrap(data.getParcelableExtra(AtividadesCrud.OBJETO_CONSULTA));
                db.Inserir(m);
                AtualizaListagem();
            } else if (requestCode == AtividadesCrud.ACAO_ALTERAR_CRUD) {
                m = Parcels.unwrap(data.getParcelableExtra(AtividadesCrud.OBJETO_CONSULTA));
                db.Update(m);
                AtualizaListagem();
            }
        }
    }

    public void AtualizaListagem() {
        mConsultas.clear();
        ConsultaDB db = new ConsultaDB(getActivity());
        mConsultas.addAll(db.Listagem());
        mAdapter.notifyDataSetChanged();
        mQtdIntes.setText(String.valueOf(mConsultas.size()));
    }

    private class AtendimentosRecycleAdapter extends RecyclerView.Adapter<ConsultaViewHolderAdapter> {

        private ArrayList<Consulta> mValues;

        public AtendimentosRecycleAdapter(Context context, ArrayList<Consulta> items) {
            mValues = items;
        }

        @Override
        public ConsultaViewHolderAdapter onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_consulta, parent, false);
            return new ConsultaViewHolderAdapter(view);
        }

        public Consulta getValueAt(int position) {
            return mValues.get(position);
        }

        @Override
        public void onBindViewHolder(final ConsultaViewHolderAdapter holder, final int position) {
            holder.bindOS(getValueAt(position));
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }
    }

    public class ConsultaViewHolderAdapter extends RecyclerView.ViewHolder {

        @Bind(R.id.lblDataAtendimento)
        public TextView DataAtendimento;
        @Bind(R.id.lblHoraAtendimento)
        public TextView HoraAtendimento;
        @Bind(R.id.lblPaciente)
        public TextView Paciente;
        @Bind(R.id.lblEspecialidade)
        public TextView Especialidade;

        public ConsultaViewHolderAdapter(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void bindOS(Consulta consulta) {

            HoraAtendimento.setText(consulta.getHoraAtendimento());
            DataAtendimento.setText(consulta.getDataAtendimento());
            Paciente.setText(consulta.getPaciente());
            Especialidade.setText(consulta.getNomeMedico());

        }

    }

}
