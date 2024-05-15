package com.heima.article.test;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.alibaba.fastjson.JSONArray;
import com.heima.article.mapper.ApArticleContentMapper;
import com.heima.article.mapper.ApArticleMapper;
import com.heima.file.service.FileStorageService;
import com.heima.model.article.pojos.ApArticle;
import com.heima.model.article.pojos.ApArticleContent;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ArticleFreemarkerTest {

    @Autowired
    private ApArticleContentMapper apArticleContentMapper;

    @Autowired
    private Configuration configuration;

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private ApArticleMapper apArticleMapper;

    /**
     * 已知文章id，生成内容的html模板文件。这里手动生成，后面会集成在添加文章功能里面。
     * @throws Exception
     * @author 徐路明
     */
    @Test
    public void createStaticUrlTest() throws Exception {
        // 1.接受文章内容
        LambdaQueryWrapper<ApArticleContent> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ApArticleContent::getArticleId,1383827787629252610L);
        ApArticleContent apArticleContent = apArticleContentMapper.selectOne(queryWrapper);
        if (apArticleContent != null && StringUtils.isNotBlank(apArticleContent.getContent())) {
            StringWriter out = new StringWriter();
            //2.文章内容通过freemarker生成html文件
            Template template = configuration.getTemplate("article.ftl");
            // 将数据模型放到map中
            Map<String,Object> content = new HashMap<>();
            content.put("content", JSONArray.parseArray(apArticleContent.getContent()));
            // 合成
            template.process(content, out);

            //3.把html上传到minio中
            InputStream is = new ByteArrayInputStream(out.toString().getBytes());
            String path = fileStorageService.uploadHtmlFile("", apArticleContent.getArticleId() + ".html", is);

            //4.修改ap_article表，保存static_url字段
            ApArticle article = new ApArticle();
            article.setId(apArticleContent.getArticleId());
            article.setStaticUrl(path);
            apArticleMapper.updateById(article);
        }

    }
}
