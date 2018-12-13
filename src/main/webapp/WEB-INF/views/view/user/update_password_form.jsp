<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<input type="hidden" name="user.id" value="${user.id }" />
<div class="form-group">
	<label class="col-sm-4 control-label" for="old_password">旧密码<span class="text-danger">*</span></label>
	<div class="col-sm-8">
		<input type="password" id="old_password" name="oldPassword" value="" required class="form-control">
	</div>
</div>
<div class="form-group">
	<label class="col-sm-4 control-label" for="new_password">新密码 <span class="text-danger">*</span></label>
	<div class="col-sm-8">
		<input type="password" id="new_password" name="user.password" value="" required class="form-control">
	</div>
</div>

<div class="form-group m-t-sm">
	<div class="col-sm-6 col-sm-push-4">
		<button class="btn btn-md btn-primary " type="submit">
			<strong>确认修改</strong>
		</button>
		<button type="button" class="btn btn-white m-l-sm" data-dismiss="modal">取消</button>
	</div>
</div>
