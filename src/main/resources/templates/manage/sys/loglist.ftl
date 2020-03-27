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
        日志记录
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

              <table class="table table-bordered table-striped">
                <thead>
                <tr>
                  <th>时间</th>
                  <th>用户名</th>
                  <th>类别</th>
                  <th>操作</th>
                </tr>
                </thead>
                <tbody>
               <#list loglist as log >
                <tr>
                  <td>${log.logTime?datetime}</td>
                  <td>${log.username}</td>
                  <td>${log.category}</td>
                  <td>${log.opName}</td>
                </tr>
               </#list> 
                </tbody>
             
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

 	</script>
 	
 </@defaultLayout.layout> 
