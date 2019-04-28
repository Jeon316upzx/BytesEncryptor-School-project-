package com.example.ifeanyi.bytesencryptor;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import javax.crypto.NoSuchPaddingException;

/**
 * Created by IFEANYI on 1/12/2017.
 */
public class EncryptFragment extends Fragment {

    ListView listView;
    File file;
    File [] myfiles;



    ArrayList<File> arrayList;
    int p;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.encrypt_fragment,container,false);

        listView = (ListView) v.findViewById(R.id.MyFilesList);



         arrayList = new ArrayList<File>();


        file = Environment.getExternalStorageDirectory();


        myfiles = file.listFiles();

        for(File f1 : myfiles)
        {
            if(f1.getAbsolutePath().endsWith("mp3") || f1.getAbsolutePath().endsWith("pdf") || f1.getAbsolutePath().endsWith("pptx") || f1.getAbsolutePath().endsWith("png") || f1.getAbsolutePath().endsWith("jpeg"))
            {
                if (f1.isDirectory())
                {
                    file.listFiles();
                }
                else
                {
                    arrayList.add(f1.getAbsoluteFile());
                }

            }

        }

        ArrayAdapter<File> adapter = new ArrayAdapter<File>(getContext(),android.R.layout.simple_list_item_1,arrayList);

        listView.setAdapter(adapter);



        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                 p = position;
                return true;
            }
        });



        registerForContextMenu(listView);


        return v;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if(v.getId() == R.id.MyFilesList)
        {
            MenuInflater inf = getActivity().getMenuInflater();
            inf.inflate(R.menu.context_menu,menu);


        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.ENCRYPT_WITH_AES:

                break;
            case R.id.ENCRYPT_WITH_RSA:

                break;
            case R.id.ENCRYPT_WITH_SHA:

                break;
        }
        return super.onContextItemSelected(item);
    }

}
