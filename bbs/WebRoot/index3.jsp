<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@include file="/inc/_header.jsp" %>
<div class="container-fluid">
	<%@include file="/inc/_nav.jsp" %>
    <div class="row-fluid">
        <div class="span2">
            <div class="accordion" id="accordion-96521">
                <div class="accordion-group">
                    <div class="accordion-heading">
                        <a class="accordion-toggle" data-toggle="collapse"  href="#accordion-element-801140">选项卡1</a>
                    </div>
                    <div id="accordion-element-801140" class="accordion-body in collapse">
                        <div class="accordion-inner">
                            选项卡内容1
                        </div>
                    </div>
                </div>
                <div class="accordion-group">
                    <div class="accordion-heading">
                        <a class="accordion-toggle" data-toggle="collapse" href="#accordion-element-115637">选项卡2</a>
                    </div>
                    <div id="accordion-element-115637" class="accordion-body in collapse">
                        <div class="accordion-inner">
                            选项卡内容2
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="span6">
            <div class="carousel slide" id="carousel-320923">

                <ol class="carousel-indicators">
                    <li class="active" data-slide-to="0" data-target="#carousel-320923">
                    </li>
                    <li data-slide-to="1" data-target="#carousel-320923">
                    </li>
                    <li data-slide-to="2" data-target="#carousel-320923">
                    </li>
                </ol>
                <div class="carousel-inner">
                    <div class="item active">
                        <img alt="" src="http://placehold.it/800x350" />
                    </div>
                    <div class="item">
                        <img alt="" src="http://placehold.it/800x350" />
                    </div>
                    <div class="item">
                        <img alt="" src="http://placehold.it/800x350" />
                    </div>
                </div> <a data-slide="prev" href="#carousel-320923" class="left carousel-control">‹</a> <a data-slide="next" href="#carousel-320923" class="right carousel-control">›</a>
            </div>
        </div>
        <div class="span4">
        </div>
    </div>
</div>
<div style="height:200px"></div>
<%@include file="/inc/_footer.jsp" %>
