
      <!-- sidebar menu: : style can be found in sidebar.less -->
      <ul class="sidebar-menu" data-widget="tree">
        <li class="header">导航栏</li>
        <li <@navItemStyle prefix="/manage/index" />>
          <a href="/manage/index">
            <i class="fa fa-dashboard"></i> <span>工作台</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li <@navSubItemStyle prefix="/manage/index" />><a href="/manage/index"><i class="fa fa-circle-o"></i> 首页</a></li>
          </ul>
        </li>
        
        <@shiro.hasAnyPermissions name="sysuser:view, sysrole:view, syslog:view, syserror:view">
        <li <@navItemStyle prefix="/manage/sys" />>
          <a href="#">
            <i class="fa fa-pie-chart"></i>
            <span>系统管理</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <@shiro.hasPermission name="sysuser:view">
            <li <@navSubItemStyle prefix="/manage/sys/user" />><a href="/manage/sys/userlist"><i class="fa fa-circle-o"></i> 用户列表</a></li>
            </@shiro.hasPermission>
            <@shiro.hasPermission name="sysrole:view">
            <li <@navSubItemStyle prefix="/manage/sys/role" />><a href="/manage/sys/rolelist"><i class="fa fa-circle-o"></i> 角色列表</a></li>
            </@shiro.hasPermission>
            <@shiro.hasPermission name="syslog:view">
            <li <@navSubItemStyle prefix="/manage/sys/log" />><a href="/manage/sys/loglist"><i class="fa fa-circle-o"></i> 日志记录</a></li>
            </@shiro.hasPermission>
            <@shiro.hasPermission name="syserror:view">
            <li <@navSubItemStyle prefix="/manage/sys/error" />><a href="/manage/sys/errorlist"><i class="fa fa-circle-o"></i> 异常记录</a></li>
            </@shiro.hasPermission>
          </ul>
        </li>
        </@shiro.hasAnyPermissions>
       
      </ul>
      