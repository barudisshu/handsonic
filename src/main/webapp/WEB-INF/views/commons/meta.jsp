<%@ include file="./taglibs.jsp" %>
<meta charset="UTF-8"/>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="description" content="SunLeanring">
<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
<link rel="icon" href="${ctx}/static/favicon.ico" type="image/x-icon"/>
<link rel="shortcut icon" href="${ctx}/static/favicon.ico" type="image/x-icon"/>
<link rel="stylesheet" href="${ctx}/static/libs/bootstrap/css/bootstrap.css"/>
<link rel="stylesheet" href="${ctx}/static/libs/font-awesome/css/font-awesome.css"/>
<link rel="stylesheet" href="${ctx}/static/libs/admin-lte/css/AdminLTE.css">
<link rel="stylesheet" href="${ctx}/static/libs/admin-lte/css/skins/_all-skins.css">
<link rel="stylesheet" href="${ctx}/static/css/style.css">
<!--[if lt IE 9]>
<script type="text/javascript" src="${ctx}/static/libs/html5shiv/html5shiv.min.js"></script>
<script type="text/javascript" src="${ctx}/static/libs/respond/respond.min.js"></script>
<![endif]-->
<script type="text/javascript" src="${ctx}/static/libs/jquery/jquery.js"></script>
<script type="text/javascript" src="${ctx}/static/libs/jquery/jquery-migrate.js"></script>
<script type="text/javascript" src="${ctx}/static/libs/jsrender/jsrender.js"></script>
<script type="text/javascript" src="${ctx}/static/libs/bootstrap/js/bootstrap.js"></script>
<script type="text/javascript">
    // 调整AdminLTE的默认属性
    var AdminLTEOptions = {
        sidebarSlimScroll : true
    };
    // 全局上下文
    var ctx = '${ctx}';
</script>
<script type="text/javascript" src="${ctx}/static/libs/admin-lte/js/app.min.js"></script>
<script type="text/javascript" src="${ctx}/static/js/app.js"></script>
<script type="text/javascript" src="${ctx}/static/js/utils.js"></script>
<script type="text/javascript" src="${ctx}/static/js/tpls.js"></script>
<script type="text/javascript">
    $(function() {
        App.setup(); // 初始化
        App.changeSkin('skin-blue'); // 设置颜色主题
    });
</script>
