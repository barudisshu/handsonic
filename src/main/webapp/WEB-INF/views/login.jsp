<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../views/commons/taglibs.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Login</title>
        <script type="text/javascript">
            function test() {
            }
        </script>
    </head>
    <body>
        <div class="container">
            <div class="row" style="margin-top: 200px;">
                <div class="col-lg-4 col-lg-offset-4">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Login
                        </div>
                        <div class="panel-body">
                            <c:if test="${not empty msg}">
                                <div class="alert alert-danger alert-dismissible">
                                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                                    <c:out value="${msg}"/>
                                </div>
                            </c:if>
                            <form id="loginForm" action="/auth/login" class="form-horizontal" method="post">
                                <div class="form-group">
                                    <div class="col-lg-12">
                                        <input value="admin" placeholder="Username" type="text" name="username"
                                               class="form-control col-lg-12"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-lg-12">
                                        <input value="123456" placeholder="Password" type="password" name="password"
                                               class="form-control col-lg-12"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-lg-6">
                                        <input placeholder="Validation Code" type="text" name="captcha"
                                               class="form-control col-lg-6"/>
                                    </div>
                                    <div class="col-lg-6">
                                        <table>
                                            <tr>
                                                <td style="height: 35px; vertical-align: middle;">
                                                    <img class="captcha"
                                                         style="vertical-align: middle; margin: 0; padding: 0;"
                                                         src="/captcha"/>
                                                </td>
                                            </tr>
                                        </table>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-lg-12">
                                        <div class="checkbox">
                                            <label>
                                                <input checked type="checkbox" name="rememberMe"> RememberMe
                                            </label>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-lg-12">
                                        <input value="Login" type="submit" class="btn btn-primary btn-block"/>
                                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-lg-6">
                                        <a href="${ctx}/forget">Forget Password</a>
                                    </div>
                                    <div class="col-lg-6">
                                        <a class="pull-right" href="${ctx}/register">Register</a>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>