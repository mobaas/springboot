<#import "/manage/inc/defaultLayout.ftl" as defaultLayout >

<@defaultLayout.layout>
    <style>
        .pagination{
            display: block;
        }
    </style>
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        用户列表
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
              <h3 class="box-title">Data Table With Full Features</h3>
            </div>
            <!-- /.box-header -->
            <div class="box-body">
                <button class="btn btn-danger" onclick="add()">新增</button>
                <table id="example2" class="table table-bordered table-hover">
                    <thead>
                    <tr>
                        <th>用户名</th>
                        <th>用户类型</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                </table>
<#--
              <table class="table table-bordered table-striped">
                <thead>
                <tr>
                  <th>用户名</th>
                  <th>用户类型</th>
                  <th>列4</th>
                  <th>操作</th>
                </tr>
                </thead>
                <tbody>
               <#list userlist as user >
                <tr>
                  <td>${user.username}</td>
                  <td>-</td>
                  <td>-</td>
                  <td>-</td>
                  <td>
                  <@shiro.hasPermission name="sysuser:edit">
                  		<a href="javascript:edit(${user.id})" class="btn btn-default btn-sm">编辑</a>
                  </@shiro.hasPermission>
                  </td>
                </tr>
               </#list> 
                </tbody>
             
              </table>
-->
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

        var table = $('#example2').DataTable({
            dom:'<"top">rt<"bottom"pi><"clear">',
            serverSide: true,  //服务器模式
            ordering: false,  // 关闭排序
            searching: false,  // 关闭插件自带的搜索，我们会自定义搜索
            processing: true,  //是否显示“处理中...”
            /*               scrollX: true,  //水平方向的滚动条
                           autoWidth : true,  // 自动宽度*/
            lengthMenu: [10],  // 分页器的页数
            ajax: "/manage/sys/userlistPage",
            // 与<table>标签中的<th>标签内的字段对应
            columns: [{
                data: "username"
            }, {
                data: "type"
            }, null],
            aoColumnDefs: [{//倒数第一列
                targets: -1,
                bSortable: false,
                render: function (data, type, row) {
                    var html = '<button class="btn btn-danger" value="'+row.id+'" onclick="edit('+row.id+')">编辑</button>&nbsp;&nbsp;&nbsp;&nbsp;'
                            + '<button class="btn btn-danger" value="'+row.id+'" onclick="del('+row.id+')">删除</button>';
                    return html;
                }
            }]
        });

 	   function edit(uid) {
 	   		//iframe层
/*			layer.open({
			  type: 2,
			  title: '编辑用户',
			  shadeClose: true,
			  shade: 0.8,
			  area: ['500px', '400px'],
			  content: '/manage/sys/useredit?id=' + uid
			}); */
           location.href = "/manage/sys/useradd?id="+uid;
 	   }

 	   function add() {
           location.href = "/manage/sys/useradd";
       }

       function del(uid) {
           $.ajax({
               url:"/manage/sys/userdel",//url
               type:"POST",//方法类型
               dataType:"json",//预期服务器返回的数据类型
               data:{"id":uid},
               success:function (result) {
                   if(result.code != 0){
                       alert(result.message);
                       return;
                   }
                   layer.msg('删除成功');
                   table.ajax.reload(null,false);
               },
               error:function() {
                   alert("服务器出现异常，请稍后再试！");
               }
           });
       }


       $(function () {
           table;
       })
 	   
 	</script>
 	
 </@defaultLayout.layout> 
