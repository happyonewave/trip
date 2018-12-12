<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div class="ibox-content">
    <div class="table-responsive ">
        <table class="table table-centerbody table-striped table-condensed text-nowrap" id="editable-sample">
            <thead>
            <tr>
                <%--id--%>
                <%--name--%>
                <%--price--%>
                <%--stock--%>
                <%--main_img_url--%>
                <%--summary--%>
                <%--img_id--%>
                <%--introduction--%>
                <%--_from--%>
                <%--theme--%>
                <%--type--%>
                <%--city--%>
                <%--discount--%>
                <%--delete_time--%>
                <%--create_time--%>
                <%--updata_time--%>
                <th>产品号</th>
                <th>产品名</th>
                <th>价格</th>
                <th>库存</th>
                <th>主图</th>
                <th>简介</th>
                <%--<th>图片id</th>--%>
                <th>介绍</th>
                <%--<th>来自</th>--%>
                <th>分类</th>
                <th>旅游方式</th>
                <th>开始地点</th>
                <th>优惠金额</th>
                <th>上一次修改时间</th>
                <th>上架时间</th>

                <th class="text-right">操作</th>
            </tr>
            </thead>
            <tbody>
            <c:if test="${page!=null && (page.list)!= null && fn:length(page.list) > 0 }">
                <c:forEach var="n" items="${page.list }">
                    <tr>
                            <%--<td>${n.title }</td>--%>
                            <%--<td>${n.description }</td>--%>
                            <%--<td>${n.address }</td>--%>

                        <td>${n.id}</td>
                        <td>${n.name}</td>
                        <td>${n.price}</td>
                        <td>${n.stock}</td>
                        <td><img src="${ctx }/static/images/product/${n.mainImgUrl}" height="30%"/></td>
                        <td>${n.summary}</td>
                            <%--<td>${n.imgId}</td>--%>
                        <td>${n.introduction}</td>
                            <%--<td>${n.from}</td>--%>
                        <td>${n.theme}</td>
                        <td>${n.type}</td>
                        <td>${n.city}</td>
                        <td>${n.discount}</td>
                            <%--<td><fmt:formatDate value="${n.deleteTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>--%>
                        <td><fmt:formatDate value="${n.updataTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                        <td><fmt:formatDate value="${n.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                            <%--<td><fmt:formatDate value="${n.productTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>--%>
                            <%--<td><fmt:formatDate value="${n.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>--%>
                        <td class="text-right text-nowrap">
                            <div class="btn-group ">
                                <button class="btn btn-white btn-sm delete"  data-id="${n.id }">
                                    <i class="fa fa-pencil">下架</i>
                                </button>
                            </div>
                        </td>

                        <%--<td class="text-right text-nowrap">--%>
                            <%--<div class="btn-group ">--%>
                                <%--<button class="btn btn-white btn-sm edit" data-id="${n.id }" data-toggle="modal"--%>
                                        <%--data-target="#edit">--%>
                                    <%--<i class="fa fa-pencil"></i> 编辑--%>
                                <%--</button>--%>
                            <%--</div>--%>
                        <%--</td>--%>
                    </tr>
                </c:forEach>
            </c:if>
            </tbody>
        </table>
    </div>

    <!-- 分页表单 -->
    <form action="${ctx }/product/list_page" id="productPageForm">
        <!-- 查询条件，用隐藏表单域 -->
        <input type="hidden" value="${keywords }" name="keywords"/>

        <!-- 分页控键 -->
        <!-- formId: 分页控件表单ID -->
        <!-- showPageId: ajax异步分页获取的数据需要加载到指定的位置 -->
        <jsp:include page="/WEB-INF/views/common/page.jsp" flush="true">
            <jsp:param name="formId" value="productPageForm"/>
            <jsp:param name="showPageId" value="ibox"/>
        </jsp:include>
    </form>

</div>