package com.bw.util;

import com.bw.entity.Article;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Component;
import javax.imageio.stream.FileImageOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

@Component
public class ImageUtil {
    //图片暂存路径
    private final static String PATH="C:/Users/18735/IdeaProjects/ArticleCRUD/src/main/webapp/temp/";
    //将从数据库读入的文件暂存本地
    public void saveLocal(List<Article> articles,HttpServletRequest request ){
        File file=new File(PATH);
        FileImageOutputStream outputStream = null;
        if(!file.exists()){
            file.mkdirs();
        }
        for(Article article:articles){
            try {
                outputStream = new FileImageOutputStream(new File(PATH+"/"+article.getId()+".jpg"));
                outputStream.write(article.getImage(),0,article.getImage().length);
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if(outputStream!=null){
                    try {
                        outputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void deleteImages() {
        File file = new File(PATH);
        if(file.isFile())
        {
        }else {
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                    files[i].delete();
            }
        }
    }

    public List<String> showByBaseb64(List<Article> articles){
        List<String> base64String=new ArrayList<>();
        for(Article article:articles){
            base64String.add("data:image/jpeg;base64,"+Base64.encodeBase64String(article.getImage()));
        }
        return base64String;
    }

    public void showImage(byte[] image, HttpServletResponse response){
        OutputStream os = null;
        response.setContentType("image/*");

        try {
            os = response.getOutputStream();
            os.write(image);
        } catch (IOException e) {
            e.printStackTrace();
        }  finally {
            if(os != null){
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
