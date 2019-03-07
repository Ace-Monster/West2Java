package bilibili;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class JasonBean {
    JasonBean() { }
    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}

class Data{
    Data() { }
    private int aid;//AV号
    @SerializedName("pic") private String img;//标题图
    private String title;//标题
    @SerializedName("desc") private String intro;//简介
    private long pubdate;//上传时间
    private Stat stat;
    private Owner owner;
    private ArrayList<Pages> pages;

    public long getPubdate() {
        return pubdate;
    }

    public int getAid() {
        return aid;
    }

    public Owner getOwner() {
        return owner;
    }

    public Stat getStat() {
        return stat;
    }

    public String getImg() {
        return img;
    }

    public String getIntro() {
        return intro;
    }

    public ArrayList<Pages> getPages() {
        return pages;
    }

    public String getTitle() {
        return title;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public void setPages(ArrayList<Pages> pages) {
        this.pages = pages;
    }

    public void setStat(Stat stat) {
        this.stat = stat;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPubdate(long pubdate) {
        this.pubdate = pubdate;
    }
}

class Stat{
    Stat() { }
    private int aid;//AV号
    private int view;//再生数
    private int danmaku;//弹幕数
    private int reply;//回复量
    private int favorite;//收藏数
    private int coin;//硬币数
    private int share;//分享量
    private int like;//点赞数

    public int getAid() {
        return aid;
    }

    public int getCoin() {
        return coin;
    }

    public int getDanmaku() {
        return danmaku;
    }

    public int getReply() {
        return reply;
    }

    public int getFavorite() {
        return favorite;
    }

    public int getLike() {
        return like;
    }

    public int getShare() {
        return share;
    }

    public int getView() {
        return view;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public void setCoin(int coin) {
        this.coin = coin;
    }

    public void setDanmaku(int danmaku) {
        this.danmaku = danmaku;
    }

    public void setFavorite(int favorite) {
        this.favorite = favorite;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public void setReply(int reply) {
        this.reply = reply;
    }

    public void setShare(int share) {
        this.share = share;
    }

    public void setView(int view) {
        this.view = view;
    }
}

class Owner{
    Owner() { }
    private int mid;
    private String name;

    public int getMid() {
        return mid;
    }

    public String getName() {
        return name;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class Pages{
    Pages() { }
    private int cid;//弹幕池ID
    private int page;//分P编号

    public int getCid() {
        return cid;
    }

    public int getPage() {
        return page;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public void setPage(int page) {
        this.page = page;
    }
}