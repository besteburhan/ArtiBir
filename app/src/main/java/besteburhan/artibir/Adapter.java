package besteburhan.artibir;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

/**
 * Created by besteburhan on 1.10.2017.
 */

public class Adapter extends BaseAdapter{

    LayoutInflater layoutInflater;
    ArrayList<Questions> arrayList;

    public Adapter(Activity activity, ArrayList<Questions> arrayList) {
        layoutInflater=(LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.arrayList = arrayList;

    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View satir;
        Questions questions=arrayList.get(i);
        satir = layoutInflater.inflate(R.layout.questions,null);
        TextView textViewIssue = satir.findViewById(R.id.textViewIssue);
        TextView textViewDate = satir.findViewById(R.id.textViewDate);
        TextView textViewPoint = satir.findViewById(R.id.textViewPoint);
        TextView textViewCategory = satir.findViewById(R.id.textViewCategory);
        textViewIssue.setText(questions.getQuestionIssue());
        textViewDate.setText(questions.getQuestionDate());
        textViewPoint.setText(questions.getQuestionPoint());
        textViewCategory.setText(questions.getQuestionCategory());
        return satir;
    }
}
