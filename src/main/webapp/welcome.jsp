<%--
  Created by IntelliJ IDEA.
  User: 18735
  Date: 2020/10/10
  Time: 0:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <a href="article/findAll">查询所有文章</a>

    <form action="article/findByTitle" method="get">
        文章题目关键字：<input type="text" name="title"><br/>
        <input type="submit" value="查询">
    </form>

    <h3>保存文章</h3>
    <form action="article/saveArticle" method="post" enctype="multipart/form-data">
        文章题目：<input type="text" name="title"/><br/>
        文章图片：<input type="file" name="image"/><br/>
        文章内容：<textarea name="content"></textarea>
        <input type="submit" value="提交">
    </form>

    <h3>修改文章</h3>
    <form action="article/updateArticle" method="post" enctype="multipart/form-data">
        文章题目：<input type="text" name="title"/><br/>
        文章图片：<input type="file" name="image"/><br/>
        文章内容：<textarea name="content"></textarea>
        <input type="submit" value="修改">
    </form>

    <h3>删除文章</h3>
    <form action="article/deleteArticle" method="post">
        文章题目：<input type="text" name="title"/><br/>
        <input type="submit" value="删除">
    </form>
</body>
</html>
