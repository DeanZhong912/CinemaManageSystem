package Authority.impl;

import Authority.Normal;
import Service.impl.Customservice;

/**
 * Created by diandian on 2019/7/7.
 */
public class DefaultCustomer implements Normal{
    private int Aud_id;
    public Customservice customservice=new Customservice(Aud_id);
    public DefaultCustomer(int aud_id) {
        Aud_id = aud_id;
        customservice.setAud_id(aud_id);
    }

    @Override
    public void search() {
        customservice.find();
    }

    @Override
    public void order() {
        customservice.order();
    }

    @Override
    public void refund() {
        customservice.refund();
    }

    @Override
    public void change() {
        customservice.change();
    }
}
