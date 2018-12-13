<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<div class="navbar-header">
    <a class="navbar-minimalize minimalize-styl-2 btn btn-primary " href="#"><i class="fa fa-bars"></i> </a>
</div>
<ul class="nav navbar-top-links navbar-right notification-menu">
    <%--<li class="dropdown"><a href="#" class="dropdown-toggle count-info top-language" data-toggle="dropdown"> <i class="fa fa-globe"></i> <span>选择语言</span> <span class="caret"></span>--%>
    <%--</a>--%>
    <%--<ul class="dropdown-menu dropdown-menu-usermenu pull-right">--%>
    <%--<li><a href="#"> 简体中文</a></li>--%>
    <%--<li><a href="#"> 马来西亚</a></li>--%>
    <%--<li><a href="#"> 日语</a></li>--%>
    <%--<li><a href="#"> 繁体中文</a></li>--%>
    <%--</ul></li>--%>
    <%--<li class="dropdown"><a class="dropdown-toggle count-info" data-toggle="dropdown" href="#"> <i class="fa fa-envelope"></i> <span class="label label-warning">16</span>--%>
    <%--</a>--%>
    <%--<div class="dropdown-menu dropdown-menu-head pull-right">--%>
    <%--<h5 class="title">You have 5 Mails</h5>--%>
    <%--<ul class="dropdown-list normal-list">--%>
    <%--<li class="new"><a href=""> <span class="thumb"><img src="${ctx}/static/images/photos/user1.png" alt=""></span> <span class="desc"> <span class="name">陈小林 <span class="badge badge-success">new</span></span> <span class="msg">小帅哥晚上有空吗？一起出来玩吧！...</span>--%>
    <%--</span>--%>
    <%--</a></li>--%>
    <%--<li><a href=""> <span class="thumb"><img src="${ctx}/static/images/photos/user2.png" alt=""></span> <span class="desc"> <span class="name">Jonathan Smith</span> <span class="msg">Lorem ipsum dolor sit amet...</span>--%>
    <%--</span>--%>
    <%--</a></li>--%>
    <%--<li><a href=""> <span class="thumb"><img src="${ctx}/static/images/photos/user3.png" alt=""></span> <span class="desc"> <span class="name">Jane Doe</span> <span class="msg">Lorem ipsum dolor sit amet...</span>--%>
    <%--</span>--%>
    <%--</a></li>--%>
    <%--<li><a href=""> <span class="thumb"><img src="${ctx}/static/images/photos/user4.png" alt=""></span> <span class="desc"> <span class="name">Mark Henry</span> <span class="msg">Lorem ipsum dolor sit amet...</span>--%>
    <%--</span>--%>
    <%--</a></li>--%>
    <%--<li><a href=""> <span class="thumb"><img src="${ctx}/static/images/photos/user5.png" alt=""></span> <span class="desc"> <span class="name">Jim Doe</span> <span class="msg">Lorem ipsum dolor sit amet...</span>--%>
    <%--</span>--%>
    <%--</a></li>--%>
    <%--<li class="new"><a href="">Read All Mails</a></li>--%>
    <%--</ul>--%>
    <%--</div></li>--%>
    <%--<li class="dropdown"><a class="dropdown-toggle count-info" data-toggle="dropdown" href="#"> <i class="fa fa-bell"></i> <span class="label label-primary">8</span>--%>
    <%--</a>--%>
    <%--<div class="dropdown-menu dropdown-menu-head pull-right">--%>
    <%--<h5 class="title">Notifications</h5>--%>
    <%--<ul class="dropdown-list normal-list">--%>
    <%--<li class="new"><a href=""> <span class="label label-danger"><i class="fa fa-bolt"></i></span> <span class="name">Server #1 overloaded. </span> <em class="small">34 mins</em>--%>
    <%--</a></li>--%>
    <%--<li class="new"><a href=""> <span class="label label-danger"><i class="fa fa-bolt"></i></span> <span class="name">Server #3 overloaded. </span> <em class="small">1 hrs</em>--%>
    <%--</a></li>--%>
    <%--<li class="new"><a href=""> <span class="label label-danger"><i class="fa fa-bolt"></i></span> <span class="name">Server #5 overloaded. </span> <em class="small">4 hrs</em>--%>
    <%--</a></li>--%>
    <%--<li class="new"><a href=""> <span class="label label-danger"><i class="fa fa-bolt"></i></span> <span class="name">Server #31 overloaded. </span> <em class="small">4 hrs</em>--%>
    <%--</a></li>--%>
    <%--<li class="new"><a href="">See All Notifications</a></li>--%>
    <%--</ul>--%>
    <%--</div></li>--%>

    <li class="user-dropdown">
        <a href="#" class="btn  dropdown-toggle" data-toggle="dropdown">
            <img src="${ctx}/static/images/photos/user-avatar.png" alt="" width="20">
            <shiro:principal/>
            <span class="caret"></span>
        </a>
        <ul class="dropdown-menu dropdown-menu-usermenu pull-right">
            <%--<li><a href="#"><i class="fa fa-user"></i> Profile</a></li>--%>
            <%--<li><a href="#"><i class="fa fa-cog"></i> Settings</a></li>--%>
            <li><a href="#" data-toggle="modal" data-target="#update-password"><i class="fa fa-pencil-square-o"></i>
                修改密码</a></li>
            <li><a href="${ctx }/logout"><i class="fa fa-sign-out"></i> 退出</a></li>
        </ul>
    </li>
</ul>
<%--<td class="text-right text-nowrap">--%>
<%--<div class="btn-group ">--%>
<%--<button class="btn btn-white btn-sm edit" data-id="${n.id }" data-toggle="modal"--%>
<%--data-target="#edit">--%>
<%--<i class="fa fa-pencil"></i> 编辑--%>
<%--</button>--%>
<%--</div>--%>
<%--</td>--%>

<script>

    var _ctx = '${ctx}';
    $(document).ready(function () {

        //验证码在模态框出现前加载
        $("#update-password").on('show.bs.modal', function (event) {
            // var button = $(event.relatedTarget);
            // var id = button.data("id");
            // alert("12");
            $("#passwordForm").load(_ctx + '/user/update/password');//加载待编辑数据
        });
    });
</script>