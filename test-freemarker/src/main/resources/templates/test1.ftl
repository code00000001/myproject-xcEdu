<DOCTYPE html>
    <html>
    <head>
        <meta charset="utf‐8">
        <title>Hello World!</title>
    </head>
    <body>
    Hello ${name}! <br>
    ===========================
    <body>
    <table>
        <tr>
            <td>序号</td>
            <td>姓名</td>
            <td>年龄</td>
            <td>金额</td>
        </tr>
        <#list stus as stu>
            <tr>
                <td>${stu_index + 1}</td>
                <td>${stu.name}</td>
                <td>${stu.age}</td>
                <td>${stu.money}</td>
            </tr>
        </#list>
    </table>
    <br/><br/>
    遍历map中的数据<br/>
    ${stuMap['stu1'].name}<br/>
    ${stuMap.stu1.age}<br/>
    ===========================<br/>
    map keys遍历<br/>

    <table>
        <tr>
            <td>序号</td>
            <td>姓名</td>
            <td>年龄</td>
            <td>金额</td>
        </tr>
        <#list stuMap?keys as k>
            <tr>
                <td>${k_index+1}</td>
                <td>${stuMap[k].name}</td>
                <td>${stuMap[k].age}</td>
                <td>${stuMap[k].money}</td>

            </tr>
        </#list>
    </table>
    <br/><br/>
    ===========================<br/>
    if指令<br/>
    <table>
        <tr>
            <td>姓名</td>
            <td>年龄</td>
            <td>金额</td>
        </tr>
        <#list stus as stu>
            <tr>
                <td <#if stu.name=='小明'>style="background: red"</#if>>${stu.name}</td>
                <td>${stu.age}</td>
                <td>${stu.money}</td>
            </tr>
        </#list>
    </table>
    </body>
    </html>
