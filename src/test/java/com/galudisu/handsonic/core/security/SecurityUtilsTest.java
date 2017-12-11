package com.galudisu.handsonic.core.security;

import com.galudisu.handsonic.test.BaseTest;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.util.ByteSource;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class SecurityUtilsTest extends BaseTest {
    @Autowired
    CredentialsMatcher credentialsMatcher;

    @Test
    public void encryptPasswordTest() {
        String password = "123456";
        String salt = SecurityUtils.generateSalt();

        String entryptedPassword = SecurityUtils.entryptPassword(password, salt);

        System.out.println("Plain Password - " + password);
        System.out.println("Salt - " + salt);
        System.out.println("Encrypted Password - " + entryptedPassword);
    }

    /**
     * 密码加密测试
     */
    @Test
    public void encryptPasswordAuthTest() {
        String username = "admin";
        String password = "123456";
        String salt = "14ecc5e72a91a05d6d42d66c286b105b";
        String entryptedPassword1 = "8c7fa11694511f6265986dbd6a35cce19464aef032af638f124bb639fe29c116613f63640477867c97b7a7b7e81aa61fc6488df138d768e0064a96e023ce00cf";
        String entryptedPassword2 = SecurityUtils.entryptPassword(password, salt);

        System.out.println("Plain Password - " + password);
        System.out.println("Salt - " + salt);
        System.out.println("Encrypted Password 1 - " + entryptedPassword1);
        System.out.println("Encrypted Password 2 - " + entryptedPassword2);

        // 登录凭证
        SecurityAuthenticationToken token = SecurityUtils.createFormToken(username, password, null, null, false);

        // 构建用户信息
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(new SecurityUser(username, username), entryptedPassword2, token.getUsername());
        info.setCredentialsSalt(ByteSource.Util.bytes(salt));

        // 密码认证结果
        boolean result = credentialsMatcher.doCredentialsMatch(token, info);

        Assert.assertTrue(result);
    }
}
