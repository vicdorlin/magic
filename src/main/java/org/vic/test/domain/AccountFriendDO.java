package org.vic.test.domain;


/**
 * 好友关系
 *
 * @author vicdor
 * @create 2018-05-21 17:13
 */
public class AccountFriendDO {
    private String accountOpenid;
    private String friendOpenid;

    public AccountFriendDO() {}

    public AccountFriendDO(String accountOpenid, String friendOpenid, boolean isAdd) {
        this.accountOpenid = accountOpenid;
        this.friendOpenid = friendOpenid;
    }

    public String getAccountOpenid() {
        return accountOpenid;
    }

    public void setAccountOpenid(String accountOpenid) {
        this.accountOpenid = accountOpenid;
    }

    public String getFriendOpenid() {
        return friendOpenid;
    }

    public void setFriendOpenid(String friendOpenid) {
        this.friendOpenid = friendOpenid;
    }
}
    