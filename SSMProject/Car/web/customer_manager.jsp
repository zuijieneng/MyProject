<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js"></script>
    <style>
        table{
            width:90%;
            margin: 50px 5%;
            border: 1px solid;
            border-collapse: collapse;
        }
        .tab_list td,th{
            border:1px solid;
            max-width:130px;text-overflow: ellipsis;overflow: hidden;
            width: 130px;
        }
    </style>
</head>
<body>
<table>
    <tr>
        <th>客户id</th>
        <th>头像</th>
        <th>姓名</th>
        <th>性别</th>
        <th>电话</th>
        <th>身份证号</th>
        <th>职业</th>
        <th>是否注销</th>
        <th>地址</th>
        <th>注册时间</th>
        <th>
            <input type="button" value="删除"  name="deleteCu">
        </th>
    </tr>
    <c:if test="${empty applicationScope.list}">
        <jsp:forward page="/customer/list.action"></jsp:forward>
    </c:if>
    <c:forEach items="${applicationScope.list}" var="cu" varStatus="vs">
        <tr>
            <td>${cu.customerId}</td>
            <td>
                <c:choose>
                    <c:when test="${cu.customerPhoto eq 'customer_003.jpg'}">
                        <img src="/images/customer/customer_003.jpg" style="width: 40px;height: 40px"/>
                    </c:when>
                    <c:when test="${cu.customerPhoto ne '1.jpg'}">
                        <img src="/img/upload/${cu.customerPhoto}" style="width: 40px;height: 40px"/>
                    </c:when>
                </c:choose>
            </td>
            <td>${cu.customerName}</td>
            <td>${cu.customerSex}</td>
            <td>${cu.customerPhone}</td>
            <td>${cu.customerPid}</td>
            <td>${cu.customerJob}</td>
            <td>
                <c:if test="${cu.customerStatus eq 1}">
                    否
                </c:if>
                <c:if test="${cu.customerStatus ne 1}">
                    是
                </c:if>
            </td>
            <td>${cu.customerAddress}</td>
            <td><fmt:formatDate value="${cu.customerTime}" pattern="yyyy-MM-dd"/></td>
            <td>
                <input type="radio" id="${cu.customerId}">
            </td>
        </tr>
    </c:forEach>
</table>
<!--分页、导航页--->
<a href="/customer/list.action?pageNumber=${applicationScope.pageInfo.prePage}">上一页</a>
<c:forEach items="${applicationScope.pageInfo.navigatepageNums}" var="navpage" varStatus="vs">
    <c:if test="${applicationScope.pageInfo.pageNum eq vs.index+1}">
            <span style="color: red">
                <a href="/customer/list.action?pageNumber=${navpage}">${navpage}</a>
            </span>
    </c:if>
    <c:if test="${applicationScope.pageInfo.pageNum ne vs.index+1}">
        <a href="/customer/list.action?pageNumber=${navpage}">${navpage}</a>
    </c:if>
</c:forEach>
<a href="/customer/list.action?pageNumber=${applicationScope.pageInfo.nextPage}">下一页</a>
<!--Excel导出功能-->
<a href="/excel/export.do">导出表格</a>

<h3>提示信息：${requestScope.errormessage}</h3>
<!--添加学生的表单-->
<h4 style="color: blue">添加用户</h4>
<div class="addStu" >
    <form action="/customer/insert.action" method="post" enctype="multipart/form-data">
        <tr>
            <td>
                客户id：<input type="text" name="customerId"/><span style="color: red" id="sidspan"></span>
            </td>
        </tr><br>
        <tr>
            <td>
                头像：<input type="file" name="photo" />
            </td>
        </tr><br>
        <tr>
            <td>
                姓名：<input type="text" name="customerName"/>
            </td>
        </tr><br>
        <tr>
            <td>
                性别：男:<input type="radio" name="customerSex" value="男" />|女:<input type="radio" name="customerSex" value="女" />
            </td>
        </tr><br>
        <tr>
            <td>电话：<input type="text" name="customerPhone">
            </td>
        </tr><br>
        <tr>
            <td>身份证号：<input type="text" name="customerPid">
            </td>
        </tr><br>
        <tr>
            <td>职业：<input type="text" name="customerJob"></tr>
        </td><br>
        <tr>
            <td>
                是否注销：否<input type="text" name="customerStatus" value="1" hidden="hidden">
            </td>
        </tr><br>
        <tr>
            <td>地址：<input type="text" name="customerAddress">
                <select id="province">
                </select>
                <select id="city">
                </select>
            </td>
        </tr><br>
        <tr>
            <td>注册时间：<input type="date" name="customerTime">
            </td>
        </tr><br>
        <tr><td><input type="submit" value="提交"/></td></tr>
    </form>
    </table>
</div>
<script type="text/javascript">
    $(function(){
        <!--点击checked，再次点击checked变为false-->
        $("input:radio").click(function(){
            var status=$(this).attr("checked");
            if(status){
                $(this).attr("checked",false);
            }else{
                $(this).attr("checked",true);

            }
        });
        <!--获取所有要删除的学生的sid，连成字符串类型，以‘,’隔开-->
        $("input[name='deleteCu']").click(function(){
            var list=$("input:radio:checked");
            var sid="";
            $.each(list,function(i,n){
                sid+=n.id+","; //便于批量删除，delete from student where sid in (sid)
            });
            sid=sid.substr(0,sid.length-1); //截取字符串：将最后一个‘，’去掉
            //跳转到删除controller，将学生sid和当前的页数传过去，当删除完之后仍然停留在当前页面
            window.location.href="/customer/delete.action?cid="+sid+"&pageNumber="+${applicationScope.pageInfo.pageNum};
        });
        var pros="";
        var city="";
        $.ajax({
            async:true,
            type:'GET',
            url:'/customer/getProvince.action',
            cache:false,
            dataType:'json',
            success:function (data) {
                $("select[id='province']").html("");
                $.each(data,function (i,n) {
                    $("select[id='province']").append("<option>"+n+"</option>");
                });
                $("select[id='province'] option").click(function () {
                    pros=$("select[id='province'] option:checked").text();
                    $.ajax({
                        async:true,
                        type:'GET',
                        url:'/customer/getCity.action',
                        cache:false,
                        data:"proName="+pros,
                        dataType:'json',
                        success:function (data) {
                            $("select[id='city']").html("");
                            $.each(data,function (i,n) {
                                $("select[id='city']").append("<option>"+n+"</option>");
                            });
                            $("select[id='city'] option").click(function () {
                                city=$("select[id='city'] option:checked").text();
                                //重新选择的时候要情空
                                $("input[name='customerAddress']").val("");
                                $("input[name='customerAddress']").val(pros+"-"+city);
                            })
                        }
                    });
                })
            }
        })
    })
</script>
</body>

</html>
