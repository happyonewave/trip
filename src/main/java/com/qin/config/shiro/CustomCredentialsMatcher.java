package com.qin.config.shiro;

import com.qin.common.util.salt.Digests;
import com.qin.common.util.salt.Encodes;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.util.ByteSource;

public class CustomCredentialsMatcher extends SimpleCredentialsMatcher {

    private static final int HASH_INTERATIONS = 1024;

    @Override
    public boolean doCredentialsMatch(AuthenticationToken authcToken,
                                      AuthenticationInfo info) {

        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        String accountCredentials = (String) getCredentials(info);
        ByteSource credentialsSalt = ((SimpleAuthenticationInfo) info).getCredentialsSalt();
        Object tokenCredentials = encrypt( new String(token.getPassword()), credentialsSalt);
        System.out.println("credentialsSalt:" + credentialsSalt.getBytes());
        System.out.println("tokenCredentials:" + tokenCredentials);
        System.out.println("accountCredentials:" + accountCredentials);
        return equals(tokenCredentials, accountCredentials);
    }

    // 将传进来密码加密方法
    private String encrypt(String password, ByteSource salt) {

        byte[] hashPassword = Digests.sha1(password.getBytes(), salt.getBytes(), HASH_INTERATIONS);

        System.out.println("password:" + password);
        System.out.println("aaa:" + Encodes.encodeHex(Digests.sha1(password.getBytes(), salt.getBytes(), HASH_INTERATIONS)));
        System.out.println("aaa:" + Encodes.encodeHex(Digests.sha1(password.getBytes(), salt.getBytes(), HASH_INTERATIONS)));
        System.out.println("aaa:" + Encodes.encodeHex(Digests.sha1(password.getBytes(), salt.getBytes(), HASH_INTERATIONS)));
        System.out.println("aaa:" + Encodes.encodeHex(Digests.sha1(password.getBytes(), salt.getBytes(), HASH_INTERATIONS)));
        System.out.println("aaa:" + Encodes.encodeHex(Digests.sha1(password.getBytes(), salt.getBytes(), HASH_INTERATIONS)));
        System.out.println("aaa:" + Encodes.encodeHex(Digests.sha1(password.getBytes(), salt.getBytes(), HASH_INTERATIONS)));
        return Encodes.encodeHex(hashPassword);


    }

}
