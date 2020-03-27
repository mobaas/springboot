<#import "/manage/inc/popupLayout.ftl" as popupLayout >

<@popupLayout.layout>

<section class="content">
    <div class="row">
        <div class="col-xs-12">

            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">权限列表</h3>
                </div>
                <!-- /.box-header -->
                <div class="box-body">
                    <table id="example2" class="table table-bordered table-hover">
                        <thead>
                        <tr>
                            <th>权限名称</th>
                            <th>权限字符串</th>
                            <th>资源路径</th>
                        </tr>
                        </thead>
                    <tbody>
                       <#list permission as pm>
                        <tr>
                          <td>${pm.name}</td>
                          <td>${pm.permission}</td>
                          <td>${pm.url}</td>
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
    
</@popupLayout.layout>