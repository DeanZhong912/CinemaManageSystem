package Authority.impl;

import Authority.Manage;

import Service.impl.Manageservice;
import Service.impl.service;

/**
 * Created by diandian on 2019/7/7.
 */
public class DefaultManage implements Manage {
    private int Aud_id;
    public Manageservice manageservice=new Manageservice(Aud_id);
    public DefaultManage(int aud_id) {
        Aud_id = aud_id;
        manageservice.setAud_id(aud_id);
    }

    @Override
    public void search() {
        manageservice.ManageFind();
    }

    @Override
    public void addMovie() {
        manageservice.addMovie();
    }

    @Override
    public void delMovie() {
        manageservice.delMovie();
    }

    @Override
    public void addShow() {
        manageservice.addShow();
    }

    @Override
    public void delShow() {
        manageservice.delShow();
    }
}
