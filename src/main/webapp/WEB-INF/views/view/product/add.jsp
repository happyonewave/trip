<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>后台管理系统</title>
<meta name="keyword" content="">
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="renderer" content="webkit">
<meta name="Author" content="zifan">
<meta name="copyright" content="All Rights Reserved">
<link href="${ctx}/static/css/bootstrap.min.css" rel="stylesheet">
<link href="${ctx}/static/font-awesome/css/font-awesome.css" rel="stylesheet">
<link href="${ctx}/static/css/plugins/toastr/toastr.min.css" rel="stylesheet">
<link href="${ctx}/static/css/plugins/chosen/chosen.css" rel="stylesheet">
<link href="${ctx}/static/css/plugins/fileinput/fileinput.min.css" rel="stylesheet">
<link href="${ctx}/static/css/animate.css" rel="stylesheet">
<link href="${ctx}/static/css/style.css" rel="stylesheet">

</head>

<body class="fixed-sidebar">
	<div id="wrapper">
		<!----左侧导航开始----->
		<nav class="navbar-default navbar-static-side" role="navigation" id="leftnav"></nav>
		<!----左侧导航结束----->

		<!---右侧内容区开始---->
		<div id="page-wrapper" class="gray-bg">
			<!---顶部状态栏 star-->
			<div class="row ">
				<nav class="navbar navbar-fixed-top" role="navigation" id="topnav"></nav>
			</div>
			<!---顶部状态栏 end-->

			<!--------当前位置----->
			<div class="row  border-bottom white-bg page-heading">
				<div class="col-sm-4">
					<h2>电商管理</h2>
					<ol class="breadcrumb">
						<li><a href="index.html">管理首页</a></li>
						<li>站内产品</li>
						<li class="active">上架产品</li>
					</ol>
				</div>
			</div>

			<!-----内容区域---->
			<div class="wrapper wrapper-content animated fadeInRight">
				<div class="ibox float-e-margins ">
					<div class="ibox-content p-t-slg">
						<form name="entity" id="input_form" class="form-horizontal">

							<div class="form-group">
								<label class="col-sm-12 col-md-4 col-lg-2 control-label" for="name"><span class="text-danger">* </span>产品名</label>
								<div class="col-sm-12 col-md-7 col-lg-9">
									<input type="text" id="name" name="name" value="" placeholder="请输入产品名" class="form-control" required>
								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-12 col-md-4 col-lg-2 control-label" for="price"><span class="text-danger">*</span>价格</label>
								<div class="col-sm-12 col-md-7 col-lg-9">
									<input type="number" id="price" name="price" value="" placeholder="请输入价格" class="form-control" required>
								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-12 col-md-4 col-lg-2 control-label" for="stock"><span class="text-danger">*</span>库存</label>
								<div class="col-sm-12 col-md-7 col-lg-9">
									<input type="number" id="stock" name="stock" value="" placeholder="请输入库存" class="form-control" required>
								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-12 col-md-4 col-lg-2 control-label" for="summary"><span class="text-danger">* </span>简介</label>
								<div class="col-sm-12 col-md-7 col-lg-9">
									<input type="text" id="summary" name="summary" value="" placeholder="请输入简介" class="form-control" required>
								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-12 col-md-4 col-lg-2 control-label" for="introduction"><span class="text-danger">*</span>介绍</label>
								<div class="col-sm-12 col-md-7 col-lg-9">
									<input type="text" id="introduction" name="introduction" value="" placeholder="请输入介绍" class="form-control" required>
								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-12 col-md-4 col-lg-2 control-label" for="theme"><span class="text-danger">*</span>分类</label>
								<div class="col-sm-12 col-md-7 col-lg-9">
									<%--<input type="text" id="theme" name="theme" value="" placeholder="请输入分类" class="form-control" required>--%>
										<select id="theme"  class="form-control" name="theme">
											<c:forEach var="t" items="${list}">
												<option value="${t.id}">${t.name}</option>
											</c:forEach>
										</select>
								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-12 col-md-4 col-lg-2 control-label" for="type"><span class="text-danger">*</span>旅游方式</label>
								<div class="col-sm-12 col-md-7 col-lg-9">
									<input type="text" id="type" name="type" value="" placeholder="请输入旅游方式" class="form-control" required>
								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-12 col-md-4 col-lg-2 control-label" for="city"><span class="text-danger">*</span>开始地点</label>
								<div class="col-sm-12 col-md-7 col-lg-9">
									<input type="text" id="city" name="city" value="" placeholder="请输入开始地点" class="form-control" required>
								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-12 col-md-4 col-lg-2 control-label" for="discount"><span class="text-danger">*</span>优惠金额</label>
								<div class="col-sm-12 col-md-7 col-lg-9">
									<input type="number" id="discount" name="discount" value="" placeholder="请输入优惠金额" class="form-control" required>
								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-12 col-md-4 col-lg-2 control-label" for="mainImgUrl"><span class="text-danger">*</span>主图</label>
								<div class="col-sm-12 col-md-7 col-lg-9">
									<input type="file" class="file-loading" id="file" name="file" value="" placeholder="请输入主图">
									<input  type="hidden" id="mainImgUrl" name="mainImgUrl" value="" placeholder="请输入主图" class="form-control" required>
								</div>
							</div>

							<div class="hr-line-dashed"></div>


							<div class="form-group">
								<div class="col-sm-4 col-sm-offset-2">
									<button class="btn btn-primary" type="submit">
										<i class="fa fa-check"></i> 填写完成，提交！
									</button>
									<button class="btn btn-white" type="reset">取消</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
			<!-----内容结束----->

			<!----版权信息----->
			<div class="footer">
				<div class="pull-right">
					10GB of <strong>250GB</strong> Free.
				</div>
				<div>
					<strong>Copyright</strong> Example Company &copy; 2014-2015
				</div>
			</div>
		</div>
		<!---右侧内容区结束----->

	</div>

	<!-- 全局 scripts -->
	<script src="${ctx}/static/js/jquery-2.1.1.js"></script>
	<script src="${ctx}/static/js/bootstrap.js"></script>
	<script src="${ctx}/static/js/wuling.js"></script>
	<script src="${ctx}/static/js/plugins/pace/pace.min.js"></script>
	<script src="${ctx}/static/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
	<script src="${ctx}/static/js/plugins/metisMenu/jquery.metisMenu.js"></script>
	<!-- 插件 scripts -->
	<script src="${ctx}/static/js/plugins/chosen/chosen.jquery.js"></script>
	<!---下拉菜单--->
	<script src="${ctx}/static/js/plugins/toastr/toastr.min.js" async></script>
	<!---顶部弹出提示--->
	<script src="${ctx}/static/js/plugins/validate/jquery.validate.min.js"></script>
	<!---表单验证--->
	<script src="${ctx}/static/js/plugins/validate/validate-cn.js"></script>
	<!---validate 自定义方法--->
	<script src="${ctx}/static/js/plugins/fileinput/fileinput.js"></script>
	<!---文件上传--->
	<script src="${ctx}/static/js/plugins/fileinput/fileinput_locale_zh.js"></script>
	<!--date style-->
	<script src="${ctx}/static/js/My97DatePicker/WdatePicker.js"></script>
	<!---文件上传中文配置--->

	<script>
    var _ctx = '${ctx}';
    var file_name = "";
    $(".chosen-select").chosen({
      no_results_text: '未找到此项',
      width: "100%",
      allow_single_deselect: true,
      disable_search_threshold: 10
    });
    $(document).ready(function() {
        // $("#theme").load(_ctx + '/theme/list');
      $("#input_form").validate({
        debug: true,
        submitHandler: function(form) {
          addform(form);
        }
      });
      function addform(form) {
        $.ajax({
          url: _ctx + "/product/add",
          type: "post",
          data: $(form).serialize(),
          success: function(data) {
            if (data.status == '1') {
              toastr.success('', data.msg);
              window.location.href=_ctx+"/product/list"; 
            } else
              toastr.error('', data.msg);
          },
          error: function(data) {
            toastr.error('', '上架失败');
          }
        });
      }


        function deleteimage(filename) {
            $.ajax({
                url: _ctx + "/product/add/mainImgUrl/delete",
                type: "delete",
                data: filename,
                success: function(data) {
                    if (data.status == '1') {
                        file_name = '';
                        toastr.success('', data.msg);
                        // window.location.href=_ctx+"/product/list";
                    } else{
                        toastr.error('', data.msg);
                    }

                },
                error: function(data) {
                    toastr.error('', '册除失败');
                }
            });
        }

        //单个图片上传配置
        $("#file").fileinput({
            language: 'zh',
            uploadUrl: _ctx + "/product/add/mainImgUrl/upload", //服务端上传地址
            dropZoneEnabled: true, //是否启动拖拽
            uploadAsync: true, //false同步批量上传,true异步上传
            showCaption: false, //是否显示按钮前的INPUT
            autoReplace: true, // 替换上一次浏览的图像，
            validateInitialCount: true, //是否包括已有的文件数，新加文件个数和现有文件相加的判断，<编辑页面时开启,新增加页面关闭>
            overwriteInitial: true, //是否要覆盖已有的内容。<编辑页面时开启,新增加页面关闭>
            minFileCount: 1, //最小文件数，为0可选
            maxFileCount: 1, //最大文件数，为0无限
            maxFileSize: 1000, //文件大小限制
            previewFileType: "image", //文件类型
            allowedFileExtensions: ["jpg", "gif", "png", "jpeg"],//文件允许后缀名称
            browseClass: "btn btn-primary", //自定义浏览按钮样式
            minImageWidth: 10,
            minImageHeight: 10,
            maxImageWidth: 1200,
            maxImageHeight: 1200,
            // msgFilesTooLess: '必须上传 <b>{n}</b>个{files}，您可以选择新的文件进行替换. ',
            layoutTemplates: {
                actionUpload: '' //删除小按钮
            },
            initialCaption: "请选择本地图片" ,//默认输入框文字
            // deleteUrl: _ctx + "/product/add/mainImgUrl/delete", //服务端删除地址
            // deleteExtraData: {fileName:file_name}
            <%--initialPreview: ['<img src="${ctx}/static/img/a1.jpg" class="file-preview-image" alt="The Moon" title="The Moon">'],//编辑页面初始化数据--%>
            // initialPreviewConfig: [{
            //     caption: "图片一",
            //     width: "120px",
            //     url: "/111",
            //     key: 1
            // },
            //     //URL 为从服务端删除图片的地址
            //
            // ],
            // //通过此方法可以删除服务端上传的图片

            /*
             uploadExtraData: function(previewId, index) {
                   return{
                       userid: "10000",
                       username: "zifan",
                       key: index
                   }
            },*///扩展AJAX传送数据。

        })
		// 	.off('filepreupload').on('filepreupload', function() {
        //     return alert("确定要上传文件吗？")
        // })

            .on("filesuccessremove",function(event,key){
                console.log("filesuccessremove");
                if (file_name != ''){
                    deleteimage(file_name);
				}
                console.log(event,key);
            })

            .on("filepredelete",function(event,key){
                console.log("filepredelete");
                if (file_name != ''){
                    deleteimage(file_name);
                }
                console.log(event,key);
            })

            .on("filedeleted",function(event,key){
                console.log("filedeleted");
                // if (file_name != ''){
                //     deleteimage(file_name);
                // }
                console.log(event,key);
            })

            .on("fileclear",function(event,key){
                console.log("fileclear");
                if (file_name != ''){
                    deleteimage(file_name);
                }
                console.log(event,key);
            })


            .on("filecleared",function(event,key){
                console.log("filecleared");
                // if (file_name != ''){
                //     deleteimage(file_name);
                // }
                console.log(event,key);
            })


            .on("filedeleteerror",function(event,key){
                console.log("filedeleteerror");
                console.log(event,key);
            })

            .on('fileuploaderror', function(event, data, previewId, index) {
                var form = data.form,
                    files = data.files,
                    extra = data.extra,
                    response = data.response,
                    reader = data.reader;
                //console.log(data)
                toastr.error('', '保存失败');
            })

            .on('fileuploaded',function(event, data, previewId, index){
                data = data.response;
                if (data.status == '1') {
                    console.log("fileuploaded:1");
                    console.log("data:" + JSON.stringify(data));
                    file_name = data.url;
					$("#mainImgUrl").attr('value',data.url);
                    toastr.success('', data.msg);
                } else {
                    console.log("fileuploaded:2");
                    console.log("data:" + JSON.stringify(data));
                    toastr.error('', data.msg);
                }
            })

            .on('filezoomshow', function(event, params) {
                console.log('File zoom show ', params.sourceEvent, params.previewId, params.modal);
            });

    });
  </script>
</body>
</html>
