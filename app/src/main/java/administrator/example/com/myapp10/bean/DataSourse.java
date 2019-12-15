package administrator.example.com.myapp10.bean;

import cn.bmob.v3.BmobObject;

public class DataSourse extends BmobObject {

    private String ChinaShuyu;
    private String EnglishShuyu;
    private String explain;

    public String getChinaShuyu() {
        return ChinaShuyu;
    }

    public void setChinaShuyu(String chinaShuyu) {
        ChinaShuyu = chinaShuyu;
    }

    public String getEnglishShuyu() {
        return EnglishShuyu;
    }

    public void setEnglishShuyu(String englishShuyu) {
        EnglishShuyu = englishShuyu;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }
}
