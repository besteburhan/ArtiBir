package besteburhan.artibir;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Scroller;
import android.widget.Spinner;

/**
 * Created by besteburhan on 4.8.2017.
 */

public class TabAskQuestionFragment extends Fragment{


    EditText editTextExplanation;
    EditText editTextIssue;
    ImageButton imageButtonCamera;
    ImageButton imageButtonAddLocation;
    ImageButton imageButtonSend;
    Spinner spinner;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.tab_ask_question,container,false);
        editTextExplanation = (EditText) view.findViewById(R.id.editTextExplanation);
        editTextIssue = (EditText) view.findViewById(R.id.editTextIssue);
        imageButtonAddLocation = (ImageButton) view.findViewById(R.id.imageButtonAddLocation);
        imageButtonCamera = (ImageButton) view.findViewById(R.id.imageButtonCamera);
        imageButtonSend = (ImageButton) view.findViewById(R.id.imageButtonSend);

        spinner = (Spinner) view.findViewById(R.id.dropdown_categories_Quest);
        String[] items =  new String[]{"Diğer","Ulaşım","Seyahat","Spor","Yeme-İçme-Alışveriş","Tatil Gezi",
                "Sağlık","Eğitim","Haber","Sosyal Aktivite","Acil"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(),
                android.R.layout.simple_spinner_dropdown_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        String text = spinner.getSelectedItem().toString();

        imageButtonAddLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), Maps.class);
                startActivity(intent);
            }
        });



        return view;


    }

}
