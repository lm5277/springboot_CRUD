<html>
    <title>首页</title>
 <head>
     <script src="jquery-3.6.0.min.js"></script>
 </head>
    <script type="text/javascript">
        $(function(){
            $(".delete").click(function(){
                var href = $(this).attr("href");
                // alert(href);
                $("form").attr("action", href).submit();
                return false;
            })
        });

    </script>

<body>

    <form action="" method="POST" id="form">
        <input type="hidden" name="_method" value="DELETE"/>
    </form>


    <table border="1" cellpadding="10" cellspacing="0">
        <tr>
            <th>id</th>
            <th>头像</th>
            <th>姓名</th>
            <th>性别</th>
            <th>工资</th>
            <th>时间</th>
            <th>修改</th>
            <th>删除</th>
        </tr>

        <#list users as user>
            <tr>
                <td>${user.id}</td>
                <td><img width="50" height="50" alt="" src="head/show?id=${user.id }"></td>
                <td>${user.name}</td>
                <td>
                    <#if user.sex=1>
                        男
                    <#elseif user.sex=2>
                        女
                    </#if>
                </td>
                <td>${user.salary}</td>
                <td>${user.birth?string("yyyy-MM-dd")}</td>s
                <td><a href="user/${user.id}">修改</a></td>
                <td><a class="delete" href="${springMacroRequestContext.contextPath}/user/${user.id}">删除</a></td>
            </tr>
        </#list>

    </table>
<br>
<br>
<br>
<br>
<a href="tj">添加</a>
</body>
</html>