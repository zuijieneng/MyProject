<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>云盘项目</title>
    <!--1.1 引入min.css-->
    <link type="text/css" rel="stylesheet" href="<c:url value='/bootstrap-3.3.7-dist/css/bootstrap.min.css'/>"/>
    <!--1.2 引入bootstrap-theme.min.css-->
    <link type="text/css" rel="stylesheet" href="<c:url value='/bootstrap-3.3.7-dist/css/bootstrap-theme.min.css'/>"/>

    <!--2.1 引入bootstrap-table.min.css-->
    <link type="text/css" rel="stylesheet" href="<c:url value='/bootstrap-3.3.7-dist/css/bootstrap-table.min.css'/>"/>

    <!-- 3.1 bootstarp-editable基本使用：1引入bootstrap-editable.css -->
    <link type="text/css" rel="stylesheet" href="<c:url value='/bootstrap-3.3.7-dist/css/bootstrap-editable.css'/>" />

    <!--1.3 引入jquery-->
    <script type="text/javascript" src="<c:url value='/js/jquery-3.3.1.min.js'/>"></script>
    <!--1.4 引入bootstrap.min.js-->
    <script type="text/javascript" src="<c:url value='/bootstrap-3.3.7-dist/js/bootstrap.min.js'/>"></script>

    <!--2.2 引入bootstrap-table.min.js-->
    <script type="text/javascript" src="<c:url value='/bootstrap-3.3.7-dist/js/bootstrap-table.min.js'/>"></script>
    <!--2.3 引入bootstrap-table-editable.min.js-->
    <script type="text/javascript" src="<c:url value='/bootstrap-3.3.7-dist/js/bootstrap-table-editable.min.js'/>"></script>
    <!--2.4 引入bootstrap-table-zh-CN.min.js-->
    <script type="text/javascript" src="<c:url value='/bootstrap-3.3.7-dist/js/bootstrap-table-zh-CN.min.js'/>"></script>

    <!--3.1 引入jquery.serializejson.min.js 使用serializeJSON方法-->
    <script type="text/javascript" src="<c:url value='/js/jquery.serializejson.min.js'/>"></script>

    <!--3.3 bootstarp-editable基本使用：2引入bootstrap-editable.min.js -->
    <script type="text/javascript"
            src="<c:url value='/bootstrap-3.3.7-dist/js/bootstrap-editable.min.js'/>"></script>
