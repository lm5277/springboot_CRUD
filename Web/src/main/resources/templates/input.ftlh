<html>
<head>
    <script src="/webjars/jquery/3.4.0"></script>
    <title>编辑</title>
    <script type="text/javascript">

        $(function(){
            function aa()
            {
                $('#head').trigger("click");
            }
        });



    </script>

</head>
<body>

<!-- 数据模型。实体对象，用来新增与回显 -->
<form action="${springMacroRequestContext.contextPath}/user" method="POST" enctype="multipart/form-data">
    <br/><br/>

    <#if user.id ?? && user.id != 0>
        <input  type="hidden" name="id" value="${user.id}"/>
        <input type="hidden" name="_method" value="PUT"/>
    </#if>


    <#if user.id ??>
    <#else>
        姓名:<input type="text" name="name"><br>
    </#if>

    头像:
    <#if user.id ??  && user.id != 0>
        <img alt="" src="/head/show?id=${id}" width="50" height="50" onclick="aa()">
    </#if>

    <input type="file" name="head" id="head"><br>

    性别:
    <#if user.sex ??>
        <input type="radio" name="sex" value="1" <#if user.sex == 1>checked</#if> >男
        <input type="radio" name="sex" value="2" <#if user.sex == 2>checked</#if> >女<br>
    <#else>
        <input type="radio"  name="sex"  value="1"/>男

        <input type="radio" name="sex" value="2"/>女<br>
    </#if>

    日期:
    <#if user.birth ??>
        <input type="date" name="birth" value="${user.birth?string("yyyy-MM-dd")}"/><br>
    <#else>
        <input type="date" name="birth"/><br>
    </#if>

    工资:
    <#if user.salary ??>
        <input type="text" name="salary" value="${user.salary}"/><br>
    <#else>
        <input type="text" name="salary"/><br>
    </#if>


    <input type="submit" value="保存"/>
</form>



</body>
</html>