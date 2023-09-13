package com.example.myapplication.Presenter;

import com.example.myapplication.Model.Banner;
import com.example.myapplication.Model.HomeModel;

import java.util.ArrayList;
import java.util.List;

public class HomePresenter {
    public HomePresenter() {

    }
    public  List<HomeModel> getdata(List<HomeModel> homeModelList)
    {
        List<HomeModel> listfilm = new ArrayList<>();
        for (HomeModel a : homeModelList)
        {
            if(a.getType().equals("COLLECTION") || a.getType().equals("BANNER"))
            {
                listfilm.add(a);
            }

        }
        return  listfilm;
    }

        public List<Banner> getbanner(List<HomeModel> homeModelList)
    {
        List<Banner> listbanner = new ArrayList<>();
        for (HomeModel a : homeModelList)
        {
            if(a.getType().equals("BANNER"))
            {
                for (Banner l : a.getContent())
                {
                    listbanner.add(l);
                }

            }

        }
        return  listbanner;
    }
    public  List<HomeModel> getlistfilm(List<HomeModel> homeModelList)
    {
        List<HomeModel> listfilm = new ArrayList<>();
        for (HomeModel a : homeModelList)
        {
            if(a.getType().equals("COLLECTION") )
            {
                listfilm.add(a);
            }

        }
        return  listfilm;
    }


}
