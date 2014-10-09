<%@ page language="java" pageEncoding="UTF-8" %>
    <c:if test="${not empty flashMessages}">
		<div id="m-success-message" style="display:none;">
		  <ul>
		  <c:forEach items="${flashMessages}" var="item">
		    <li>${item}</li>
		  </c:forEach>
		  </ul>
		</div>
	</c:if>
	<!-- 菜单CSS样式 -->
	<style type="text/css">
        .dropdown-submenu {
            position: relative;
        }
        .dropdown-submenu > .dropdown-menu {
            top: 0;
            left: 100%;
            margin-top: -6px;
            margin-left: -1px;
            -webkit-border-radius: 0 6px 6px 6px;
            -moz-border-radius: 0 6px 6px;
            border-radius: 0 6px 6px 6px;
        }
        .dropdown-submenu:hover > .dropdown-menu {
            display: block;
        }
        .dropdown-submenu > a:after {
            display: block;
            content: " ";
            float: right;
            width: 0;
            height: 0;
            border-color: transparent;
            border-style: solid;
            border-width: 5px 0 5px 5px;
            border-left-color: #ccc;
            margin-top: 5px;
            margin-right: -10px;
        }
        .dropdown-submenu:hover > a:after {
            border-left-color: #fff;
        }
        .dropdown-submenu.pull-left {
            float: none;
        }
        .dropdown-submenu.pull-left > .dropdown-menu {
            left: -100%;
            margin-left: 10px;
            -webkit-border-radius: 6px 0 6px 6px;
            -moz-border-radius: 6px 0 6px 6px;
            border-radius: 6px 0 6px 6px;
        }
    </style>
	
    <!-- start of header bar -->
    <div class="navbar navbar-default">
		  <div class="navbar-header">
		    <button class="navbar-toggle" type="button" data-toggle="collapse" data-target=".navbar-responsive-collapse">
		      <span class="icon-bar"></span>
		      <span class="icon-bar"></span>
		      <span class="icon-bar"></span>
		    </button>
		    <a class="navbar-brand" href="${scopePrefix}/">天翔</a>
	    </div>
	    <div class="navbar-collapse collapse navbar-responsive-collapse">
		    <!-- 动态菜单部分 -->
		    <!-- 系统管理  -->
            <ul class="nav navbar-nav">
	            <c:forEach items="${menuItemList}" var="item">
	                    <c:if test="${item.chiledItems == '[]'}">
						        <li class="navbar-link">
						        	<c:if test="${item.menuUrl == '#'}">
								    	<a href="#"> ${item.menuName}</a>
								    </c:if>
								    <c:if test="${item.menuUrl != '#'}">
								    	<a href="${scopePrefix}${item.menuUrl}"> ${item.menuName}</a>
								    </c:if>
						        </li>
				        </c:if>
						<!-- 有子节点的 -->
						<c:if test="${item.chiledItems != '[]'}">
						    <li class="navbar-link dropdown">
							    <a data-toggle="dropdown" class="dropdown-toggle" href="#">${item.menuName} <b class="caret"></b></a>
					            <ul class="dropdown-menu panel-body nav nav-list">
						            <c:forEach items="${item.chiledItems}" var="son">
										    <c:if test="${son.chiledItems == '[]'}">
												<li>
												    <c:if test="${son.menuUrl == '#'}">
												    	<a href="#"> ${son.menuName}</a>
												    </c:if>
												    <c:if test="${son.menuUrl != '#'}">
												    	<a href="${scopePrefix}${son.menuUrl}"> ${son.menuName}</a>
												    </c:if>
												</li>
											</c:if>
											<c:if test="${son.chiledItems != '[]'}">
											    <li class="divider"></li>
												<li class="dropdown-submenu"><a href="#"><i class="icon-user"></i>${son.menuName}</a>
													<ul class="dropdown-menu  panel-body nav nav-list">
														<c:forEach items="${son.chiledItems}" var="grandson">
														    <li>
															    <c:if test="${grandson.menuUrl == '#'}">
															    	<a href="#"><i class="icon-user"></i>${grandson.menuName}</a>
															    </c:if>
															    <c:if test="${grandson.menuUrl != '#'}">
															    	<a href="${scopePrefix}${grandson.menuUrl}"><i class="icon-user"></i>${grandson.menuName}</a>
															    </c:if>
															</li>
														</c:forEach>
													</ul>
												</li>
											</c:if>
									</c:forEach>
		                         </ul>
	                         </li>
						</c:if>
				</c:forEach>
            </ul>

            <ul class="nav navbar-nav navbar-right">
              <li class="dropdown">
	                <a data-toggle="dropdown" class="dropdown-toggle" href="#">
	                  <sec:authentication property="principal.displayName" />
	                  <b class="caret"></b>
	                </a>
	                <ul class="dropdown-menu">
	                  <li><a href="${scopePrefix}/user/change-password-input.do">修改密码</a></li>
	                  <li><a href="${scopePrefix}/user/profile-list.do">个人信息</a></li>
	                  <li class="divider"></li>
	                  <li><a href="${scopePrefix}/j_spring_security_logout">退出</a></li>
	                </ul>
                </li>
                
                <li class="dropdown">
	                <a data-toggle="dropdown" class="dropdown-toggle" href="#">皮肤切换</a>
	                <ul class="dropdown-menu">
	                  <li class="divider"></li>
	                  <li><a href="${scopePrefix}/uicss/uicss-save.do?userCSS=Cerulean">Cerulean</a></li>
	                  <li><a href="${scopePrefix}/uicss/uicss-save.do?userCSS=Slate">Slate</a></li>
	                  <li><a href="${scopePrefix}/uicss/uicss-save.do?userCSS=Cosmo">Cosmo</a></li>
	                  <li><a href="${scopePrefix}/uicss/uicss-save.do?userCSS=Cyborg">Cyborg</a></li>
	                  <li><a href="${scopePrefix}/uicss/uicss-save.do?userCSS=Darkly">Darkly</a></li>
	                  <li><a href="${scopePrefix}/uicss/uicss-save.do?userCSS=Flatly">Flatly</a></li>
	                  <li><a href="${scopePrefix}/uicss/uicss-save.do?userCSS=Superhero">Superhero</a></li>
					  <li><a href="${scopePrefix}/uicss/uicss-save.do?userCSS=United">United</a></li>
					  <li><a href="${scopePrefix}/uicss/uicss-save.do?userCSS=Yeti">Yeti</a></li>
	                  <li class="divider"></li>
	                  <li><a href="${scopePrefix}/uicss/uicss-save.do?userCSS=Journal">Journal</a></li>
	                  <li><a href="${scopePrefix}/uicss/uicss-save.do?userCSS=Lumen">Lumen</a></li>
	                  <li><a href="${scopePrefix}/uicss/uicss-save.do?userCSS=Paper">Paper</a></li>
	                  <li><a href="${scopePrefix}/uicss/uicss-save.do?userCSS=Readable">Readable</a></li>
	                  <li><a href="${scopePrefix}/uicss/uicss-save.do?userCSS=Sandstone">Sandstone</a></li>
	                  <li><a href="${scopePrefix}/uicss/uicss-save.do?userCSS=Simplex">Simplex</a></li>
	                  <li><a href="${scopePrefix}/uicss/uicss-save.do?userCSS=Spacelab">Spacelab</a></li>
	                  <li class="divider"></li>
	                </ul>
              </li>
            </ul>
              
          </div><!-- /.nav-collapse -->
      </div><!-- /navbar-inner -->
    <!-- end of header bar -->
