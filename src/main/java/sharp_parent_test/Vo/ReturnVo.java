package sharp_parent_test.Vo;

public class ReturnVo {


    private String errcode;
    private String errmsg;
    private Object errdata;

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public Object getErrdata() {
        return errdata;
    }

    public void setErrdata(Object errdata) {
        this.errdata = errdata;
    }

    @Override
    public String toString() {
        return "ReturnVo [errcode=" + errcode + ", errmsg=" + errmsg + ", errdata=" + errdata + "]";
    }


}