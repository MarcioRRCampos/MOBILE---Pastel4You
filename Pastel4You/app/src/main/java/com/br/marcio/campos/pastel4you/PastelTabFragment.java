package com.br.marcio.campos.pastel4you;

import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import com.br.marcio.campos.pastel4you.adpter.TabAdpter;
import com.google.samples.apps.iosched.ui.widget.SlidingTabLayout;

import livroandroid.lib.fragment.BaseFragment;
import livroandroid.lib.utils.Prefs;

public class PastelTabFragment extends BaseFragment {


    private SlidingTabLayout mSlidingTabLayout;
    private ViewPager viewPager;
    private TabAdpter tabLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment_tab_pastel, container, false);
        // ViewPager
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        viewPager.setOffscreenPageLimit(2);
        viewPager.setAdapter(new TabAdpter(getContext(), getChildFragmentManager()));
        mSlidingTabLayout = (SlidingTabLayout) view.findViewById(R.id.sliding_tabs);
        // Deixa as tabs com mesmo tamanho (layout_weight=1)
        mSlidingTabLayout.setDistributeEvenly(true);
        mSlidingTabLayout.setViewPager(viewPager);
        mSlidingTabLayout.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {

            @Override
            public int getIndicatorColor(int i) {
                // Cor do indicador da TAB
                return getResources().getColor(R.color.accent);
            }
        });

        mSlidingTabLayout.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                // Salva o índice da página/tab selecionada
                Prefs.setInteger(getContext(), "tabIdx", viewPager.getCurrentItem());
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        // Inicia o aplicativo com o índice da última tab/página selecionada

        int tabIdx = Prefs.getInteger(getContext(), "tabIdx");
        viewPager.setCurrentItem(tabIdx);
        return view;
    }
}