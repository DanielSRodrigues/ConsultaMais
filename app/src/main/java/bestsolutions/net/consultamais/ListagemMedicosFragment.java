package bestsolutions.net.consultamais;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.parceler.Parcels;

import java.util.ArrayList;

import bestsolutions.net.consultamais.database.MedicoDB;
import bestsolutions.net.consultamais.database.PacienteDB;
import bestsolutions.net.consultamais.entidades.AtividadesCrud;
import bestsolutions.net.consultamais.entidades.Medico;
import bestsolutions.net.consultamais.entidades.Paciente;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ListagemMedicosFragment extends Fragment {

    public RecyclerView mListagem;
    public MedicosRecycleAdapter mAdapter;
    public ArrayList<Medico> mMedicos;
    public FloatingActionButton mAddMedicos;
    @Bind(R.id.qtdItens)
    public TextView mQtdIntes;

    public ListagemMedicosFragment() {
    }

    public static ListagemMedicosFragment newInstance() {
        ListagemMedicosFragment fragment = new ListagemMedicosFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_listagem_medicos, container, false);
        ButterKnife.bind(this, view);

        mListagem = (RecyclerView) view.findViewById(R.id.listaMedicos);
        mListagem.setLayoutManager(new LinearLayoutManager(getActivity()));
        mListagem.setItemAnimator(new DefaultItemAnimator());

        MedicoDB db = new MedicoDB(getActivity());
        mMedicos = db.Listagem();

        mAdapter = new MedicosRecycleAdapter(getActivity(), mMedicos);
        mListagem.setAdapter(mAdapter);
        AtualizaListagem();


        mAddMedicos = (FloatingActionButton) view.findViewById(R.id.addMedico);
        mAddMedicos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), CrudMedicoActivity.class);
                startActivityForResult(i, AtividadesCrud.ACAO_NOVO_CRUD);
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Medico m = null;
        MedicoDB db = new MedicoDB(getActivity());
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == AtividadesCrud.ACAO_NOVO_CRUD) {
                m = Parcels.unwrap(data.getParcelableExtra(AtividadesCrud.OBJETO_MEDICO));
                db.Inserir(m);
                AtualizaListagem();
            } else if (requestCode == AtividadesCrud.ACAO_ALTERAR_CRUD) {
                m = Parcels.unwrap(data.getParcelableExtra(AtividadesCrud.OBJETO_MEDICO));
                db.Update(m);
                AtualizaListagem();
            }
        }
    }

    public void AtualizaListagem() {
        mMedicos.clear();
        MedicoDB db = new MedicoDB(getActivity());
        mMedicos.addAll(db.Listagem());
        mAdapter.notifyDataSetChanged();
        mQtdIntes.setText(String.valueOf(mMedicos.size()));
    }

    private class MedicosRecycleAdapter extends RecyclerView.Adapter<MedicoViewHolderAdapter> {

        private ArrayList<Medico> mValues;

        public MedicosRecycleAdapter(Context context, ArrayList<Medico> items) {
            mValues = items;
        }

        @Override
        public MedicoViewHolderAdapter onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_medico, parent, false);
            return new MedicoViewHolderAdapter(view);
        }

        public Medico getValueAt(int position) {
            return mValues.get(position);
        }

        @Override
        public void onBindViewHolder(final MedicoViewHolderAdapter holder, final int position) {
            holder.bind(getValueAt(position));
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }
    }

    public class MedicoViewHolderAdapter extends RecyclerView.ViewHolder {
        private View context;
        @Bind(R.id.lblMedico)
        public TextView mMedico;
        @Bind(R.id.lblEspecialidade)
        public TextView mEspecialidade;

        @Bind(R.id.toolbarMedico)
        public Toolbar mToolbar;

        public MedicoViewHolderAdapter(View view) {
            super(view);
            ButterKnife.bind(this, view);
            context = view;
        }

        public void bind(final Medico medico) {
            mMedico.setText(medico.getNome());
            mEspecialidade.setText(medico.getEspecialidade());
            
            mToolbar.getMenu().clear();
            mToolbar.inflateMenu(R.menu.crud_menu);
            mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    int id = item.getItemId();
                    if (id == R.id.modificar) {
                        Intent i = new Intent(context.getContext(), CrudMedicoActivity.class);
                        Parcelable parcelable = Parcels.wrap(medico);
                        i.putExtra(AtividadesCrud.OBJETO_MEDICO, parcelable);
                        i.putExtra(AtividadesCrud.CONTEXTO_CRUD, true);
                        startActivityForResult(i, AtividadesCrud.ACAO_ALTERAR_CRUD);
                    } else if (id == R.id.excluir) {
                        MedicoDB db = new MedicoDB(getActivity());
                        db.Delete(medico.getId());
                        AtualizaListagem();
                    }
                    return true;
                }
            });
        }
    }
}
