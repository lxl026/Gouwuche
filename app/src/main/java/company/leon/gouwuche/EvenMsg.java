package company.leon.gouwuche;

/**
 * Created by Leon on 2017/10/31.
 */

public class EvenMsg {
    private int mod;//信息模式
    //1 表示收藏修改 2表示添加到购物车
    private int goodsId;//表示商品编号
    boolean star;//标记收藏
    public EvenMsg(int mod,int goodsId,boolean star){
        this.mod=mod;
        this.goodsId=goodsId;
        this.star=star;
    }

    public void setMod(int mod) {
        this.mod = mod;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public boolean isStar() {
        return star;
    }

    public int getMod() {
        return mod;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setStar(boolean star) {
        this.star = star;
    }

}
