package com.misplantitas.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

public class InfoNavFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Infla el diseño del fragmento
        View rootView = inflater.inflate(R.layout.fragment_infonav, container, false);

        // Encuentra el botón por su ID
        Button botonIrAMapa = rootView.findViewById(R.id.botonIrAMapa);

        // Configura un OnClickListener para el botón
        botonIrAMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crea una intención para ir a la actividad mapaUbicacion
                Intent intent = new Intent(getActivity(), mapaUbicacion.class);
                startActivity(intent);
            }
        });

        return rootView;
    }
}