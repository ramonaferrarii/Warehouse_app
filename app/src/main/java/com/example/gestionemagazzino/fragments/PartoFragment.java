package com.example.gestionemagazzino.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.gestionemagazzino.R;
import com.example.gestionemagazzino.models.FirebaseWrapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PartoFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private String[] dbKeys={"Aspiratore Neonatale","Guanti In Lattice","Rotolo Cerotto", "Pinzetta Ombelicale", "Pinza Clamp"};

    private ArrayList<EditText> editTextsList = new ArrayList<EditText>();

    private EditText aspiratoreneonatale;
    private EditText guantiinlatticesterili;
    private EditText rotolocerotto;
    private EditText pinzettaombelicale;
    private EditText pinzaclamp;



    public PartoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AspirationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PartoFragment newInstance(String param1, String param2) {
        PartoFragment fragment = new PartoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View externalView = inflater.inflate(R.layout.fragment_parto, container, false);
        editTextsList.add(externalView.findViewById(R.id.ET_aspiratore_neonatale));
        editTextsList.add(externalView.findViewById(R.id.ET_guanti_in_lattice_sterili));
        editTextsList.add(externalView.findViewById(R.id.ET_rotolo_cerotto));
        editTextsList.add(externalView.findViewById(R.id.ET_pinzetta_ombelicale));
        editTextsList.add(externalView.findViewById(R.id.ET_pinzetta_ombelicale));
        editTextsList.add(externalView.findViewById(R.id.ET_pinza_clamp));
        Button saveButton = externalView.findViewById(R.id.B_save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String, Integer> editTextValues= new HashMap<>();
                for(int i=0;i<4;i++)
                    editTextValues.put(dbKeys[i], Integer.parseInt(editTextsList.get(i).getText().toString()));


                FirebaseWrapper.RTDatabase RTdb = new FirebaseWrapper.RTDatabase();
                for (Map.Entry<String, Integer> entry : editTextValues.entrySet()){
                    RTdb.updateDbData("Parto",entry.getKey(),entry.getValue());
                }
                for (EditText editText : editTextsList)
                    editText.setText("0");
                CharSequence msg="parametri salvati";
                Toast.makeText(externalView.getContext(), msg, Toast.LENGTH_SHORT).show();
                //TODO: handle the case where the user submits an empty editText
            }
        });
        return externalView;
    }
}