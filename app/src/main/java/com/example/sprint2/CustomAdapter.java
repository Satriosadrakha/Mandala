package com.example.sprint2;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.TextView;

public class CustomAdapter extends BaseAdapter implements ListAdapter {
    private Context context;

    public CustomAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getViewTypeCount() {
        return getCount();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getCount() {
        return AksaraLessonActivity.modelArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return AksaraLessonActivity.modelArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder(); LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_row, null, true);

            holder.listrow_sunda = (TextView) convertView.findViewById(R.id.listrow_sunda);
            holder.listrow_contoh = (TextView) convertView.findViewById(R.id.listrow_contoh);
            holder.listrow_image = (ImageButton) convertView.findViewById(R.id.listrow_image);
            holder.listrow_sound = (ImageButton) convertView.findViewById(R.id.listrow_sound);

            convertView.setTag(holder);
        }else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = (ViewHolder)convertView.getTag();
        }

        holder.listrow_sunda.setText(AksaraLessonActivity.modelArrayList.get( position ).getSunda());
        holder.listrow_contoh.setText(AksaraLessonActivity.modelArrayList.get( position ).getContoh());

        int imageResource = AksaraLessonActivity.modelArrayList.get( position ).getImage();
        Drawable res = context.getResources().getDrawable(imageResource);
        holder.listrow_image.setBackground(res);

        int soundpos = AksaraLessonActivity.modelArrayList.get( position ).getSound()-1;
        holder.listrow_sound.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Object tag = v.getTag();
                final MediaPlayer mp;
                if(soundpos<7){
                    if(soundpos==1){
                        mp = (MediaPlayer) MediaPlayer.create(context, context.getResources().getIdentifier(String.valueOf( "e_" ),"raw", context.getPackageName()));
                    } else{
                        mp = (MediaPlayer) MediaPlayer.create(context, context.getResources().getIdentifier(String.valueOf( context.getResources().getStringArray(R.array.swara)[soundpos] ),"raw", context.getPackageName()));
                    }
                } else {
                    mp = (MediaPlayer) MediaPlayer.create(context, context.getResources().getIdentifier(String.valueOf( context.getResources().getStringArray(R.array.ngalagena)[soundpos-7] ),"raw", context.getPackageName()));
                }
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override public void onCompletion(MediaPlayer mp) {
                        mp.reset();
                        mp.release();
                        mp=null;
                    }
                });
                mp.start();
            }
        });

        View view = convertView;
        return view;
    }
    private class ViewHolder {
        private TextView listrow_sunda, listrow_contoh;
        private ImageButton listrow_image, listrow_sound;

    }
}
