package com.example.myapplicationn;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class NoteAdapter extends ArrayAdapter<Note> {

    public NoteAdapter(@NonNull Context context, @NonNull List<Note> objects, int resource) {
        super(context, resource=0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View newItem ;
        LayoutInflater li = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        newItem = li.inflate(R.layout.note_line,parent,false);
        ImageView icone = newItem.findViewById(R.id.txt_matiere);
        ImageView textcore = newItem.findViewById(R.id.txtScore);
        TextView textScore = newItem.findViewById(R.id.txtSore);
        if(this.getItem(position).getScore()<10)
            icone.setImageResource(R.drawable);

        return newItem;
    }
}
