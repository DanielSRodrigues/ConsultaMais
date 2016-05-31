package bestsolutions.net.consultamais;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class ListagemPacientesFragment extends Fragment {


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
        return inflater.inflate(R.layout.fragment_listagem_pacientes, container, false);
    }
}
