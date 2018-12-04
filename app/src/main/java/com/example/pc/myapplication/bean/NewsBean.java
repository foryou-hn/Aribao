package com.example.pc.myapplication.bean;

import java.util.List;

/**
 * 文 件 名：NewsBean
 * 描    述：
 * 作    者：chenhao
 * 时    间：2018/11/29
 * 版    权：v1.0
 */
public class NewsBean {

    /**
     * body : <div class="main-wrap content-wrap">
     <div class="headline">

     <div class="img-place-holder"></div>



     </div>

     <div class="content-inner">




     <div class="question">
     <h2 class="question-title">有哪些用八个字连续说出来就包含三个成语的例子？</h2>

     <div class="answer">

     <div class="meta">
     <img class="avatar" src="http://pic2.zhimg.com/10c64abb01e2573c7f6f5c7d5e6116ed_is.jpg">
     <span class="author">路路，</span><span class="bio">做点自己感兴趣的事，发现更大的世界。</span>
     </div>

     <div class="content">
     <p>考虑到自己能想起的成语数量实在有限，所以就把这个问题交给计算机来处理吧。</p>
     <p>首先，很容易就在网上下载到了一个 access 格式的数据库，经检查实际存储了去重后的四字成语两万九千多个。导出为 txt 格式的文件。</p>
     <p><img class="content-image" src="http://pic2.zhimg.com/70/v2-272e4e445cc497558d35ed6c2017d431_b.jpg" alt=""></p>
     <p>然后打开了 eclipse ，写下了以下代码：</p>
     <div class="highlight">
     <pre><code class="language-java"><span class="kn">package</span> <span class="nn">com.example.chengyuconnect</span><span class="o">;</span>

     <span class="kn">import</span> <span class="nn">java.io.BufferedReader</span><span class="o">;</span>
     <span class="kn">import</span> <span class="nn">java.io.File</span><span class="o">;</span>
     <span class="kn">import</span> <span class="nn">java.io.FileInputStream</span><span class="o">;</span>
     <span class="kn">import</span> <span class="nn">java.io.InputStreamReader</span><span class="o">;</span>
     <span class="kn">import</span> <span class="nn">java.sql.Connection</span><span class="o">;</span>
     <span class="kn">import</span> <span class="nn">java.sql.ResultSet</span><span class="o">;</span>
     <span class="kn">import</span> <span class="nn">java.sql.Statement</span><span class="o">;</span>
     <span class="kn">import</span> <span class="nn">java.sql.DriverManager</span><span class="o">;</span>

     <span class="kd">public</span> <span class="kd">class</span> <span class="nc">ChengyuConnect</span> <span class="o">{</span>
     <span class="kd">static</span> <span class="n">String</span><span class="o">[]</span> <span class="n">cy</span> <span class="o">=</span> <span class="k">new</span> <span class="n">String</span><span class="o">[</span><span class="mi">30000</span><span class="o">];</span>
     <span class="kd">static</span> <span class="n">String</span><span class="o">[]</span> <span class="n">cy1</span><span class="o">=</span><span class="k">new</span> <span class="n">String</span><span class="o">[</span><span class="mi">30000</span><span class="o">];</span>
     <span class="kd">static</span> <span class="n">String</span><span class="o">[]</span> <span class="n">cy2</span><span class="o">=</span><span class="k">new</span> <span class="n">String</span><span class="o">[</span><span class="mi">30000</span><span class="o">];</span>

     <span class="kd">public</span> <span class="kd">static</span> <span class="kt">void</span> <span class="nf">main</span><span class="o">(</span><span class="n">String</span><span class="o">[]</span> <span class="n">args</span><span class="o">)</span> <span class="o">{</span>
     <span class="n">getCy</span><span class="o">();</span>
     <span class="n">splitCy</span><span class="o">();</span>
     <span class="n">searchCy</span><span class="o">();</span>
     <span class="o">}</span>

     <span class="kd">static</span> <span class="kt">void</span> <span class="nf">getCy</span><span class="o">()</span> <span class="o">{</span>
     <span class="k">try</span> <span class="o">{</span>
     <span class="n">File</span> <span class="n">f</span> <span class="o">=</span> <span class="k">new</span> <span class="n">File</span><span class="o">(</span><span class="s">"F:\\chengyu.txt"</span><span class="o">);</span>
     <span class="n">InputStreamReader</span> <span class="n">reader</span> <span class="o">=</span> <span class="k">new</span> <span class="n">InputStreamReader</span><span class="o">(</span><span class="k">new</span> <span class="n">FileInputStream</span><span class="o">(</span><span class="n">f</span><span class="o">),</span><span class="s">"Unicode"</span><span class="o">);</span>
     <span class="n">BufferedReader</span> <span class="n">br</span> <span class="o">=</span> <span class="k">new</span> <span class="n">BufferedReader</span><span class="o">(</span><span class="n">reader</span><span class="o">);</span>
     <span class="n">String</span> <span class="n">line</span> <span class="o">=</span> <span class="n">br</span><span class="o">.</span><span class="na">readLine</span><span class="o">();</span>
     <span class="k">for</span><span class="o">(</span><span class="kt">int</span> <span class="n">n</span> <span class="o">=</span> <span class="mi">0</span><span class="o">;</span><span class="n">line</span><span class="o">.</span><span class="na">length</span><span class="o">()==</span><span class="mi">4</span><span class="o">;</span><span class="n">n</span><span class="o">++)</span> <span class="o">{</span>
     <span class="n">cy</span><span class="o">[</span><span class="n">n</span><span class="o">]</span> <span class="o">=</span> <span class="n">line</span><span class="o">;</span>
     <span class="n">line</span> <span class="o">=</span> <span class="n">br</span><span class="o">.</span><span class="na">readLine</span><span class="o">();</span>
     <span class="o">}</span>
     <span class="o">}</span> <span class="k">catch</span> <span class="o">(</span><span class="n">Exception</span> <span class="n">e</span><span class="o">)</span> <span class="o">{</span>
     <span class="n">e</span><span class="o">.</span><span class="na">printStackTrace</span><span class="o">();</span>
     <span class="o">}</span>
     <span class="o">}</span>

     <span class="kd">static</span> <span class="kt">void</span> <span class="nf">splitCy</span><span class="o">()</span> <span class="o">{</span>
     <span class="k">for</span><span class="o">(</span><span class="kt">int</span> <span class="n">i</span> <span class="o">=</span> <span class="mi">0</span><span class="o">;</span><span class="n">i</span><span class="o">&lt;</span><span class="n">cy</span><span class="o">.</span><span class="na">length</span> <span class="o">&amp;&amp;</span> <span class="n">cy</span><span class="o">[</span><span class="n">i</span><span class="o">+</span><span class="mi">1</span><span class="o">]!=</span><span class="kc">null</span> <span class="o">;</span><span class="n">i</span><span class="o">++)</span> <span class="o">{</span>
     <span class="n">cy1</span><span class="o">[</span><span class="n">i</span><span class="o">]</span> <span class="o">=</span> <span class="n">cy</span><span class="o">[</span><span class="n">i</span><span class="o">].</span><span class="na">substring</span><span class="o">(</span><span class="mi">0</span><span class="o">,</span><span class="mi">2</span><span class="o">);</span>
     <span class="n">cy2</span><span class="o">[</span><span class="n">i</span><span class="o">]</span> <span class="o">=</span> <span class="n">cy</span><span class="o">[</span><span class="n">i</span><span class="o">].</span><span class="na">substring</span><span class="o">(</span><span class="mi">2</span><span class="o">,</span><span class="mi">4</span><span class="o">);</span>
     <span class="o">}</span>
     <span class="o">}</span>

     <span class="kd">static</span> <span class="kt">void</span> <span class="nf">searchCy</span><span class="o">()</span> <span class="o">{</span>
     <span class="k">for</span><span class="o">(</span><span class="kt">int</span> <span class="n">i</span><span class="o">=</span><span class="mi">0</span><span class="o">;</span><span class="n">i</span><span class="o">&lt;</span><span class="n">cy</span><span class="o">.</span><span class="na">length</span><span class="o">;</span><span class="n">i</span><span class="o">++)</span>
     <span class="k">for</span><span class="o">(</span><span class="kt">int</span> <span class="n">j</span><span class="o">=</span><span class="mi">0</span><span class="o">;</span><span class="n">j</span><span class="o">&lt;</span><span class="n">cy</span><span class="o">.</span><span class="na">length</span><span class="o">;</span><span class="n">j</span><span class="o">++)</span>
     <span class="k">if</span><span class="o">(</span><span class="n">cy2</span><span class="o">[</span><span class="n">i</span><span class="o">]</span> <span class="o">!=</span> <span class="kc">null</span> <span class="o">&amp;&amp;</span> <span class="n">cy1</span><span class="o">[</span><span class="n">j</span><span class="o">]</span> <span class="o">!=</span> <span class="kc">null</span> <span class="o">&amp;&amp;</span> <span class="n">cy2</span><span class="o">[</span><span class="n">i</span><span class="o">].</span><span class="na">equals</span><span class="o">(</span><span class="n">cy1</span><span class="o">[</span><span class="n">j</span><span class="o">]))</span>
     <span class="k">for</span><span class="o">(</span><span class="kt">int</span> <span class="n">k</span><span class="o">=</span><span class="mi">0</span><span class="o">;</span><span class="n">k</span><span class="o">&lt;</span><span class="n">cy</span><span class="o">.</span><span class="na">length</span><span class="o">;</span><span class="n">k</span><span class="o">++)</span>
     <span class="k">if</span><span class="o">(</span><span class="n">cy2</span><span class="o">[</span><span class="n">j</span><span class="o">]</span> <span class="o">!=</span> <span class="kc">null</span> <span class="o">&amp;&amp;</span> <span class="n">cy1</span><span class="o">[</span><span class="n">k</span><span class="o">]</span> <span class="o">!=</span> <span class="kc">null</span> <span class="o">&amp;&amp;</span> <span class="n">cy2</span><span class="o">[</span><span class="n">j</span><span class="o">].</span><span class="na">equals</span><span class="o">(</span><span class="n">cy1</span><span class="o">[</span><span class="n">k</span><span class="o">]))</span>
     <span class="n">System</span><span class="o">.</span><span class="na">out</span><span class="o">.</span><span class="na">println</span><span class="o">(</span><span class="n">cy</span><span class="o">[</span><span class="n">i</span><span class="o">]+</span><span class="n">cy</span><span class="o">[</span><span class="n">k</span><span class="o">]);</span>
     <span class="o">}</span>

     <span class="o">}</span>
     </code></pre>
     </div>
     <p>大概的思路是：</p>
     <ol><li>读取 txt 文件里的成语，每次读取一行，将四字成语存储到一个数组里；</li>
     <li>将成语的前两个字和后两个字分开存储到两个数组里；</li>
     <li>根据题意，符合条件的三个成语，需要满足第一个成语的后两个字 ，是第二个成语的前两个字，第二个成语的后两个字，又是第三个成语的前两个字。所以写三个嵌套的循环，从存储了后两个字的数组开始，和存储了前两个字的数组作比较，如果两个字相同，就再使用后者对应的成语的后两个字，和存储了前两个字的数组作比较，如果还能发现相同的，就表示这三个成语符合条件。</li>
     </ol><p>运行，几秒钟就可以看到所有结果：</p>
     <p><img class="content-image" src="http://pic2.zhimg.com/70/v2-480487fb7570714cc59423ef5d39f79d_b.jpg" alt=""></p>
     <p>将结果复制粘贴到表格里，数量 2 万 5809：</p>
     <p><img class="content-image" src="http://pic1.zhimg.com/70/v2-863432bec104c82167a1b945f2de8c24_b.jpg" alt=""></p>
     <p>全部结果就不发出来了，因为不知道知乎是否支持写 20 多万字的回答。</p>
     <hr><p>在表格的截图里，还有三个单元表，CY 看起来像是表示成语……嗯，这其实是一个失败的尝试。本想直接在表格里处理，可是没料到 VLOOKUP 函数处理速度太慢，几万条数据直接让表格崩溃了，所以最后就留一张测试部分的截图作为纪念吧。</p>
     <p><img class="content-image" src="http://pic4.zhimg.com/70/v2-29edbfd2590af67fa12087b8f8126ab7_b.jpg" alt=""></p>
     <hr><p>2018.11.29 更新</p>
     <p>感谢 @忠肝义胆吕奉先 在评论中指出回答里的一个问题：在输出的结果中，存在前后两个成语相同的情况，如果认为三个成语是互不相同的成语，则需要在代码中限制输出的是前后不相同的两个成语连接起来的八个字。</p>
     <p>除了直接改代码，也可以把结果复制粘贴到表格里，在表格里进行判断和筛选，这样还能发现一批前后两个字互换位置，结果仍是成语的成语。</p>
     </div>
     </div>


     <div class="view-more"><a href="http://www.zhihu.com/question/303120362">查看知乎讨论<span class="js-question-holder"></span></a></div>

     </div>


     </div>
     </div><script type=“text/javascript”>window.daily=true</script>
     * image_source : 编辑一口气瞎说
     * title : 说出八个字包含三个成语，不难，我来说 20000 个吧
     * image : https://pic2.zhimg.com/v2-8039bf487d591fb8c3683bf88ea5d11d.jpg
     * share_url : http://daily.zhihu.com/story/9702656
     * js : []
     * ga_prefix : 112916
     * images : ["https://pic2.zhimg.com/v2-ac66f852e1a94ed1625ed097bc5537bd.jpg"]
     * type : 0
     * id : 9702656
     * css : ["http://news-at.zhihu.com/css/news_qa.auto.css?v=4b3e3"]
     */

    private String body;
    private String image_source;
    private String title;
    private String image;
    private String share_url;
    private String ga_prefix;
    private int type;
    private int id;
    private List<?> js;
    private List<String> images;
    private List<String> css;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getImage_source() {
        return image_source;
    }

    public void setImage_source(String image_source) {
        this.image_source = image_source;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public String getGa_prefix() {
        return ga_prefix;
    }

    public void setGa_prefix(String ga_prefix) {
        this.ga_prefix = ga_prefix;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<?> getJs() {
        return js;
    }

    public void setJs(List<?> js) {
        this.js = js;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public List<String> getCss() {
        return css;
    }

    public void setCss(List<String> css) {
        this.css = css;
    }
}
