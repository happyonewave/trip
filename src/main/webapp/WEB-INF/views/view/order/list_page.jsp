<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div class="ibox-content">
    <div class="table-responsive ">
        <table class="table table-centerbody table-striped table-condensed text-nowrap" id="editable-sample">
            <thead>
            <tr>
                <th>订单号</th>
                <th>总价</th>
                <th>订单状态</th>
                <th>日期</th>
                <th>旅游人数</th>
                <th>产品号</th>
                <th>产品名</th>
                <th>联系人姓名</th>
                <th>联系人手机号</th>
                <th>身份证号</th>
                <th>电子邮箱</th>
                <th>是否评价</th>
                <th class="text-right">操作</th>
            </tr>
            </thead>
            <tbody>
            <c:if test="${page!=null && (page.list)!= null && fn:length(page.list) > 0 }">
                <c:forEach var="n" items="${page.list }">
                    <tr>
                        <td>${n.orderNo}</td>
                        <td>${n.totalPrice}</td>
                        <td><c:choose>
                            <c:when test="${n.status == 1}">
                                已付款
                            </c:when>
                            <c:when test="${n.status == 2}">
                                已成功
                            </c:when>
                            <c:when test="${n.status == 3}">
                                未付款
                            </c:when>
                            <c:when test="${n.status == 4}">
                                已结束
                            </c:when>
                            <c:when test="${n.status == 5}">
                                已评价
                            </c:when>
                            <c:when test="${n.status == 6}">
                                失败
                            </c:when>
                            <c:otherwise>
                            </c:otherwise>
                        </c:choose></td>
                        <td><fmt:formatDate value="${n.date}" pattern="yyyy-MM-dd"/></td>
                        <td>${n.totalCount}</td>
                        <td>${n.productId}</td>
                        <td>${n.snapName}</td>
                        <td>${n.contactName}</td>
                        <td>${n.phone}</td>
                        <td>${n.idcard}</td>
                        <td>${n.email}</td>
                        <td>
                            <c:choose>
                                <c:when test="${n.comments == 1}">已评价</c:when>
                                <c:otherwise>未评价</c:otherwise>
                            </c:choose>
                        </td>
                        <td><fmt:formatDate value="${n.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                        <td class="text-right text-nowrap">
                            <div class="btn-group ">
                                <button class="btn btn-white btn-sm handle" <c:if test="${n.status == 2||n.status == 6}">disabled ="disabled"</c:if> data-id="${n.id }" data-status="<c:choose><c:when test="${n.status == 1}">2</c:when><c:otherwise>6</c:otherwise></c:choose>">
                                    <i class="fa fa-pencil"></i> <c:choose><c:when test="${n.status == 1}">确认</c:when><c:when test="${n.status == 2}">已成功 无法操作</c:when><c:when test="${n.status == 6}">已失败 无法操作</c:when><c:otherwise>拒绝</c:otherwise></c:choose>
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
    <form action="${ctx }/order/list_page" id="orderPageForm">
        <!-- 查询条件，用隐藏表单域 -->
        <input type="hidden" value="${keywords }" name="keywords"/>

        <!-- 分页控键 -->
        <!-- formId: 分页控件表单ID -->
        <!-- showPageId: ajax异步分页获取的数据需要加载到指定的位置 -->
        <jsp:include page="/WEB-INF/views/common/page.jsp" flush="true">
            <jsp:param name="formId" value="orderPageForm"/>
            <jsp:param name="showPageId" value="ibox"/>
        </jsp:include>
    </form>

</div>