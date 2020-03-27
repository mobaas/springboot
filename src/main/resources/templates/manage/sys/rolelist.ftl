<#import "/manage/inc/defaultLayout.ftl" as defaultLayout >

<@defaultLayout.layout>
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        角色管理
        <small>advanced tables</small>
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
        <li><a href="#">Tables</a></li>
        <li class="active">Data tables</li>
      </ol>
    </section>

    <!-- Main content -->
    <section class="content">
      <div class="row">
        <div class="col-xs-12">
          
          <div class="box">
            <div class="box-header">
            </div>
            <!-- /.box-header -->
            <a href="/manage/sys/roleadd" class="btn btn-danger"><i class="fa fa-plus"></i>角色新增</a>
            <div class="box-body">
              <table id="example2" class="table table-bordered table-hover">
                <thead>
                <tr>
                  <th>角色名称</th>
                  <th>角色描述</th>
                  <th>是否可用</th>
                  <th>操作</th>
                </tr>
                </thead>
                <#--<tbody>
                   <#list rolepage.list as role >
                    <tr>
                      <td>${role.role}</td>
                      <td>${role.description}</td>
                      <td><#if (role.available)>可用<#else>不可用</#if>
                      </td>
                      <td>
                      <@shiro.hasPermission name="sysrole:edit">
                            <a href="javascript:edit(${role.id})" class="btn btn-default btn-sm">编辑</a>
                      </@shiro.hasPermission>
                      </td>
                    </tr>
                   </#list>
                </tbody>-->

              </table>
            </div>
            <!-- /.box-body -->
          </div>
          <!-- /.box -->
        </div>
        <!-- /.col -->
      </div>
      <!-- /.row -->
    </section>
    <!-- /.content -->

 	<script type="text/javascript">

       $(function () {
           $('#example2').DataTable({
               serverSide: true,  //服务器模式
               ordering: false,  // 关闭排序
               searching: false,  // 关闭插件自带的搜索，我们会自定义搜索
               processing: true,  //是否显示“处理中...”
               /*               scrollX: true,  //水平方向的滚动条
                              autoWidth : true,  // 自动宽度*/
               lengthMenu: [20,15,10],  // 分页器的页数
               ajax: "/manage/sys/rolelistPage",
               // 与<table>标签中的<th>标签内的字段对应
               columns: [{
                   data: "role"
               }, {
                   data: "description"
               },{
                   data: "available"
               }, null],
               aoColumnDefs: [{//倒数第一列
                   targets: -1,
                   bSortable: false,
                   render: function (data, type, row) {
                       var html ='<a href="/manage/sys/rolefrom?id='+ row.id +'">编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;'
                               /*+ '<button class="btn btn-danger" onclick="edit('+row.id+')" value="'+row.id+'">修改权限</button>&nbsp;&nbsp;&nbsp;&nbsp;'*/
                       +'<a href="/manage/sys/roledel?id='+ row.id +'" onclick="return confirmd()">删除</a>'
                               /*+ '<button class="btn btn-danger" value="'+row.id+'">删除</button>';*/
                       return html;
                   }
               }]
           });
       })


       function edit(uid) {
           //iframe层
           alert(uid);
           layer.open({
               type: 2,
               title: '修改权限',
               shadeClose: true,
               shade: 0.8,
               area: ['500px', '400px'],
               content: '/manage/sys/rolepermission?id=' + uid
           });
       }
       function confirmd() {
           var msg = "您真的确定要删除吗？/n/n请确认！";
           if (confirm(msg)==true){
               return true;
           }else{
               return false;
           }
       }
 	</script>

 </@defaultLayout.layout>
