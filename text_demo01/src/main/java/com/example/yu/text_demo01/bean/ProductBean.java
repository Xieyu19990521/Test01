package com.example.yu.text_demo01.bean;

public class ProductBean {
    String msg;
    String code;
    Seller seller;
    Data data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class Seller{
        String description;
        String icon;
        String name;
        long productNums;
        double score;
        long sellerid;

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public long getProductNums() {
            return productNums;
        }

        public void setProductNums(long productNums) {
            this.productNums = productNums;
        }

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }

        public long getSellerid() {
            return sellerid;
        }

        public void setSellerid(long sellerid) {
            this.sellerid = sellerid;
        }
    }
    public class Data{
        long bargainPrice;
        String createtime;
        String detailUrl;
        String images;
        long itemtype;
        long pid;
        long price;
        long pscid;
        long salenum;
        long sellerid;
        String subhead;
        String title;

        public long getBargainPrice() {
            return bargainPrice;
        }

        public void setBargainPrice(long bargainPrice) {
            this.bargainPrice = bargainPrice;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getDetailUrl() {
            return detailUrl;
        }

        public void setDetailUrl(String detailUrl) {
            this.detailUrl = detailUrl;
        }

        public String getImages() {
            return images;
        }

        public void setImages(String images) {
            this.images = images;
        }

        public long getItemtype() {
            return itemtype;
        }

        public void setItemtype(long itemtype) {
            this.itemtype = itemtype;
        }

        public long getPid() {
            return pid;
        }

        public void setPid(long pid) {
            this.pid = pid;
        }

        public long getPrice() {
            return price;
        }

        public void setPrice(long price) {
            this.price = price;
        }

        public long getPscid() {
            return pscid;
        }

        public void setPscid(long pscid) {
            this.pscid = pscid;
        }

        public long getSalenum() {
            return salenum;
        }

        public void setSalenum(long salenum) {
            this.salenum = salenum;
        }

        public long getSellerid() {
            return sellerid;
        }

        public void setSellerid(long sellerid) {
            this.sellerid = sellerid;
        }

        public String getSubhead() {
            return subhead;
        }

        public void setSubhead(String subhead) {
            this.subhead = subhead;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
