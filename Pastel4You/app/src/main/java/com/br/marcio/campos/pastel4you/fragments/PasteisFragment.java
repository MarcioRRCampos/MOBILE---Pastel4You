package com.br.marcio.campos.pastel4you.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.br.marcio.campos.pastel4you.MyApplicativo;
import com.br.marcio.campos.pastel4you.R;
import com.br.marcio.campos.pastel4you.adpter.PastelAdpter;
import com.br.marcio.campos.pastel4you.domain.Pastel;
import com.br.marcio.campos.pastel4you.domain.PastelService;
import com.br.marcio.campos.pastel4you.persistencia.PastelDAO;
import com.br.marcio.campos.pastel4you.prefs.MainActivity;

import java.util.ArrayList;
import java.util.List;

import livroandroid.lib.utils.AndroidUtils;

/**
 * Created by Marcio on 18/11/2015.
 */
public class PasteisFragment extends BaseFragment {

    protected RecyclerView recyclerView;
    private List<Pastel> pastels;
    private LinearLayoutManager mLayoutManager;
    private String tipo;
    private SwipeRefreshLayout swipeLayout;

    // Action Bar de Contexto
    private ActionMode actionMode;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null){
            this.tipo = getArguments().getString("tipo");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pasteis, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //recyclerView.setHasFixedSize(true);

        // Swipe to Refresh
        swipeLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeToRefresh);
        swipeLayout.setOnRefreshListener(OnRefreshListener());
        swipeLayout.setColorSchemeResources(
                R.color.refresh_progress_1,
                R.color.refresh_progress_2,
                R.color.refresh_progress_3);
        return view;
    }

    private SwipeRefreshLayout.OnRefreshListener OnRefreshListener(){
        return new SwipeRefreshLayout.OnRefreshListener(){
            @Override
            public void onRefresh(){
            // Atualiza ao fazer o gesto Swipe To Refresh
                if (AndroidUtils.isNetworkAvailable(getContext())) {
                    taskPastel(true);
                } else {
                    alert("Verificar conexão");
                }
            }
        };
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //Busca os Pasteis na BD por padrão
        taskPastel(false);
    }

    @Override
    public void onResume() {
        super.onResume();

        if(MyApplicativo.getInstance().isPrecisaAtualizar(this.tipo)) {
            // Faz a leitura Novamente do Banco

            taskPastel(false);
            toast("Lista atualizada!");
        }
    }

    private void taskPastel(boolean pullToRefresh) {
        startTask("pasteis", new GetPastelTask(pullToRefresh), pullToRefresh ? R.id.swipeToRefresh : R.id.progress);
    }

    private PastelAdpter.PastelOnClickListener onClickPastel(){
        return new PastelAdpter.PastelOnClickListener(){

            @Override
            public void onClickPastel(View view, int idx) {
                Pastel p = pastels.get(idx);
                if (actionMode == null) {
                    Intent intent = new Intent(getContext(), MainActivity.class);
                    intent.putExtra("pastel", p);
                    startActivity(intent);
                } else {
                    // Seleciona o pastel e atualiza a lista
                    p.selected = !p.selected;
                    updateActionModeTitle();
                    recyclerView.getAdapter().notifyDataSetChanged();
                }

            }
                @Override
                public void onLongClickPastel(View view, int idx) {
                    if (actionMode != null) {
                        return;
                    }

                    //Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
                    actionMode = getAppCompatActivity().startSupportActionMode(getActionModeCallback());

                    Pastel p = pastels.get(idx);
                    p.selected = true;
                    recyclerView.getAdapter().notifyDataSetChanged();

                    updateActionModeTitle();
                }

        };
    }

    private void updateActionModeTitle() {
    }

    // Task para buscar os pasteis -------------------------------------------

    private class GetPastelTask implements livroandroid.lib.fragment.BaseFragment.TaskListener<List<Pastel>> {
        private boolean refresh;

        public GetPastelTask(boolean refresh){
            this.refresh = refresh;
        }

        @Override
        public List<Pastel> execute() throws Exception {
            Thread.sleep(300);
            //Busca carros em background (Thread)
            return PastelService.getPastels(getContext(), tipo, refresh);
        }

        @Override
        public void updateView(List<Pastel> pastels) {

            if(pastels != null ){
                PasteisFragment.this.pastels = pastels;
                // Atualiza a view na UI Treade
                recyclerView.setAdapter(new PastelAdpter(getContext(), pastels, onClickPastel()));

            }
        }

        @Override
        public void onError(Exception e) {
            alert("Ocorreu algum erro ao buscar dados!");
        }

        @Override
        public void onCancelled(String s) {

        }
    }

    private ActionMode.Callback getActionModeCallback() {
                    return new ActionMode.Callback() {
                        @Override
                        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                            //Infla a menu resource providing context menu itens para preecher a Toolbar
                            MenuInflater inflater = getActivity().getMenuInflater();
                            inflater.inflate(R.menu.menu_frag_pastel_context, menu);
                            return true;
                        }

                        @Override
                        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {

                            return true;
                        }

                        @Override
                        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                            List<Pastel> selectedPastel = getSelectedPastel();
                            if(item.getItemId() == R.id.action_remove){

                                // Deleta os pastei do banco de acordo com o DAO
                                PastelDAO db = new PastelDAO(getContext());
                                try {
                                    for (Pastel p : selectedPastel) {
                                        db.delete(p);
                                        pastels.remove(p);
                                    }

                                }catch (Exception e){

                                    e.fillInStackTrace();
                                } finally {
                                    db.close();
                                }
                            }

                            //TODO: Compartilhar pasteis!
                            /*else if (item.getItemId() == R.id.action_share) {
                                toast("Compartilhar: " + selectedPastel);
                                // Dispara a tarefa para fazer downloa das fotos

                                startTask("compartilhar", new CompartilharTask(selectedPastel));
                            }*/


                            // Encerra o action mode
                            mode.finish();
                            return true;
                        }

                        @Override
                        public void onDestroyActionMode(ActionMode mode) {
                        //Limpa a ActionMode e intens selecionados
                            actionMode = null;
                            for (Pastel p: pastels) {
                                p.selected = false;
                            }
                            // Atualiza a Lista de pasteis
                            recyclerView.getAdapter().notifyDataSetChanged();

                        }

                    };
    }

    private List<Pastel> getSelectedPastel() {
        List<Pastel> list = new ArrayList<>();
        for (Pastel p: pastels) {
            if(p.selected){
                list.add(p);
            }
        }
        return list;
    }

    //Todo: Implemetar os dowloads do Banco

}
