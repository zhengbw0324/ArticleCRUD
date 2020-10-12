package com.bw.util;

import com.bw.entity.Article;
import com.bw.entity.Content;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Component
public class ShowUtil {

    @Autowired
    ImageUtil imageUtil;

    public void showArticle(List<Article> articles, HttpServletResponse response) {


        List<String> base64String=imageUtil.showByBaseb64(articles);

        PrintWriter printWriter=null;
        response.setContentType("text/html;charset=UTF-8");
        try {
            int i=0;
            printWriter=response.getWriter();
            for(Article article:articles){
                printWriter.print("<font style='font-size:40'>"+article.getTitle()+"</font><br/>");
                printWriter.print("<font style='font-size:25'>"+"最新更新时间："+ article.getDate()+"</font><br/>");
                printWriter.print("<img  src="+ base64String.get(i)+" height=\"300\"><br/>");
                for(Content content:article.getContents()){
                    printWriter.print("<font style='font-size:25'>"+content.getPage().replace("\r", "<br/>")+"</font>");
                }
                printWriter.print("<br/>");
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            printWriter.close();
        }
    }
}
