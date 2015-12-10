package com.br.marcio.campos.pastel4you.fragments;

/**
 * Created by Marcio on 08/11/2015.
 */
public class BaseFragment extends livroandroid.lib.fragment.BaseFragment {

    @Override
    protected boolean isLogLifecycle() {
        return true;
    }

    @Override
    protected boolean isLogOn() {
        return false;
    }
}
