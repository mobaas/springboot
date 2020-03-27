<#import "/manage/inc/defaultLayout.ftl" as defaultLayout >

<@defaultLayout.layout>

<!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        404 Error Page
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
        <li><a href="#">Examples</a></li>
        <li class="active">403 error</li>
      </ol>
    </section>

    <!-- Main content -->
    <section class="content">
      <div class="error-page">
        <h2 class="headline text-yellow"> 403</h2>

        <div class="error-content">
          <h3><i class="fa fa-warning text-yellow"></i> Oops! Access denied.</h3>

          <p>
            You have not permission to access this page.
            Meanwhile, you may <a href="/index">return to dashboard</a>.
          </p>
         
        </div>
        <!-- /.error-content -->
      </div>
      <!-- /.error-page -->
    </section>
    <!-- /.content -->
    
</@defaultLayout.layout> 