package com.galudisu.handsonic.config;

import com.galudisu.handsonic.core.security.SecurityAuthenticationToken;
import com.galudisu.handsonic.core.security.SecurityConstants;
import com.galudisu.handsonic.core.security.SecurityRealm;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.realm.Realm;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebSecurityConfig {
    @Bean(name = "realm")
    public Realm realm() {
        SecurityRealm realm = new SecurityRealm();
        realm.setCredentialsMatcher(credentialsMatcher());
        realm.setAuthenticationTokenClass(SecurityAuthenticationToken.class);
        return realm;
    }

    @Bean
    public CredentialsMatcher credentialsMatcher() {
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher(SecurityConstants.HASH_ALGORITHM);
        credentialsMatcher.setHashIterations(SecurityConstants.HASH_INTERATIONS);
        credentialsMatcher.setStoredCredentialsHexEncoded(true);
        return credentialsMatcher;
    }
}
