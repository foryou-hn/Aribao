package com.example.pc.myapplication.bean;

import java.util.List;

/**
 * 文 件 名：NewsBean
 * 描    述：
 * 作    者：chenhao
 * 时    间：2018/11/23
 * 版    权：v1.0
 */
public class NewsBean {

    /**
     * date : 20181123
     * stories : [{"ga_prefix":"112313","id":9702236,"images":["https://pic1.zhimg.com/v2-90663931e889a42612aabd890578aac0.jpg"],"title":"2018，创业黄金时代结束的一年","type":0},{"ga_prefix":"112312","id":9702228,"images":["https://pic4.zhimg.com/v2-b87b60e317be50ee6fda47fc0e81f77b.jpg"],"title":"大误 · 世纪难题：两个人盖一床被子怎么才能不漏风？","type":0},{"ga_prefix":"112310","id":9701866,"images":["https://pic3.zhimg.com/v2-e2fd317396bc14af906da741441cf8f6.jpg"],"multipic":true,"title":"科研我看不懂，但这些实验照片可是真惊艳啊","type":0},{"ga_prefix":"112309","id":9702141,"images":["https://pic3.zhimg.com/v2-8350fa5a9aadeb091d239ea1ecb9399a.jpg"],"title":"什么样的报表真难看？拿这种好看的比一下恍然大悟","type":0},{"ga_prefix":"112308","id":9702186,"images":["https://pic3.zhimg.com/v2-ce363a0c0cdca1e933d963af964fda66.jpg"],"title":"怀孕得公司审批，否则要么流产要么处分，怎么会有这种规定？","type":0},{"ga_prefix":"112307","id":9702116,"images":["https://pic4.zhimg.com/v2-7a683d18005c4b257c0b1e94c2fbed47.jpg"],"multipic":true,"title":"对，你没看错，这些东西我真的手动做出来了","type":0},{"ga_prefix":"112306","id":9702096,"images":["https://pic4.zhimg.com/v2-235768298e17a4bbfb7ea840ee72361b.jpg"],"title":"瞎扯 · 如何正确地吐槽","type":0}]
     * top_stories : [{"ga_prefix":"112313","id":9702236,"image":"https://pic4.zhimg.com/v2-1dd8719cb92c459937f7d2958163c617.jpg","title":"2018，创业黄金时代结束的一年","type":0},{"ga_prefix":"112310","id":9701866,"image":"https://pic3.zhimg.com/v2-df56230ac8710c52dacb68867fe3c81e.jpg","title":"科研我看不懂，但这些实验照片可是真惊艳啊","type":0},{"ga_prefix":"112220","id":9702068,"image":"https://pic2.zhimg.com/v2-c23587054e65d32192b04f2552183d49.jpg","title":"一代鸭杰 · 史高治 · 麦克达克","type":0},{"ga_prefix":"112218","id":9702053,"image":"https://pic4.zhimg.com/v2-8f024b738b4b1522a173ac44ab3c8cb3.jpg","title":"- 看这个球，转得越快，你越焦虑 - 请您住嘴","type":0},{"ga_prefix":"112209","id":9701942,"image":"https://pic2.zhimg.com/v2-d4d71015adcd4ae988b045757d771a7d.jpg","title":"校园可以同时开拍哈利波特和还珠格格，这是中国最神奇的大学","type":0}]
     */

    private String date;
    private List<StoriesBean> stories;
    private List<TopStoriesBean> top_stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<StoriesBean> getStories() {
        return stories;
    }

    public void setStories(List<StoriesBean> stories) {
        this.stories = stories;
    }

    public List<TopStoriesBean> getTop_stories() {
        return top_stories;
    }

    public void setTop_stories(List<TopStoriesBean> top_stories) {
        this.top_stories = top_stories;
    }

    public static class StoriesBean {
        /**
         * ga_prefix : 112313
         * id : 9702236
         * images : ["https://pic1.zhimg.com/v2-90663931e889a42612aabd890578aac0.jpg"]
         * title : 2018，创业黄金时代结束的一年
         * type : 0
         * multipic : true
         */

        private String ga_prefix;
        private int id;
        private String title;
        private int type;
        private boolean multipic;
        private List<String> images;

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public boolean isMultipic() {
            return multipic;
        }

        public void setMultipic(boolean multipic) {
            this.multipic = multipic;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }

    public static class TopStoriesBean {
        /**
         * ga_prefix : 112313
         * id : 9702236
         * image : https://pic4.zhimg.com/v2-1dd8719cb92c459937f7d2958163c617.jpg
         * title : 2018，创业黄金时代结束的一年
         * type : 0
         */

        private String ga_prefix;
        private int id;
        private String image;
        private String title;
        private int type;

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}
