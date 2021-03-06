package com.example.shadow.exercise_array2.fragments;


import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.shadow.exercise_array2.MainActivity;
import com.example.shadow.exercise_array2.Pictures;
import com.example.shadow.exercise_array2.R;
import com.example.shadow.exercise_array2.Social;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Home#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Home extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    int image = 0, color, currentFirstVisible, i = 0;
    ArrayList<Pictures> pic;
    ArrayList<Integer> colors ;
    RecyclerView rv;
    GridLayoutManager glm;
    Social rva;
    View v;
    Toolbar toolbar;
    BottomNavigationViewEx bnve;
    AppBarLayout appBarLayout;
    getNavbarColor getNavbarColor;
    picColors picColors;

    public Home() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Home.
     */
    // TODO: Rename and change types and number of parameters
    public static Home newInstance(String param1, String param2) {
        Home fragment = new Home();
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
        v = inflater.inflate(R.layout.fragment_home, container, false);

        appBarLayout = getActivity().findViewById(R.id.abl);
        bnve = getActivity().findViewById(R.id.bnve);
        rv = v.findViewById(R.id.rv);
        toolbar = getActivity().findViewById(R.id.toolbar);

        getNavbarColor = new getNavbarColor();
        pic = new ArrayList<>();
        pic.add(new Pictures("MohammadReza","My Poppy is Sleeping",R.drawable.poppy));
        pic.add(new Pictures("Yasan Ghafarian","My Cute Cats",R.drawable.cats));
        pic.add(new Pictures("Sasan Alizadeh","Sleeping Beauty xD",R.drawable.cute));
        pic.add(new Pictures("Amir","Blah Blah Blah!",R.drawable.dumb));
        pic.add(new Pictures("Sahar Azizi","The Sea Horse!",R.drawable.sea));

        glm = new GridLayoutManager(getActivity(),1,GridLayoutManager.VERTICAL,false);
        rv.setLayoutManager(glm);
        rva = new Social(pic,getActivity());
        rv.setAdapter(rva);
        picColors = new picColors(getActivity());
        colors = picColors.getBitmap();

        rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                currentFirstVisible = glm.findFirstCompletelyVisibleItemPosition();
                if(currentFirstVisible != i && -1 < currentFirstVisible && currentFirstVisible < pic.size()){
                    i = currentFirstVisible;
//                    image = pic.get(currentFirstVisible).getImage();
//                    color = getNavColor.findNavbarColor(BitmapFactory.decodeResource(getActivity().getResources(),
//                            image),getActivity());
                    int i = colors.get(currentFirstVisible);
                    bnve.setBackgroundColor(i);
                    toolbar.setBackgroundColor(i);
                }

               // rv.setBackgroundColor(color);
//                RecyclerView.ViewHolder firstViewHolder = rv.findViewHolderForLayoutPosition(viewsId);
//                itemView = firstViewHolder.itemView;

//                if(currentFirstVisible > firstVisibleInListview)
//                    Log.i("RecyclerView scrolled: ", "scroll up!");
//                else
//                    Log.i("RecyclerView scrolled: ", "scroll down!");

                //firstVisibleInListview = currentFirstVisible;
            }
        });

        return v;
    }

}