</head>
<body>
<!--0:定义全局变量-->
        <script type="text/javascript">
            var uid=1;
            var dfu="0";
        </script>

        <!--创建一个div装所有的bootdtrap组件-->
        <div class="container"  style="width:1000px;height:600px;">
            <!--带表格的面板-->
            <div class="panel panel-default">
                <div class="panel-heading">天道酬勤，不忘初衷！<span id="unameSpan"></span></div>
                <!--按钮组-->
                <div class="btn-group" role="group" aria-label="..."  id="toolbar">
                    <!--点击创建文件夹按钮  模态框addMyDirectoryModal显示   和点击事件完全相同-->
                    <button type="button" class="btn btn-default"  data-toggle="modal" data-target="#addMyDirectoryModal">创建文件夹</button>
                    <button type="button" class="btn btn-default" data-toggle="modal" data-target="#addMyFileModal">文件上传</button>
                    <button type="button" class="btn btn-default" id="myDirectoryBackButton">后退一步</button>
                    <button type="button" class="btn btn-default" id="deleteAllSelectedButton">删除选中</button>
                    <button type="button" class="btn btn-default"  id="userLogoutButton">退出登录</button>
                </div>
                <!--显示数据的表格-->
                <table class="table"  id="tab_data">

                </table>
            </div>
        </div>

        <!--登录模态框：类似于弹出框-->
        <div class="modal fade" id="userLoginModal" >
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title">用户登录</h4>
                    </div>
                    <div class="modal-body">
                        <form id="userLoginForm">
                            <div class="form-group">
                                <label class="control-label">用户名字：</label>
                                <input type="text" class="form-control"  name="uname" value="一号">
                            </div>
                            <div class="form-group">
                                <label class="control-label">用户密码：</label>
                                <input type="text" class="form-control"  name="upwd" value="123456">
                            </div>
                            <div class="form-group">
                                <label class="control-label">验&nbsp;证&nbsp;码：</label>
                                <img src="<c:url value='/kaptcha.jpg'/>"  id="img_yzm"/>
                                <input type="text" class="form-control"  name="uyzm">
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">退出</button>
                        <button type="button" class="btn btn-primary"  id="loginSubmitButton">登录</button>
                    </div>
                </div>
            </div>
        </div>
        <script type="text/javascript">
            $(function(){
                //文档加载成功  判断session域中是否有user
                var user="${sessionScope.user}";
                if(user){
                    uid="${sessionScope.user.uid}";
                    setTableData();
                }else{
                    $("#userLoginModal").modal("show");//弹出登录模态框
                }
            });
            $(function(){
                //登陆按钮
                $("#loginSubmitButton").bind("click",function(){
                    var uname=$("#userLoginForm input[name='uname']").val();
                    var upwd=$("#userLoginForm input[name='upwd']").val();
                    var uyzm=$("#userLoginForm input[name='uyzm']").val();
                    var loginUrlParam="uname="+uname+"&upwd="+upwd+"&uyzm="+uyzm;
                    $.get("/user/login.action",loginUrlParam,function(rv){
                        if(rv.code==1){
                            uid=rv.data.uid;
                            $("#unameSpan").html(rv.data.uname.fontcolor("blue"));
                            $("#userLoginModal").modal("hide");
                            //数据加载
                            setTableData();
                        }
                    },"json");
                });
                //更改验证码
                $("#img_yzm").click(function () {
                    $("#img_yzm").attr("src","http://localhost/kaptcha.jpg?id=" + new Date());
                });
            });
        </script>
        <script type="text/javascript">
            //退出登录
            $(function(){
                $("#userLogoutButton").bind("click",function(){
                    $.get("/user/logout.action",null,function(rv){
                        alert(rv.msg);
                        if(rv.code==1){
                            $("#tab_data").bootstrapTable("destroy"); //表格数据销毁
                            uid="";
                            dfu="0";
                            $("#unameSpan").html("");
                            $("#userLoginModal").modal("show");//登录模态框显示
                        }else if(responseVo.status==4000){
                            $("#userLoginModal").modal("show");//没有登陆 弹出模态框
                        }
                    },"json");
                });
            });
        </script>

        <!--3 文件上传的模态框-->
        <div class="modal fade" id="addMyFileModal">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title">文件上传</h4>
                    </div>
                    <div class="modal-body">
                        <form id="addMyFileForm">
                            <div class="form-group">
                                <label class="control-label">文件名字：</label>
                                <input type="text" class="form-control" name="dname">
                            </div>
                            <div class="form-group">
                                <label class="control-label">文件类型：</label>
                                <label class="radio-inline">
                                    <input type="radio" name="dprivate" value="1" checked>共享
                                </label>
                                <label class="radio-inline">
                                    <input type="radio" name="dprivate"  value="0"> 私有
                                </label>
                            </div>
                            <div class="form-group">
                                <label class="control-label">文件上传：</label>
                                <input type="file" class="form-control" name="photo">
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                        <button type="button" class="btn btn-primary" id="addMyFileSubmitButton">上传</button>
                    </div>
                </div>
            </div>
        </div>
        <script type="text/javascript">
            $(function(){
                $("#addMyFileSubmitButton").bind("click",function(){
                    //获取表单对应formdata对象
                    var dataForm=new FormData($("#addMyFileForm")[0]);
                    dataForm.append("uid",uid);
                    dataForm.append("dfu",dfu);
                    //ajax请求
                    $.ajax({
                        type:"POST",
                        data:dataForm,
                        url:"/file/upload.action",
                        dataType:"json",
                        async:false,
                        contentType:false,//如果请求参数是FormData 必须设置 contentType请求头为false
                        processData:false,//如果请求参数是FormData 必须设置 processData请求头为false
                        success:function(responseVo){
                            if(responseVo.code==1){
                                //添加模态框隐藏
                                $("#addMyFileModal").modal("hide");
                                //刷新表格信息
                                $("#tab_data").bootstrapTable("refresh"); //刷新表格
                            }else if(responseVo.status==4000){
                                $("#addMyFileModal").modal("hide");
                                $("#userLoginModal").modal("show");//没有登陆 弹出模态框
                            }
                        }
                    });
                });
            });
        </script>

        <!--3 创建文件夹模态框-->
        <div class="modal fade" id="addMyDirectoryModal">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="exampleModalLabel">创建文件夹</h4>
                    </div>
                    <div class="modal-body">
                        <form id="addMyDirectoryForm">
                            <div class="form-group">
                                <label class="control-label">文件夹名字：</label>
                                <input type="text" class="form-control" name="dname">
                            </div>
                            <div class="form-group">
                                <label class="control-label">文件夹类型：</label>
                                <label class="radio-inline">
                                    <input type="radio" name="dprivate" value="1" checked>共享
                                </label>
                                <label class="radio-inline">
                                    <input type="radio" name="dprivate"  value="0"> 私有
                                </label>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                        <button type="button" class="btn btn-primary" id="addMyDirectorySubmitButton">添加</button>
                    </div>
                </div>
            </div>
        </div>
        <!--创建文件夹的js-->
        <script type="text/javascript">
            $(function(){
                $("#addMyDirectorySubmitButton").bind("click",function(){
                    //拿表单的数据请求action  进行文件夹添加
                    var jsonObj=$("#addMyDirectoryForm").serializeJSON();//获取表单数据对应的json
                    jsonObj.uid=uid; //给json对象添加属性
                    jsonObj.dfu=dfu; //给json对象添加属性
                    var jsonStr=JSON.stringify(jsonObj);//获取jsong对象对应的字符串
                    $.ajax({
                        url:"/directory/insert.action",
                        type:"POST",
                        data:jsonStr,
                        cache:false,
                        async:false,
                        dataType:"json",
                        contentType:"application/json;charset=utf-8",/*请求参数是json：必须加请求头contentType 指定请求参数的类型*/
                        success:function(responseVo){
                            alert(responseVo.msg);
                            if(responseVo.code==1){
                                //当前模态框隐藏
                                $("#addMyDirectoryModal").modal("hide");
                                //刷新表格的数据
                                $("#tab_data").bootstrapTable("refresh"); //刷新表格
                            }else if(responseVo.status==4000){
                                $("#addMyDirectoryModal").modal("hide");
                                $("#userLoginModal").modal("show");//没有登陆 弹出模态框
                            }
                        }
                    });
                });
            });
        </script>
        <!--删除选中-->
        <script type="text/javascript">
            $(function(){
                $("#deleteAllSelectedButton").bind("click",function(){
                    //获取那些行的checkbox被选中
                    var coll=$("#tab_data").bootstrapTable("getSelections");
                    var dids="";
                    for(var i=0;i<coll.length;i++){
                        dids+=(coll[i].did+"");//111234111
                        if(i<coll.length-1){
                            dids+=",";//111234111
                        }
                    }
                    var b=confirm("确定要删除吗？");
                    if(!b){return;}
                    //发送ajax请求
                    $.ajax({
                        type:"DELETE",
                        data:null,
                        url:"/directory/delete/"+dids+".action",
                        dataType:"json",
                        async:false,
                        cache:false,
                        success:function(responseVo){
                            alert(responseVo.msg);
                            if(responseVo.code==1){
                                //刷新表格信息
                                $("#tab_data").bootstrapTable("refresh"); //刷新表格
                            }else if(responseVo.status==4000){
                                $("#userLoginModal").modal("show");//没有登陆 弹出模态框

                            }
                        }
                    });
                });
            });
        </script>

        <!--1：给表格填充数据-->
        <script type="text/javascript">
            function setTableData(){
                $("#tab_data").bootstrapTable({
                    url:"/directory/child.action",
                    //data:"uid="+uid+"&dfu="+dfu,
                    type:"GET",
                    cache:false,
                    queryParams:{"uid":uid,"dfu":dfu},
                    dateType:"json",
                    //对相应数据进行解析
                    //分页步骤3：修改responseHandler 相应结果需要指定rows(当前用要显示的行记录)和total(总记录数)
                    responseHandler:function(responseVo){
                        if(responseVo.code==1){
                            //方法的参数responseVo是 相应对象responseVo
                            return  responseVo.data;
                        }
                        return null;
                    },
                    //设置列名和列值：field指定responseVo.data的元素的属性 title指定列名
                    columns:[
                        {checkbox:true},/*删除的多选框*/
                        {field:"dname",title:"文件名字",formatter:function(value,row,index){
                                if(row.dtype==1){//文件：点击下载
                                    var downloadUrl="/file/download.action?dfid="+row.dfid;
                                    return '<a  class="btn btn-default" href="'+downloadUrl+'"><span class="glyphicon glyphicon-save" aria-hidden="true"></span>&emsp;'+row.dname+'</a>';
                                }
                                //文件夹：点击打开
                                return '<button type="button" class="btn btn-default" onclick="changeDfu('+row.did+')"><span class="glyphicon glyphicon-folder-open" aria-hidden="true"></span>&emsp;'+row.dname+'</button>';
                            }
                        },
                        {field:"dtime",title:"创建时间",formatter:function(value,row,index){
                                var myDate=new Date(row.dtime);
                                return myDate.getFullYear()+"年"+(myDate.getMonth()+1)+"月"+myDate.getDate()+"号";
                            }
                        },
                        {field:"dsize",title:"文件大小(kb)"},
                        {field:"dprivate",title:"文件类型",editable:{
                                  title:"请选择类型：",
                                  type:"select",
                                  source:[{value:"1",text:"共享"},{value:"0",text:"私有"}]}
                        }
                    ],
                    onEditableSave: function(field,row,oldValue){
                        //把行数据转换为json串
                        var  rowJsonStr=JSON.stringify(row);
                        //发送ajax请求
                        $.ajax({
                            type:"PUT",
                            data:rowJsonStr,
                            cache:false,
                            async:false,
                            contentType:"application/json;charset=utf-8",/*请求参数是json：必须加请求头contentType 指定请求参数的类型*/
                            url:"/directory/update.action",
                            success:function(responseVo){
                                alert(responseVo.msg);
                                if(responseVo.code==1){
                                    $("#tab_data").bootstrapTable("refresh"); //刷新表格
                                }
                            }
                        });
                    }
                });
            }
            $(function(){
                //文档加载成功！ 数据加载一次
                //setTableData();
            });
            //打开文件夹
            function changeDfu(did){
                dfu=dfu+"-"+did; //0-1-3
                $('#tab_data').bootstrapTable('destroy');//清空表格的数据
                //刷新表格的数据
                setTableData();
            }
            //后退一步
            $(function(){
                $("#myDirectoryBackButton").bind("click",function(){
                    if(dfu!="0"){
                        dfu=dfu.substring(0,dfu.lastIndexOf("-"));//0-1-2-3
                        $('#tab_data').bootstrapTable('destroy');//清空表格的数据
                        //刷新表格的数据
                        setTableData();
                    }
                });
            })
        </script>
</body>
</html>