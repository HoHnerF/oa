<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="top.jsp"/>

<section id="content" class="table-layout animated fadeIn">
    <div class="tray tray-center">
        <div class="content-header">
            <h2> 部门列表 </h2>
            <p class="lead"></p>
        </div>
        <div class="admin-form theme-primary mw1000 center-block" style="padding-bottom: 175px;">
            <div class="panel  heading-border">
                <div class="panel-menu">
                    <div class="row">
                        <div class="hidden-xs hidden-sm col-md-3">
                            <div class="btn-group">
                                <button type="button" class="btn btn-default light">
                                    <i class="fa fa-refresh"></i>
                                </button>
                                <button type="button" class="btn btn-default light">
                                    <i class="fa fa-trash"></i>
                                </button>
                                <button type="button" class="btn btn-default light">
                                    <i class="fa fa-plus" onclick="javascript:window.location.href='/department/to_add';"></i>
                                </button>
                            </div>
                        </div>
                        <div class="col-xs-12 col-md-9 text-right">
                            <div class="btn-group">
                                <button type="button" class="btn btn-default light">


                                    <i class="fa fa-chevron-left" onclick="javascript:window.location.href='/department/selectByPage?currentPage='+${page.currentPage-1}" ></i>

                                </button>
                                <button type="button" class="btn btn-default light"  onclick="javascript:window.location.href='/department/selectByPage?currentPage='+${page.currentPage+1}" >
                                    <i class="fa fa-chevron-right"></i>
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="panel-body pn">
                    <table id="message-table" class="table admin-form theme-warning tc-checkbox-1">
                        <thead>
                        <tr class="">
                            <th class="text-center hidden-xs">Select</th>
                            <th class="hidden-xs">部门编号</th>
                            <th class="hidden-xs">部门名称</th>
                            <th class="hidden-xs">地址</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${list}" var="dept">
                        <tr class="message-unread">
                            <td class="hidden-xs">
                                <label class="option block mn">
                                    <input type="checkbox" name="mobileos" value="FR">
                                    <span class="checkbox mn"></span>
                                </label>
                            </td>
                            <td>${dept.sn}</td>
                            <td>${dept.name}</td>
                            <td>${dept.address}</td>
                            <td>
                                <a href="/department/to_update?sn=${dept.sn}">编辑</a>
                                <a href="/department/remove?sn=${dept.sn}">删除</a>
                            </td>
                        </tr>
                        </c:forEach>
                        </tbody>
                        <tr>
                            <td colspan="6">
                                第${page.currentPage}/${page.totalPage}页&nbsp;&nbsp;&nbsp;
                                每页显示${page.pageSize}条数据&nbsp;&nbsp;&nbsp;
                                总人数：${page.totalSize}&nbsp;&nbsp;&nbsp;
                                <c:if test="${page.currentPage != 1}">
                                    <a href="/department/selectByPage?currentPage=1">首页</a>|<a href="/department/selectByPage?currentPage=${page.currentPage-1}">上一页</a>&nbsp;&nbsp;
                                </c:if>
                                <c:forEach begin="1" end="${page.totalPage}" var="i">
                                    <c:if test="${page.currentPage == i}">
                                        ${i}
                                    </c:if>
                                    <c:if test="${page.currentPage != i}">
                                        <a href="/department/selectByPage?currentPage=${i}">${i}</a>

                                    </c:if>
                                </c:forEach>


                                <c:if test="${page.currentPage != page.totalPage}">
                                    <a href="/department/selectByPage?currentPage=${page.currentPage+1}">下一页</a>|<a href="/department/selectByPage?currentPage=${page.totalPage}">尾页</a>
                                </c:if>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
    </div>
</section>

<jsp:include page="bottom.jsp"/>