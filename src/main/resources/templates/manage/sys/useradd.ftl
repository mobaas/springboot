<#import "/manage/inc/defaultLayout.ftl" as defaultLayout >

<@defaultLayout.layout>
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
                    <form id="form1">
                        <input type="hidden" class="form-control" id="id" name="id" value="${user.id!}" readonly="true">
                        <div class="box-body">
                            <div class="row">
                                <div class="col-xs-6">
                                    <div class="form-group">
                                        <label for="exampleInputEmail1" class="col-sm-2 control-label">用户名：</label>
                                        <div class="col-sm-6">
                                            <#if (user.id)??>
                                                <input type="text" class="form-control" id="username" name="username" value="${user.username!}" readonly="true" required="true">
                                            <#else>
                                                <input type="text" class="form-control" id="username" name="username" required="true">
                                            </#if>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-xs-6">
                                    <div class="form-group">
                                        <label for="exampleInputEmail1" class="col-sm-2 control-label">密码：</label>
                                        <div class="col-sm-6">
                                            <input type="password" class="form-control" id="password" name="password" required="true">
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-xs-6">
                                    <div class="form-group">
                                        <label for="exampleInputEmail1" class="col-sm-2 control-label">用户类型：</label>
                                        <div class="col-sm-6">
                                            <label>
                                                <input type="radio" name="type" class="minimal" value="0" <#if (user.type)??><#if  user.type == 0>checked</#if></#if>>
                                                普通用户
                                            </label>
                                            <label>
                                                <input type="radio" name="type" class="minimal" value="1" <#if (user.type)??><#if  user.type== 1>checked</#if></#if>>
                                                管理用户
                                            </label>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-xs-6">
                                    <div class="form-group">
                                        <label for="exampleInputEmail1" class="col-sm-2 control-label">用户角色：</label>
                                        <div class="col-sm-6">
                                            <#list roles as role >
                                                <input type="radio" name="roleId" class="minimal" value="${role.id}" <#if (user.roleId)??><#if role.id == user.roleId>checked</#if></#if>>
                                                ${role.role}
                                            </#list>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="box-footer">
                            <button type="button" class="btn btn-primary" onclick="save()">保存</button>
                        </div>
                    </form>

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
    function save() {
        $.ajax({
            url:"/manage/sys/useraddForm",//url
            type:"POST",//方法类型
            dataType:"json",//预期服务器返回的数据类型
            data:$("#form1").serialize(),
            success:function (result) {
                if(result.code != 0){
                    alert(result.message);
                }
            },
            error:function() {
                alert("服务器出现异常，请稍后再试！");
            }
        });
    }
</script>

</@defaultLayout.layout>
