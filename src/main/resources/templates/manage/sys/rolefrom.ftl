<#import "/manage/inc/defaultLayout.ftl" as defaultLayout >

<@defaultLayout.layout>
<#--<@popupLayout.layout>-->


 	<!-- Main content -->
    <section class="content">
    	<form id="inputForm" action="##" onsubmit="return false" method="post" class="form-group">
            <input type="hidden" name="id" value="${role.id}">
            <p>角色名称: <input type="text" name="role" value="${role.role}"/></p>
            <p>角色描述: <input type="text" name="description" value="${role.description}" /></p>
                <p>角色权限:<#--<input type="checkbox" name="chkAllResourceType" id="chkAllResourceType"  />全选<p>-->
                <dd>
                    <#list permission as dict>
                        <#if rolepermission??>

                                    <input type="checkbox" name="rolepermission"  id="resourceType"  value="${dict.id }"
                            <#list rolepermission as rp> <#if  rp =dict.id>  checked </#if></#list>>
                         ${dict.name}
                        <#else>
                            <input type="checkbox" name="rolepermission"  id="resourceType"  value="${dict.id }" > ${dict.name}
                        </#if>
                        <#--<#if (dict_index+1)%6==0>
                            <br>
                        </#if>-->
                    </#list>
                </dd>

            <p>是否可用:
                <input type="radio" name="available" id="available" value="0"   <#if (!role.available)>checked</#if>/><label>否</label>&nbsp;
                <input type="radio" name="available" id="available" value="1"   <#if (role.available)>checked</#if>/><label>是</label>
            </p>
            <button type="button" class="btn btn-primary" onclick="save()" id="btnSubmit"><i class="fa fa-check"></i> 保 存</button>&nbsp;
            <button type="button" class="btn btn-primary" id="btnCancel" onclick="window.history.back(-1);"><i class="fa fa-check"></i>关 闭</button>
        </form>
    </section>
<script type="text/javascript">
   /* $('#inputForm').validate({
        var text=$(this).contents().find("body").text();
        // 根据后台返回值处理结果
        var j=$.parseJSON(text);
        alert("111111111111");
        if(j.status!=0) {
            alert('导入成功');
            alert(j.message);
        } else {
            alert('导入成功');
            //location.href='BookResourceList.jsp'
        }
    });*/

    function save() {
        $.ajax({
            //几个参数需要注意一下
            type: "POST",//方法类型
            dataType: "json",//预期服务器返回的数据类型
            url: "/manage/sys/roleupdate" ,//url
            data: $('#inputForm').serialize(),
            success: function (result) {
                if (result.code == 0){

//                    window.location.href="http://shanghepinpai.com";
                    window.history.back(-1);
                    alert(result.message);
                }
            },
            error : function() {
                alert("异常！");
            }
        });
    }
</script>
<#--</@popupLayout.layout>-->
</@defaultLayout.layout>
